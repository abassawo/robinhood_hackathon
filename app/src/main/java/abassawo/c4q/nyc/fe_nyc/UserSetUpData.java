package abassawo.c4q.nyc.fe_nyc;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by c4q-vanice on 8/1/15.
 */
public class UserSetUpData extends SQLiteOpenHelper {

    public final String TAG = "Database Operations";
    public static final int databaseVersion = 1;
    public String query = "Create Table" + TableData.TableInfo.tableUserInfo +
            "(" + TableData.TableInfo.userName +
            " Text," + TableData.TableInfo.userPassword + " Text);";

    // For this practice I only need the context. This creates the database.
    public UserSetUpData(Context context) {
        super(context, TableData.TableInfo.databaseName, null, databaseVersion);
        Log.d(TAG, "Created Database");
    }

    // This creates the table.
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(query);
        Log.d(TAG, "Created Table");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    // This creates an object of SQLite.
    public void insertInfo(UserSetUpData dataOps, String clientname, String name, String password){
        SQLiteDatabase SQ = dataOps.getWritableDatabase();
        ContentValues CV = new ContentValues();
        CV.put(TableData.TableInfo.clientName, clientname);
        CV.put(TableData.TableInfo.userName, name);
        CV.put(TableData.TableInfo.userPassword, password);
        long K = SQ.insert(TableData.TableInfo.tableUserInfo, null, CV);
        Log.d(TAG, "One raw inserted");
    }

    public Cursor getInfo(UserSetUpData dop){
        SQLiteDatabase SQ = dop.getReadableDatabase();
        String [] columns = {TableData.TableInfo.userName, TableData.TableInfo.userPassword};
        Cursor CR = SQ.query(TableData.TableInfo.tableUserInfo, columns, null, null, null, null, null);
        return CR;
    }
}
