package dankmemes.myleswh.dankmemes.database;

import android.content.Context;

import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableOnSubscribe;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by myleswh on 13/06/2017.
 */

public class LocalDBHelperRx {

    public static Completable getInsertObservable(final Context context, final String seenUrl) {
        return Completable.create(new CompletableOnSubscribe() {
            @Override
            public void subscribe(CompletableEmitter e) throws Exception {
                // TODO throw if insertion fails
                LocalDBHelper.insertSeenImage(context, seenUrl);
                e.onComplete();
            }
        }).subscribeOn(Schedulers.io());
    }

    public static Single<Boolean> getHasSeenImageObservable(final Context context, final String id) {
        return Single.create(new SingleOnSubscribe<Boolean>() {
            @Override
            public void subscribe(SingleEmitter<Boolean> e) throws Exception {
                e.onSuccess(LocalDBHelper.hasSeenImage(context, id));
            }
        }).subscribeOn(Schedulers.io());
    }

    public static Completable getClearObservable(final Context context) {
        return Completable.create(new CompletableOnSubscribe() {
            @Override
            public void subscribe(CompletableEmitter e) throws Exception {
                LocalDBHelper.clearDB(context);
                e.onComplete();
            }
        });
    }



}
