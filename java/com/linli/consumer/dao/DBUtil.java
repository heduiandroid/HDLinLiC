package com.linli.consumer.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.linli.consumer.bean.FoodListBean;
import com.linli.consumer.bean.GoodDetailUploadBean;
import com.linli.consumer.bean.GoodsBean;
import com.linli.consumer.bean.GoodsDetailBean;
import com.linli.consumer.bean.GoodsDetailListBean;
import com.linli.consumer.bean.UpdateFoodBean;
import com.linli.consumer.bean.UpdateGoodsBean;
import com.linli.consumer.common.HandleSuccess;
import com.linli.consumer.dbUtils.HdDatabaseHelper;
import com.linli.consumer.net.FoodNet;
import com.linli.consumer.net.ShopNet;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

import static com.linli.consumer.widget.ShopSortLayout.FOOD_MAIN;
import static com.linli.consumer.widget.ShopSortLayout.SHOP_MAIN;

/**
 * Created by tomoyo on 2016/12/12.
 */


public class DBUtil  {

    public static final String  DB_NAME = "GOODS.DB";           //数据库 名称
    public static final int BD_VERSION = 1;                     //数据库版本
    public static final String GOODS_TABLE = "GoodsTable";      //数据库 表

    private static DBUtil dBUtil;

    private SQLiteDatabase db;

    private DBUtil(Context context){
        HdDatabaseHelper hdDatabaseHelper = new HdDatabaseHelper(context,DB_NAME,null,BD_VERSION);
        db = hdDatabaseHelper.getWritableDatabase();
    }

    public synchronized static DBUtil getInstance(Context context){
        if(dBUtil == null){
            dBUtil = new DBUtil(context);
        }
        return dBUtil;
    }




    public  List<GoodsBean> queryByGoodId(long goodId){
        List<GoodsBean > list = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from "+GOODS_TABLE+" where goodsId = " +goodId,null);
        if(cursor != null){
            if(cursor.moveToFirst()){
                do {
                    GoodsBean goodsBean = new GoodsBean();
                    goodsBean.setGoodId(cursor.getLong(cursor.getColumnIndex("goodsId")));
                    goodsBean.setGoodsName(cursor.getString(cursor.getColumnIndex("goodsName")));
                    goodsBean.setGoodsSpec(cursor.getLong(cursor.getColumnIndex("goodsSpec")));
                    goodsBean.setGoodsSpecName(cursor.getString(cursor.getColumnIndex("goodsSpecName")));
                    goodsBean.setInventory(cursor.getInt(cursor.getColumnIndex("inventory")));
                    goodsBean.setIspackagecost(cursor.getInt(cursor.getColumnIndex("ispackagecost")));
                    goodsBean.setNumber(cursor.getInt(cursor.getColumnIndex("number")));
                    goodsBean.setPrice(cursor.getFloat(cursor.getColumnIndex("price")));
                    goodsBean.setImagePath(cursor.getString(cursor.getColumnIndex("imagePath")));
                    goodsBean.setIsChoice(cursor.getLong(cursor.getColumnIndex("isChoice"))!=0);
                    goodsBean.setShopId(cursor.getLong(cursor.getColumnIndex("shopId")));
                    goodsBean.setShopName(cursor.getString(cursor.getColumnIndex("shopName")));
                    goodsBean.setType(cursor.getInt(cursor.getColumnIndex("type")));
                    list.add(goodsBean);
                }while (cursor.moveToNext());
            }
            cursor.close();
        }

        return list;
    }

    /**
     * 通过shopId查找数据
     * @param shopId 店铺id
     * @return List<GoodsBean>
     * */
    public List<GoodsBean> queryByShopId(long shopId){
        List<GoodsBean > list = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from "+GOODS_TABLE+" where shopId = " +shopId,null);
        if(cursor != null){
            if(cursor.moveToFirst()){
                do {
                    GoodsBean goodsBean = new GoodsBean();
                    goodsBean.setGoodId(cursor.getLong(cursor.getColumnIndex("goodsId")));
                    goodsBean.setGoodsName(cursor.getString(cursor.getColumnIndex("goodsName")));
                    goodsBean.setGoodsSpec(cursor.getLong(cursor.getColumnIndex("goodsSpec")));
                    goodsBean.setGoodsSpecName(cursor.getString(cursor.getColumnIndex("goodsSpecName")));
                    goodsBean.setInventory(cursor.getInt(cursor.getColumnIndex("inventory")));
                    goodsBean.setIspackagecost(cursor.getInt(cursor.getColumnIndex("ispackagecost")));
                    goodsBean.setNumber(cursor.getInt(cursor.getColumnIndex("number")));
                    goodsBean.setPrice(cursor.getFloat(cursor.getColumnIndex("price")));
                    goodsBean.setImagePath(cursor.getString(cursor.getColumnIndex("imagePath")));
                    goodsBean.setIsChoice(cursor.getLong(cursor.getColumnIndex("isChoice"))!=0);
                    goodsBean.setShopId(cursor.getLong(cursor.getColumnIndex("shopId")));
                    goodsBean.setShopName(cursor.getString(cursor.getColumnIndex("shopName")));
                    goodsBean.setType(cursor.getInt(cursor.getColumnIndex("type")));
                    list.add(goodsBean);
                }while (cursor.moveToNext());
            }
            cursor.close();
        }
        return list;
    }

