package com.linli.consumer.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.linli.consumer.R;
import com.linli.consumer.bean.CouponsBean;
import com.linli.consumer.bean.FoodRecipeBean;
import com.linli.consumer.bean.Generic;
import com.linli.consumer.bean.GoodsBean;
import com.linli.consumer.bean.GoodsDetailBean;
import com.linli.consumer.bean.ServiceGoodBean;
import com.linli.consumer.common.Common;
import com.linli.consumer.common.HandleSuccess;
import com.linli.consumer.dao.DBUtil;
import com.linli.consumer.domain.City;
import com.linli.consumer.domain.Order;
import com.linli.consumer.net.IntrestBuyNet;
//import com.linli.consumer.ui.load_h5.ParentH5NewActivity;
import com.linli.consumer.ui.cybercafe.MainCafeActivity;
import com.linli.consumer.ui.main.CheckVersionActivity;
import com.linli.consumer.ui.main.ReturnMoneyActivity;
import com.linli.consumer.ui.main.ScanningBuyActivity;
import com.linli.consumer.ui.main.SearchResultsActivity;
import com.linli.consumer.ui.main.WeChatBindPhoneActivity;
import com.linli.consumer.ui.shop_v2.GoodsMoreV2Activity;
import com.linli.consumer.ui.shop_v2.ShopDirectActivity;
import com.linli.consumer.ui.shop_v2.ShopDirectSearchActivity;
import com.linli.consumer.ui.shop_v2.ShopSortV2Activity;
import com.linli.consumer.ui.takecar.EvaluateDriverActivity;
import com.linli.consumer.ui.directbusiness.RedBagDialogActivity;
import com.linli.consumer.ui.directbusiness.ShopCartDirectActivity;
import com.linli.consumer.ui.directbusiness.ShopDetailDirectV2Activity;
import com.linli.consumer.ui.guide.GuideActivity;
import com.linli.consumer.ui.main.AddressManageActivity;
import com.linli.consumer.ui.main.BrowserAdverActivity;
import com.linli.consumer.ui.main.ConnectToSellerActivity;
import com.linli.consumer.ui.main.LoginYZXActivity;
import com.linli.consumer.ui.main.MyCouponActivity;
import com.linli.consumer.ui.main.OnlinePayMethodActivity;
import com.linli.consumer.ui.main.OrdersActivity;
import com.linli.consumer.ui.main.PaiYiPaiCartActivity;
import com.linli.consumer.ui.main.PaySuccessActivity;
import com.linli.consumer.ui.main.SearchActivity;
import com.linli.consumer.ui.main.SongXiaobaoDownloadActivity;
import com.linli.consumer.ui.main.WeChatShareActivity;
import com.linli.consumer.ui.main.YZXIndexActivity;
import com.linli.consumer.ui.main.ZoomableImageActivity;
import com.linli.consumer.ui.cookbook.OrderHealthWayViewActivity;
import com.linli.consumer.ui.services.ServiceGoodDetailActivity;
import com.linli.consumer.ui.services.SubmitOrderServicesActivity;
import com.linli.consumer.ui.shop_v2.CashCouponActivity;
import com.linli.consumer.ui.shop_v2.CashCouponRedBagActivity;
import com.linli.consumer.ui.shop_v2.FoodDetailActivity;
import com.linli.consumer.ui.shop_v2.FoodRecipeActivity;
import com.linli.consumer.ui.shop_v2.FoodRecipeSearchActivity;
import com.linli.consumer.ui.shop_v2.ImageActivity;
import com.linli.consumer.ui.shop_v2.MyCouponRedBagActivity;
import com.linli.consumer.ui.shop_v2.ShopActivity;
import com.linli.consumer.ui.shop_v2.ShopCartActivity;
import com.linli.consumer.ui.shop_v2.ShopDetailActivity;
import com.linli.consumer.ui.shop_v2.ShopDetailSelfActivity;
import com.linli.consumer.ui.shop_v2.ShopFoodOrderActivity;
import com.linli.consumer.ui.shop_v2.WidgetActivity;
import com.linli.consumer.ui.shop_v2.shopdetail.GoodsDetailActivity;
import com.linli.consumer.ui.takecar.ListTakeCarNeedActivity;
import com.linli.consumer.ui.takecar.TakeCarIndexActivity;
import com.linli.consumer.ui.takecar.TakeCarVoiceNeedActivity;
import com.linli.consumer.ui.takecar.TakeCarWaitDriverAcceptActivity;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;


