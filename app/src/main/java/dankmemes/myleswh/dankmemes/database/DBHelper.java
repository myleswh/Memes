package dankmemes.myleswh.dankmemes.database;

import android.content.Context;

import io.reactivex.Completable;

/**
 * Created by myleswh on 15/06/2017.
 */

public class DBHelper {
    private final Context context;

    public DBHelper(Context context) {
        this.context = context;
    }

    public void insertSeenImage(String url) {
        LocalDBHelper.hasSeenImage(context, url);
    }

    public boolean hasSeenImage(String url) {
        return LocalDBHelper.hasSeenImage(context, url);
    }

    public void clearDB() {
        LocalDBHelper.clearDB(context);
    }

    public Completable getInsertObservable(String url) {
        return LocalDBHelperRx.getInsertObservable(context, url);
    }

    public Completable getClearObserable() {
        return LocalDBHelperRx.getClearObservable(context);
    }

}