    /**
     * 通过商品id查找数据
     * @param goodId 商品id
     * */
    public GoodsBean queryByGoodId(long goodId,long specId){
        Cursor cursor = db.rawQuery("select * from "+GOODS_TABLE +" where goodsId = ? and goodsSpec = ?"
                ,new String[]{goodId+"",specId+""});
        GoodsBean goodsBean = new GoodsBean();
        if(cursor != null){
            if(cursor.moveToFirst()){
                do {
                    long goodsId = cursor.getLong(cursor.getColumnIndex("goodsId"));
                    String goodsName = cursor.getString(cursor.getColumnIndex("goodsName"));
                    long goodsSpec = cursor.getLong(cursor.getColumnIndex("goodsSpec"));
                    String goodsSpecName = cursor.getString(cursor.getColumnIndex("goodsSpecName"));
                    int inventory = cursor.getInt(cursor.getColumnIndex("inventory"));
                    int ispackagecost = cursor.getInt(cursor.getColumnIndex("ispackagecost"));
                    int number = cursor.getInt(cursor.getColumnIndex("number"));
                    float price = cursor.getFloat(cursor.getColumnIndex("price"));
                    String imagePath = cursor.getString(cursor.getColumnIndex("imagePath"));
                    boolean isChoice = cursor.getLong(cursor.getColumnIndex("isChoice"))!=0;
                    long shopId = cursor.getLong(cursor.getColumnIndex("shopId"));
                    String shopName = cursor.getString(cursor.getColumnIndex("shopName"));
                    int type = cursor.getInt(cursor.getColumnIndex("type"));
                    goodsBean.setGoodId(goodsId);
                    goodsBean.setGoodsName(goodsName);
                    goodsBean.setGoodsSpec(goodsSpec);
                    goodsBean.setGoodsSpecName(goodsSpecName);
                    goodsBean.setInventory(inventory);
                    goodsBean.setIspackagecost(ispackagecost);
                    goodsBean.setNumber(number);
                    goodsBean.setPrice(price);
                    goodsBean.setImagePath(imagePath);
                    goodsBean.setIsChoice(isChoice);
                    goodsBean.setShopId(shopId);
                    goodsBean.setShopName(shopName);
                    goodsBean.setType(type);

                } while (cursor.moveToNext());
            } else {
                goodsBean = null;
            }
            cursor.close();
        }
        return goodsBean;
    }

    /**
     * 将所有数据列出
     * 返回多行记录
     *
     * @return List<GoodsBean> list
     */
    public List<GoodsBean> queryAll() {
        List<GoodsBean > list = new ArrayList<>();
        Cursor cursor = db.query(GOODS_TABLE,null,null,null,null,null,null);
        if(cursor != null){
            if(cursor.moveToFirst()){
                do {
                    GoodsBean goodsBean = new GoodsBean();
                    goodsBean.setGoodId(cursor.getLong(cursor.getColumnIndex("goodsId")));
                    goodsBean.setGoodsName(cursor.getString(cursor.getColumnIndex("goodsName")));
                    goodsBean.setGoodsSpec(cursor.getLong(cursor.getColumnIndex("goodsSpec")));
                    goodsBean.setGoodsSpecName(cursor.getString(cursor.getColumnIndex("goodsSpecName")));
                    goodsBean.setInventory(cursor.getInt(cursor.getColumnIndex("inventory")));
                    goodsBean.setIspackagecost(cursor.getInt(cursor.getColumnIndex("ispackagecost")));
                    goodsBean.setNumber(cursor.getInt(cursor.getColumnIndex("number")));
                    goodsBean.setPrice(cursor.getFloat(cursor.getColumnIndex("price")));
                    goodsBean.setImagePath(cursor.getString(cursor.getColumnIndex("imagePath")));
                    goodsBean.setIsChoice(cursor.getLong(cursor.getColumnIndex("isChoice"))!=0);
                    goodsBean.setShopId(cursor.getLong(cursor.getColumnIndex("shopId")));
                    goodsBean.setShopName(cursor.getString(cursor.getColumnIndex("shopName")));
                    goodsBean.setType(cursor.getInt(cursor.getColumnIndex("type")));
                    list.add(goodsBean);
                }while (cursor.moveToNext());
            }
            cursor.close();
        }
        return list;
    }

    /**
     * 通过商品id删除指定商品
     * @param goodId    商品id
     * @param specId    规格id
     * @return 返回boolean，判断是否执行成功
     * */
    public void deleteByGoodId(long goodId,long specId){

        db.delete(GOODS_TABLE,"goodsId = ? and goodsSpec = ?",new String[] {goodId+"",specId+""});

    }
    /**
     * 通过商品id删除指定商品
     * @param goodId    商品id
     * @return 返回boolean，判断是否执行成功
     * */
    public void deleteByGoodId(long goodId){

        db.delete(GOODS_TABLE,"goodsId = ?",new String[] {goodId+""});

    }
    /**
     * 通过商品id删除指定商品
     * @param shopId    商品id
     * @return 返回boolean，判断是否执行成功
     * */
    public void deleteByShopId(long shopId){

        db.delete(GOODS_TABLE,"shopId = ?",new String[] {shopId+""});

    }

