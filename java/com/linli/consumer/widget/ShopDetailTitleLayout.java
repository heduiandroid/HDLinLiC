package com.linli.consumer.widget;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.linli.consumer.R;
import com.linli.consumer.app.AppContext;
import com.linli.consumer.base.BaseActivity;
import com.linli.consumer.base.UIHelper;
import com.linli.consumer.bean.CollBean;
import com.linli.consumer.bean.ConcernedList;
import com.linli.consumer.bean.FoodShopInfoBean;
import com.linli.consumer.bean.GoodsShopInfoBean;
import com.linli.consumer.bean.LeaguerAdd;
import com.linli.consumer.bean.ServiceStoreBean;
import com.linli.consumer.bean.VipUpBean;
import com.linli.consumer.common.HandleSuccess;
import com.linli.consumer.domain.User;
import com.linli.consumer.net.FoodNet;
import com.linli.consumer.net.ShopNet;
import com.linli.consumer.utils.CommonUtil;

import java.util.List;

import static com.linli.consumer.widget.ShopSortLayout.FOOD_MAIN;
import static com.linli.consumer.widget.ShopSortLayout.SERVICE_MAIN;
import static com.linli.consumer.widget.ShopSortLayout.SHOP_MAIN;

/**
 * Created by tomoyo on 2016/12/1.
 */

public class ShopDetailTitleLayout extends LinearLayout implements View.OnClickListener{

    private ImageView back;     //返回图片
    private LinearLayout backLl;    //返回按钮
    private TextView name;      //title题目
    private ImageView vipIm;        //会员展示
    private ImageView detail;       //详情图片
    private ImageView titleBack;    //黑色背景
    private View lineV;         //分割线

    private Context context;

    private User user = AppContext.getInstance().getUser();     //用户
    private int type = 0;       //类型
    private long shopId = 0;     //店铺id
    private Long regionId = null;//店铺区域ID
    private boolean isDetailShowing = false;

    private int collStatus = 12;     //店铺收藏状态
                                //添加收藏 1收藏成功 ;2已经收藏
                                //查询收藏 11-已经收藏 12-没有收藏

    private CollSuccessDialog collSuccessDialog;
    private long conceredId = 0l;
    private int isVip = 11;     //是否是会员的状态  11:以前不是vip，现在也不是  12:以前是vip,现在不是了  13:是vip，以前现在都是



    public ShopDetailTitleLayout(Context context) {
        this(context,null);
    }

