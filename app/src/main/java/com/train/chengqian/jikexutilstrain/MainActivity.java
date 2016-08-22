package com.train.chengqian.jikexutilstrain;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.train.chengqian.jikexutilstrain.fragment.DBFragment;
import com.train.chengqian.jikexutilstrain.fragment.HttpFragment;
import com.train.chengqian.jikexutilstrain.fragment.ImageFragment;
import com.train.chengqian.jikexutilstrain.fragment.InjectFragment;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

@ContentView(R.layout.activity_main)
public class MainActivity extends XUtilsActivity {

    @ViewInject(R.id.viewpager)
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position){
                    case 0 :
                        return new InjectFragment();
                    case 1 :
                        return new HttpFragment();
                    case 2 :
                        return new ImageFragment();
                    case 3 :
                        return new DBFragment();
                }
                return null;

            }

            @Override
            public int getCount() {
                return 4;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                switch (position){
                    case 0 :
                        return "Annotation";
                    case 1 :
                        return "Http";
                    case 2 :
                        return "Image";
                    case 3 :
                        return "Database";
                }
                return null;
            }
        });
    }

}