    /**
     * 将商品数据插入数据库
     * @param goodsBean
     * @return boolean 判断是否插入成功
     */
    private void insertGoodById(GoodsBean goodsBean){
        ContentValues values = new ContentValues();
        values.put("goodsId",goodsBean.getGoodId());
        values.put("goodsName",goodsBean.getGoodsName());
        values.put("goodsSpec",goodsBean.getGoodsSpec());
        values.put("goodsSpecName",goodsBean.getGoodsSpecName());
        values.put("inventory",goodsBean.getInventory());
        values.put("ispackagecost",goodsBean.getIspackagecost());
        values.put("number",goodsBean.getNumber());
        values.put("price",goodsBean.getPrice());
        values.put("imagePath",goodsBean.getImagePath());
        values.put("isChoice",goodsBean.getIsChoice());
        values.put("shopId",goodsBean.getShopId());
        values.put("shopName",goodsBean.getShopName());
        values.put("type",goodsBean.getType());
        db.insert(GOODS_TABLE,null,values);
    }


    /**
     * @param goodsBean 更新的商品信息
     *
     * 单独给外部使用
     * */
    public void updateGoodInfo(GoodsBean goodsBean){
        ContentValues values = new ContentValues();
        values.put("goodsId",goodsBean.getGoodId());
        values.put("goodsName",goodsBean.getGoodsName());
        values.put("goodsSpec",goodsBean.getGoodsSpec());
        values.put("goodsSpecName",goodsBean.getGoodsSpecName());
        values.put("inventory",goodsBean.getInventory());
        values.put("ispackagecost",goodsBean.getIspackagecost());
        values.put("number",goodsBean.getNumber());
        values.put("price",goodsBean.getPrice());
        values.put("imagePath",goodsBean.getImagePath());
        values.put("isChoice",goodsBean.getIsChoice());
        values.put("shopId",goodsBean.getShopId());
        values.put("shopName",goodsBean.getShopName());
        values.put("type",goodsBean.getType());
        db.update(GOODS_TABLE,values,"goodsId = ? and goodsSpec = ?"
                ,new String[] {goodsBean.getGoodId()+"",goodsBean.getGoodsSpec()+""});
    }

    /**
     * 更新商品数量信息
     * @param goodsBean  要更新的商品信息
     * @param num 更新的商品数量 1增加   -1减少
     * @see {@linkplain DBUtil#queryByGoodId(long,long)},此两者要一起用，先查询，在更新
     * */
    private void updateNum(GoodsBean goodsBean,int num){
        goodsBean.setNumber(goodsBean.getNumber()+num);
        ContentValues values = new ContentValues();
        values.put("number",goodsBean.getNumber());
        db.update(GOODS_TABLE,values,"goodsId = ? and goodsSpec = ?"
                ,new String[] {goodsBean.getGoodId()+"",goodsBean.getGoodsSpec()+""});
    }

    /**
     * 添加商品数量
     * @param goodId
     * @param shopId
     * */
    @Deprecated
    private void addGoodNum(long goodId,String goodsName,double price,String imagePath,
                            long shopId,String shopName){
        GoodsBean goodsBean = queryByGoodId(goodId,0);
        if(goodsBean != null){
            updateNum(goodsBean,1);
        }else {
            goodsBean = new GoodsBean(null,goodId,goodsName,0,"",0,0,1,price,imagePath,true,shopId,shopName,1);
            insertGoodById(goodsBean);
        }
    }

    /**
     * 美食转换goodsBean
     * */
    public GoodsBean convertFood(FoodListBean.DataBean dataBean){
        GoodsBean goodsBean = new GoodsBean();
        goodsBean.setGoodId(dataBean.getId());
        goodsBean.setGoodsName(dataBean.getName());
        goodsBean.setGoodsSpecName("");
        goodsBean.setGoodsSpec(0);
        goodsBean.setIspackagecost(dataBean.getIspackagecost());
        goodsBean.setNumber(1);
        goodsBean.setPrice(dataBean.getPrice());
        goodsBean.setImagePath(dataBean.getImgpath());
        goodsBean.setShopId(dataBean.getBussId());
        goodsBean.setShopName(dataBean.getStoreName());
        goodsBean.setIsChoice(true);
        goodsBean.setType(FOOD_MAIN);
        return goodsBean;
    }


    /**
     * 商城转化goodsBean    对于detail接口
     * @param goodsDetailBean 商品详细参数
     * @param specId 规格id
     * @param specName 规格名称
     * @param price 价格
     * */
    public GoodsBean convertGoodForDetail(GoodsDetailBean goodsDetailBean,long specId,String specName
            ,BigDecimal price,int inventory){
        GoodsBean goodsBean = new GoodsBean();
        goodsBean.setGoodId(goodsDetailBean.getId());
        goodsBean.setGoodsName(goodsDetailBean.getName());
        goodsBean.setNumber(1);
        goodsBean.setImagePath(goodsDetailBean.getPrimaryImage());

        goodsBean.setGoodsSpec(specId);
        goodsBean.setGoodsSpecName(specName);
        goodsBean.setPrice(price.doubleValue());
        goodsBean.setInventory(inventory);
        goodsBean.setIspackagecost(0);

        goodsBean.setShopId(goodsDetailBean.getStoreId());
        goodsBean.setShopName(goodsDetailBean.getStoreName());
        goodsBean.setIsChoice(true);
        goodsBean.setType(SHOP_MAIN);
        return goodsBean;
    }

    /**
     * 更改商品规格id
     * */
    public void changeCategoryId(GoodsBean goodsBean){
        GoodsBean queryBean = queryByGoodId(goodsBean.getGoodId(),0);
        if(queryBean != null){
            updateNum(queryBean,1);
        } else {
            insertGoodById(goodsBean);
        }
    }


