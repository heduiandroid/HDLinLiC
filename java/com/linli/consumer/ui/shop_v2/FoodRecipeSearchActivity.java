package com.linli.consumer.ui.shop_v2;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.linli.consumer.R;
import com.linli.consumer.adapter.shop_v2.ShopSortRecipeAdapter;
import com.linli.consumer.base.BaseActivity;
import com.linli.consumer.bean.FoodRecipeBean;
import com.linli.consumer.common.HandleSuccess;
import com.linli.consumer.net.FoodNet;
import com.linli.consumer.widget.XDefaultItemAnimator;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by tomoyo on 2017/3/9.
 */

public class FoodRecipeSearchActivity extends BaseActivity {

    private ShopSortRecipeAdapter adapter;
    private XRecyclerView recyclerView;
    private List<Object> list = new ArrayList<>();

    private ImageView backIm;
    private EditText searchEd;
    private ImageView searchIm;

    private String searchStr = "";       //搜索的文字



    @Override
    protected int getLayoutId() {
        return R.layout.activity_food_recipe_search;
    }

    @Override
    protected void initView() {

        adapter = new ShopSortRecipeAdapter(this,list);
        recyclerView = (XRecyclerView)findViewById(R.id.food_recipe_search_rc);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new XDefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        recyclerView.setPullRefreshEnabled(false);
        recyclerView.setLoadingMoreEnabled(false);
        backIm = findViewClick(R.id.food_recipe_search_back_im);
        searchEd = findView(R.id.food_recipe_search_search_ed);
        searchIm = findViewClick(R.id.food_recipe_search_search_im);

    }

    @Override
    protected void initData() {
        dismiss();
    }

    private void beginSearch(){
        searchStr = searchEd.getText().toString();
        list.clear();
        FoodNet.findFoodCloud(0 + "", 1, 300, searchStr, new HandleSuccess<FoodRecipeBean>() {
            @Override
            public void success(FoodRecipeBean foodRecipeBean) {
                if(foodRecipeBean.getData() != null){
                    for(int i = 0 ; i < foodRecipeBean.getData().size() ; i ++){
                        list.add(foodRecipeBean.getData().get(i));
                    }
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public void onHDClick(View v) {
        switch (v.getId()){
            case R.id.food_recipe_search_back_im:       //返回按钮
                finish();
                break;
            case R.id.food_recipe_search_search_im:     //搜索输入框
                beginSearch();
                break;
        }
    }
}
