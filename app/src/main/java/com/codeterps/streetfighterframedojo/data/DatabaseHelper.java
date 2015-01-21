package com.codeterps.streetfighterframedojo.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.codeterps.streetfighterframedojo.R;
import com.codeterps.streetfighterframedojo.model.Attribute;
import com.codeterps.streetfighterframedojo.model.Character;
import com.codeterps.streetfighterframedojo.model.CharacterNoteVote;
import com.codeterps.streetfighterframedojo.model.FrameData;
import com.codeterps.streetfighterframedojo.model.Game;
import com.codeterps.streetfighterframedojo.model.Matchup;
import com.codeterps.streetfighterframedojo.model.MatchupNoteVote;
import com.codeterps.streetfighterframedojo.model.MatchupOutcomeVote;
import com.codeterps.streetfighterframedojo.model.Move;
import com.codeterps.streetfighterframedojo.model.PersonalCharacterNote;
import com.codeterps.streetfighterframedojo.model.PersonalMatchupNote;
import com.codeterps.streetfighterframedojo.model.PublicCharacterNote;
import com.codeterps.streetfighterframedojo.model.PublicMatchupNote;
import com.codeterps.streetfighterframedojo.model.User;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String TAG = "DATABASE";
    private static final int DATABASE_VERSION = 1;
    private static final String ASSET_DB_PATH = "databases";
    private static final String DATABASE_NAME = "SFFD-Master";

    private Context mContext;
    private String mAssetPath;
    private String mDatabasePath;
    private boolean mInitializing;
    private SQLiteDatabase mDatabase;

    private Dao<Attribute, Integer> mAttributeDao;
    private Dao<Character, Integer> mCharacterDao;
    private Dao<CharacterNoteVote, Integer> mCharacterNoteVoteDao;
    private Dao<FrameData, Integer> mFrameDataDao;
    private Dao<Game, Integer> mGameDao;
    private Dao<Matchup, Integer> mMatchupDao;
    private Dao<MatchupNoteVote, Integer> mMatchupNoteVoteDao;
    private Dao<MatchupOutcomeVote, Integer> mMatchupOutcomeVoteDao;
    private Dao<Move, Integer> mMoveDao;
    private Dao<PersonalCharacterNote, Integer> mPersonalCharacterNoteDao;
    private Dao<PersonalMatchupNote, Integer> mPersonalMatchupNoteDao;
    private Dao<PublicCharacterNote, Integer> mPublicCharacterNoteDao;
    private Dao<PublicMatchupNote, Integer> mPublicMatchupNoteDao;
    private Dao<User, Integer> mUserDao;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION, R.raw.ormlite_config);
        mContext = context;
        mAssetPath = ASSET_DB_PATH + "/" + DATABASE_NAME;
        mDatabasePath = context.getApplicationInfo().dataDir + "/databases";
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Attribute.class);
            TableUtils.createTable(connectionSource, Character.class);
            TableUtils.createTable(connectionSource, CharacterNoteVote.class);
            TableUtils.createTable(connectionSource, FrameData.class);
            TableUtils.createTable(connectionSource, Game.class);
            TableUtils.createTable(connectionSource, Matchup.class);
            TableUtils.createTable(connectionSource, MatchupNoteVote.class);
            TableUtils.createTable(connectionSource, MatchupOutcomeVote.class);
            TableUtils.createTable(connectionSource, Move.class);
            TableUtils.createTable(connectionSource, PersonalCharacterNote.class);
            TableUtils.createTable(connectionSource, PersonalMatchupNote.class);
            TableUtils.createTable(connectionSource, PublicCharacterNote.class);
            TableUtils.createTable(connectionSource, PublicMatchupNote.class);
            TableUtils.createTable(connectionSource, User.class);

            Log.d("DATABASE", "DATABASE CREATED SUCCESFULLY.");
        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "Unable to create datbases", e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, Attribute.class, true);
            TableUtils.dropTable(connectionSource, Character.class, true);
            TableUtils.dropTable(connectionSource, CharacterNoteVote.class, true);
            TableUtils.dropTable(connectionSource, FrameData.class, true);
            TableUtils.dropTable(connectionSource, Game.class, true);
            TableUtils.dropTable(connectionSource, Matchup.class, true);
            TableUtils.dropTable(connectionSource, MatchupNoteVote.class, true);
            TableUtils.dropTable(connectionSource, MatchupOutcomeVote.class, true);
            TableUtils.dropTable(connectionSource, Move.class, true);
            TableUtils.dropTable(connectionSource, PersonalCharacterNote.class, true);
            TableUtils.dropTable(connectionSource, PersonalMatchupNote.class, true);
            TableUtils.dropTable(connectionSource, PublicCharacterNote.class, true);
            TableUtils.dropTable(connectionSource, PublicMatchupNote.class, true);
            TableUtils.dropTable(connectionSource, User.class, true);
            onCreate(database, connectionSource);
        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "Unable to upgrade database from version " + oldVersion + " to new "
                    + newVersion, e);
        }
    }

    // TO CREATE NEW DATABASE, COMMENT FROM HERE TO ...
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
        File file = new File(mDatabasePath + "/" + DATABASE_NAME);
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

    //.. HERE.

    public Dao<Attribute, Integer> getAttributeDao() throws SQLException {
        if (mAttributeDao == null) {
            mAttributeDao = getDao(Attribute.class);
        }
        return mAttributeDao;
    }

    public Dao<Character, Integer> getCharacterDao() throws SQLException {
        if (mCharacterDao == null) {
            mCharacterDao = getDao(Character.class);
        }
        return mCharacterDao;
    }

    public Dao<CharacterNoteVote, Integer> getCharacterNoteVoteDao() throws SQLException {
        if (mCharacterNoteVoteDao == null) {
            mCharacterNoteVoteDao = getDao(CharacterNoteVote.class);
        }
        return mCharacterNoteVoteDao;
    }

    public Dao<FrameData, Integer> getFrameDataDao() throws SQLException {
        if (mFrameDataDao == null) {
            mFrameDataDao = getDao(FrameData.class);
        }
        return mFrameDataDao;
    }

    public Dao<Game, Integer> getGameDao() throws SQLException {
        if (mGameDao == null) {
            mGameDao = getDao(Game.class);
        }
        return mGameDao;
    }

    public Dao<Matchup, Integer> getMatchupDao() throws SQLException {
        if (mMatchupDao == null) {
            mMatchupDao = getDao(Matchup.class);
        }
        return mMatchupDao;
    }

    public Dao<MatchupNoteVote, Integer> getMatchupNoteVoteDao() throws SQLException {
        if (mMatchupNoteVoteDao == null) {
            mMatchupNoteVoteDao = getDao(MatchupNoteVote.class);
        }
        return mMatchupNoteVoteDao;
    }

    public Dao<MatchupOutcomeVote, Integer> getMatchupOutcomeVoteDao() throws SQLException {
        if (mMatchupOutcomeVoteDao == null) {
            mMatchupOutcomeVoteDao = getDao(MatchupOutcomeVote.class);
        }
        return mMatchupOutcomeVoteDao;
    }

    public Dao<Move, Integer> getMoveDao() throws SQLException {
        if (mMoveDao == null) {
            mMoveDao = getDao(Move.class);
        }
        return mMoveDao;
    }

    public Dao<PersonalCharacterNote, Integer> getPersonalCharacterNoteDao() throws SQLException {
        if (mMatchupOutcomeVoteDao == null) {
            mMatchupOutcomeVoteDao = getDao(MatchupOutcomeVote.class);
        }
        return mPersonalCharacterNoteDao;
    }

    public Dao<PersonalMatchupNote, Integer> getPersonalMatchupNoteDao() throws SQLException {
        if (mPersonalMatchupNoteDao == null) {
            mPersonalMatchupNoteDao = getDao(PersonalMatchupNote.class);
        }
        return mPersonalMatchupNoteDao;
    }

    public Dao<PublicCharacterNote, Integer> getPublicCharacterNoteDao() throws SQLException {
        if (mPublicCharacterNoteDao == null) {
            mPublicCharacterNoteDao = getDao(PublicCharacterNote.class);
        }
        return mPublicCharacterNoteDao;
    }

    public Dao<PublicMatchupNote, Integer> getPublicMatchupNoteDao() throws SQLException {
        if (mPublicMatchupNoteDao == null) {
            mPublicMatchupNoteDao = getDao(PublicMatchupNote.class);
        }
        return mPublicMatchupNoteDao;
    }

    public Dao<User, Integer> getUserDao() throws SQLException {
        if (mUserDao == null) {
            mUserDao = getDao(User.class);
        }
        return mUserDao;
    }
}