    /**
     * 添加商品数量
     * 每次添加 1
     * */
    public void addGoodNum(GoodsBean goodsBean){
        GoodsBean queryBean = queryByGoodId(goodsBean.getGoodId(),goodsBean.getGoodsSpec());
        if(queryBean != null){
            updateNum(queryBean,1);
        } else {
            insertGoodById(goodsBean);
        }
    }

    /**
     * 添加商品数量
     * @param num 商品数量
     * */
    public void addGoodNum(GoodsBean goodsBean,int num){
        GoodsBean queryBean = queryByGoodId(goodsBean.getGoodId(),goodsBean.getGoodsSpec());
        if(queryBean != null){
            updateNum(queryBean,num);
        } else {
            insertGoodById(goodsBean);
        }
    }
    /**
     * 添加商品数量
     * @param num 商品数量
     * */
    public void changeGoodNum(GoodsBean goodsBean,int num){
        GoodsBean queryBean = queryByGoodId(goodsBean.getGoodId(),goodsBean.getGoodsSpec());
        goodsBean.setNumber(num);
        if(queryBean != null){
            ContentValues values = new ContentValues();
            values.put("number",num);
            db.update(GOODS_TABLE,values,"goodsId = ? and goodsSpec = ?"
                    ,new String[] {goodsBean.getGoodId()+"",goodsBean.getGoodsSpec()+""});
        } else {
            insertGoodById(goodsBean);
        }
    }

    /**
     * 减少商品数量
     * @param goodId
     * */
    public void reduceGoodNum(long goodId,long specId){
        GoodsBean goodsBean = queryByGoodId(goodId,specId);
        if(goodsBean != null){
            if(goodsBean.getNumber() > 1){
                updateNum(goodsBean,-1);
            }else if(goodsBean.getNumber() == 1) {
                deleteByGoodId(goodId,specId);
            } else {
                deleteByGoodId(goodId, specId);
            }
        }else {
            return;
        }
    }

    public static int SHOP_GOODS_LIST = 171;        //获取list的接口
    public static int SHOP_GOODS_DETAIL = 172;      //获取商品详情的接口

    /**
     * 商城商品数据库同步
     *
     *  这个方法不用了，因为给的实体类中没有规格数据，只有详情中才有，所以这里改变策略
     * */
    @Deprecated
    public void  compareDataToDB3(List<GoodsDetailBean> list, int type, long all, long shopId){

        //通过网络数据更新本地数据，删除不存在的本地数据
        if(all == 0){       //商城数据进行更新，商品数据是否存在
            List<GoodsBean> goodsBeanList = this.queryByShopId(shopId);
            if(goodsBeanList.size() != 0){
                for(int i = goodsBeanList.size()-1 ;i >=0 ; i--) {  //倒序，防止删除不全
                    GoodsBean goodsBean = goodsBeanList.get(i);
                    int isExist = 0;
                    for(int j = 0 ; j < list.size() ; j ++){
                        GoodsDetailBean dataBean = list.get(j);
                        if(dataBean.getId() == goodsBean.getGoodId()){
                            for(int k = 0 ; k < dataBean.getGoodsSpecs().size() ; k ++ ){
                                if(goodsBean.getGoodsSpec() == dataBean.getGoodsSpecs().get(k).getId()){
                                    isExist = 0;    //只有规格id相同的时候才认为数据是存在的
                                    break;              //break:跳出最近的循环   return:此方法剩下的不执行
                                }
                            }
                            break;
                        } else {
                            isExist = isExist + 1;
                        }
                    }
                    if(isExist != 0){
                        this.deleteByGoodId(goodsBean.getGoodId(),goodsBean.getGoodsSpec());
                    }
                }
            }
        }
        //通过本地数据更改网络数据，将存在的本地数据数量赋值到网络数据上
        for(int i = 0 ;i < list.size() ; i++) {
            GoodsDetailBean dataBean = list.get(i);
            int num = 0 ;
            for(int j = 0 ; j < dataBean.getGoodsSpecs().size(); j ++){
                GoodsBean goodsBean = this.queryByGoodId(dataBean.getId(),dataBean.getGoodsSpecs().get(0).getId());
                if(goodsBean != null){
                    num = num + goodsBean.getNumber();      //这里计算
                    //dataBean.setNumber(goodsBean.getNumber());   // 更新网络数据
                    //dataBean.setChoice(goodsBean.getIsChoice());
                    //更新本地数据库
                    if(type == SHOP_GOODS_DETAIL){      //商品详情
                        goodsBean.setGoodsName(dataBean.getName());
                        goodsBean.setPrice(dataBean.getMinprice().doubleValue());
                        goodsBean.setImagePath(dataBean.getPrimaryImage());
                        goodsBean.setIsChoice(true);
                        goodsBean.setShopId(dataBean.getStoreId());
                        goodsBean.setShopName(dataBean.getStoreName());
                        goodsBean.setType(SHOP_MAIN);
                        goodsBean.setInventory(dataBean.getInventory());
                        goodsBean.setIspackagecost(0);
                        this.updateGoodInfo(goodsBean);
                    } else if( type == SHOP_GOODS_LIST){    //商城主页
                        goodsBean.setGoodsName(dataBean.getName());
                        goodsBean.setPrice(dataBean.getMinprice().doubleValue());
                        goodsBean.setImagePath(dataBean.getPrimaryImage());
                        goodsBean.setIsChoice(true);
                        goodsBean.setShopId(dataBean.getStoreId());
                        goodsBean.setShopName(dataBean.getStoreName());
                        goodsBean.setType(SHOP_MAIN);
                        goodsBean.setIspackagecost(0);
                        this.updateGoodInfo(goodsBean);
                    }
                }else {
                    dataBean.setNumber(0);
                }
            }
            dataBean.setNumber(num);
        }

    }

