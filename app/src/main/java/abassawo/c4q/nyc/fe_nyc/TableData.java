package abassawo.c4q.nyc.fe_nyc;

import android.provider.BaseColumns;

/**
 * Created by c4q-vanice on 8/1/15.
 */
public class TableData {

    public TableData(){ }

    public static abstract class TableInfo implements BaseColumns {

        public static final String userName = "Username";
        public static final String clientName = "ClientName";
        public static final String userPassword = "UserPassword";
        public static final String databaseName = "UserInfo";
        public static final String tableUserInfo = "Registration";

    }
}
