
package com.example.sunpengfei.myapplication;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.sunpengfei.com.example.sunpengfei.service.TestAidlService;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity implements ServiceConnection{

    private ViewPager viewPager;

    private LinearLayout llTab;

    private List<Fragment> fragmentList;

    private ChatFragment chatFragment;

    private ContactFragment contactFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        llTab = (LinearLayout) findViewById(R.id.ll_bottom);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        initData();
        startService();
    }

    private void startService(){
        Intent intent = new Intent(this, TestAidlService.class);
        bindService(intent, this, BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(this);
    }

    private void initData() {
        fragmentList = new ArrayList<>();
        chatFragment = new ChatFragment();
        contactFragment = new ContactFragment();
        fragmentList.add(chatFragment);
        fragmentList.add(contactFragment);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT);
        params.weight = 1;
        llTab.setWeightSum(fragmentList.size());
        for(int i = 0;i < fragmentList.size();i++){
            View view = View.inflate(this,R.layout.tab_item,null);
            TextView tv = (TextView) view.findViewById(R.id.tab_tv);
            if(fragmentList.get(i) instanceof ChatFragment){
                tv.setText("chat");
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        viewPager.setCurrentItem(0);
                    }
                });
            }else if(fragmentList.get(i) instanceof ContactFragment){
                tv.setText("contact");
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        viewPager.setCurrentItem(1);
                    }
                });
            }
            view.setLayoutParams(params);
            llTab.addView(view);
        }
        viewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));
        viewPager.setCurrentItem(0);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                viewPager.setCurrentItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        // noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        IMyAidlInterface.Stub binder = (IMyAidlInterface.Stub) service;
        try {
            binder.add(100,200);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {

    }

    public class MyAdapter extends FragmentPagerAdapter{

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }
    }

}
