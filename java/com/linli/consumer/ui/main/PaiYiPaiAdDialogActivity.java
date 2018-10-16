package com.linli.consumer.ui.main;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.linli.consumer.R;
import com.linli.consumer.app.AppContext;
import com.linli.consumer.base.BaseActivity;
import com.linli.consumer.bean.AdverPosition;
import com.linli.consumer.bean.AdvertisementList;
import com.linli.consumer.bean.AdvertisementListF;
import com.linli.consumer.bean.FindAreaInfo;
import com.linli.consumer.bean.FoodBean;
import com.linli.consumer.bean.GoodBean;
import com.linli.consumer.bean.MallShopInfo;
import com.linli.consumer.bean.RequestParamPYPBean;
import com.linli.consumer.bean.ServiceStoreBean;
import com.linli.consumer.bean.StoreInfo;
import com.linli.consumer.common.HandleSuccess;
import com.linli.consumer.domain.City;
import com.linli.consumer.domain.CookBook;
import com.linli.consumer.domain.Product;
import com.linli.consumer.net.FoodNet;
import com.linli.consumer.net.IntrestBuyNet;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.TimeZone;

import static com.linli.consumer.widget.ShopSortLayout.FOOD_MAIN;
import static com.linli.consumer.widget.ShopSortLayout.SERVICE_MAIN;
import static com.linli.consumer.widget.ShopSortLayout.SHOP_MAIN;

