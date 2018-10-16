package com.linli.consumer.ui.main;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.linli.consumer.R;
import com.linli.consumer.adapter.UserNeedAndFeedbackAdapter;
import com.linli.consumer.app.AppContext;
import com.linli.consumer.base.BaseActivity;
import com.linli.consumer.bean.FeedBacks;
import com.linli.consumer.bean.VoiceInfo;
import com.linli.consumer.common.Common;
import com.linli.consumer.common.HandleSuccess;
import com.linli.consumer.domain.City;
import com.linli.consumer.domain.Message;
import com.linli.consumer.domain.Shop;
import com.linli.consumer.domain.User;
import com.linli.consumer.domain.UserNeedAndFeedback;

import com.linli.consumer.net.IntrestBuyNet;
import com.linli.consumer.utils.DemoContext;
import com.linli.consumer.utils.LonLat;

import java.util.ArrayList;
import java.util.List;

import io.rong.imkit.RongIM;
import io.rong.imkit.fragment.ConversationListFragment;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;

public class MessageActivity extends BaseActivity implements AdapterView.OnItemClickListener, AbsListView.OnScrollListener, SwipeRefreshLayout.OnRefreshListener {
    private ListView lv_feedback;
    private TextView tv_nodata;
    private LinearLayout ll_IMConversations;

    private View v_feedback;
    private View v_my_message;
    private ArrayList<Message> myMessages = new ArrayList<Message>();
    private ArrayList<Message> sysMessages = new ArrayList<Message>();
    private ArrayList<UserNeedAndFeedback> feedbacks = new ArrayList<UserNeedAndFeedback>();
    private ArrayList<UserNeedAndFeedback> newItems = new ArrayList<UserNeedAndFeedback>();//新的条目
    private UserNeedAndFeedbackAdapter userNeedAndFeedbackAdapter = null;
    private AppContext appContext;
    private User user;
    private City city;
    private boolean converListIsShowing = false;
    private int pager = 1;
    private SwipeRefreshLayout srl_feedback;
    private LinearLayout footerview;

    @Override
    protected int getLayoutId() {
        return R.layout.conversationlist;
    }

