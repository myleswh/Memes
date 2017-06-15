package dankmemes.myleswh.dankmemes.application;

import android.app.Application;


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
        //DaggerApplicationComponent
        //applicationComponent = DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this)).build();

    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
