package dankmemes.myleswh.dankmemes.mainpage;

import java.util.List;

import javax.inject.Inject;

import dankmemes.myleswh.dankmemes.network.GalleryAPI;
import dankmemes.myleswh.dankmemes.network.GalleryModel;
import io.reactivex.Observable;
import io.reactivex.SingleObserver;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by myleswh on 06/06/2017.
 */
public class MainPresenter implements MainActivityContract.Presenter {

    private MainActivityContract.View view;
    private GalleryAPI galleryAPI;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Inject
    public MainPresenter(MainActivityContract.View view, GalleryAPI galleryAPI) {
        this.view = view;
        this.galleryAPI = galleryAPI;
    }

    @Override
    public void loadImageUrls() {
        galleryAPI.loadGallery()
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
                .toList()
                .subscribe(new SingleObserver<List<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(List<String> objects) {
                        view.setImageUrls(objects);
                    }

                    @Override
                    public void onError(Throwable e) {
                        // TODO
                        e.printStackTrace();
                    }
                });

    }
}