import java.io.Serializable;
import java.util.List;

import static android.R.attr.type;
import static com.linli.consumer.widget.ShopSortLayout.FOOD_MAIN;

/**
 * Created by tomoyo on 2016/11/24.
 */

public class UIHelper {
    /**
     * 去微信绑定手机号界面
     * @param
     * */
    public static void togoBindPhoneActivity(Context context,String openId){
        Intent intent = new Intent(context, WeChatBindPhoneActivity.class);
        intent.putExtra("openId",openId);
//        intent.putExtra("isNewAccount",isNewAccount);
        context.startActivity(intent);
    }
    /**
     * 去扫码页面
     * @param type 1扫店铺码
     */
    public static void togoScanningBuyActivity(Context context,int type){
        Intent intent = new Intent(context, ScanningBuyActivity.class);
        intent.putExtra("type",type);
        context.startActivity(intent);
    }

    public static void togoShopActivity(Context context,int type){
        Intent intent = new Intent(context, ShopActivity.class);
        intent.putExtra("Sort",type);
        context.startActivity(intent);
    }
    public static void togoSearchResultActivity(Context context,int searchType,String searchText){
        Intent intent = new Intent(context,SearchResultsActivity.class);
        intent.putExtra("searchType",searchType);
        intent.putExtra("searchText",searchText);
        context.startActivity(intent);
    }
    public static void togoDirectShopActivity(Context context){
        Intent intent = new Intent(context, ShopDirectActivity.class);
        context.startActivity(intent);
    }
    public static void togoDirectSearchActivity(Context context){
        Intent intent = new Intent(context, ShopDirectSearchActivity.class);
        context.startActivity(intent);
    }
    public static void togoShopOrderActivity(Context context){
        Intent intent = new Intent(context, ShopFoodOrderActivity.class);
        context.startActivity(intent);
    }
    public static void togoOrdersActivity(Context context){
        Intent intent = new Intent(context, OrdersActivity.class);
        context.startActivity(intent);
    }
    /**
     * 由主界面跳转到店铺内
     * @param shopId 店铺id
     * */
    public static void togoShopDetailActivity(Context context,long shopId,String shopName,int type){
        Intent intent = new Intent(context, ShopDetailActivity.class);
        intent.putExtra("shopId",shopId);
        intent.putExtra("shopName",shopName);
        intent.putExtra("Sort",type);
        context.startActivity(intent);
    }
    /**
     * 由主界面跳转到店铺内
     * @param shopId 店铺id
     * */
    public static void togoShopDirectDetailActivity(Context context,long shopId,String shopName,int type){
        Intent intent = new Intent(context, ShopDetailDirectV2Activity.class);
        intent.putExtra("shopId",shopId);
        intent.putExtra("shopName",shopName);
        intent.putExtra("Sort",type);
        context.startActivity(intent);
    }
    /**
     * 由主界面跳转到店铺内(带着桌号)
     * @param shopId 店铺id
     * */
    public static void togoShopDetailActivityWithRoom(Context context,long shopId,String shopName,int type,String room){
        Intent intent = new Intent(context, ShopDetailActivity.class);
        intent.putExtra("shopId",shopId);
        intent.putExtra("shopName",shopName);
        intent.putExtra("Sort",type);
        intent.putExtra("room",room);
        context.startActivity(intent);
    }

    /**
     * 由购物车跳转到店铺内
     * @param shopId 店铺id
     * */
    public static void togoShopDetailActivityFromCart(Context context,long shopId,String shopName,int type
            ){
        Intent intent = new Intent(context, ShopDetailActivity.class);
        intent.putExtra("shopId",shopId);
        intent.putExtra("shopName",shopName);
        intent.putExtra("Sort",type);
        intent.putExtra("isFromCart",true);
        context.startActivity(intent);
    }
    /**
     * 总购物车
     * */
    public static void togoCartActivity(Context context){
        Intent intent = new Intent(context, ShopCartActivity.class);
        intent.putExtra("Sort",type);
        context.startActivity(intent);
    }

