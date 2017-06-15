package dankmemes.myleswh.dankmemes.database;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by myleswh on 15/06/2017.
 */
@Module
public class DBModule {

    @Singleton
    @Provides
    public DBHelper provideDBHelper(Context context) {
        return new DBHelper(context);
    }
}
