package com.linli.consumer.dao;

import android.content.Context;
import com.linli.consumer.bean.FoodListBean;
import com.linli.consumer.bean.GoodDetailUploadBean;
import com.linli.consumer.bean.GoodsBean;
import com.linli.consumer.bean.GoodsDetailBean;
import com.linli.consumer.bean.GoodsDetailListBean;
import com.linli.consumer.bean.MallGoodsVo;
import com.linli.consumer.common.HandleSuccess;
import com.linli.consumer.net.ShopNet;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.linli.consumer.widget.ShopSortLayout.FOOD_MAIN;
import static com.linli.consumer.widget.ShopSortLayout.SHOP_MAIN;

/**
 * Created by tomoyo on 2016/12/12.
 */


public class DBUtil2  {

    private DaoManager daoManager ;

    public DBUtil2(Context context){
        daoManager = DaoManager.getInstance();
        daoManager.init(context);
    }







    /**
     * 完成对student的某一条记录的删除
     * 不适用，这个是根据bean的key进行删除的(id)
     * @param goodsBean
     * @return
     */
    @Deprecated
    public boolean deleteToDB(GoodsBean goodsBean){
        boolean flag = false;
        try {
            daoManager.getDaoSession().delete(goodsBean);
            flag = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }





    /**
     * 通过shopId查找数据
     * @param shopId 店铺id
     * @return List<GoodsBean>
     * */
    public List<GoodsBean> queryByShopId(long shopId){
        List<GoodsBean> list = daoManager.getDaoSession().queryBuilder(GoodsBean.class)
                .where(GoodsBeanDao.Properties.ShopId.eq(shopId)).build().list();
        return list;
    }


    /**
     * 通过商品id查找数据
     * @param goodId 商品id
     * */
    public GoodsBean queryByGoodId(long goodId,long specId){
        GoodsBean  goodsBean = daoManager.getDaoSession().queryBuilder(GoodsBean.class)
                .where(GoodsBeanDao.Properties.GoodId.eq(goodId))
                .where(GoodsBeanDao.Properties.GoodsSpec.eq(specId)).build().unique();
        return goodsBean;
    }

    /**
     * 将所有数据列出
     * 返回多行记录
     *
     * @return List<GoodsBean> list
     */
    public List<GoodsBean> queryAll() {
        return daoManager.getDaoSession().loadAll(GoodsBean.class);
    }

    /**
     * 通过商品id删除指定商品
     * @param goodId
     * @return 返回boolean，判断是否执行成功
     * */
    public boolean deleteByGoodId(long goodId){

        GoodsBean  goodsBean = daoManager.getDaoSession().queryBuilder(GoodsBean.class)
                .where(GoodsBeanDao.Properties.GoodId.eq(goodId)).build().unique();
        if(goodsBean != null){
            daoManager.getDaoSession().delete(goodsBean);
            return true;
        }else {
            return false;
        }
    }


    /**
     * 将商品数据插入数据库
     * @param goodsBean
     * @return boolean 判断是否插入成功
     */
    private boolean insertGoodById(GoodsBean goodsBean){
        boolean flag = false;
        GoodsBean bean = new GoodsBean(null,goodsBean.getGoodId(),
                goodsBean.getGoodsName(),goodsBean.getGoodsSpec(),
                goodsBean.getGoodsSpecName(),
                goodsBean.getInventory(),
                goodsBean.getIspackagecost(),
                goodsBean.getNumber(),
                goodsBean.getPrice(),
                goodsBean.getImagePath(),
                true,
                goodsBean.getShopId(),
                goodsBean.getShopName(),
                goodsBean.getType());
        flag = daoManager.getDaoSession().insert(bean) != -1? true : false;
        return flag;
    }


    /**
     * @param goodsBean 更新的商品信息
     *
     * 单独给外部使用
     * */
    public void updateGoodInfo(GoodsBean goodsBean){
        daoManager.getDaoSession().update(goodsBean);
    }

    /**
     * 更新商品数量信息
     * @param goodsBean  要更新的商品信息
     * @param num 更新的商品数量 1增加   -1减少
     * @see {@linkplain DBUtil2#queryByGoodId(long,long)},此两者要一起用，先查询，在更新
     * */
    private void updateNum(GoodsBean goodsBean,int num){
        goodsBean.setNumber(goodsBean.getNumber()+num);
        daoManager.getDaoSession().update(goodsBean);
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
     * */
    public void addGoodNum(GoodsBean goodsBean){
        GoodsBean queryBean = queryByGoodId(goodsBean.getGoodId(),goodsBean.getGoodsSpec());
        if(queryBean != null){
            if(queryBean.getGoodsSpec() == 0){
                updateNum(queryBean,1);
            } else {

            }

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
                deleteByGoodId(goodId);
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
    @Deprecated
    public void compareDataToDB(List<MallGoodsVo> list, int type,long all,long shopId){

        if(all == 0){       //商城数据进行更新，商品数据是否存在
            List<GoodsBean> goodsBeanList = this.queryByShopId(shopId);
            if(goodsBeanList.size() != 0){
                for(int i = goodsBeanList.size()-1 ;i >=0 ; i--) {  //倒序，防止删除不全
                    GoodsBean goodsBean = goodsBeanList.get(i);
                    int isExist = 0;
                    for(int j = 0 ; j < list.size() ; j ++){
                        MallGoodsVo dataBean = list.get(j);
                        if(dataBean.getMallGoods().getId() == goodsBean.getGoodId()){
                            isExist = 0;
                            break;              //break:跳出最近的循环   return:此方法剩下的不执行
                        } else {
                            isExist = isExist + 1;
                        }
                    }
                    if(isExist != 0){
                        this.deleteByGoodId(goodsBean.getGoodId());
                    }
                }
            }
        }

        for(int i = 0 ;i < list.size() ; i++) {
            MallGoodsVo mallGoodsVo = list.get(i);
            GoodsBean goodsBean =  this.queryByGoodId(mallGoodsVo.getMallGoods().getId(),0);
            if(goodsBean != null){
                mallGoodsVo.setNumber(goodsBean.getNumber());   // 更新网络数据
                mallGoodsVo.setShow(goodsBean.getIsChoice());
                //更新本地数据库
                if(type == SHOP_GOODS_DETAIL){      //商品详情
                    if(mallGoodsVo.getMallGoods().getIsSpec() == 0){    //无规格
                        goodsBean.setGoodsName(mallGoodsVo.getMallGoods().getName());
                        goodsBean.setPrice(mallGoodsVo.getMallGoods().getNospecPlatformPrice().doubleValue());
                        goodsBean.setImagePath(mallGoodsVo.getMallGoods().getPrimaryImage());
                        goodsBean.setIsChoice(true);
                        goodsBean.setShopId(mallGoodsVo.getMallGoods().getMallStore().getId());
                        goodsBean.setShopName(mallGoodsVo.getMallGoods().getMallStore().getName());
                        this.updateGoodInfo(goodsBean);
                    } else if(mallGoodsVo.getMallGoods().getIsSpec() == 1){ //有规格
                        goodsBean.setGoodsName(mallGoodsVo.getMallGoods().getName());
                        goodsBean.setPrice(mallGoodsVo.getVoList().get(0).getMallGoodsSpec().getPlatformPrice().doubleValue());
                        goodsBean.setImagePath(mallGoodsVo.getVoList().get(0).getImgList().get(0).getImgUrl());
                        goodsBean.setIsChoice(true);
                        goodsBean.setShopId(mallGoodsVo.getMallGoods().getMallStore().getId());
                        goodsBean.setShopName(mallGoodsVo.getMallGoods().getMallStore().getName());
                        this.updateGoodInfo(goodsBean);
                    }
                } else if( type == SHOP_GOODS_LIST){    //商城主页
                    if(mallGoodsVo.getMallGoods().getIsSpec() == 0){    //无规格
                        goodsBean.setGoodsName(mallGoodsVo.getMallGoods().getName());
                        goodsBean.setPrice(mallGoodsVo.getMallGoods().getNospecPlatformPrice().doubleValue());
                        goodsBean.setImagePath(mallGoodsVo.getMallGoods().getPrimaryImage());
                        goodsBean.setIsChoice(true);
                        goodsBean.setShopId(mallGoodsVo.getMallGoods().getMallStore().getId());
                        goodsBean.setShopName(mallGoodsVo.getMallGoods().getMallStore().getName());
                        this.updateGoodInfo(goodsBean);
                    } else if(mallGoodsVo.getMallGoods().getIsSpec() == 1){     //有规格
                        goodsBean.setGoodsName(mallGoodsVo.getMallGoods().getName());
                        goodsBean.setPrice(mallGoodsVo.getPlatformPrice().doubleValue());
                        goodsBean.setImagePath(mallGoodsVo.getImgSrc());
                        goodsBean.setIsChoice(true);
                        goodsBean.setShopId(mallGoodsVo.getMallGoods().getMallStore().getId());
                        goodsBean.setShopName(mallGoodsVo.getMallGoods().getMallStore().getName());
                        this.updateGoodInfo(goodsBean);
                    }
                }

            } else {
                mallGoodsVo.setNumber(0);
            }
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
    public void compareDataToDB2(List<GoodsDetailBean> list, int type, long all, long shopId){


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
                        this.deleteByGoodId(goodsBean.getGoodId());
                    }
                }
            }
        }
        //通过本地数据更改网络数据，将存在的本地数据数量赋值到网络数据上
        for(int i = 0 ;i < list.size() ; i++) {
            GoodsDetailBean dataBean = list.get(i);
            GoodsBean goodsBean =  this.queryByGoodId(dataBean.getId(),0);
            if(goodsBean != null){
                dataBean.setNumber(goodsBean.getNumber());   // 更新网络数据
                dataBean.setChoice(goodsBean.getIsChoice());
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
                    this.updateGoodInfo(goodsBean);
                } else if( type == SHOP_GOODS_LIST){    //商城主页
                    goodsBean.setGoodsName(dataBean.getName());
                    goodsBean.setPrice(dataBean.getMinprice().doubleValue());
                    goodsBean.setImagePath(dataBean.getPrimaryImage());
                    goodsBean.setIsChoice(true);
                    goodsBean.setShopId(dataBean.getStoreId());
                    goodsBean.setShopName(dataBean.getStoreName());
                    goodsBean.setType(SHOP_MAIN);
                    this.updateGoodInfo(goodsBean);
                }

            } else {
                dataBean.setNumber(0);
            }
        }

    }


    /**
     * 美食 数据库同步
     * @param list 传入要同步的list
     * @param all 当商家删除掉商品时，数据库还有，此时要进行数据库的删除操作
     * */
    public void compareDataToDB(List<FoodListBean.DataBean> list,long all,long shopId){

        if(all == 0){       //商城数据进行更新，商品数据是否存在
            List<GoodsBean> goodsBeanList = this.queryByShopId(shopId);
            if(goodsBeanList.size() != 0){
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
                        this.deleteByGoodId(goodsBean.getGoodId());
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
                this.updateGoodInfo(goodsBean);
            } else {
                dataBean.setNumber(0);
            }
        }

    }

    /**
     * 同步本地美食数据
     * 1,查询本地数据库商品是否下架，如果下架既删除
     * 2,将本地数据库价格等信息进行同步
     * */
    public void synchronizeFoodToDB(){
        final List<GoodsBean> dbList = this.queryAll();                  //查询全部数据库商品
        if(dbList != null && dbList.size() != 0){                       //数据库有数据的时候
            String ids = this.queryFoodsDetailFromSer(dbList);          //拼装查询参数
           /* queryFoodDetailByIds(ids, new HandleSuccess<FoodListBean>() {
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
                                        DBUtil2.this.updateGoodInfo(goodsBean);
                                        break;      //存在就跳出最近的循环
                                    } else {
                                        isExist = false;
                                    }
                                }
                            }
                            if(!isExist){
                                DBUtil2.this.deleteByGoodId(goodsBean.getGoodId());  //不存在就删除
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
                                                DBUtil2.this.updateGoodInfo(goodsBean);
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
                                                DBUtil2.this.updateGoodInfo(goodsBean);
                                            }
                                        }
                                        break;      //存在就跳出最近的循环
                                    } else {
                                        isExist = false;
                                    }
                                }
                            }
                            if(!isExist){
                                DBUtil2.this.deleteByGoodId(goodsBean.getGoodId());  //不存在就删除
                            }
                        }
                    }
                }
            });
        }

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


}