    /**
     * 商城商品数据库同步
     *
     * 将网络数据与本地数据进行比较
     * 用于填充数量
     * 分为两步:
     * 1,从本地查出商品数量，对网络数据进行填充
     * 2,将网络数据的价格覆盖到本地进行更新
     *
     *
     * @param list 要同步的数据
     * @param type 类型，3个接口有俩个取价格一样，另一个一样
     * @param all 如果传入全部，就进行数据对比
     * */
    public void  compareDataToDB2(List<GoodsDetailBean> list, int type, long all, long shopId,boolean iscompare){

        //通过网络数据更新本地数据，删除不存在的本地数据
        if(list != null && list.size() > 0 ){
            if(all == 0){       //商城数据进行更新，商品数据是否存在
                if (iscompare){
                    List<GoodsBean> goodsBeanList = this.queryByShopId(shopId);
                    if(goodsBeanList.size() > 0 ){
                        for(int i = goodsBeanList.size()-1 ;i >=0 ; i--) {  //倒序，防止删除不全
                            GoodsBean goodsBean = goodsBeanList.get(i);
                            int isExist = 0;
                            for(int j = 0 ; j < list.size() ; j ++){
                                GoodsDetailBean dataBean = list.get(j);
                                if(dataBean.getId() == goodsBean.getGoodId()){
                                    isExist = 0;    //只有规格id相同的时候才认为数据是存在的
                                    break;              //break:跳出最近的循环   return:此方法剩下的不执行
                                } else {
                                    isExist = isExist + 1;
                                }
                            }
                            if(isExist != 0){
                                this.deleteByGoodId(goodsBean.getGoodId(),goodsBean.getGoodsSpec());
                            }
                        }
                    }
                }
            }
            //通过本地数据更改网络数据，将存在的本地数据数量赋值到网络数据上
            for(int i = 0 ;i < list.size() ; i++) {
                GoodsDetailBean dataBean = list.get(i);
                int num = 0 ;
                for(int j = 0 ; j < dataBean.getGoodsSpecs().size(); j ++){
                    GoodsBean goodsBean = this.queryByGoodId(dataBean.getId(),dataBean.getGoodsSpecs().get(j).getId());
                    if(goodsBean != null){
                        num = num + goodsBean.getNumber();      //这里计算
                        //dataBean.setNumber(goodsBean.getNumber());   // 更新网络数据
                        //dataBean.setChoice(goodsBean.getIsChoice());
                        //更新本地数据库
                        if(type == SHOP_GOODS_DETAIL){      //商品详情
                            goodsBean.setGoodsName(dataBean.getName());
                            goodsBean.setPrice(dataBean.getGoodsSpecs().get(j).getPlatformPrice().doubleValue());
                            goodsBean.setImagePath(dataBean.getPrimaryImage());
                            goodsBean.setIsChoice(true);
                            goodsBean.setShopId(dataBean.getStoreId());
                            goodsBean.setShopName(dataBean.getStoreName());
                            goodsBean.setType(SHOP_MAIN);
                            goodsBean.setInventory(dataBean.getInventory());
                            goodsBean.setIspackagecost(0);
                            this.updateGoodInfo(goodsBean);
                        } else if( type == SHOP_GOODS_LIST){    //商城主页
                            goodsBean.setGoodsName(dataBean.getName());
                            goodsBean.setImagePath(dataBean.getPrimaryImage());
                            goodsBean.setPrice(dataBean.getGoodsSpecs().get(j).getPlatformPrice().doubleValue());
                            goodsBean.setIsChoice(true);
                            goodsBean.setShopId(dataBean.getStoreId());
                            goodsBean.setShopName(dataBean.getStoreName());
                            goodsBean.setType(SHOP_MAIN);
                            goodsBean.setIspackagecost(0);
                            this.updateGoodInfo(goodsBean);
                        }
                    }else {
                        dataBean.setNumber(0);
                    }
                }
                dataBean.setNumber(num);
            }
        }
    }

