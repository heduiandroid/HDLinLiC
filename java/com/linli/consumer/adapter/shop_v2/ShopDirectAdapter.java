package com.linli.consumer.adapter.shop_v2;

import android.content.Context;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.linli.consumer.R;
import com.linli.consumer.adapter.HorizontalGoodsDirectAdapter;
import com.linli.consumer.adapter.HorizontalGoodsVerAdapter;
import com.linli.consumer.base.UIHelper;
import com.linli.consumer.bean.DirectGoodBean;
import com.linli.consumer.myview.MyListView;
import com.linli.consumer.utils.CommonUtil;

import java.util.ArrayList;
import java.util.List;

import static com.linli.consumer.widget.ShopSortV2Layout.SHOP_MORE_HOT_SALE;

/**
 * Created by Administrator on 2016/8/9.
 */
public class ShopDirectAdapter extends Adapter<ViewHolder> {

    private Context context;
    private List<Object> data;

    private static final int TYPE_HOR = 0;
    private static final int TYPE_VER = 1;

    private int type;
    private HorizontalGoodsDirectAdapter adapter;
    private HorizontalGoodsVerAdapter adapterVer;





    public ShopDirectAdapter(Context context, List<Object> list, int type){
        this.context = context;
        this.data = list;
        this.type = type;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view ;
        ViewHolder holder = null;
        switch (viewType){
            case TYPE_HOR:
                view = LayoutInflater.from(context).inflate(R.layout.goods_main_item_hor,parent,false);
                holder = new GoodsViewHolderHor(view);
                break;
            case TYPE_VER:
                view = LayoutInflater.from(context).inflate(R.layout.goods_main_item_ver,parent,false);
                holder = new GoodsViewHolderVer(view);
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        if (type ==  SHOP_MORE_HOT_SALE){//横版
            final ArrayList<DirectGoodBean.DataBean> list = new ArrayList<>();
            Log.i("test",data.size()+"");
            for (int i = 0;i < data.size();i++){
                DirectGoodBean.DataBean item = (DirectGoodBean.DataBean)data.get(i);
                list.add(item);
            }
            if (list != null && list.size() > 0){
                ((GoodsViewHolderHor) holder).hsv_goods.setVisibility(View.VISIBLE);
                for (int i = 0;i<list.size();i++){
                    View view = LayoutInflater.from(context).inflate(R.layout.horizontal_good_cell,null,false);
                    SimpleDraweeView niv_cell_goodpic = (SimpleDraweeView) view.findViewById(R.id.niv_cell_goodpic);
                    TextView tv_good_name = (TextView) view.findViewById(R.id.tv_good_name);
                    TextView tv_price = (TextView) view.findViewById(R.id.tv_price);
                    niv_cell_goodpic.setImageURI(CommonUtil.zoomPic(list.get(i).getPrimaryImage()));
                    tv_good_name.setText(list.get(i).getName()+"\n");
                    tv_price.setText("￥" + list.get(i).getMinprice());
                    final int finalI = i;
                    view.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            UIHelper.togoGoodsDetailActivity(context,list.get(finalI).getId(),"");
                        }
                    });
                    ((GoodsViewHolderHor) holder).ll_goods_container.addView(view);
                }

//                adapter = new HorizontalGoodsDirectAdapter(list,context);
//                ((GoodsViewHolderHor)holder).gv_goods.setAdapter(adapter);
            }else {
                ((GoodsViewHolderHor) holder).hsv_goods.setVisibility(View.GONE);
            }
//            ((GoodsViewHolderHor)holder).gv_goods.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                @Override
//                public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
//                    UIHelper.togoGoodsDetailActivity(context,list.get(pos).getId(),"");
//                }
//            });
        }else{//竖版
            final ArrayList<DirectGoodBean.DataBean> list = new ArrayList<>();
            Log.i("test",data.size()+"");
            for (int i = 0;i < data.size();i++){
                DirectGoodBean.DataBean item = (DirectGoodBean.DataBean)data.get(i);
                list.add(item);
            }
            if (list != null && list.size() > 0){
                adapterVer = new HorizontalGoodsVerAdapter(list,context);
                ((GoodsViewHolderVer)holder).lv_goods.setAdapter(adapterVer);
            }
            ((GoodsViewHolderVer)holder).lv_goods.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
                    UIHelper.togoGoodsDetailActivity(context,list.get(pos).getId(),"");
                }
            });
        }

    }

    @Override
    public int getItemViewType(int position) {
        if(type == SHOP_MORE_HOT_SALE ){
            return TYPE_HOR;
        } else {
            return TYPE_VER;
        }
    }



    @Override
    public int getItemCount() {
        if (data != null&& data.size() > 0) {
            return 1;
        } else {
            return 0;
        }
    }

    class GoodsViewHolderHor extends ViewHolder{
        HorizontalScrollView hsv_goods;
        LinearLayout ll_goods_container;
        public GoodsViewHolderHor(View itemView) {
            super(itemView);
            ll_goods_container = (LinearLayout) itemView.findViewById(R.id.ll_goods_container);
            hsv_goods = (HorizontalScrollView) itemView.findViewById(R.id.hsv_goods);
        }
    }

    class GoodsViewHolderVer extends ViewHolder {
        MyListView lv_goods;

        public GoodsViewHolderVer(View itemView) {
            super(itemView);
            lv_goods = (MyListView) itemView.findViewById(R.id.lv_goods);
        }
    }



}
