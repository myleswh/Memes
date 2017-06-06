package dankmemes.myleswh.dankmemes.dagger;

import javax.inject.Singleton;

import dagger.Component;
import dankmemes.myleswh.dankmemes.DankApplication;

/**
 * Created by myleswh on 06/06/2017.
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(DankApplication application);
}
