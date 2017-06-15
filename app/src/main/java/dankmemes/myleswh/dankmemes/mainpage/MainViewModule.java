package dankmemes.myleswh.dankmemes.mainpage;

import dagger.Module;
import dagger.Provides;
import dankmemes.myleswh.dankmemes.application.CustomScope;
import dankmemes.myleswh.dankmemes.database.DBHelper;
import dankmemes.myleswh.dankmemes.network.GalleryAPI;
import dankmemes.myleswh.dankmemes.utils.OnItemClickListener;
import retrofit2.Retrofit;

/**
 * Created by myleswh on 06/06/2017.
 */
@Module
public class MainViewModule {

    private MainActivityContract.View view;
    private OnItemClickListener onItemClickListener;

    public MainViewModule(MainActivityContract.View view, OnItemClickListener onItemClickListener) {
        this.view = view;
        this.onItemClickListener = onItemClickListener;
    }

    @Provides
    @CustomScope
    public MainActivityContract.View provideProfileView() {
        return view;
    }

    @Provides
    @CustomScope
    public OnItemClickListener provideOnItemClickListener() {
        return onItemClickListener;
    }

    @Provides
    @CustomScope
    public MainActivityContract.Presenter provideProfilePresenter(GalleryAPI galleryAPI, DBHelper dbHelper) {
        return new MainPresenter(view, galleryAPI, dbHelper);
    }

    @Provides
    @CustomScope
    public MainAdapter provideMainAdapter(OnItemClickListener onItemClickListener) {
        return new MainAdapter(onItemClickListener);
    }

}
