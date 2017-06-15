package dankmemes.myleswh.dankmemes.mainpage;

import dagger.Subcomponent;
import dankmemes.myleswh.dankmemes.application.CustomScope;

/**
 * Created by myleswh on 06/06/2017.
 */
@CustomScope
@Subcomponent(modules = MainViewModule.class)
public interface MainViewComponent {
    void inject(MainActivity mainActivity);
}
