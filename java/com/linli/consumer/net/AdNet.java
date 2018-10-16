package com.linli.consumer.net;

import android.app.Activity;
import android.widget.Toast;

import com.linli.consumer.bean.AdverPosition;
import com.linli.consumer.bean.AdvertisementList;
import com.linli.consumer.bean.AdvertisementListF;
import com.linli.consumer.bean.FindAreaInfo;
import com.linli.consumer.common.HandleSuccess;
import com.linli.consumer.domain.City;
import com.linli.consumer.domain.User;

import java.util.List;

import static com.linli.consumer.widget.ShopSortLayout.FOOD_MAIN;
import static com.linli.consumer.widget.ShopSortLayout.SHOP_MAIN;

/**
 * Created by tomoyo on 2017/1/16.
 * 广告的网络请求方法，包含三步
 * 1,请求区域码
 * 2,请求广告列表
 * 3,请求广告
 */


public class AdNet {

    /**
     * 调用此方法获取广告
     * @param activity 上下文环境
     * @param city 获取地理位置
     * @param user 请求参数，user 为空则传入0
     * @param type 美食和商城的区分
     * @param adInterface 实现的接口，最后数据回调于此
     * */
    public static void queryAd(final Activity activity, final City city, final User user, final int type,
                               final String name, final int pageNum,
                               final AdInterface adInterface){
        IntrestBuyNet.findcityList(city.getArea().replace("区", "").replace("县", ""), new HandleSuccess<FindAreaInfo>() {
            @Override
            public void success(FindAreaInfo s) {
                if (s.getStatus() == 1){
                    List<FindAreaInfo.DataBean> areas = s.getData();
                    if (areas != null && areas.size()>0){
                        request_adver_position(activity,areas.get(0).getId(),user,type,
                                name,pageNum,
                                adInterface);//想查广告第二步 再查广告位
                    }else {
//                        Toast.makeText(activity,"还没有商讯哦~",Toast.LENGTH_SHORT).show();
                    }
                }else {
//                    Toast.makeText(activity,"还没有商讯哦~",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



    private static void request_adver_position(final Activity activity, final int areaCode
            , final User user, final int type, final String name, final int pageNum,
                                               final AdInterface adInterface) {
        if(type == FOOD_MAIN){
            IntrestBuyNet.searchStandardFood(areaCode, new HandleSuccess<AdverPosition>() {
                @Override
                public void success(AdverPosition s) {
                    if (s.getStatus() == 1){
                        List<AdverPosition.DataBean> positions = s.getData();
                        if (positions != null && positions .size() > 0){
                            for (int i = 0;i<positions.size();i++){
                                String positionName = positions.get(i).getName();
                                if (positionName.contains(name)){
                                    request_advertisement_businessinfo(activity,areaCode,
                                            positions.get(i).getId(),user,type,pageNum,
                                            adInterface);
                                    break;
                                }
                            }
                        }else {
                            Toast.makeText(activity,"当前还没有商讯哦~",Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(activity,s.getMsg().toString(),Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else if(type == SHOP_MAIN){
            IntrestBuyNet.searchStandard(areaCode, new HandleSuccess<AdverPosition>() {
                @Override
                public void success(AdverPosition s) {
                    if (s.getStatus() == 1){
                        List<AdverPosition.DataBean> positions = s.getData();
                        if (positions != null && positions .size() > 0){
                            for (int i = 0;i<positions.size();i++){
                                String positionName = positions.get(i).getName();
                                if (positionName.contains(name)){
                                    request_advertisement_businessinfo(activity,areaCode,
                                            positions.get(i).getId(),user,type,pageNum,
                                            adInterface);
                                    break;
                                }
                            }
                        }else {
                            Toast.makeText(activity,"当前还没有商讯哦~",Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(activity,s.getMsg().toString(),Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }

    private static void request_advertisement_businessinfo(final Activity activity, int areaCode
            , Long positionId, User user, int type,int pageNum, final AdInterface adInterface) {
        String userId;
        if (user != null){
            userId = user.getId();
        }else{
            userId="0";
        }
        if(type == FOOD_MAIN){

            IntrestBuyNet.searchByParamFood(10,pageNum,5,positionId, userId, areaCode,new HandleSuccess<AdvertisementListF>() {
                @Override
                public void success(AdvertisementListF s) {
                    if (s.getStatus() == 1){
                        if (s.getData() != null && s.getData().size() > 0 ){
                            adInterface.onAdHandle(s);
                        }
                    }else {
                        Toast.makeText(activity,s.getMsg().toString(),Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else if(type == SHOP_MAIN){
            IntrestBuyNet.searchByParam(10,pageNum,5,positionId, userId, areaCode,new HandleSuccess<AdvertisementList>() {
                @Override
                public void success(AdvertisementList s) {
                    if (s.getStatus() == 1){
                        if (s.getData() != null && s.getData().size() > 0  ){
                            adInterface.onAdHandle(s);
                        }
                    }else {
                        Toast.makeText(activity,s.getMsg().toString(),Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }
    public interface AdInterface{
        void onAdHandle(Object advertisementList);
    }

}
