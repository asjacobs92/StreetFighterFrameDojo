package com.codeterps.streetfighterframedojo.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.codeterps.streetfighterframedojo.R;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String TAG = "DATABASE";
    private static final int DATABASE_VERSION = 1;
    private static final String ASSET_DB_PATH = "databases";
    private static final String DATABASE_NAME = "Leaflet-Master";

    private Context mContext;
    private String mAssetPath;
    private String mDatabasePath;
    private boolean mInitializing;
    private SQLiteDatabase mDatabase;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION, R.raw.ormlite_config);
        mContext = context;
        mAssetPath = ASSET_DB_PATH + "/" + DATABASE_NAME;
        mDatabasePath = context.getApplicationInfo().dataDir + "/databases";
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        /*try {
            TableUtils.createTable(connectionSource, Species.class);
            TableUtils.createTable(connectionSource, LeafletUrl.class);
            TableUtils.createTable(connectionSource, DatabaseInfo.class);
            TableUtils.createTable(connectionSource, RankedSpecies.class);
            TableUtils.createTable(connectionSource, CollectedLeaf.class);
            Log.d("DATABASE", "DATABASE CREATED SUCCESFULLY.");
        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "Unable to create datbases", e);
        }*/
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        /*try {
            TableUtils.dropTable(connectionSource, Species.class, true);
            TableUtils.dropTable(connectionSource, LeafletUrl.class, true);
            TableUtils.dropTable(connectionSource, DatabaseInfo.class, true);
            TableUtils.dropTable(connectionSource, RankedSpecies.class, true);
            TableUtils.dropTable(connectionSource, CollectedLeaf.class, true);
            onCreate(database, connectionSource);
        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "Unable to upgrade database from version " + oldVersion + " to new "
                    + newVersion, e);
        }*/
    }


    @Override
    public synchronized SQLiteDatabase getWritableDatabase() {
        if (mDatabase != null && mDatabase.isOpen() && !mDatabase.isReadOnly()) {
            return mDatabase;  // The database is already open for business
        }

        if (mInitializing) {
            throw new IllegalStateException("getWritableDatabase called recursively");
        }

        // If we have a read-only database open, someone could be using it
        // (though they shouldn't), which would cause a lock to be held on
        // the file, and our attempts to open the database read-write would
        // fail waiting for the file lock.  To prevent that, we acquire the
        // lock on the read-only database, which shuts out other users.

        boolean success = false;
        SQLiteDatabase db = null;
        try {
            mInitializing = true;
            db = createOrOpenDatabase();

            onOpen(db);
            success = true;
            return db;
        } finally {
            mInitializing = false;
            if (success) {
                if (mDatabase != null) {
                    mDatabase.close();
                }
                mDatabase = db;
            } else {
                if (db != null) db.close();
            }
        }
    }

    @Override
    public synchronized SQLiteDatabase getReadableDatabase() {
        if (mDatabase != null && mDatabase.isOpen()) {
            return mDatabase;  // The database is already open for business
        }

        if (mInitializing) {
            throw new IllegalStateException("getReadableDatabase called recursively");
        }

        try {
            return getWritableDatabase();
        } catch (SQLiteException e) {
            Log.e(TAG, "Couldn't open " + DATABASE_NAME + " for writing (will try read-only):", e);
        }

        SQLiteDatabase db = null;
        try {
            mInitializing = true;
            String path = mContext.getDatabasePath(DATABASE_NAME).getPath();
            db = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READONLY);

            onOpen(db);
            Log.w(TAG, "Opened " + DATABASE_NAME + " in read-only mode");
            mDatabase = db;
            return mDatabase;
        } finally {
            mInitializing = false;
            if (db != null && db != mDatabase) db.close();
        }
    }

    /**
     * Close any open database object.
     */
    @Override
    public synchronized void close() {
        if (mInitializing) throw new IllegalStateException("Closed during initialization");

        if (mDatabase != null && mDatabase.isOpen()) {
            mDatabase.close();
            mDatabase = null;
        }
    }

    private SQLiteDatabase createOrOpenDatabase() {
        // test for the existence of the db file first and don't attempt open
        // to prevent the error trace in log on API 14+
        SQLiteDatabase db = null;
        File file = new File(mDatabasePath+ "/" + DATABASE_NAME);
        if (file.exists()) {
            db = returnDatabase();
        }

        if (db == null) {
            // database does not exist, copy it from assets and return it
            copyDatabaseFromAssets();
            //copyAssetsToExternalStorage(ASSET_SPECIES_PATH);
            db = returnDatabase();
        }

        return db;
    }

    private SQLiteDatabase returnDatabase() {
        try {
            SQLiteDatabase db = SQLiteDatabase.openDatabase(mDatabasePath + "/" + DATABASE_NAME, null, SQLiteDatabase.OPEN_READWRITE);
            Log.i(TAG, "successfully opened database " + DATABASE_NAME);
            return db;
        } catch (SQLiteException e) {
            Log.w(TAG, "could not open database " + DATABASE_NAME + " - " + e.getMessage());
            return null;
        }
    }

    private void copyDatabaseFromAssets() {
        Log.w(TAG, "Copying database from assets...");

        InputStream is;
        try {
            is = mContext.getAssets().open(mAssetPath);
            File f = new File(mDatabasePath + "/");
            if (!f.exists()) {
                f.mkdir();
            }

            writeFileToDisk(is, new FileOutputStream(mDatabasePath + "/" + DATABASE_NAME));

            Log.w(TAG, "database copy complete");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeFileToDisk(InputStream in, OutputStream outs) throws IOException {
        byte[] buffer = new byte[1024];
        int length;
        while ((length = in.read(buffer)) > 0) {
            outs.write(buffer, 0, length);
        }
        outs.flush();
        outs.close();
        in.close();
    }
}