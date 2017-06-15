package dankmemes.myleswh.dankmemes.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;


/**
 * Class for interacting with local DB
 */
public class MySQLiteHelper extends android.database.sqlite.SQLiteOpenHelper {
    public static final String TAG = "LOCAL_DB";

    private static final String DB_NAME = "DankMemes.db";
    private static final int DB_VERSION = 1;

    public static final String SEEN_IMAGE_TABLE = "SeenImages";

    public static final String COLUMN_IMAGE_ID= "id";

    //QUERIES
    public static final String QUERY_CREATE_TABLE = "CREATE TABLE " + SEEN_IMAGE_TABLE + " (" +
            COLUMN_IMAGE_ID + " 'TEXT'," +
            "PRIMARY KEY (" + COLUMN_IMAGE_ID + ") );";


    public MySQLiteHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(QUERY_CREATE_TABLE);
        ContentValues values = new ContentValues();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE " + SEEN_IMAGE_TABLE + ";");
        onCreate(db);
    }
}