    /**
     * 美食 数据库同步
     * @param list 传入要同步的list
     * @param all 当商家删除掉商品时，数据库还有，此时要进行数据库的删除操作
     * */
    public void compareDataToDB(List<FoodListBean.DataBean> list,long all,long shopId,boolean iscompare){

        if(list != null && list.size() > 0 ){
            if(all == 0){       //商城数据进行更新，商品数据是否存在
                if (iscompare){
                    List<GoodsBean> goodsBeanList = this.queryByShopId(shopId);
                    if(goodsBeanList.size() > 0 ){
                        for(int i = goodsBeanList.size()-1 ;i >=0 ; i--) {  //倒序，防止删除不全
                            GoodsBean goodsBean = goodsBeanList.get(i);
                            int isExist = 0;
                            for(int j = 0 ; j < list.size() ; j ++){
                                FoodListBean.DataBean dataBean = list.get(j);
                                if(dataBean.getId() == goodsBean.getGoodId()){
                                    isExist = 0;
                                    break;              //break:跳出最近的循环   return:此方法剩下的不执行
                                } else {
                                    isExist = isExist + 1;
                                }
                            }
                            if(isExist != 0){
                                this.deleteByGoodId(goodsBean.getGoodId(),0);
                            }
                        }
                    }
                }
            }
            for(int i = 0 ;i < list.size() ; i++) {
                FoodListBean.DataBean dataBean = list.get(i);
                GoodsBean goodsBean =  this.queryByGoodId(dataBean.getId(),0);
                if(goodsBean != null){
                    dataBean.setNumber(goodsBean.getNumber());   // 更新网络数据
                    //更新本地数据库
                    goodsBean.setGoodsName(dataBean.getName());
                    goodsBean.setPrice(dataBean.getPrice());
                    goodsBean.setImagePath(dataBean.getImgpath());
                    goodsBean.setIsChoice(true);
                    goodsBean.setShopId(dataBean.getBussId());
                    goodsBean.setShopName(dataBean.getStoreName());
                    goodsBean.setType(FOOD_MAIN);
                    goodsBean.setIspackagecost(dataBean.getIspackagecost());
                    this.updateGoodInfo(goodsBean);
                } else {
                    dataBean.setNumber(0);
                }
            }
        }
    }

    /**
     * 同步购物车美食数据
     * */
    public void synchronizeFood(){
        final List<GoodsBean> dbList = this.queryByType(FOOD_MAIN);
        if(dbList != null && dbList.size() != 0 ){
            String ids = this.queryFoodsDetailFromSer(dbList);          //拼装查询参数
            FoodNet.queryFoodDetailByIds(ids, new HandleSuccess<UpdateFoodBean>() {
                @Override
                public void onFailure(Call<UpdateFoodBean> call, Throwable t) {
                }
                @Override
                public void success(final UpdateFoodBean updateFoodBean) {
                    if(updateFoodBean.getData() != null && updateFoodBean.getData().size() > 0){

                        new Thread(new Runnable() {     //在查询返回结果后，有数据的情况下，新开线程执行更新本地数据方法
                            @Override
                            public void run() {
                                for(int i = 0 ; i < updateFoodBean.getData().size() ; i ++){
                                    if(updateFoodBean.getData().get(i).getHdFoodCookbook() != null
                                            && updateFoodBean.getData().get(i).getHdFoodCookbook().getName() != null){
                                        updateFood(updateFoodBean.getData().get(i));      //查到更新信息
                                    } else {
                                        DBUtil.this.deleteByGoodId(dbList.get(i).getGoodId(),0);    //查不到 删除信息
                                    }
                                }
                            }
                        }).start();
                    }
                }
            });
        }
    }
    /**
     * 更新美食信息
     * */
    private void updateFood(UpdateFoodBean.DataBean dataBean){
        ContentValues values = new ContentValues();
        GoodsBean goodsBean = this.queryByGoodId(dataBean.getHdFoodCookbook().getId(),0);
        values.put("goodsId",dataBean.getHdFoodCookbook().getId());
        values.put("goodsName",dataBean.getHdFoodCookbook().getName());
        values.put("goodsSpec",0);
        values.put("goodsSpecName","");
        values.put("inventory",0);
        values.put("ispackagecost",dataBean.getHdFoodCookbook().getIspackagecost());
        values.put("number",goodsBean.getNumber());
        values.put("price",dataBean.getHdFoodCookbook().getPrice());
        values.put("imagePath",dataBean.getHdFoodCookbook().getImgpath());
        values.put("isChoice",goodsBean.getIsChoice());
        values.put("shopId",goodsBean.getShopId());
        values.put("shopName",dataBean.getStoreName());
        values.put("type",goodsBean.getType());
        db.update(GOODS_TABLE,values,"goodsId = ? and goodsSpec = ?"
                ,new String[] {goodsBean.getGoodId()+"",goodsBean.getGoodsSpec()+""});
    }


    /**
     * 同步购物车商城数据
     * */
    public void synchronizeGoods(){
        final List<GoodsBean> dbList = this.queryByType(SHOP_MAIN);
        if(dbList != null && dbList.size() != 0 ){
            String ids = this.queryFoodsDetailFromSpec(dbList);          //拼装查询参数
            ShopNet.findGoodBySpecId(ids, new HandleSuccess<UpdateGoodsBean>() {
                @Override
                public void onFailure(Call<UpdateGoodsBean> call, Throwable t) {
                }
                @Override
                public void success(final UpdateGoodsBean updateGoodsBean) {
                    if(updateGoodsBean.getData() != null && updateGoodsBean.getData().size() > 0){

                        new Thread(new Runnable() {     //在查询返回结果后，有数据的情况下，新开线程执行更新本地数据方法
                            @Override
                            public void run() {
                                for(int i = 0 ; i < updateGoodsBean.getData().size() ; i ++){
                                    if(updateGoodsBean.getData().get(i) != null
                                            && updateGoodsBean.getData().get(i).getGoodsName() != null){
                                        updateShop(updateGoodsBean.getData().get(i));      //查到更新信息
                                    } else {
                                        DBUtil.this.deleteByGoodId(dbList.get(i).getGoodId(),dbList.get(i).getGoodsSpec());    //查不到删除信息
                                    }
                                }
                            }
                        }).start();
                    }
                }
            });
        }

    }
    /**
     * 更新商城信息
     * */
    private void updateShop(UpdateGoodsBean.DataBean dataBean){
        ContentValues values = new ContentValues();
        GoodsBean goodsBean = this.queryByGoodId(dataBean.getGoodsId(),dataBean.getGoodsSpecId());
        values.put("goodsId",dataBean.getGoodsId());
        values.put("goodsName",dataBean.getGoodsName());
        values.put("goodsSpec",dataBean.getGoodsSpecId());
        values.put("goodsSpecName",goodsBean.getGoodsSpecName());
        values.put("inventory",dataBean.getInventory());
        values.put("ispackagecost",0);
        values.put("number",goodsBean.getNumber());
        values.put("price",dataBean.getPrice());
        values.put("imagePath",dataBean.getImgPath());
        values.put("isChoice",goodsBean.getIsChoice());
        values.put("shopId",goodsBean.getShopId());
        values.put("shopName",dataBean.getStoreName());
        values.put("type",goodsBean.getType());
        db.update(GOODS_TABLE,values,"goodsId = ? and goodsSpec = ?"
                ,new String[] {goodsBean.getGoodId()+"",goodsBean.getGoodsSpec()+""});
    }

