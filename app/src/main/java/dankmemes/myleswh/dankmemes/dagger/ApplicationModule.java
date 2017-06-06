package dankmemes.myleswh.dankmemes.dagger;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dankmemes.myleswh.dankmemes.DankApplication;

/**
 * Created by myleswh on 06/06/2017.
 */
@Module
public class ApplicationModule {

    DankApplication dankApplication;

    public ApplicationModule(DankApplication dankApplication) {
        this.dankApplication = dankApplication;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return dankApplication;
    }
}
