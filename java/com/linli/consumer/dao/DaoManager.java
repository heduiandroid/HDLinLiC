
package com.linli.consumer.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;


/**
 * Created by tomoyo on 2016/12/12.
 */


public class DaoManager {

    private static final String TAG = DaoMaster.class.getSimpleName();
    private static final String DB_NAME = "goods.db";
    private static DaoMaster.DevOpenHelper mHelper;
    private static DaoMaster daoMaster;
    private static DaoSession daoSession;
    private static SQLiteDatabase db;
    private Context context;
    private static DaoManager daoManager ;



    /**
     * 使用单例模式获得操作数据库的对象
     * @return
     */

    public static DaoManager getInstance(){
        if(daoManager == null){
            daoManager = new DaoManager();
        }
        return daoManager;
    }

    public void init(Context context){
        this.context = context;
    }



    /**
     * 判断数据库是否存在，如果不存在则创建
     * @return
     */

    public DaoMaster getDaoMaster(){
        if(daoMaster ==null){
            mHelper = new DaoMaster.DevOpenHelper(context,DB_NAME,null);
            daoMaster = new DaoMaster(mHelper.getWritableDatabase());
        }
        return daoMaster;
    }


    /**
     * 完成对数据库的增删查找
     * @return
     */

    public DaoSession getDaoSession(){
        if (daoSession == null) {
            if(daoMaster == null){
                daoMaster = getDaoMaster();
            }
            daoSession = daoMaster.newSession();
        }
        return daoSession;
    }


    /**
     * 关闭数据库
     */

    public void closeDB(){
        if(null != null){
            daoSession.clear();
            daoSession = null;
        }
        if(mHelper != null){
            mHelper.close();
            mHelper = null;
        }
    }

}