    /**
     * 同步本地美食数据
     * 1,查询本地数据库商品是否下架，如果下架既删除
     * 2,将本地数据库价格等信息进行同步
     * */
    @Deprecated
    public void synchronizeFoodToDB(){
        final List<GoodsBean> dbList = this.queryAll();                  //查询全部数据库商品
        if(dbList != null && dbList.size() != 0){                       //数据库有数据的时候
            String ids = this.queryFoodsDetailFromSer(dbList);          //拼装查询参数
           /* FoodNet.queryFoodDetailByIds(ids, new HandleSuccess<FoodListBean>() {
                @Override
                public void success(FoodListBean foodListBean) {
                    if(foodListBean.getData() != null && foodListBean.getData().size() != 0){   //返回数据不为空
                        for(int i = dbList.size() - 1 ; i >= 0 ; i --){     //遍历本地数据
                            GoodsBean goodsBean = dbList.get(i);            //goodsBean一定存在，之前已判断
                            boolean isExist = true;                         //默认存在
                            for(int j = 0 ; j <foodListBean.getData().size(); j ++){    //遍历服务器数据，用本地的在服务器中对比
                                if(foodListBean.getData().get(j) != null){              //服务器数据有可能为空 null
                                    FoodListBean.DataBean dataBean = foodListBean.getData().get(j);
                                    if(dataBean.getId() == goodsBean.getGoodId()){      //对比
                                        isExist = true;
                                        //更新本地数据库
                                        goodsBean.setGoodsName(dataBean.getName());
                                        goodsBean.setPrice(dataBean.getPrice());
                                        goodsBean.setImagePath(dataBean.getImgpath());
                                        //goodsBean.setIsChoice(true);
                                        goodsBean.setShopId(dataBean.getBussId());
                                        goodsBean.setShopName(dataBean.getStoreName());
                                        goodsBean.setIspackagecost(dataBean.getIspackagecost());
                                        DBUtil.this.updateGoodInfo(goodsBean);
                                        break;      //存在就跳出最近的循环
                                    } else {
                                        isExist = false;
                                    }
                                }
                            }
                            if(!isExist){
                                DBUtil.this.deleteByGoodId(goodsBean.getGoodId(),goodsBean.getGoodsSpec());  //不存在就删除
                            }
                        }
                    }
                }
            });*/
        }

    }

