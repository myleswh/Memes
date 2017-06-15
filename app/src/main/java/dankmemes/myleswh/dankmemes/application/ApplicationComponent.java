package dankmemes.myleswh.dankmemes.application;

import javax.inject.Singleton;

import dagger.Component;
import dankmemes.myleswh.dankmemes.database.DBModule;
import dankmemes.myleswh.dankmemes.mainpage.MainViewComponent;
import dankmemes.myleswh.dankmemes.mainpage.MainViewModule;
import dankmemes.myleswh.dankmemes.network.NetworkModule;

/**
 * Created by myleswh on 06/06/2017.
 */
@Singleton
@Component(modules = {ApplicationModule.class, NetworkModule.class, DBModule.class})
public interface ApplicationComponent {
       MainViewComponent plus(MainViewModule mainViewModule);
}