    public ShopDetailTitleLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.shop_detail_title_widget,this);
        this.context = context;
        initView();
        initCollection();
    }

    private void initView(){
        back = (ImageView)findViewById(R.id.shop_detail_title_back_im);     //返回图片
        backLl = (LinearLayout)findViewById(R.id.shop_detail_title_back_ll);
        name = (TextView)findViewById(R.id.shop_detail_title_name_tv);      //标题
        vipIm = (ImageView) findViewById(R.id.shop_self_title_vip_im);
        detail = (ImageView)findViewById(R.id.shop_detail_title_detail_im);         //详情图片
        titleBack = (ImageView)findViewById(R.id.shop_detail_title_background_im);  //背景图片
        lineV = (View)findViewById(R.id.shop_detail_title_line_v);      //分割线
        back.setOnClickListener(this);
        vipIm.setOnClickListener(this);
        detail.setOnClickListener(this);
        name.setOnClickListener(this);

        collSuccessDialog = new CollSuccessDialog(context);
    }

    /**
     * 设置基本参数
     * 在实例化控件后一定要调用
     *
     * @param nameStr 店铺名称
     * @param type 店铺类型 美食 && 商城
     * @param shopId 店铺id
     *
     * */
    public void setName(String nameStr,int type ,long shopId,boolean isDetailShowing){
        name.setText(nameStr);
        this.type = type;
        this.shopId = shopId;
        this.isDetailShowing = isDetailShowing;
        if (type == SERVICE_MAIN || shopId==999L){
            vipIm.setVisibility(INVISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.shop_detail_title_name_tv:
            case R.id.shop_detail_title_back_im:
                CommonUtil.scanForActivity(context).finish();
                //((FragmentActivity)context).finish();
                break;
            case R.id.shop_self_title_vip_im:
                onCollectionClick();
                break;
            case R.id.shop_detail_title_detail_im:
                UIHelper.togoShopSelfActivity(context,shopId,type);
                break;
        }
    }

    /**
     * 初始化关注按钮
     * 登录后改变状态
     * 暴露给外界Activity#onResume
     * */
    public void initCollection(){
        //先查商家区域ID备用
        switch (type){
            case FOOD_MAIN:
                FoodNet.foodShopInfo(shopId, new HandleSuccess<FoodShopInfoBean>() {    //获取区域码
                    @Override
                    public void success(FoodShopInfoBean foodShopInfoBean) {
                        if(foodShopInfoBean.getData() != null){
                            regionId = foodShopInfoBean.getData().getRegionId();
                        }
                    }
                });
                break;
            case SHOP_MAIN:
                ShopNet.shopInfo(shopId, new HandleSuccess<GoodsShopInfoBean>() {
                    @Override
                    public void success(GoodsShopInfoBean goodsShopInfoBean) {
                        if(goodsShopInfoBean.getData() != null){
                            regionId = goodsShopInfoBean.getData().getRegionId();
                        }
                    }
                });
                break;
            case SERVICE_MAIN:
                FoodNet.findServiceStoresInfos(shopId, new HandleSuccess<ServiceStoreBean>() {
                    @Override
                    public void success(ServiceStoreBean s) {
                        if(s.getData() != null){
                            regionId = s.getData().getRegionId();
                        }
                    }
                });
                break;
        }


        if (isDetailShowing){
            vipIm.setVisibility(GONE);
            titleBack.setBackgroundColor(getResources().getColor(R.color.white));
            back.setImageResource(R.mipmap.back);
            name.setTextColor(getResources().getColor(R.color.gray));
            detail.setImageResource(R.mipmap.ic_shop_detail_title_detail_black1);
        }else {
            if (user != null) {
                ShopNet.queryStoreMember(user.getId(), shopId, new HandleSuccess<ConcernedList>() {//////////////////////////////////////////////////////////////////////////
                    @Override
                    public void success(ConcernedList s) {
                        List<ConcernedList.DataBean> concernedShops = s.getData();
                        if (concernedShops != null && concernedShops.size() > 0){
                            for (int i = 0;i < concernedShops.size();i++){
                                long serverShopId = concernedShops.get(i).getStoreId();
                                if (serverShopId == shopId){
                                    conceredId = concernedShops.get(i).getId();//如果这个ID == shopId   isConcerned = true    break;
                                    if (concernedShops.get(i).getStatus() == 0){     //有关注
                                        vipIm.setImageResource(R.mipmap.icon_concerned);
                                        isVip = 13;
                                    }else if (concernedShops.get(i).getStatus() == 1){   //无关注，但是之前关注过
                                        isVip = 12;
                                    }
                                    break;
                                }
                            }
                        }else {       //无关注,之前也没有关注过
                            isVip = 11;
                        }
                    }
                });
            }
        }

    }

    /**
     * 收藏按钮的点击事件
     * */
    private void onCollectionClick(){
        if(user != null){
            ((BaseActivity) CommonUtil.scanForActivity(context)).proDialog.show();
            switch (isVip){
                case 11:
                    int sex = 3;//1男 0女  3未知
                    if (user.getSex() != null){
                        if (user.getSex().equals("男")){
                            sex = 1;
                        }else if (user.getSex().equals("女")){
                            sex = 0;
                        }
                    }
                    ShopNet.leaguerFor(shopId, user.getId(), user.getNickName()!=null?user.getNickName():user.getPhone()
                            , regionId, user.getPhone(), user.getHeadPath()!=null?user.getHeadPath():" ",user.getBirthday()!=null?user.getBirthday():" ",sex,new HandleSuccess<LeaguerAdd>() {
                                @Override
                                public void success(LeaguerAdd s) {
                                    if (s.getData() != null) {
                                        conceredId = s.getData().getId();
                                        isVip = 13;
                                        vipIm.setImageResource(R.mipmap.icon_concerned);
                                        ((BaseActivity) CommonUtil.scanForActivity(context)).proDialog.dismiss();
                                        showCollDialog();
                                    }
                                }
                            });
                    break;
                case 12:
                    ShopNet.updateStoreMember(conceredId, 0, new HandleSuccess<VipUpBean>() {
                        @Override
                        public void success(VipUpBean vipBean) {
                            isVip = 13;
                            vipIm.setImageResource(R.mipmap.icon_concerned);
                            ((BaseActivity) CommonUtil.scanForActivity(context)).proDialog.dismiss();
                            showCollDialog();
                        }
                    });
                    break;
                case 13:
                    ShopNet.updateStoreMember(conceredId, 1, new HandleSuccess<VipUpBean>() {
                        @Override
                        public void success(VipUpBean vipBean) {
                            isVip = 12;
                            vipIm.setImageResource(R.mipmap.icon_notconcern);
                            ((BaseActivity) CommonUtil.scanForActivity(context)).proDialog.dismiss();
                        }
                    });
                    break;
            }
        }else {
            UIHelper.togoLoginActivity(context);
        }
    }

    /**
     * 展示收藏成功的dialog，如果用户没有点击消失dialog，延迟后自动消失
     * */
    private void showCollDialog(){
        collSuccessDialog.show();
        if(collSuccessDialog.isShowing()){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    collSuccessDialog.dismiss();
                }
            },1000);
        }
    }

    /**
     * dialog消失
     * */
    private void dismissDialog(CollBean collBean){
        if(collBean.getStatus() == 1 ){
            ((BaseActivity) CommonUtil.scanForActivity(context)).proDialog.dismiss();
        }
    }

    /**
     * 在appbar变化时更新title状态
     * */
    public void changeTitle(AppBarStateChangeListener.State state){
        if(state == AppBarStateChangeListener.State.COLLAPSED){     //折叠
            back.setImageResource(R.mipmap.ic_shop_main_title_back_black);
            name.setTextColor(getResources().getColor(R.color.gray));
            if(collStatus == 11 ){
                vipIm.setImageResource(R.mipmap.icon_concerned);

            } else if(collStatus == 12){
                vipIm.setImageResource(R.mipmap.icon_notconcern);
            }
            detail.setImageResource(R.mipmap.ic_shop_detail_title_detail_black1);
            titleBack.setVisibility(INVISIBLE);
            lineV.setVisibility(VISIBLE);
        } else if(state == AppBarStateChangeListener.State.EXPANDED){   //展开
            back.setImageResource(R.mipmap.ic_shop_main_title_back_white);
            name.setTextColor(getResources().getColor(R.color.white));
            if(collStatus == 11 ){
                vipIm.setImageResource(R.mipmap.icon_concerned);

            } else if(collStatus == 12){
                vipIm.setImageResource(R.mipmap.icon_notconcern);
            }
            detail.setImageResource(R.mipmap.ic_shop_detail_title_detail_white1);
            titleBack.setVisibility(VISIBLE);
            lineV.setVisibility(GONE);
        } else if(state == AppBarStateChangeListener.State.IDLE){
            back.setImageResource(R.mipmap.ic_shop_main_title_back_black);
            name.setTextColor(getResources().getColor(R.color.gray));
            if(collStatus == 11 ){
                vipIm.setImageResource(R.mipmap.icon_concerned);

            } else if(collStatus == 12){
                vipIm.setImageResource(R.mipmap.icon_notconcern);
            }
            detail.setImageResource(R.mipmap.ic_shop_detail_title_detail_black1);
            titleBack.setVisibility(INVISIBLE);
            lineV.setVisibility(GONE);
        }
    }
}