    /**
     * 同步本地商城数据
     * * 1,查询本地数据库商品是否下架，如果下架既删除
     * 2,将本地数据库价格等信息进行同步
     * */
    @Deprecated
    public void synchronizeShopToDB(){
        final List<GoodsBean> dbList = this.queryAll();                  //查询全部数据库商品
        if(dbList != null && dbList.size() != 0){                       //数据库有数据的时候
            GoodDetailUploadBean uploadBean = new GoodDetailUploadBean();
            List<Long> ids = new ArrayList<>();
            for(int i = 0 ; i < dbList.size() ; i ++){
                ids.add(dbList.get(i).getGoodId());
            }

            uploadBean.setGoodIds(ids);
            //String ids = this.queryFoodsDetailFromSer(dbList);          //拼装查询参数
            ShopNet.findGoodByIds(uploadBean, new HandleSuccess<GoodsDetailListBean>() {
                @Override
                public void success(GoodsDetailListBean goodsDetailListBean) {
                    if(goodsDetailListBean.getData() != null && goodsDetailListBean.getData().size() != 0){   //返回数据不为空
                        for(int i = dbList.size() - 1 ; i >= 0 ; i --){     //遍历本地数据
                            GoodsBean goodsBean = dbList.get(i);            //goodsBean一定存在，之前已判断
                            boolean isExist = true;                         //默认存在
                            for(int j = 0 ; j <goodsDetailListBean.getData().size(); j ++){    //遍历服务器数据，用本地的在服务器中对比
                                if(goodsDetailListBean.getData().get(j) != null){              //服务器数据有可能为空 null
                                    GoodsDetailBean goodsDetailBean = goodsDetailListBean.getData().get(j);
                                    if(goodsDetailBean.getId() == goodsBean.getGoodId()){      //对比
                                        isExist = true;
                                        for(int k = 0 ; k < goodsDetailBean.getGoodsSpecs().size();k ++ ) {     //判断是否规格还有
                                            if(goodsDetailBean.getGoodsSpecs().get(k).getId() == goodsBean.getGoodsSpec()){
                                                goodsBean.setGoodsName(goodsDetailBean.getName());
                                                goodsBean.setPrice(goodsDetailBean.getGoodsSpecs().get(k).getPlatformPrice().doubleValue());
                                                goodsBean.setInventory(goodsDetailBean.getGoodsSpecs().get(k).getInventory());
                                                goodsBean.setImagePath(goodsDetailBean.getPrimaryImage());
                                                //goodsBean.setIsChoice(true);
                                                goodsBean.setShopId(goodsDetailBean.getStoreId());
                                                goodsBean.setShopName(goodsDetailBean.getStoreName());
                                                goodsBean.setIspackagecost(0);
                                                DBUtil.this.updateGoodInfo(goodsBean);
                                                break;
                                            } else {
                                                goodsBean.setGoodsName(goodsDetailBean.getName());          //没有规格把第一条规格放入
                                                goodsBean.setPrice(goodsDetailBean.getGoodsSpecs().get(0).getPlatformPrice().doubleValue());
                                                String aya = goodsDetailBean.getGoodsSpecs().get(0).getSpecaValue()+
                                                        goodsDetailBean.getGoodsSpecs().get(0).getSpecbValue()+
                                                        goodsDetailBean.getGoodsSpecs().get(0).getSpeccValue();
                                                goodsBean.setGoodsSpecName(aya);
                                                goodsBean.setInventory(goodsDetailBean.getGoodsSpecs().get(0).getInventory());
                                                goodsBean.setImagePath(goodsDetailBean.getPrimaryImage());
                                                //goodsBean.setIsChoice(true);
                                                goodsBean.setShopId(goodsDetailBean.getStoreId());
                                                goodsBean.setShopName(goodsDetailBean.getStoreName());
                                                goodsBean.setIspackagecost(0);
                                                DBUtil.this.updateGoodInfo(goodsBean);
                                            }
                                        }
                                        break;      //存在就跳出最近的循环
                                    } else {
                                        isExist = false;
                                    }
                                }
                            }
                            if(!isExist){
                                DBUtil.this.deleteByGoodId(goodsBean.getGoodId(),goodsBean.getGoodsSpec());  //不存在就删除
                            }
                        }
                    }
                }
            });
        }

    }

    /**
     * 通过类型查询商品信息
     *
     * 同步本地数据库时使用
     *
     * @param type FOOD_MAIN  SHOP_MAIN
     * */
    private List<GoodsBean> queryByType(int type){
        List<GoodsBean > list = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from "+GOODS_TABLE+" where type = " +type,null);
        if(cursor != null){
            if(cursor.moveToFirst()){
                do {
                    GoodsBean goodsBean = new GoodsBean();
                    goodsBean.setGoodId(cursor.getLong(cursor.getColumnIndex("goodsId")));
                    goodsBean.setGoodsName(cursor.getString(cursor.getColumnIndex("goodsName")));
                    goodsBean.setGoodsSpec(cursor.getLong(cursor.getColumnIndex("goodsSpec")));
                    goodsBean.setGoodsSpecName(cursor.getString(cursor.getColumnIndex("goodsSpecName")));
                    goodsBean.setInventory(cursor.getInt(cursor.getColumnIndex("inventory")));
                    goodsBean.setIspackagecost(cursor.getInt(cursor.getColumnIndex("ispackagecost")));
                    goodsBean.setNumber(cursor.getInt(cursor.getColumnIndex("number")));
                    goodsBean.setPrice(cursor.getFloat(cursor.getColumnIndex("price")));
                    goodsBean.setImagePath(cursor.getString(cursor.getColumnIndex("imagePath")));
                    goodsBean.setIsChoice(cursor.getLong(cursor.getColumnIndex("isChoice"))!=0);
                    goodsBean.setShopId(cursor.getLong(cursor.getColumnIndex("shopId")));
                    goodsBean.setShopName(cursor.getString(cursor.getColumnIndex("shopName")));
                    goodsBean.setType(cursor.getInt(cursor.getColumnIndex("type")));
                    list.add(goodsBean);
                }while (cursor.moveToNext());
            }
            cursor.close();
        }
        return list;
    }




    /**
     * 拼装查询数据
     * @param list 通过从 数据库 查询出的list，拼装成参数
     * */
    public String queryFoodsDetailFromSer(List<GoodsBean> list){
        StringBuffer stringBuffer = new StringBuffer("");
        for(int i = 0; i < list.size() ; i++){
            stringBuffer.append(list.get(i).getGoodId()+",");
        }
        stringBuffer.deleteCharAt(stringBuffer.length()-1);
        return stringBuffer.toString();
    }

    /**
     * 拼装查询数据
     * @param list 通过从 数据库 查询出的list，拼装成参数
     *             这个专门给商城使用，用于拼装上传参数，这里取 specId
     * */
    private String queryFoodsDetailFromSpec(List<GoodsBean> list){
        StringBuffer stringBuffer = new StringBuffer("");
        for(int i = 0; i < list.size() ; i++){
            stringBuffer.append(list.get(i).getGoodsSpec()+",");
        }
        stringBuffer.deleteCharAt(stringBuffer.length()-1);
        return stringBuffer.toString();
    }



}

