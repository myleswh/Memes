package dankmemes.myleswh.dankmemes.mainpage;

import dagger.Module;
import dagger.Provides;
import dankmemes.myleswh.dankmemes.application.CustomScope;
import dankmemes.myleswh.dankmemes.network.GalleryAPI;
import retrofit2.Retrofit;

/**
 * Created by myleswh on 06/06/2017.
 */
@Module
public class MainViewModule {

    private MainActivityContract.View view;

    public MainViewModule( MainActivityContract.View view) {
        this.view = view;
    }

    @Provides
    @CustomScope
    public MainActivityContract.View provideProfileView() {
        return view;
    }

    @Provides
    @CustomScope
    public MainActivityContract.Presenter provideProfilePresenter(GalleryAPI galleryAPI) {
        return new MainPresenter(view, galleryAPI);
    }

    @Provides
    @CustomScope
    public MainAdapter provideMainAdapter() {
        return new MainAdapter();
    }

}
