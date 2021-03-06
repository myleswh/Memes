package dankmemes.myleswh.dankmemes.application;

import android.app.Application;

import com.google.firebase.analytics.FirebaseAnalytics;

import dankmemes.myleswh.dankmemes.database.PersistenceModule;
import dankmemes.myleswh.dankmemes.network.NetworkModule;


/**
 * Created by myleswh on 06/06/2017.
 */

public class DankApplication extends Application {

    private ApplicationComponent applicationComponent;
    private FirebaseAnalytics firebaseAnalytics;

    @Override
    public void onCreate() {
        super.onCreate();
        initDagger();
        initAnalytics();
    }

    private void initAnalytics() {
         firebaseAnalytics = FirebaseAnalytics.getInstance(this);
    }

    private void initDagger() {
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .persistenceModule(new PersistenceModule())
                .networkModule(new NetworkModule())
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
