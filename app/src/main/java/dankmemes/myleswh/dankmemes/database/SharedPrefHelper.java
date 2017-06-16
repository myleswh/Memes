package dankmemes.myleswh.dankmemes.database;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by myleswh on 16/06/2017.
 */

public class SharedPrefHelper {

    private final SharedPreferences sharedPreferences;

    public SharedPrefHelper(Context context) {
        sharedPreferences = context.getSharedPreferences("", Context.MODE_PRIVATE);
    }

    public boolean getBoolean(String key, boolean defaultValue) {
       return sharedPreferences.getBoolean(key, defaultValue);
    }

    public void setBoolean(String key, boolean value) {
        sharedPreferences.edit().putBoolean(key, value).commit();
    }

}
