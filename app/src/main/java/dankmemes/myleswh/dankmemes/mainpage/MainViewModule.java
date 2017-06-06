package dankmemes.myleswh.dankmemes.mainpage;

import dagger.Module;
import dagger.Provides;
import dankmemes.myleswh.dankmemes.application.CustomScope;

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
    public MainActivityContract.Presenter provideProfilePresenter() {
        return new MainPresenter(view);
    }

}
