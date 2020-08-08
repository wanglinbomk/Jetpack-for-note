package com.example.lifeofnote.ui.home;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.example.lifeofnote.R;
import com.example.lifeofnote.databinding.ActivityMainBinding;
import com.example.lifeofnote.ui.home.detail.DetailFragment;
import com.example.lifeofnote.ui.home.mine.MineFragment;
import com.example.lifeofnote.ui.home.statistics.StatisticsFragment;
import com.example.lifeofnote.utils.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity {

    private ActivityMainBinding binding;
    private MainViewModel viewModel;
    private Fragment currentFragment;
    private Fragment hideFragment;
    private int lastProstion;
    private List<Fragment> mFragments;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initLocal();
        initNavigationListen(savedInstanceState);
    }

    private void initLocal() {
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        binding.setMainViewModel(viewModel);
        binding.setLifecycleOwner(this);

        StatusBarUtil.setRootViewFitsSystemWindows(this, false);
        StatusBarUtil.setTranslucentStatus(this);
        StatusBarUtil.setStatusBarDarkTheme(MainActivity.this, true);

        mFragments = new ArrayList<>();
        mFragments.add(DetailFragment.newInstance());
        mFragments.add(StatisticsFragment.newInstance());
        mFragments.add(MineFragment.newInstance());

    }

    private void initNavigationListen(Bundle saveInstancestate) {
        if (saveInstancestate == null) {
            setSelectedFragment(0);
        }
        binding.navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                //不要忘记了, 在当前activity onCreate中设置 取消padding,  因为这个padding 我们用代码实现了,不需要系统帮我
                StatusBarUtil.setRootViewFitsSystemWindows(MainActivity.this, false);
                //设置状态栏透明
                StatusBarUtil.setTranslucentStatus(MainActivity.this);
                switch (menuItem.getItemId()) {
                    case R.id.detailFragment:
                        MainActivity.this.setSelectedFragment(0);
                        break;
                    case R.id.statisticsFragment:
                        MainActivity.this.setSelectedFragment(1);
                        break;
                    case R.id.mineFragment:
                        MainActivity.this.setSelectedFragment(2);
                        break;
                }
                return true;
            }
        });
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("last_position", lastProstion);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        lastProstion = savedInstanceState.getInt("last_position");//获取重建时的fragment的位置
        setSelectedFragment(lastProstion);//恢复销毁前显示的fragment
    }
    private void setSelectedFragment(int position) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        currentFragment = fragmentManager.findFragmentByTag("fragment" + position);//要显示的fragment(解决了activity重建时新建实例的问题)
        hideFragment = fragmentManager.findFragmentByTag("fragment" + lastProstion);//要隐藏的fragment(解决了activity重建时新建实例的问题)
        if (position != lastProstion) {//如果位置不同
            if (hideFragment != null) {//如果要隐藏的fragment存在，则隐藏
                transaction.hide(hideFragment);
            }
            if (currentFragment == null) {//如果要显示的fragment不存在，则新加并提交事务
                currentFragment = mFragments.get(position);
                transaction.add(R.id.fuck, currentFragment, "fragment" + position);
            } else {//如果要显示的存在则直接显示
                transaction.show(currentFragment);
            }
        }

        if (position == lastProstion) {//如果位置相同
            if (currentFragment == null) {//如果fragment不存在(第一次启动应用的时候)
                currentFragment = mFragments.get(position);
                transaction.add(R.id.fuck, currentFragment, "fragment" + position);
            }//如果位置相同，且fragment存在，则不作任何操作
        }
        transaction.commit();//提交事务
        lastProstion = position;//更新要隐藏的fragment的位置
    }
}