public class PaiYiPaiAdDialogActivity extends BaseActivity {
    private SimpleDraweeView sdv_adv;
    private ProgressBar pb_progress;
    private long waitingTime = 2*1000;
    private final int PRODUCT = 1;
    private final int ADVERT = 2;
    private final int MEETING = 3;
    private final int ANNOUNCE = 4;
    private ArrayList<Integer> types = new ArrayList<Integer>();
    private int type;//拍拍类型 （拍什么的判断）1商品  2广告  3招商会议  4公告
    private boolean isTypeSetted = false;//用户是否设置过拍一拍类型 默认false
    private long shopId = 0l;
    private int category = 0;
    private AppContext appContext;
    private City city;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_pai_yi_pai_ad_dialog;
    }

    @Override
    protected void initView() {
        sdv_adv = findViewClick(R.id.sdv_adv);
        pb_progress = findView(R.id.pb_progress);
        appContext = (AppContext) getApplicationContext();
        city = appContext.getCity();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                PaiYiPaiAdDialogActivity.this.finish();
            }
        },5000);
    }

    @Override
    protected void initData() {
        dismiss();
        shopId = getIntent().getLongExtra("shopId",0l);
        category = getIntent().getIntExtra("category",0);
        getTypeRandom();
       //获取该商铺可拍的数据
        requestDatasPaiYiPai();
    }


    private void requestDatasPaiYiPai() {
        //如果获取shopId成功
        new CountDownTimer(waitingTime, 1000){//一秒之后才进入拍后的界面
            @Override
            public void onTick(long millisUntilFinished) {
            }
            @Override
            public void onFinish() {
                switch (type){
                    case PRODUCT://拍商品
                        System.out.println(shopId);
                        if (shopId != 0l){
                            switch (category){
                                case 1:
                                    request_prods_paiyipai();
                                    break;
                                case 2:
                                    request_foods_paiyipai();
                                    break;
                                case 3:
                                    request_services_paiyipai();
                                    break;
                                default:
                                    break;
                            }
                        }
                        break;
                    case ADVERT://拍广告
                        System.out.println(shopId);
                        if (shopId != 0l){
                            request_advert_paiyipai();
                        }
                        break;
                    case MEETING://拍会场
                        break;
                    case ANNOUNCE://拍公告
                        System.out.println(shopId);
                        if (shopId != 0l){
                            request_announce_paiyipai();
                        }
                        break;
                    default:
                        break;
                }
            }
        }.start();
    }

    private void getTypeRandom() {
        types.clear();
        SharedPreferences preferences = getSharedPreferences("userSetting", Context.MODE_PRIVATE);
        int ifhave = preferences.getInt("paipro", 0);
        if (ifhave == 1){
            types.add(PRODUCT);
        }
        ifhave = preferences.getInt("paiadv",0);
        if (ifhave == 1){
            types.add(ADVERT);
        }
        ifhave = preferences.getInt("paimee",0);
        if (ifhave == 1){
            types.add(MEETING);
        }
        ifhave = preferences.getInt("paiann",0);
        if (ifhave == 1){
            types.add(ANNOUNCE);
        }
        if (types.size()>0){//设置过拍出的类型
            isTypeSetted = true;
            type = types.get(getRandomNum(types.size()));
        }else {//用户没设置过类型 就要随机拍出各种类型 不包括参会信息表类型
            types.add(PRODUCT);
            types.add(ADVERT);
            types.add(ANNOUNCE);//公告要不要也去掉了？
            type = types.get(getRandomNum(types.size()));
        }
    }

    @Override
    public void onHDClick(View v) {

    }

    private void ReRandom(int t) {
        if (types.size()>1){
            for (int i = 0;i < types.size();i++){
                if (types.get(i) == t){
                    types.remove(i);
                }
            }
            type = types.get(getRandomNum(types.size()));
            switch (type){
                case PRODUCT:
                    switch (category){
                        case 1:
                            request_prods_paiyipai();
                            break;
                        case 2:
                            request_foods_paiyipai();
                            break;
                        case 3:
                            request_services_paiyipai();
                            break;
                        default:
                            break;
                    }
                    break;
                case ADVERT:
                    request_advert_paiyipai();
                    break;
                case MEETING:
                    break;
                case ANNOUNCE:
                    request_announce_paiyipai();
                    break;
                default:
                    break;
            }
        }else {
            if (!isTypeSetted){
                PaiYiPaiAdDialogActivity.this.finish();
                Toast.makeText(PaiYiPaiAdDialogActivity.this,"该商家暂未参加拍一拍活动",Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * 随机一个int型
     * @param i 随机0-i的数
     * @return 随机数int
     */
    private int getRandomNum(int i){
        Random random = new Random();
        return random.nextInt(i);
    }

    private long getShopIdByHardware() {
        long shopid = 0;
        switch (type){
            case 1:
                shopid = 64l;
                break;
            case 2:
                shopid = 73l;
                break;
            case 3:
                shopid = 64l;
                break;
            case 4:
                shopid = 73l;
                break;
        }

        return shopid;
    }


    /*
    拍广告的接口 可能是四种类型的广告，图片，文字，图文，视频
     */
    private void request_advert_paiyipai() {
        request_arecode_by_location();
    }
    private void request_arecode_by_location() {
        if (city != null){
            IntrestBuyNet.findcityList(city.getArea().replace("区", "").replace("县", ""), new HandleSuccess<FindAreaInfo>() {
                @Override
                public void success(FindAreaInfo s) {
                    if (s.getStatus() == 1){
                        List<FindAreaInfo.DataBean> areas = s.getData();
                        if (areas != null && areas.size()>0){
                            int areaCode = areas.get(0).getId();
                            request_adver_position(areaCode);//想查广告第二步 再查广告位
                        }else {
                            noDatas(ADVERT);
                        }
                    }else {
                        noDatas(ADVERT);
                    }
                }
            });
        }else {
            noDatas(ADVERT);
        }
    }

    private void request_adver_position(final int areaCode) {
        if (category == 1){//商城
            IntrestBuyNet.searchStandard(areaCode, new HandleSuccess<AdverPosition>() {
                @Override
                public void success(AdverPosition s) {
                    if (s.getStatus() == 1){
                        List<AdverPosition.DataBean> positions = s.getData();
                        if (positions != null && positions .size() > 0){
                            for (int i = 0;i<positions.size();i++){
                                String positionName = positions.get(i).getName().trim();
                                if (positionName.equals("拍一拍")){
                                    Log.i("test","执行了");
                                    request_advertisement_paiyipai(positions.get(i).getId(),areaCode);
                                    break;
                                }
                            }
                        }else {
                            noDatas(ADVERT);
                        }
                    }else {
                        noDatas(ADVERT);
                    }
                }
            });
        }else if (category == 2){//美食
            IntrestBuyNet.searchStandardF(areaCode, new HandleSuccess<AdverPosition>() {
                @Override
                public void success(AdverPosition s) {
                    if (s.getStatus() == 1){
                        List<AdverPosition.DataBean> positions = s.getData();
                        if (positions != null && positions .size() > 0){
                            for (int i = 0;i<positions.size();i++){
                                String positionName = positions.get(i).getName().trim();
                                if (positionName.equals("拍一拍")){
                                    Log.i("test","执行了");
                                    request_advertisement_paiyipai(positions.get(i).getId(),areaCode);
                                    break;
                                }
                            }
                        }else {
                            noDatas(ADVERT);
                        }
                    }else {
                        noDatas(ADVERT);
                    }
                }
            });
        }else {
            noDatas(ADVERT);
        }

    }


    private void request_advertisement_paiyipai(Long id, int areaCode) {
        if (category == 1){//商城
            IntrestBuyNet.searchByParamP(5,id, "0", areaCode,shopId,new HandleSuccess<AdvertisementList>() {
                @Override
                public void success(AdvertisementList s) {
                    if (s.getStatus() == 1){
                        if (s.getData() != null && s.getData().size() > 0){
                            List<AdvertisementList.DataBean> businfos = s.getData();
                            Log.i("test","有"+businfos.size()+"条数据");
                            AdvertisementList.DataBean item = businfos.get(getRandomNum(businfos.size()));
                            Intent intent = new Intent();
                            intent.putExtra("id",item.getId());
                            intent.putExtra("named",item.getName());
                            intent.putExtra("path",item.getPicurl());
                            intent.putExtra("intro", item.getContent());
                            Date dt = new Date(item.getCreatetime());
                            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            format.setTimeZone(TimeZone.getTimeZone("GMT+8"));
                            intent.putExtra("createTime",format.format(dt));
                            intent.putExtra("merchantId",item.getStoreId());
                            int typed = Integer.valueOf(item.getType());
                            intent.putExtra("typed", typed);
                            switch (typed){
                                case 1:
                                    intent.setClass(PaiYiPaiAdDialogActivity.this,PaiYiPaiAdvertisementPicActivity.class);
                                    startActivity(intent);
                                    break;
                                case 2:
                                    intent.setClass(PaiYiPaiAdDialogActivity.this,PaiYiPaiAdvertisementActivity.class);
                                    startActivity(intent);
                                    break;
                                case 3:
                                    intent.setClass(PaiYiPaiAdDialogActivity.this,PaiYiPaiAdvertisementTextActivity.class);
                                    startActivity(intent);
                                    break;
                                case 4:
                                    intent.setClass(PaiYiPaiAdDialogActivity.this,PaiYiPaiAdvertisementOtherActivity.class);
                                    startActivity(intent);
                                    break;
                                default:
                                    break;
                            }
                        }else {
                            noDatas(ADVERT);
                        }
                    }else {
                        noDatas(ADVERT);
                    }
                }
            });
        }else if (category == 2){//美食
            IntrestBuyNet.searchByParamFoodP(5,id, "0", areaCode,shopId, new HandleSuccess<AdvertisementListF>() {
                @Override
                public void success(AdvertisementListF s) {
                    if (s.getStatus() == 1){
                        if (s.getData() != null && s.getData().size() > 0){
                            List<AdvertisementListF.DataBean> businfos = s.getData();
                            Log.i("test","有"+businfos.size()+"条数据");
                            AdvertisementListF.DataBean item = businfos.get(getRandomNum(businfos.size()));
                            Intent intent = new Intent();
                            intent.putExtra("id",item.getId());
                            intent.putExtra("named",item.getName());
                            intent.putExtra("path",item.getPicurl());
                            intent.putExtra("intro", item.getContent());
                            Date dt = new Date(item.getCreatetime());
                            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            format.setTimeZone(TimeZone.getTimeZone("GMT+8"));
                            intent.putExtra("createTime",format.format(dt));
                            intent.putExtra("merchantId",item.getStoreId());
                            int typed = Integer.valueOf(item.getType());
                            intent.putExtra("typed", typed);
                            PaiYiPaiAdDialogActivity.this.finish();
                            switch (typed){
                                case 1:
                                    intent.setClass(PaiYiPaiAdDialogActivity.this,PaiYiPaiAdvertisementPicActivity.class);
                                    startActivity(intent);
                                    break;
                                case 2:
                                    intent.setClass(PaiYiPaiAdDialogActivity.this,PaiYiPaiAdvertisementActivity.class);
                                    startActivity(intent);
                                    break;
                                case 3:
                                    intent.setClass(PaiYiPaiAdDialogActivity.this,PaiYiPaiAdvertisementTextActivity.class);
                                    startActivity(intent);
                                    break;
                                case 4:
                                    intent.setClass(PaiYiPaiAdDialogActivity.this,PaiYiPaiAdvertisementOtherActivity.class);
                                    startActivity(intent);
                                    break;
                                default:
                                    break;
                            }
                        }else {
                           noDatas(ADVERT);
                        }
                    }else {
                        noDatas(ADVERT);
                    }
                }
            });
        }else {
            noDatas(ADVERT);
        }
    }

    /*
  拍商家公告的接口 只有一种类型的公告 随机取出一条给下个页面
   */
    private void request_announce_paiyipai() {
        if (category == 1){
            IntrestBuyNet.findShopByshopId(shopId, new HandleSuccess<MallShopInfo>() {
                @Override
                public void success(MallShopInfo s) {
                    if (s.getStatus() == 1){
                        if (s.getData().getNotice() != null){
                            PaiYiPaiAdDialogActivity.this.finish();
                            Intent intent = new Intent();
                            intent.putExtra("title",s.getData().getName());
                            intent.putExtra("contents", s.getData().getNotice());
                            intent.putExtra("shopid",s.getData().getId());
                            intent.putExtra("shopname",s.getData().getName());
                            intent.putExtra("category",SHOP_MAIN);
                            intent.setClass(PaiYiPaiAdDialogActivity.this, PaiYiPaiAnnouncementActivity.class);
                            startActivity(intent);
                        }else {//没有公告
                           noDatas(ANNOUNCE);
                        }
                    }else {
                        noDatas(ANNOUNCE);
                    }
                }
            });
        }else if (category == 2){
            IntrestBuyNet.findFoodShopByshopId(shopId, new HandleSuccess<StoreInfo>() {
                @Override
                public void success(StoreInfo s) {
                    if (s.getStatus() == 1){
                        if (s.getData().getNotice() != null){
                            PaiYiPaiAdDialogActivity.this.finish();
                            Intent intent = new Intent();
                            intent.putExtra("title",s.getData().getName());
                            intent.putExtra("contents", s.getData().getNotice());
                            intent.putExtra("shopid",s.getData().getId());
                            intent.putExtra("category",FOOD_MAIN);
                            intent.putExtra("shopname",s.getData().getName());
                            intent.setClass(PaiYiPaiAdDialogActivity.this, PaiYiPaiAnnouncementActivity.class);
                            startActivity(intent);
                        }else {//没有公告
                            noDatas(ANNOUNCE);
                        }
                    }else {//没有公告
                        noDatas(ANNOUNCE);
                    }
                }
            });
        }else if (category == 3){
            FoodNet.findServiceStoresInfos(shopId, new HandleSuccess<ServiceStoreBean>() {
                @Override
                public void success(ServiceStoreBean s) {
                    if (s.getData() != null){
                        if (s.getData().getNotice() != null){
                            PaiYiPaiAdDialogActivity.this.finish();
                            Intent intent = new Intent();
                            intent.putExtra("title",s.getData().getName());
                            intent.putExtra("contents", s.getData().getNotice());
                            intent.putExtra("shopid",s.getData().getId());
                            intent.putExtra("category",SERVICE_MAIN);
                            intent.putExtra("shopname",s.getData().getName());
                            intent.setClass(PaiYiPaiAdDialogActivity.this, PaiYiPaiAnnouncementActivity.class);
                            startActivity(intent);
                        }else {
                            noDatas(ANNOUNCE);
                        }
                    }else {
                        noDatas(ANNOUNCE);
                    }
                }
            });
        }
    }
    /*
     拍商品的接口 可能是三种类型的商品 普通商城商品 餐饮类商品 服务类商品
  */
    private ArrayList<Product> products = new ArrayList<Product>();
//    private Product myProduct = new Product();
    private void request_prods_paiyipai() {
        RequestParamPYPBean bean = new RequestParamPYPBean();
        bean.setNumPerPage(20);
        bean.setPageNum(1);
        bean.setStoreId(shopId);
        bean.setIsShow(1);
        IntrestBuyNet.findGoodsIsPat(bean, new HandleSuccess<GoodBean>() {
            @Override
            public void success(GoodBean s) {
                if (s.getStatus() == 0){
                    List<GoodBean.DataBean> goodList = s.getData();
                    if (goodList != null && goodList.size() > 0){
                        for (int i = 0;i < goodList.size();i++) {
                            GoodBean.DataBean item = goodList.get(i);
                            Log.i("test","第"+i+"条 "+item.getName()+item.getIsShow());
                            Product product = new Product();
                            product.setId(item.getId());
                            product.setShopId(item.getStoreId());
                            product.setName(item.getName());
                            product.setPrice(item.getMinprice());
                            product.setPicPath(item.getPrimaryImage());
                            product.setShopCartNum(1);
                            product.setSpecId(item.getGoodsSpecs().get(0).getId());
                            product.setPrice(item.getGoodsSpecs().get(0).getPlatformPrice());
                            product.setResidueNum(item.getGoodsSpecs().get(0).getInventory());
                            products.add(product);
                        }
                        if (products.size()>0) {
                            PaiYiPaiAdDialogActivity.this.finish();
//                            myProduct = products.get(getRandomNum(products.size()));//取出其中一条 - 暂时处理方式
                            Intent intent = new Intent();
                            intent.putExtra("products",(Serializable) products);
                            intent.putExtra("CATE",SHOP_MAIN);
//                            intent.putExtra("shopid",myProduct.getShopId());
//                            intent.putExtra("name",myProduct.getName());
//                            intent.putExtra("price", myProduct.getPrice());
//                            intent.putExtra("imgsrc",myProduct.getPicPath());
//                            intent.putExtra("specId",myProduct.getSpecId());
                            intent.setClass(PaiYiPaiAdDialogActivity.this, PaiYiPaiProductActivity.class);
                            startActivity(intent);
                        }else {
                            noDatas(PRODUCT);
                        }
                    }else {
                        noDatas(PRODUCT);
                    }
                }else {
                    noDatas(PRODUCT);
                }
            }
        });
    }

    private void noDatas(int paiType) {
        ReRandom(paiType);//没有可拍商品，再随机就不随商品了
        if (isTypeSetted) {
            switch (paiType){
                case PRODUCT:
                    switch (category){
                        case 1:
                            Toast.makeText(PaiYiPaiAdDialogActivity.this, "商家没有可拍商品哦~", Toast.LENGTH_SHORT).show();
                            break;
                        case 2:
                            Toast.makeText(PaiYiPaiAdDialogActivity.this, "商家没有可拍菜品哦~", Toast.LENGTH_SHORT).show();
                            break;
                        case 3:
                            Toast.makeText(PaiYiPaiAdDialogActivity.this, "商家没有可拍服务哦~", Toast.LENGTH_SHORT).show();
                            break;
                        default:
                            break;
                    }
                    break;
                case ADVERT:
                    Toast.makeText(PaiYiPaiAdDialogActivity.this, "商家没有可拍优惠哦~", Toast.LENGTH_SHORT).show();
                    break;
                case ANNOUNCE:
                    Toast.makeText(PaiYiPaiAdDialogActivity.this, "商家暂无公告哦~", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }
    }

    private ArrayList<CookBook> cookBooks = new ArrayList<CookBook>();
    private CookBook myCookbook = new CookBook();
    private void request_foods_paiyipai() {
        IntrestBuyNet.findCookBook(20, 1, shopId, 1, 0,new HandleSuccess<FoodBean>() {
            @Override
            public void success(FoodBean s) {
                if (s.getStatus() == 1){
                    List<FoodBean.DataBean> cookBookList = s.getData();
                    if (cookBookList.size() > 0){
                        for (int i = 0;i < cookBookList.size();i++) {
                            FoodBean.DataBean item = cookBookList.get(i);
                            Log.i("test","第"+i+"条 "+item.getName()+item.getStatus());
                            CookBook food = new CookBook();
                            food.setSignName("");
                            food.setShopId(item.getBussId());
                            food.setId(item.getId());
                            food.setName(item.getName());
                            food.setMaining(item.getMaining());
                            food.setAccessories(item.getAccessories());
                            food.setImgpath(item.getImgpath());
                            food.setPrice(item.getPrice());
                            cookBooks.add(food);
                        }
                        if (cookBooks.size()>0) {
                            PaiYiPaiAdDialogActivity.this.finish();
//                            myCookbook = cookBooks.get(getRandomNum(cookBooks.size()));//取出其中一条 - 暂时处理方式
                            Intent intent = new Intent();
                            intent.putExtra("foods",(Serializable) cookBooks);
                            intent.putExtra("CATE",FOOD_MAIN);
//                            intent.putExtra("id",myCookbook.getId());
//                            intent.putExtra("shopid",myCookbook.getShopId());
//                            intent.putExtra("name",myCookbook.getName());
//                            intent.putExtra("maining",myCookbook.getMaining());
//                            intent.putExtra("accessories",myCookbook.getAccessories());
//                            intent.putExtra("imgpath",myCookbook.getImgpath());
//                            intent.putExtra("price",myCookbook.getPrice());
                            intent.setClass(PaiYiPaiAdDialogActivity.this,PaiYiPaiProductActivity.class);
                            startActivity(intent);
                        }else {
                            noDatas(PRODUCT);
                        }
                    }else {//查拍一拍服务项
                        noDatas(PRODUCT);
                    }
                }else {
                    noDatas(PRODUCT);
                }
            }
        });
    }
    private ArrayList<Product> services = new ArrayList<Product>();
    private Product myService = new Product();
    private void request_services_paiyipai() {
        noDatas(PRODUCT);
    }
}