    /**
     * 跳转到店铺自身详情
     * @param storeId 店铺id
     * @param type 店铺类型
     * */
    public static void togoShopSelfActivity(Context context,long storeId,int type){
        Intent intent = new Intent(context, ShopDetailSelfActivity.class);
        intent.putExtra("Sort",storeId);
        intent.putExtra("type",type);
        context.startActivity(intent);
    }

    /**
     * 搜索
     * */
    public static void togoSearchActivity(Context context,int type){
        Intent intent = new Intent(context, SearchActivity.class);
        intent.putExtra("searchtype",type);
        context.startActivity(intent);
    }

    /**
     * 跳转到商品详情页
     * */
    public static void togoGoodsDetailActivity(Context context,long goodsId,String shopName){
        Intent intent = new Intent(context, GoodsDetailActivity.class);
        intent.putExtra("goodsId",goodsId);
        intent.putExtra("shopName",shopName);
        context.startActivity(intent);
    }

    /**
     * 跳转到商品规格选择界面，此处使用activity实现
     * */
    public static int GOODS_SPEC_REQUEST_CODE = 1717;
    /**
     * @param isIns 这里判断是否是从立即购买还是从加入购物车里来的  0:加入购物车    1:立即购买
     * */
    public static void togoGoodsSpecActivity(Context context, GoodsDetailBean goodsDetailBean, int isIns){
        Intent intent = new Intent(context, WidgetActivity.class);
        intent.putExtra("GoodsDetail",goodsDetailBean);
        intent.putExtra("isIns",isIns);
        ((Activity)context).startActivityForResult(intent,GOODS_SPEC_REQUEST_CODE);
    }

    /**
     * 美食详情页
     * {@link com.linli.consumer.adapter.shop_v2.ShopDetailGoodsAdapter}
     * */
    public static void togoFoodDetailActivity(Context context,long shoreId,String shopName,long titleId, long foodId){
        Intent intent = new Intent(context,FoodDetailActivity.class);
        intent.putExtra("shopName",shopName);
        intent.putExtra("shoreId",shoreId);
        intent.putExtra("titleId",titleId);
        intent.putExtra("foodId",foodId);

        context.startActivity(intent);
    }

    /**
     * 跳转到地址管理，true获取地址  FALSE修改地址
     * */
    public static void togoAddressManageActivity(Context context){
        Intent intent = new Intent(context, AddressManageActivity.class);
        intent.putExtra("choose",true);
        ((Activity)context).startActivityForResult(intent,1002);
    }

