package com.linkstreet.vinoth.myapplication;

import android.app.Application;

import com.linkstreet.vinoth.myapplication.db.DaoMaster;
import com.linkstreet.vinoth.myapplication.db.DaoSession;

import org.greenrobot.greendao.database.Database;

/**
 * Created by vinoth on 19/4/17.
 */

public class AppController extends Application {
    public static final boolean ENCRYPTED = true;
    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this,"users-db");
        Database db = helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }

}
