package com.app.hehego.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;

import com.app.common.sdk.bean.Tab;
import com.app.hehego.R;
import com.app.hehego.fragment.CartFragment;
import com.app.hehego.fragment.CategoryFragment;
import com.app.hehego.fragment.HomeFragment;
import com.app.hehego.fragment.HotFragment;
import com.app.hehego.fragment.MineFragment;
import com.app.uisdk.view.FragmentTabHost;

/**
 * Created by Administrator on 2016/11/26.
 */
public class HomeActivity extends AppCompatActivity{

    //项目根据替换这三个数组:mFragementArray mTitleArray mIconArray
    private final Class[] mFragementArray = {HomeFragment.class,HotFragment.class,CategoryFragment.class,CartFragment.class,MineFragment.class};
    private final int[] mTitleArray = {R.string.home,R.string.hot,R.string.catagory,R.string.cart,R.string.mine};
    private final int[] mIconArray = {R.drawable.selector_icon_home,R.drawable.selector_icon_hot,R.drawable.selector_icon_category,R.drawable.selector_icon_cart,R.drawable.selector_icon_mine};

    private LayoutInflater mInflater;
    private FragmentTabHost mTabhost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initTab();
    }

    private void initTab() {
        mInflater = LayoutInflater.from(this);
        mTabhost = (FragmentTabHost) this.findViewById(android.R.id.tabhost);
        mTabhost.setup(this,getSupportFragmentManager(),R.id.realtabcontent);

        for (int i = 0; i < mFragementArray.length; i++){
            Tab tab = new Tab(mFragementArray[i],mTitleArray[i],mIconArray[i]);
            TabHost.TabSpec tabSpec = mTabhost.newTabSpec(getString(tab.getTitle()));
            tabSpec.setIndicator(buildIndicator(tab));
            mTabhost.addTab(tabSpec,tab.getFragment(),null);
        }

        mTabhost.getTabWidget().setShowDividers(LinearLayout.SHOW_DIVIDER_NONE);
        mTabhost.setCurrentTab(0);
    }

    private View buildIndicator(Tab tab){
        View view =mInflater.inflate(R.layout.tab_indicator,null);
        ImageView img = (ImageView) view.findViewById(R.id.icon_tab);
        TextView text = (TextView) view.findViewById(R.id.txt_indicator);
        img.setBackgroundResource(tab.getIcon());
        text.setText(tab.getTitle());
        return  view;
    }

}
