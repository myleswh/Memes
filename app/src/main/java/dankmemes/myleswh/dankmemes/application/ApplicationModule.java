package dankmemes.myleswh.dankmemes.application;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

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
