package com.example.lenovo.map;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MessageListActivity extends AppCompatActivity {

    Bundle bundle;
    Toolbar toolbar;

    ArrayList<MessageData> dataList;

    TabLayout tabLayout;
    ViewPager viewPager;
    FragmentAdapter mFragmentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_list);

        bundle = this.getIntent().getExtras();
        dataList = bundle.getParcelableArrayList("MessageList");

//        //模拟数据
//        dataList = new ArrayList<MessageData>();
//        double[] l1 = { 33.4248535, -111.9495278};
//        String[] tag1 = {"hello", "world"};
//        MessageData m1 = new MessageData("h@163.com", "2016-4-21", "2001", "nick",  l1, "test1",
//                        "message1", 0 ,tag1);
//        double[] l2 = { 33.4436965, -111.9297588};
//        String[] tag2 = {"hello2", "world2"};
//        MessageData m2 = new MessageData("h@163.com", "2016-4-22", "2001", "nick",  l2, "test2",
//                        "message2", 1 ,tag2);
//        double[] l3 = { 33.4136965, -111.9597588};
//        String[] tag3 = {"hello3", "world3"};
//        MessageData m3 = new MessageData("h@163.com", "2016-4-23", "2001", "nick",  l3, "test2",
//                "message3", 2 ,tag3);
//        dataList.add(m1);
//        dataList.add(m2);
//        dataList.add(m3);

        toolbar = (Toolbar) this.findViewById(R.id.toolbar_list_message);
        final View.OnClickListener toolbar_listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        };
        toolbar.setNavigationOnClickListener(toolbar_listener);

        tabLayout = (TabLayout) this.findViewById(R.id.tab_messageList);
        viewPager = (ViewPager) this.findViewById(R.id.viewpager_messageList);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        List<String> titles = new ArrayList<>();
        titles.add("Commercial");
        titles.add("Personal");
        titles.add("Social");
        titles.add("All");

        for(int i=0;i<titles.size();i++){
            tabLayout.addTab(tabLayout.newTab().setText(titles.get(i)));
        }
        List<Fragment> fragments = new ArrayList<>();
        for(int i=0;i<titles.size();i++){
            fragments.add(new ListFragment());
        }
        mFragmentAdapter =
                new FragmentAdapter(getSupportFragmentManager(), fragments, titles);
        //给ViewPager设置适配器
        viewPager.setAdapter(mFragmentAdapter);
        //将TabLayout和ViewPager关联起来。
        tabLayout.setupWithViewPager(viewPager);
        //给TabLayout设置适配器
        tabLayout.setTabsFromPagerAdapter(mFragmentAdapter);
    }

    public ArrayList<MessageData> getDataList() {return dataList;}

}