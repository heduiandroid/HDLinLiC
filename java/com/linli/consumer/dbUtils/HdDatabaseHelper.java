package com.linli.consumer.dbUtils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static com.linli.consumer.dao.DBUtil.GOODS_TABLE;

/**
 * Created by tomoyo on 2017/3/10.
 */

public class HdDatabaseHelper extends SQLiteOpenHelper {



    public static final String CREATE_GOODS = "CREATE TABLE " + GOODS_TABLE+ " (" + //
            "id INTEGER PRIMARY KEY autoincrement," + // 0: id
            "goodsId INTEGER NOT NULL ," + // 1: goodId
            "goodsName TEXT," + // 2: goodsName
            "goodsSpec INTEGER NOT NULL ," + // 3: goodsSpec
            "goodsSpecName TEXT," + // 4: goodsSpecName
            "inventory INTEGER NOT NULL ," + // 5: inventory
            "ispackagecost INTEGER NOT NULL ," + // 6: ispackagecost
            "number INTEGER NOT NULL ," + // 7: number
            "price REAL NOT NULL ," + // 8: price
            "imagePath TEXT," + // 9: imagePath
            "isChoice INTEGER NOT NULL ," + // 10: isChoice
            "shopId INTEGER NOT NULL ," + // 11: shopId
            "shopName TEXT," + // 12: shopName
            "type INTEGER NOT NULL );";      //13:type

    private Context mContext;


    public HdDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
        Log.i("test","version:"+version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_GOODS);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i("test","oldVersion:"+oldVersion+" "+"newVersion:"+newVersion);
    }
}
