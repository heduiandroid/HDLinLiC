package com.linli.consumer.adapter.shop_v2;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.linli.consumer.R;
import com.linli.consumer.app.AppContext;
import com.linli.consumer.bean.CateIdAndNameBean;
import com.linli.consumer.bean.CouponsBean;
import com.linli.consumer.bean.Login;
import com.linli.consumer.common.HandleSuccess;
import com.linli.consumer.domain.City;
import com.linli.consumer.domain.User;
import com.linli.consumer.net.IntrestBuyNet;
import com.linli.consumer.ui.shop_v2.MyCouponRedBagActivity;
import com.linli.consumer.widget.XDefaultItemAnimator;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.linli.consumer.widget.ShopSortLayout.SHOP_FOOD_REDBAG;

/**
 * @author tomoyo on 08/07/16.
 * 商城分类主页的适配器
 * 适配多种分类
 */
public class CouponsSortAdapter extends PagerAdapter {


    //标题有关
    private Context context;
    private List<String> titleList = new ArrayList<>();
    private int type ;     //标题的大分类

    public List<String> titleStrings = new ArrayList<>();
    private AppContext appContext;
    private User user;
    private City city ;
    private Map<Integer,Integer> numMap = new HashMap<>();  //分页的map,key:position  value:num


    private static final int REFRESH = 88;
    private static final int LOADMORE = 99;
    private List<CateIdAndNameBean> mainCates;

    public CouponsSortAdapter(Context context, List<String> list, int type, List<CateIdAndNameBean> mainCates) {
        this.context = context;
        this.titleList = list;
        this.type = type;
        this.mainCates = mainCates;
        appContext = (AppContext)context.getApplicationContext();
        city = AppContext.getInstance().getCity();
        user = appContext.getUser();
        if (type == SHOP_FOOD_REDBAG){
            for (int i = 0;i < mainCates.size();i++){
                titleStrings.add(mainCates.get(i).getName());
            }
            for(int i = 0; i < titleStrings.size() ; i ++){
                numMap.put(i,1);
            }
        }

    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view ;
        view = LayoutInflater.from(context).inflate(R.layout.shop_sort_main_adapter_container, container, false);
        initView(view,position);
        if (view != null) {
            view.setTag(String.valueOf(position));
            container.addView(view);
        }
        return view;
    }

