package dankmemes.myleswh.dankmemes.application;

import android.app.Application;

import dankmemes.myleswh.dankmemes.network.DaggerNetworkComponent;
import dankmemes.myleswh.dankmemes.network.NetworkComponent;
import dankmemes.myleswh.dankmemes.network.NetworkModule;


/**
 * Created by myleswh on 06/06/2017.
 */

public class DankApplication extends Application {

    private ApplicationComponent applicationComponent;
    private NetworkComponent networkComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initDagger();
    }

    private void initDagger() {
        //DaggerApplicationComponent
        applicationComponent = DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this)).build();
        networkComponent = DaggerNetworkComponent.builder().networkModule(new NetworkModule()).build();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

    public NetworkComponent getNetworkComponent() {
        return networkComponent;
    }
}
