package com.linli.consumer.ui.cookbook;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.linli.consumer.R;
import com.linli.consumer.adapter.OrderHealthWayAdapter;
import com.linli.consumer.domain.OrderHealthWay;
import com.linli.consumer.ui.main.SearchActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 养生道的listview  wwy页面展示
 */
public class OrderHealthWayViewActivity extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private ImageView ivTitleBack;///标题栏中的返回
    private TextView tvTitle;//标题栏中的汉字
    private EditText tv_order_edittext;
    private ImageView order_iv_search_right;
    private TextView tvname;//标题
    private TextView tvliulanliang;
    private TextView tvlead;
    private TextView tvlabel;
    private TextView tvcontent;
    private TextView tvtime;
    private ListView mListViewHealthWay;//养生道中Listview
    private OrderHealthWayAdapter mOrderHealthWayAdapter;//listview对应的adapter
    private Context mContext;
    private List<OrderHealthWay> OrderHealthWayList = new ArrayList<OrderHealthWay>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_health_way_view);
        mContext = this.getApplicationContext();

        init();
        OrderHealthWayList = getOrderHealthWayList();//此处获取数据源
        if (OrderHealthWayList != null) {
            mOrderHealthWayAdapter = new OrderHealthWayAdapter(this, OrderHealthWayList);
        }
        if (mOrderHealthWayAdapter != null) {
            mListViewHealthWay.setAdapter(mOrderHealthWayAdapter);
        }
    }

    public void init() {
        findViewById(R.id.iv_order_title_find_back).setOnClickListener(this);
        order_iv_search_right = (ImageView) findViewById(R.id.iv_to_search);
//        tv_order_edittext = (EditText) findViewById(R.id.tv_order_edittext);
//        tv_order_edittext.setOnClickListener(this);
        tvname = (TextView) findViewById(R.id.iv_order_healthway_itemtitle);
        tvliulanliang = (TextView) findViewById(R.id.tv_health_way_itemliulanliang);
        tvlead = (TextView) findViewById(R.id.tv_order_healthway_item_lead);
        tvlabel = (TextView) findViewById(R.id.tv_order_healthway_item_label);
        tvcontent = (TextView) findViewById(R.id.tv_order_healthway_item_content);
        tvtime = (TextView) findViewById(R.id.iv_order_health_item_date);
        tvTitle = (TextView) findViewById(R.id.tv_order_title_find);
        tvTitle.setText("养生膳");
        tvTitle.setOnClickListener(this);
        order_iv_search_right.setOnClickListener(this);
        mListViewHealthWay = (ListView) findViewById(R.id.order_listview_health_way);
        mListViewHealthWay.setOnItemClickListener(this);


    }

    public List<OrderHealthWay> getOrderHealthWayList()
    {
        OrderHealthWay orderHealthWay1 = new OrderHealthWay("【养生又好吃的零食】", R.mipmap.temple1, 512, "1.葵花子-养颜;2.花生-能防皮肤病;3.核桃-可秀甲;4.大枣-预防坏血病;5.奶酪-固齿;6.无花果-促 \n" +
                "进血液循环;7.南瓜子和开心果-健脑;8.奶糖-润肤;9.葡萄干-补血10.芝麻糊-乌发;11.巧克力-怡情;12.薄荷糖-润喉13.柑橘-富含\n" +
                "维生素C。", "标签：", "零食 保健", "2016-9-25");
        OrderHealthWay orderHealthWay2 = new OrderHealthWay("【饭后养生】", R.mipmap.temple2, 5065, "①吃得太油腻，喝杯芹菜汁;②吃火锅后喝点酸奶，保护胃肠道黏膜;③消化不良，饭后喝大麦茶或橘皮水;④吃方便面后吃水果，补偿维生素与矿物质的不足;⑤吃蟹后喝生姜红糖水，祛寒暖胃、促进消化、缓解胃部不适;⑥饭后吃个柿子，可润肺生津、养阴清燥。"
                , "标签：", "饮品 习惯", "2016-8-29");
        OrderHealthWay orderHealthWay3 = new OrderHealthWay("【中医说枸杞养生】", R.mipmap.temple3, 8732, "常吃枸杞子能“坚筋骨、耐寒暑”。是滋补调养和抗衰老的良药。很多人都不知道常吃枸杞子可以美容。这因为，枸杞子可以提\n" +
                "高皮肤吸收氧分的能力，另外，还能起到美白作用。《本草纲目》记载：“枸杞，补肾生精，养肝，明目，坚精骨，去疲劳，易颜色，变白，明目安神，令人长 \n" +
                "寿。”", "标签：", "安神 长寿", "2016-7-31");
        OrderHealthWay orderHealthWay4 = new OrderHealthWay("【夏日养生菜之豆腐】", R.mipmap.temple4, 193, "豆腐的蛋白质含量丰富，而且豆腐蛋白属完全蛋白，不仅含有人体必需的八种氨基酸，而且比例也接近人体需要，营养价值较高;有降低血脂，保护血管细胞，预防心血管疾病的作用。此外，豆腐对病后调养、减肥、细腻肌肤亦很有好处。"
                , "标签：", "豆腐 蛋白质", "2016-7-28");
        OrderHealthWay orderHealthWay5 = new OrderHealthWay("【养生与颜色】", R.mipmap.temple5, 751, "《黄帝内经》根据五行学说，以人体五脏为中心，五色与五脏相配，即绿红黄白黑。红主心，绿主肝，黄主脾，白主肺，黑主肾。一\n" +
                "般来说，春多吃绿(椰菜、黄瓜等)，夏多吃红(胡萝卜、番茄)，秋多吃白(白萝卜、银耳)，冬多吃黑(海带、黑芝麻)，四季吃黄(南瓜、植物种子)。"
                , "标签：", "四季 颜色", "2016-7-26");
        OrderHealthWay orderHealthWay6 = new OrderHealthWay("【生活小知识】", R.mipmap.temple6, 3120, "夏季养生十大最佳方法：1、最佳调味品：食醋;2、最佳蔬菜：苦味菜;3、最佳汤肴：番茄汤;4、最佳肉食：鸭肉;5、最佳饮料：热茶;6、最佳营养素：维生素E;7、最佳运动：游泳;8、最佳服色：红色;10、最佳保健措施：起睡定时。"
                , "标签：", "生活 窍门", "2016-6-19");
        OrderHealthWay orderHealthWay7 = new OrderHealthWay("【冬季养生试试“煮水果”】", R.mipmap.temple7, 213, "①苹果：蒸熟了的苹果具有止泻作用。②梨：蒸熟了的梨具有清热润肺、止咳化痰的作用。③枣：蒸熟的枣比生枣更易消化。④山楂：蒸熟的山楂不伤脾胃。⑤柚子皮：和蜂蜜一起煮成茶水，具有消食、除痰、解酒、美容等功能。"
            , "标签：", "水果 饮品", "2016-6-12");
        OrderHealthWay orderHealthWay8 = new OrderHealthWay("【最好的运动方式】", R.mipmap.temple8, 1212, "步行锻炼简便易行,尤其适宜于中老年人和体弱者的健身养生。步行能增强心血管系统功能,帮助心梗,中风的康复。正确的步\n" +
                "行方法是：挺胸抬头，迈大步;手臂随脚步节奏摆动，并和呼吸节律同步。每天1或2次，每次30~60分钟，一周累计步行5次。以微微出汗为宜，坚持3~5\n" +
                "周可见到成效。"
                , "标签：", "中老年 健身 步行", "2016-5-09");
        OrderHealthWay orderHealthWay9 = new OrderHealthWay("【早餐喝什么最养生】", R.mipmap.temple9, 1036, "1 、豆浆：适合怕胖者，有高血糖或贫血的;2 、纯牛奶：一般人都适合，但需要配主食，配坚果也不错;3 \n" +
                "、早餐奶：适合乳糖不耐受的人，需要制造饱腹感的人;4、 粥类：适合需要养胃的人，小米粥和山药粥都很温补;5、 果蔬汁：适合对清肠排毒有需求的人。"
                , "标签：", "养胃 温补", "2016-4-29");
        OrderHealthWay orderHealthWay10 = new OrderHealthWay("【早上起床后需要做的养生六件事】", R.mipmap.temple10, 592, "1、伸懒腰;2、打哈欠;3、深呼吸;4、立远眺;5、饮温水;6、净大便。活动关节，排除废气，增强肺活量，锻炼和保护视力，洗肠排毒，刺激胃液分泌和保护牙齿，改善中枢神经系统功能。"
                , "标签：", "视力 排毒", "2016-4-23");
        OrderHealthWayList.add(orderHealthWay1);
        OrderHealthWayList.add(orderHealthWay2);
        OrderHealthWayList.add(orderHealthWay3);
        OrderHealthWayList.add(orderHealthWay4);
        OrderHealthWayList.add(orderHealthWay5);
        OrderHealthWayList.add(orderHealthWay6);
        OrderHealthWayList.add(orderHealthWay7);
        OrderHealthWayList.add(orderHealthWay8);
        OrderHealthWayList.add(orderHealthWay9);
        OrderHealthWayList.add(orderHealthWay10);
        return OrderHealthWayList;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_to_search:
                Intent intent = new Intent(OrderHealthWayViewActivity.this, SearchActivity.class);
                intent.putExtra("searchtype",1);
                startActivity(intent);
                break;
            case R.id.iv_order_title_find_back:
            case R.id.tv_order_title_find:
                finish();
                break;
            default:
                break;
        }
    }

    //item的点击事件
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent it = new Intent(this, OrderHealthWayDetailsActivity.class);
        it.putExtra("healthyway",OrderHealthWayList.get(position));
        startActivity(it);
    }
}
