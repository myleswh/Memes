package dankmemes.myleswh.dankmemes.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class LocalDBHelper {

    /** *
     *
     * @param context
     * @param seenImageId
     * @return true if successful
     */
    public static void insertSeenImage(Context context, String seenImageId) {
        Log.i(MySQLiteHelper.TAG, "INSERTING imageID  " + seenImageId);

        MySQLiteHelper mySQLiteHelper = new MySQLiteHelper(context);
        SQLiteDatabase database = mySQLiteHelper.getWritableDatabase();

        try {
            ContentValues values = new ContentValues();
            values.put(MySQLiteHelper.COLUMN_IMAGE_ID, seenImageId);

            database.insertOrThrow(MySQLiteHelper.SEEN_IMAGE_TABLE, null, values);

        } catch (SQLException e) {
            Log.i(MySQLiteHelper.TAG, "Error INSERTING " + seenImageId);
            e.printStackTrace();
        }

        mySQLiteHelper.close();
    }

    public static boolean hasSeenImage(Context context, String seenImageId) {
        Log.i(MySQLiteHelper.TAG, "LocalDBHelper(" + seenImageId + ");");

        MySQLiteHelper mySQLiteHelper = new MySQLiteHelper(context);
        SQLiteDatabase database = mySQLiteHelper.getWritableDatabase();


        Cursor result =  database.rawQuery("Select Count(*) FROM " + MySQLiteHelper.SEEN_IMAGE_TABLE + " WHERE " + MySQLiteHelper.COLUMN_IMAGE_ID + " = ?", new String[]{seenImageId});
        result.moveToFirst();
        int count = result.getInt(0);

        mySQLiteHelper.close();

        if (count > 0) {
            return true;
        } else {
            return false;
        }
    }

    public static void clearDB(Context context) {
        MySQLiteHelper mySQLiteHelper = new MySQLiteHelper(context);
        SQLiteDatabase database = mySQLiteHelper.getWritableDatabase();

        database.delete(MySQLiteHelper.SEEN_IMAGE_TABLE, null, null);

        mySQLiteHelper.close();
    }

}
