package dankmemes.myleswh.dankmemes.application;

import android.app.Application;

import dankmemes.myleswh.dankmemes.database.DBModule;
import dankmemes.myleswh.dankmemes.network.NetworkModule;


/**
 * Created by myleswh on 06/06/2017.
 */

public class DankApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initDagger();
    }

    private void initDagger() {
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .dBModule(new DBModule())
                .networkModule(new NetworkModule())
                .build();

    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