    @Override
    protected void initView() {
        init();
        appContext = (AppContext) getApplicationContext();
        user = appContext.getUser();
        city = appContext.getCity();
        footerview = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.widget_loadmore,null);

    }

    @Override
    protected void initData() {
        if (YZXIndexActivity.isHaveNewMessage){
            dismiss();
            YZXIndexActivity.isHaveNewMessage = false;
            setDefaltView();
            ll_IMConversations.setVisibility(View.VISIBLE);//显示即时通讯的views
            v_my_message.setBackgroundColor(getResources().getColor(R.color.orange));
            if (!converListIsShowing){
                isReconnect();
                converListIsShowing = true;
            }
        }else {
            request_feedback();
        }
        isReconnect();
    }

    private void init() {
        TextView tv_head_name = findViewClick(R.id.tv_head_name);
        tv_head_name.setText("消息");
        findViewClick(R.id.iv_back);
        v_feedback = findView(R.id.v_feedback);
        v_my_message = findView(R.id.v_my_message);
        lv_feedback = findView(R.id.lv_feedback);
        lv_feedback.setOnItemClickListener(this);
        lv_feedback.setOnScrollListener(this);
        ll_IMConversations =  findView(R.id.ll_IMConversations);
        tv_nodata =findView(R.id.tv_nodata);

        findViewClick(R.id.rl_feedback);
        findViewClick(R.id.rl_my_message);
        srl_feedback = findView(R.id.srl_feedback);
        srl_feedback.setColorSchemeResources(R.color.orange, R.color.red, R.color.gray, R.color.green);
        srl_feedback.setSize(SwipeRefreshLayout.DEFAULT);
        srl_feedback.setProgressBackgroundColorSchemeColor(getResources().getColor(R.color.white));
        srl_feedback.setProgressViewEndTarget(true, 100);
        srl_feedback.setOnRefreshListener(this);
        setDefaltView();
        srl_feedback.setVisibility(View.VISIBLE);
        v_feedback.setBackgroundColor(getResources().getColor(R.color.orange));
    }
    private void setDefaltView() {
        srl_feedback.setVisibility(View.GONE);
        ll_IMConversations.setVisibility(View.GONE);
        tv_nodata.setVisibility(View.GONE);
        v_feedback.setBackgroundColor(getResources().getColor(R.color.white));
        v_my_message.setBackgroundColor(getResources().getColor(R.color.white));
    }

    @Override
    public void onHDClick(View v) {
        try {
            if (UserNeedAndFeedbackAdapter.mp != null && UserNeedAndFeedbackAdapter.mp.isPlaying()){
                UserNeedAndFeedbackAdapter.resetDataView();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        switch (v.getId()){
            case R.id.iv_back:
            case R.id.tv_head_name:
                finish();
//                Intent intent = new Intent();
//                intent.setClass(MessageActivity.this,YZXIndexActivity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP );
//                startActivity(intent);
                break;
            case R.id.rl_feedback:
                setDefaltView();
                srl_feedback.setVisibility(View.VISIBLE);
                v_feedback.setBackgroundColor(getResources().getColor(R.color.orange));
                if (feedbacks.size() == 0){
                    request_feedback();
                }
                break;
            case R.id.rl_my_message:
                setDefaltView();
                ll_IMConversations.setVisibility(View.VISIBLE);//显示即时通讯的views
                v_my_message.setBackgroundColor(getResources().getColor(R.color.orange));
                if (!converListIsShowing){
                    isReconnect();
                    converListIsShowing = true;
                }
                break;
            default:
                break;
        }
    }

    private void request_feedback() {
        if (pager == 1){
            showDialog();
            feedbacks.clear();
        }
        IntrestBuyNet.PVMessageData(10,pager,user.getId(),new HandleSuccess<FeedBacks>() {
            @Override
            public void success(FeedBacks s) {
                newItems.clear();
                if (s.getStatus() == 1){
                    if (s.getData() != null){
                        for (int i = 0;i <s.getData().size();i++){
                            UserNeedAndFeedback needAndFeedback = new UserNeedAndFeedback();
                            needAndFeedback.setType(s.getData().get(i).getHdPubVoice().getType());
                            needAndFeedback.setmText(s.getData().get(i).getHdPubVoice().getName());
                            needAndFeedback.setTime("");
                            needAndFeedback.setId(s.getData().get(i).getHdPubVoice().getId());
                            needAndFeedback.setUserHead(user.getHeadPath());
                            needAndFeedback.setDataPath(s.getData().get(i).getHdPubVoice().getPath());
                            needAndFeedback.setCreateTime(s.getData().get(i).getHdPubVoice().getCreateTime());
                            ArrayList<Shop> shops = new ArrayList<Shop>();///////////////////////////
                            List<FeedBacks.DataBean.HdMallStoreListBean> shopList = s.getData().get(i).getHdMallStoreList();
                            if (shopList != null && shopList.size()>0){
                                for (int j = 0;j<shopList.size();j++){
                                    FeedBacks.DataBean.HdMallStoreListBean itemShop = shopList.get(j);
                                    Shop shop = new Shop();
                                    shop.setId(itemShop.getId());
                                    shop.setCategory(itemShop.getCategoryType());
                                    shop.setShopUserId(itemShop.getCompanyMemberId());
                                    if (itemShop.getRegionId() != null){
                                        shop.setAreacode(itemShop.getRegionId().toString());
                                    }else {
                                        shop.setAreacode("0");
                                    }
                                    shop.setShopName(itemShop.getName());
                                    shop.setLogoPath(itemShop.getLogoImg() + Common.WSMALLERPICPARAM);
                                    shop.setLevel(itemShop.getCreditLevel());
                                    Double dis = LonLat.getDistance(itemShop.getLongitude(),itemShop.getLatitude(),city.getLongitude(),city.getLatitude());
                                    shop.setDistance(String.format("%.1f", dis));
                                    shop.setShopPhone(itemShop.getPhone());
                                    shop.setLevel(itemShop.getCreditLevel());
                                    shop.setRegionId(itemShop.getRegionId());
                                    shop.setShopAddr(itemShop.getAddress());
                                    shops.add(shop);
                                }
                            }
                            List<FeedBacks.DataBean.HdFoodStoreListBean> fshopList = s.getData().get(i).getHdFoodStoreList();
                            if (fshopList != null && fshopList.size()>0){
                                for (int j = 0;j<fshopList.size();j++){
                                    FeedBacks.DataBean.HdFoodStoreListBean itemShop = fshopList.get(j);
                                    Shop shop = new Shop();
                                    shop.setId(itemShop.getId());
                                    shop.setCategory(itemShop.getCategoryType());
                                    shop.setShopUserId(itemShop.getCompanyMemberId());
                                    shop.setAreacode(itemShop.getRegionId().toString());
                                    shop.setShopName(itemShop.getName());
                                    shop.setLogoPath(itemShop.getLogoImg() + Common.WSMALLERPICPARAM);
                                    shop.setLevel(itemShop.getCreditLevel());
                                    Double dis = LonLat.getDistance(itemShop.getLongitude(),itemShop.getLatitude(),city.getLongitude(),city.getLatitude());
                                    shop.setDistance(String.format("%.1f", dis));
                                    shop.setShopPhone(itemShop.getPhone());
                                    shop.setLevel(itemShop.getCreditLevel());
                                    shop.setRegionId(itemShop.getRegionId());
                                    shop.setShopAddr(itemShop.getAddress());
                                    shops.add(shop);
                                }
                            }
                            needAndFeedback.setBackProducts(shops);
                            newItems.add(needAndFeedback);
                            feedbacks.add(needAndFeedback);
                        }
                    }
                    if (pager == 1){
                        if (feedbacks.size()>0) {
                            //if  have datas
                            lv_feedback.setVisibility(View.VISIBLE);
                            tv_nodata.setVisibility(View.GONE);
                            if (feedbacks.size()>9){
                                lv_feedback.addFooterView(footerview,null,false);
                            }
                            if (userNeedAndFeedbackAdapter != null){
                                userNeedAndFeedbackAdapter.notifyDataSetChanged();
                            }else {
                                userNeedAndFeedbackAdapter = new UserNeedAndFeedbackAdapter(feedbacks,MessageActivity.this);
                                lv_feedback.setAdapter(userNeedAndFeedbackAdapter);
                            }
                            pager++;
                            requestVoiceTime(newItems);
                        }else {
                            //if have no datas
                            lv_feedback.setVisibility(View.GONE);
                            tv_nodata.setVisibility(View.VISIBLE);
                            Toast.makeText(MessageActivity.this, "还没有发送过需求哦", Toast.LENGTH_SHORT).show();
                        }
                        dismiss();
                        srl_feedback.setRefreshing(false);
                    }else {
                        if (newItems.size()>0){
                            userNeedAndFeedbackAdapter.notifyDataSetChanged();
                            pager++;
                            requestVoiceTime(newItems);
                        }else {
                            if (feedbacks.size()>=10){
                                lv_feedback.removeFooterView(footerview);
                            }
                        }
                    }
                }else {
                    if (pager==1){
                        lv_feedback.setVisibility(View.GONE);
                        Toast.makeText(MessageActivity.this, "还没有发送过需求哦", Toast.LENGTH_SHORT).show();
                    }

                }
                dismiss();
            }
        });

    }

    private void requestVoiceTime(ArrayList<UserNeedAndFeedback> newItems) {
        if (newItems!=null && newItems.size()>0){
            for (int i = 1;i<newItems.size()+1;i++){
                final int j = feedbacks.size()-i;
                if (feedbacks.get(j).getType() == 0){
                    IntrestBuyNet.getVoiceDuration(feedbacks.get(j).getmText() + "?avinfo", new HandleSuccess<VoiceInfo>() {
                        @Override
                        public void success(VoiceInfo s) {
                            double size = 0;
                            if (s.getFormat().getDuration() != null){
                                size = Double.parseDouble(s.getFormat().getDuration());
                            }
                            String  timelong = (int) Math.ceil(size) + "''";//转换为秒 单位为''
                            if (!timelong.equals("0''")){
                                feedbacks.get(j).setTime(timelong);
                                userNeedAndFeedbackAdapter.notifyDataSetChanged();
                            }
                        }
                    });
                }
            }
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()){
            case R.id.lv_feedback:
//                Toast.makeText(MessageActivity.this,"id"+feedbacks.get(position).getId(),Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onRestart() {
        if (userNeedAndFeedbackAdapter != null){
            userNeedAndFeedbackAdapter.notifyDataSetChanged();
        }
        super.onRestart();
    }

    @Override
    protected void onStop() {
        try {
            if (UserNeedAndFeedbackAdapter.mp != null && UserNeedAndFeedbackAdapter.mp.isPlaying()){
                UserNeedAndFeedbackAdapter.resetDataView();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        super.onStop();
    }



    /**
     * 判断消息是否是 push 消息
     */
    private void isReconnect() {
        Intent intent = getIntent();
        String token = null;

        if (DemoContext.getInstance() != null) {

            token = DemoContext.getInstance().getSharedPreferences().getString("RY_TOKEN", "default");
        }

        //push，通知或新消息过来
        if (intent != null && intent.getData() != null && intent.getData().getScheme().equals("rong")) {

            //通过intent.getData().getQueryParameter("push") 为true，判断是否是push消息
            if (intent.getData().getQueryParameter("push") != null
                    && intent.getData().getQueryParameter("push").equals("true")) {

                reconnect(token);
            } else {
                //程序切到后台，收到消息后点击进入,会执行这里
                if (RongIM.getInstance() == null || RongIM.getInstance().getRongIMClient() == null) {

                    reconnect(token);
                } else {
                    enterFragment();
                }
            }
        }
    }
    /**
     * 重连
     *
     * @param token
     */
    private void reconnect(String token) {

        if (getApplicationInfo().packageName.equals(AppContext.getCurProcessName(getApplicationContext()))) {

            RongIM.connect(token, new RongIMClient.ConnectCallback() {
                @Override
                public void onTokenIncorrect() {

                }

                @Override
                public void onSuccess(String s) {

                    enterFragment();
                }

                @Override
                public void onError(RongIMClient.ErrorCode errorCode) {

                }
            });
        }
    }
    /**
     * 加载 会话列表 ConversationListFragment
     */
    private void enterFragment() {

        ConversationListFragment fragment = (ConversationListFragment) getSupportFragmentManager().findFragmentById(R.id.conversationlist);

        Uri uri = Uri.parse("rong://" + getApplicationInfo().packageName).buildUpon()
                .appendPath("conversationlist")
                .appendQueryParameter(Conversation.ConversationType.PRIVATE.getName(), "false") //设置私聊会话非聚合显示
                .appendQueryParameter(Conversation.ConversationType.GROUP.getName(), "true")//设置群组会话聚合显示
                .appendQueryParameter(Conversation.ConversationType.DISCUSSION.getName(), "false")//设置讨论组会话非聚合显示
                .appendQueryParameter(Conversation.ConversationType.SYSTEM.getName(), "false")//设置系统会话非聚合显示
                .build();

        fragment.setUri(uri);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        switch (scrollState){
            case SCROLL_STATE_IDLE:
                if (view.getLastVisiblePosition() == (view.getCount()-1)){
                        request_feedback();
                }
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }

    @Override
    public void onRefresh() {
        pager = 1;
        lv_feedback.removeFooterView(footerview);


        request_feedback();
    }
/**
 * 以下为环信Demo中需用的一些代码，集成时考虑情况需要放开///////////////////////////////////////////////////////////
 *
 */
//    protected EMConversationListener convListener = new EMConversationListener() {
//        @Override
//        public void onCoversationUpdate() {
//            refresh();
//        }
//    };
//    protected Handler handler = new Handler(){
//        public void handleMessage(android.os.Message msg) {
//            switch (msg.what) {
//                case 0:
//                    onConnectionDisconnected();
//                    break;
//                case 1:
//                    onConnectionConnected();
//                    break;
//
//                case MSG_REFRESH:
//                {
//                    conversationList.clear();
//                    conversationList.addAll(loadConversationList());
//                    ecl_conversationlist.refresh();
//                    break;
//                }
//                default:
//                    break;
//            }
//        }
//    };
//    public void refresh() {
//        if(!handler.hasMessages(MSG_REFRESH)){
//            handler.sendEmptyMessage(MSG_REFRESH);
//        }
//    }
//    /**
//     * 连接断开
//     */
//    protected void onConnectionDisconnected(){
//        errorItemContainer.setVisibility(View.VISIBLE);
//        if (NetUtils.hasNetwork(MessageActivity.this)){
//            errorText.setText("没有网络连接");
//        }else {
//            errorText.setText("请检查当前网络是否可用");
//        }
//    }
//    /**
//     * 连接到服务器
//     */
//    protected void onConnectionConnected(){
//        errorItemContainer.setVisibility(View.GONE);
//    }
//    /**
//     * 获取会话列表
//     *
//     * @param
//     * @return
//    +    */
//    protected List<EMConversation> loadConversationList(){
//        // 获取所有会话，包括陌生人
//        Map<String, EMConversation> conversations = EMClient.getInstance().chatManager().getAllConversations();
//        // 过滤掉messages size为0的conversation
//        /**
//         * 如果在排序过程中有新消息收到，lastMsgTime会发生变化
//         * 影响排序过程，Collection.sort会产生异常
//         * 保证Conversation在Sort过程中最后一条消息的时间不变
//         * 避免并发问题
//         */
//        List<Pair<Long, EMConversation>> sortList = new ArrayList<Pair<Long, EMConversation>>();
//        synchronized (conversations) {
//            for (EMConversation conversation : conversations.values()) {
//                if (conversation.getAllMessages().size() != 0) {
//                    //if(conversation.getType() != EMConversationType.ChatRoom){
//                    sortList.add(new Pair<Long, EMConversation>(conversation.getLastMessage().getMsgTime(), conversation));
//                    //}
//                }
//            }
//        }
//        try {
//            // Internal is TimSort algorithm, has bug
//            sortConversationByLastChatTime(sortList);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        List<EMConversation> list = new ArrayList<EMConversation>();
//        for (Pair<Long, EMConversation> sortItem : sortList) {
//            list.add(sortItem.second);
//        }
//        return list;
//    }
//    /**
//     * 根据最后一条消息的时间排序
//     *
//     * @param
//     */
//    private void sortConversationByLastChatTime(List<Pair<Long, EMConversation>> conversationList) {
//        Collections.sort(conversationList, new Comparator<Pair<Long, EMConversation>>() {
//            @Override
//            public int compare(final Pair<Long, EMConversation> con1, final Pair<Long, EMConversation> con2) {
//
//                if (con1.first == con2.first) {
//                    return 0;
//                } else if (con2.first > con1.first) {
//                    return 1;
//                } else {
//                    return -1;
//                }
//            }
//
//        });
//    }
//
//    protected EMConnectionListener connectionListener = new EMConnectionListener() {
//
//        @Override
//        public void onDisconnected(int error) {
//            if (error == EMError.USER_REMOVED || error == EMError.USER_LOGIN_ANOTHER_DEVICE) {
//                isConflict = true;
//            } else {
//                handler.sendEmptyMessage(0);
//            }
//        }
//
//        @Override
//        public void onConnected() {
//            handler.sendEmptyMessage(1);
//        }
//    };
//    protected void hideSoftKeyboard() {
//        if (this.getWindow().getAttributes().softInputMode != WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN) {
//            if (this.getCurrentFocus() != null)
//                inputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(),
//                        InputMethodManager.HIDE_NOT_ALWAYS);
//        }
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        refresh();
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        EMClient.getInstance().removeConnectionListener(connectionListener);
//    }
//
//    @Override
//    protected void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//        if (isConflict){
//            outState.putBoolean("isConflict",true);
//        }
//    }
//    public interface EaseConversationListItemClickListener {
//        /**
//         * 会话listview item点击事件
//         * @param conversation 被点击item所对应的会话
//         */
//        void onListItemClicked(EMConversation conversation);
//    }
//    /**
//     * 设置listview item点击事件
//     * @param listItemClickListener
//     */
//    public void setConversationListItemClickListener(EaseConversationListFragment.EaseConversationListItemClickListener listItemClickListener){
//        this.listItemClickListener = listItemClickListener;
//    }
}