    /**
     * 订单界面
     * */
    public static void togoShopFoodOrderActivity(final Context context, final long goodsId, final long shopId, final int type, final long specId, final String room){
        if (type == FOOD_MAIN){
            DBUtil dbUtil = DBUtil.getInstance(context);;
            List<GoodsBean> list = dbUtil.queryByShopId(shopId);        //这里是从店铺进来的
            if(list != null && list.size() != 0){
                JSONArray json = new JSONArray();
                for (int i = 0;i<list.size();i++){
                    JSONObject item = new JSONObject();
                    try {
                        item.put("id",list.get(i).getGoodId());
                        item.put("num",list.get(i).getNumber());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    json.add(item);
                }
                Log.i("test",json.toString());
                IntrestBuyNet.findPackageFee(json.toString(), new HandleSuccess<Generic>() {
                    @Override
                    public void success(Generic s) {
                        if (s.getStatus() == 1 && s.getData() != null){
                            togoShopFoodOrderActivity(context, goodsId, shopId, type, specId,0L,room, (Double) s.getData());
                        }
                    }
                });
            }
        }else {

        /*Intent intent = new Intent(context,ShopFoodOrderActivity.class);
        intent.putExtra("goodsId",goodsId);
        intent.putExtra("shopId",shopId);
        intent.putExtra("Sort",type);
        intent.putExtra("specId",specId);*/
            togoShopFoodOrderActivity(context, goodsId, shopId, type, specId, 0L, room,0d);
            //context.startActivity(intent);
        }
    }

    /**
     * 订单界面
     * @param voiceId 趣购调用的购物接口
     * */
    public static void togoShopFoodOrderActivity(Context context,long goodsId,long shopId,int type,long specId,Long voiceId,String room,double packagePrice){
        Intent intent = new Intent(context,ShopFoodOrderActivity.class);
        intent.putExtra("goodsId",goodsId);
        intent.putExtra("shopId",shopId);
        intent.putExtra("Sort",type);
        intent.putExtra("specId",specId);
        intent.putExtra("voiceId",voiceId);
        intent.putExtra("packagePrice",packagePrice);
        if (room != null && !"null".equals(room)){
            intent.putExtra("room",room);
        }
        context.startActivity(intent);
    }

    /**
     * 跳转到支付
     * @param orderid orderSn
     * @param totalprice 总价
     * @param orderno orderSn 一般无用但是充值时此字段可判断是不是在充值 因为充值不可以用钱包里的钱充值
     * @param balancePayId 余额支付需要用的ID
     * */
    public static void togoOnLinePayActivity(Context context,long orderid,double totalprice,String orderno,Long balancePayId){
        Intent intent = new Intent(context, OnlinePayMethodActivity.class);
        intent.putExtra("orderid",orderid);
        intent.putExtra("totalprice",totalprice);
        intent.putExtra("orderno",orderno);
        intent.putExtra("balancePayId",balancePayId);
        ((Activity)context).startActivityForResult(intent,1009);
    }

    /**
     * 跳转到登录界面
     * */
    public static void togoLoginActivity(Context context){
        Intent intent = new Intent(context, LoginYZXActivity.class);
        context.startActivity(intent);
    }

    /**
     * 在下订单时，如果用户是选择线下支付，则直接跳转到支付成功界面
     * */
    public static void togoPaySuccessActivity(Context context ){
        Intent intent = new Intent(context, PaySuccessActivity.class);
        intent.putExtra("isOffline",true);
        context.startActivity(intent);
    }
    /**
     * 在下订单时，如果用户是选择线下支付，还是预约的订单，则直接跳转到支付成功界面
     * */
    public static void togoOrderSuccessServiceActivity(Context context ){
        Intent intent = new Intent(context, PaySuccessActivity.class);
        intent.putExtra("isOffline",true);
        intent.putExtra("isOrder",true);
        context.startActivity(intent);
    }

    /**
     * 跳转到食靠谱详情
     * @param dataBean 食靠谱的详情
     * */
    public static void togoFoodRecipeActivity(Context context, FoodRecipeBean.DataBean dataBean){
        Intent intent = new Intent(context, FoodRecipeActivity.class);
        intent.putExtra("data",dataBean);
        context.startActivity(intent);
    }

    /**
     * 跳转到食靠谱搜索界面
     * */
    public static void togoFoodRecipeSearchActivity(Context context){
        Intent intent = new Intent(context, FoodRecipeSearchActivity.class);
        context.startActivity(intent);
    }

    /**
     * 跳转到优惠券
     * */
    public static void togoMyCouponActivity(Context context){
        Intent intent = new Intent(context,MyCouponActivity.class);
        context.startActivity(intent);
    }

    /**
     * 跳转到养身膳
     * */
    public static void togoHealth(Context context){
        Intent intent = new Intent(context, OrderHealthWayViewActivity.class);
        context.startActivity(intent);
    }

    /**
     * 跳转到引导页
     * */
    public static void togoGuideActivity(Context context){
        Intent intent = new Intent(context, GuideActivity.class);
        context.startActivity(intent);
    }
    /**
     * 跳转到主界面
     * */
    public static void togoYZXIndexActivity(Context context){
//        Intent intent = new Intent(context, YZXIndexActivity.class);
        Intent intent = new Intent(context, MainCafeActivity.class);
        context.startActivity(intent);
    }

    /**
     * 跳转到查看图片详情
     * */
    public static void togoPicDetail(Context context,String imagePath){
        Intent intent = new Intent(context, ImageActivity.class);
        intent.putExtra("imagePath",imagePath);
        context.startActivity(intent);
    }
    /**
     * 跳转到查看图片详情
     * */
    public static void togoPaiYiPaiCart(Context context,List<GoodsBean> goods){
        Intent intent = new Intent(context, PaiYiPaiCartActivity.class);
        intent.putExtra("goods",(Serializable) goods);
        context.startActivity(intent);
    }
    /**
     * 跳转到服务项目详情
     * */
    public static void togoServiceGoodDetail(Context context,ServiceGoodBean.DataBean service){
        Intent intent = new Intent(context, ServiceGoodDetailActivity.class);
        intent.putExtra("service",(Serializable) service);
        context.startActivity(intent);
    }
    /**
     * 跳转到预约服务项目详情
     * */
    public static void togoServiceOrderActivity(Context context,ServiceGoodBean.DataBean service){
        Intent intent = new Intent(context, SubmitOrderServicesActivity.class);
        intent.putExtra("service",(Serializable) service);
        context.startActivity(intent);
    }
    /**
     * 打电话
     */
    public static void callThePhoneNumber(Context context,String phone){
        Intent itcall = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+phone));
        context.startActivity(itcall);
    }
    /**
     * 跳转浏览器
     */
    public static void callMyBrowser(Context context,String url){
        Intent intent= new Intent(context, BrowserAdverActivity.class);
        intent.putExtra("url",url);
        context.startActivity(intent);
    }
    /**
     * 跳转选择分享页面
     */
    public static void togoShareChoices(Context context){
        Intent intent= new Intent(context, WeChatShareActivity.class);
        context.startActivity(intent);
    }
    /**
     * 微信分享网页 type 1 朋友     type 2 朋友圈
     */
    public static void callWXShareWebPage(Context context,String url,int type){
        IWXAPI api = WXAPIFactory.createWXAPI(context,Common.APP_ID);
        WXWebpageObject webpage = new WXWebpageObject();
        webpage.webpageUrl = url;
        WXMediaMessage msg = new WXMediaMessage(webpage);
        msg.title = "邀请您使用邻里易购";
        msg.description = "\"以满足消费者需求为中心，商家匹配相应服务\"的全新购物体验>>";
        Bitmap thumb = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_logo);
        msg.setThumbImage(thumb);
        thumb.recycle();
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = String.valueOf(System.currentTimeMillis());
        req.message = msg;
        switch (type){
            case 1:
                req.scene = SendMessageToWX.Req.WXSceneSession;
                break;
            case 2:
                req.scene = SendMessageToWX.Req.WXSceneTimeline;
                break;
        }
        api.sendReq(req);
    }

    /**
     * 大图展示页面
     */
    public static void showThePicClearly(Context context,String url){
        Intent intent = new Intent(context, ZoomableImageActivity.class);
        intent.putExtra("url",url);
        context.startActivity(intent);
    }
    /**
     * l联系商家页面
     */
    public static void connectSeller(Context context,String phoneNum,String shopUserId,String shopName){
        Intent intent = new Intent(context, ConnectToSellerActivity.class);
        intent.putExtra("phoneNum",phoneNum);
        intent.putExtra("shopUserId",shopUserId);
        intent.putExtra("shopName",shopName);
        context.startActivity(intent);
    }
    /**
     * 调用第三方导航
     * 调用逻辑为首先尝试百度地图导航，异常后尝试调用高德地图导航，再次异常后提示用户“无法导航，未安装百度或高德地图”
     */
    public static void goToNavigationApps(Context context, String s_lat, String s_lon, String e_lat, String e_lon){
        String destinationInfoStart = "baidumap://map/direction?";//先用百度导航
        String destinationInfoEnd = "&mode=walking";
        if (s_lat != null && s_lon != null && e_lat != null && e_lon != null){
            String destinationInfoMain = "origin="+s_lat+","+s_lon+"&destination="+e_lat+","+e_lon;
            Intent iBaiDu = new Intent();
            iBaiDu.setData(Uri.parse(destinationInfoStart+destinationInfoMain+destinationInfoEnd));
            try {
                context.startActivity(iBaiDu);
            }catch (Exception e1){//尝试用高德导航
                Log.i("test","未装百度地图");
                Log.i("test","正在尝试高德地图");
                StringBuffer stringBuffer  = new StringBuffer("androidamap://navi?sourceApplication=")
                        .append("指令");
                stringBuffer.append("&lat=").append(e_lat)
                        .append("&lon=").append(e_lon)
                        .append("&dev=").append("1")
                        .append("&style=").append("2");
                Intent iGaoDe = new Intent("android.intent.action.VIEW", Uri.parse(stringBuffer.toString()));
                iGaoDe.setPackage("com.autonavi.minimap");
                try {
                    context.startActivity(iGaoDe);
                }catch (Exception e2){
                    Toast.makeText(context,"无法导航，未安装百度或高德地图",Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    /**
     * 去选择代金券优惠券界面
     * @param
     * */
    public static void togoCashCouponActivity(Context context, List<CouponsBean.DataBean> ccps){
        Intent intent = new Intent(context, CashCouponActivity.class);//现金券弹窗页
        intent.putExtra("ccps",(Serializable) ccps);
        ((Activity)context).startActivityForResult(intent,1718);
    }
    /**
     * 去选择红包优惠券界面
     * @param
     * */
    public static void togoRedBagCouponActivity(Context context, List<CouponsBean.DataBean> rcps){
        Intent intent = new Intent(context, CashCouponRedBagActivity.class);//现金券弹窗页
        intent.putExtra("rcps",(Serializable) rcps);
        ((Activity)context).startActivityForResult(intent,1719);
    }
    /**
     * 到直营购物车界面
     * */
    public static void togoDirectShopCartActivity(Context context){
        Intent intent = new Intent(context, ShopCartDirectActivity.class);
        context.startActivity(intent);
    }
    /**
     * 去选择红包列表界面
     * @param
     * */
    public static void togoRedBagListActivity(Context context){
        Intent intent = new Intent(context, MyCouponRedBagActivity.class);
        context.startActivity(intent);
    }
    /**
     * 去红包推送弹窗界面
     * @param type 1新用户  0老用户
     * */
    public static void togoRedBagDialogActivity(Context context,String count,int type){
        Intent intent = new Intent(context, RedBagDialogActivity.class);//获得红包弹窗页
        intent.putExtra("count",count);
        intent.putExtra("type",type);
        context.startActivity(intent);
    }
    /**
     * 跳转送小宝
     */
    public static void callSongXiaoBao(Context context){
        // 通过包名获取要跳转的app，创建intent对象
        Intent intent = context.getPackageManager().getLaunchIntentForPackage("com.d1031061138.rwy");
// 这里如果intent为空，就说名没有安装要跳转的应用嘛
        if (intent != null) {
            // 这里跟Activity传递参数一样的嘛，不要担心怎么传递参数，还有接收参数也是跟Activity和Activity传参数一样
            context.startActivity(intent);
        } else {
            // 没有安装要跳转的app应用，提醒一下
            Intent intentDownload = new Intent(context, SongXiaobaoDownloadActivity.class);//下载送小宝页
            context.startActivity(intentDownload);
        }
    }
    /**
     * 去出行首界面
     * @param
     * */
    public static void togoTakeCarIndexActivity(Context context){
        Intent intent = new Intent(context, TakeCarIndexActivity.class);
        context.startActivity(intent);
    }
    /**
     * 去出行记录页面
     * @param
     * */
    public static void togoMyTakeTaxiList(Context context){
        Intent intent = new Intent(context, ListTakeCarNeedActivity.class);
        context.startActivity(intent);
    }
    /**
     * 去等待司机接单页面（主动）
     * @param
     * */
    public static void togoTakeCarWaitDriverAccept(Context context){
        Intent intent = new Intent(context, TakeCarWaitDriverAcceptActivity.class);
        context.startActivity(intent);
    }
    /**
     * 去等待司机接单页面（主动带参数）
     * @param
     * @param id*/
    public static void togoTakeCarWaitDriverAcceptWithParam(Context context, Long id){
        Intent intent = new Intent(context, TakeCarWaitDriverAcceptActivity.class);
        intent.putExtra("id",id);
        context.startActivity(intent);
    }
    /**
     * 去等待司机接单页面(被动)
     * @param
     * */
    public static void togoTakeCarWaitDriverAcceptPassive(Context context){
        boolean isExist = false;
        for(int i = 0 ; i < ActivityCollector.activities.size() ; i ++){
            if(ActivityCollector.activities.get(i) instanceof TakeCarWaitDriverAcceptActivity){//此页面存在
                isExist = true;
            }
        }
        if (!isExist){
            togoTakeCarWaitDriverAccept(context);
        }
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                togoTakeCarWaitDriverAccept(context);
//            }
//        }, 500);
    }
    /**
     * 去评价司机页面(被动触发)
     * @param
     * */
    public static void togoEvaluateDriver(Context context){
        for(int i = 0 ; i < ActivityCollector.activities.size() ; i ++){
            if(ActivityCollector.activities.get(i) instanceof TakeCarWaitDriverAcceptActivity){
                ActivityCollector.activities.get(i).finish();
            }
        }
        Intent intent = new Intent(context, EvaluateDriverActivity.class);
        context.startActivity(intent);
    }
    /**
     * 司机取消订单(被动触发)
     * @param
     * */
    public static void cancelTravel(Context context){
        for(int i = 0 ; i < ActivityCollector.activities.size() ; i ++){
            if(ActivityCollector.activities.get(i) instanceof TakeCarWaitDriverAcceptActivity){
                ActivityCollector.activities.get(i).finish();
            }
        }
        Toast.makeText(context,"司机已主动取消行程",Toast.LENGTH_LONG).show();
    }
    /**
     * 去语音发送乘车需求界面
     * @param
     * @param startPoint*/
    public static void togoVoiceTakeCarNeedActivity(Context context, City startPoint){
        Intent intent = new Intent(context, TakeCarVoiceNeedActivity.class);
        intent.putExtra("startPoint",(Serializable) startPoint);
        context.startActivity(intent);
    }
    /**
     * 手动去更新页面执行APP更新操作
     * @param
     * @param */
    public static void togoCheckVersion(Context context, boolean update){
        Intent intent = new Intent(context,CheckVersionActivity.class);
        intent.putExtra("isneedupdate",update);
        context.startActivity(intent);
    }
    /**
     * 去退款页面
     * @param
     * @param order */
    public static void togoReturnMoney(Context context, Order order){
        Intent intent = new Intent(context,ReturnMoneyActivity.class);
        intent.putExtra("order",order);
        context.startActivity(intent);
    }
    /**
     * 去加载H5页面的父页面
     * @param
     * */
//    public static void togoH5ParentActivity(Context context){
//        Intent intent = new Intent(context, ParentH5NewActivity.class);
//        context.startActivity(intent);
//    }
    /**
     * 除了首页关闭所有页面
     * @param
     * */
    public static void finishAllActivityExceptIndex(){
        for(int i = 0 ; i < ActivityCollector.activities.size() ; i ++){
            if(!(ActivityCollector.activities.get(i) instanceof MainCafeActivity)){
                ActivityCollector.activities.get(i).finish();
            }
        }
    }
    /**
     * 去更多热卖或者精选商品列表-新写的
     * @param
     * */
    public static void togoMoreV2Activity(Context context,int sort){
        Intent intent = new Intent(context, ShopSortV2Activity.class);
        intent.putExtra("Sort",sort);
        context.startActivity(intent);
    }
    /**
     * 直营活动商品页面
     * @param  cate 1精品  2满减  3团购   4 0元购
     * */
    public static void togoGoodsMoreV2Activity(Context context,int cate){
        Intent intent = new Intent(context, GoodsMoreV2Activity.class);
        intent.putExtra("cate",cate);
        context.startActivity(intent);
    }
}
