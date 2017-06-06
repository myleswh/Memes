package dankmemes.myleswh.dankmemes.network;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by myleswh on 06/06/2017.
 */
@Module
public class NetworkModule {
    @Singleton
    @Provides
    public Retrofit provideRetrofit() {
        return RetrofitUtils.getRetrofit();
    }
}
