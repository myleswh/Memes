package dankmemes.myleswh.dankmemes.mainpage;

import java.util.List;

import javax.inject.Inject;

import dankmemes.myleswh.dankmemes.database.DBHelper;
import dankmemes.myleswh.dankmemes.database.SharedPrefHelper;
import dankmemes.myleswh.dankmemes.network.GalleryAPI;
import dankmemes.myleswh.dankmemes.network.GalleryModel;
import io.reactivex.Observable;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by myleswh on 06/06/2017.
 */
public class MainPresenter implements MainActivityContract.Presenter {

    private static final String SHOW_VIEWED = "showViewed";

    private MainActivityContract.View view;
    private GalleryAPI galleryAPI;
    private DBHelper dbHelper;
    private int currentPage = 0;
    private Disposable networkDisposable;
    private SharedPrefHelper sharedPrefHelper;
    private boolean showViewed;

    @Inject
    public MainPresenter(MainActivityContract.View view, GalleryAPI galleryAPI, DBHelper dbHelper, SharedPrefHelper sharedPrefHelper) {
        this.view = view;
        this.galleryAPI = galleryAPI;
        this.dbHelper = dbHelper;
        this.sharedPrefHelper = sharedPrefHelper;
        this.showViewed = sharedPrefHelper.getBoolean(SHOW_VIEWED, false);
    }

    @Override
    public void loadImageUrls() {
        currentPage = 0;
        loadImages(currentPage, true);
    }

    private void loadImages(final int page, final boolean clear) {
        // If already running Ignore TODO find more elegant way to do this?
        if (networkDisposable!=null && !networkDisposable.isDisposed()) return;

        galleryAPI.loadGallery(page)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .map(new Function<GalleryModel, List<GalleryModel.Datum>>() {
                    @Override
                    public List<GalleryModel.Datum> apply(@NonNull GalleryModel galleryModel) throws Exception {
                        return galleryModel.data;
                    }
                })
                .toObservable()
                .flatMap(new Function<List<GalleryModel.Datum>, Observable<GalleryModel.Datum>>() {
                    @Override
                    public Observable<GalleryModel.Datum> apply(@NonNull List<GalleryModel.Datum> data) throws Exception {
                        return Observable.fromIterable(data);
                    }
                })
                .filter(new Predicate<GalleryModel.Datum>() {
                    @Override
                    public boolean test(@NonNull GalleryModel.Datum datum) throws Exception {
                        return !datum.nsfw && (datum.animated == null || !datum.animated) && !datum.isAlbum;
                    }
                })
                .map(new Function<GalleryModel.Datum, String>() {
                    @Override
                    public String apply(@NonNull GalleryModel.Datum datum) throws Exception {
                        return datum.link;
                    }
                })
                .filter(new Predicate<String>() {
                    @Override
                    public boolean test(@NonNull String url) throws Exception {
                        // Filter seen images
                        return showViewed || !dbHelper.hasSeenImage(url);
                    }
                })
                .toList()
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate(new Action() {
                    @Override
                    public void run() throws Exception {
                        networkDisposable.dispose();
                        view.setLoading(false);
                    }
                })
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(@NonNull Disposable disposable) throws Exception {
                        view.setLoading(true);
                    }
                })
                .subscribe(new SingleObserver<List<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        networkDisposable = d;
                    }

                    @Override
                    public void onSuccess(List<String> objects) {
                        if (clear) view.clearImageUrls();
                        view.addImages(objects);
                        currentPage = page + 1;
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }
                });
    }

    @Override
    public void loadMoreImages() {
        loadImages(currentPage, false);
    }

    @Override
    public void dispose() {
        if (networkDisposable!= null && !networkDisposable.isDisposed()) {
            networkDisposable.dispose();
        }
    }

    @Override
    public void markViewed(String url) {
        dbHelper.getInsertObservable(url).subscribe();
    }

    @Override
    public void clearViewed() {
        dbHelper.getClearObserable().subscribe();
    }

    @Override
    public void toggleShowViewed() {
        showViewed = !showViewed;
        sharedPrefHelper.setBoolean(SHOW_VIEWED, showViewed);
    }

}