    @Override
    public int getCount() {
        if(type == SHOP_FOOD_REDBAG){
            return titleStrings.size();
        }
        else {
            return titleList.size();
        }

    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public CharSequence getPageTitle(int position) {
       if(type == SHOP_FOOD_REDBAG){
            return titleStrings.get(position);
        }
        else {
            return titleList.get(position);
        }

    }

    /**
     * 初始化
     * 包含视图初始化和数据初始化
     * 初始化根据出入的position
     * */
    private void initView(View view , final int position){

        final List<Object> list = new ArrayList<>();
        final XRecyclerView recyclerView = getRecyclerView(view);
        final RecyclerView.Adapter adapter = getAdapter(type,list);
        recyclerView.setAdapter(adapter);
        initData(position,adapter,list,type);
        recyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                moreData(position,REFRESH,recyclerView,adapter,list);
            }

            @Override
            public void onLoadMore() {
                moreData(position,LOADMORE,recyclerView,adapter,list);
            }
        });
    }

    /**
     * 第一次调用初始化数据
     * 初始化数据根据传入的位置，再查找出标题
     * @param position 位置参数
     * */
    private void initData(final int position, final RecyclerView.Adapter adapter, final List<Object> list, int Type){
        if(city != null){
            switch (type){
                case SHOP_FOOD_REDBAG: //查询所有红包
                    redBagsAllData(position,adapter,list, null);
                    break;
            }
        } else {
            Toast.makeText(context,"请获取位置信息",Toast.LENGTH_SHORT).show();
        }

        //TODO 根据position获取key，再拼接参数

    }

    /**
     * 加载更多数据
     * @param position 通过位置参数获取数据
     * @param more 对于下拉刷新 还是 上拉加载的判断，
     *             上拉刷新将数据加载到list的前面
     *             下拉加载将数据加载到list的后面
     * @param recyclerView
     * @param adapter   刷新视图
     *
     * */
    private List<Object> moreData(final int position, final int more, final XRecyclerView recyclerView,
                                  final RecyclerView.Adapter adapter, final List<Object> list){
        //TODO 根据position获取key，再拼接参数
        switch (type){
            case SHOP_FOOD_REDBAG:
                if (more == REFRESH){//下拉刷新
                    redBagsAllDataMore(position,adapter,list,more,recyclerView);
                }else if (more == LOADMORE){
                    stopRefresh(adapter,recyclerView);
                }
                //暂不需要下滑加载更多
                break;
        }
        return list;
    }



    /**
     * 动态获取一个XRecyclerView
     * @param view  传入的根视图
     * */
    private XRecyclerView getRecyclerView(View view){
        XRecyclerView recyclerView = (XRecyclerView)view.findViewById(R.id.shop_sort_main_rc);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new XDefaultItemAnimator());
        return recyclerView;
    }

    /**
     * 动态获取一个适配器
     * @param type 用于判断是哪种类型的适配器
     * */
    private RecyclerView.Adapter getAdapter(int type,List<Object> list){
        RecyclerView.Adapter adapter = null;
        switch (type){
            case SHOP_FOOD_REDBAG:          //红包Adapter
                adapter = new RedBagsAllAdapter(context,list);
                break;
        }
        return adapter;
    }


    /**
     * 刷新完成，通知更新
     *
     * @param adapter   适配器
     * @param recyclerView   列表
     *
     * */
    private void stopRefresh(RecyclerView.Adapter adapter,XRecyclerView recyclerView){
        recyclerView.refreshComplete();
        recyclerView.loadMoreComplete();
        adapter.notifyDataSetChanged();
        ((MyCouponRedBagActivity)context).dismiss();
    }

    /**
     * 查询所有红包
     * @param position
     * @param adapter
     * @param list
     * @param more
     */
    private void redBagsAllData(final int position, final RecyclerView.Adapter adapter, final List<Object> list, final Integer more) {
        if(city != null ) {
            IntrestBuyNet.findUserInfo(user.getId(), new HandleSuccess<Login>() {
                @Override
                public void success(Login s) {
//                    if (s.getStatus() == 1 && s.getData() != null && s.getData().getRedpackageAccount() != null && s.getData().getRedpackageAccount() > 0d){
//                        CouponsBean.DataBean bean = new CouponsBean.DataBean();
//                        bean.setCouponSum(s.getData().getRedpackageAccount());
//                        bean.setStatus(1);
//                        list.add(bean);
//                        adapter.notifyDataSetChanged();
//                        perNum(position);
//                    }
                    ((MyCouponRedBagActivity)context).dismiss();
                }
            });
        } else {
            city = AppContext.getInstance().getCity();
        }
    }
    /**
     * 查询更多或刷新所有红包
     * @param position
     * @param adapter
     * @param list
     * @param more
     * @param recyclerView
     */
    private void redBagsAllDataMore(final int position, final RecyclerView.Adapter adapter, final List<Object> list, final Integer more, final XRecyclerView recyclerView) {
        if(city != null ) {
            IntrestBuyNet.findUserInfo(user.getId(), new HandleSuccess<Login>() {
                @Override
                public void success(Login s) {
//                    if (s.getStatus() == 1 && s.getData() != null && s.getData().getRedpackageAccount() != null && s.getData().getRedpackageAccount() > 0d){
//                        if (more != null && more == REFRESH){
//                            list.clear();
//                            numMap.put(position,1);
//                        }
//                        CouponsBean.DataBean bean = new CouponsBean.DataBean();
//                        bean.setCouponSum(s.getData().getRedpackageAccount());
//                        bean.setStatus(1);
//                        list.add(bean);
//                        perNum(position);
//                    }
                    stopRefresh(adapter,recyclerView);
                    ((MyCouponRedBagActivity)context).dismiss();
                }
            });
        } else {
            city = AppContext.getInstance().getCity();
        }
    }
    /**
     * 分页 +1 */
    private void perNum(int position){
        int num = numMap.get(position)+1;       //分页加1
        numMap.remove(position);
        numMap.put(position,num);
    }


}
