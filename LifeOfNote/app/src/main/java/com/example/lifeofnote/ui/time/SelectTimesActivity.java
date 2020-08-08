package com.example.lifeofnote.ui.time;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.example.lifeofnote.R;
import com.example.lifeofnote.adapter.SelectTimeAdapter;
import com.example.lifeofnote.base.OnItemClickListener;
import com.example.lifeofnote.databinding.ActivitySelectTimesBinding;
import com.example.lifeofnote.databinding.ActivitySelectTypeBinding;
import com.example.lifeofnote.db.time.SelectTimeEntity;
import com.example.lifeofnote.utils.GridSpacingItemDecoration;
import com.example.lifeofnote.utils.Screenutils;
import com.example.lifeofnote.utils.StatusBarUtil;

import java.util.List;

public class SelectTimesActivity extends AppCompatActivity {

    private ActivitySelectTimesBinding binding;
    private SelectTimesViewModel selectTimesViewModel;
    private SelectTimeAdapter selectTimeAdapter;
    private int lastPost = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_select_times);
        StatusBarUtil.setRootViewFitsSystemWindows(this, false);
        StatusBarUtil.setTranslucentStatus(this);
        StatusBarUtil.setStatusBarDarkTheme(this, true);
        selectTimesViewModel = ViewModelProviders.of(this).get(SelectTimesViewModel.class);
        binding.setTimeModel(selectTimesViewModel);
        binding.setLifecycleOwner(this);
        initViews();
    }

    private void initViews() {
        binding.ivClose.setOnClickListener(mClick);

        selectTimesViewModel.getSelectTimeEntity().observe(this, new Observer<List<SelectTimeEntity>>() {
            @Override
            public void onChanged(final List<SelectTimeEntity> selectTimeEntities) {
                if (selectTimeEntities != null && selectTimeEntities.size() != 0) {
                    selectTimeEntities.get(lastPost).setShowing(true);
                    selectTimeAdapter = new SelectTimeAdapter(selectTimeEntities, getLayoutInflater());
                    binding.rvTimes.setLayoutManager(new GridLayoutManager(getApplicationContext(), 4));
                    binding.rvTimes.addItemDecoration(new GridSpacingItemDecoration(4, Screenutils.dip2px(getApplicationContext(), 12), false));
                    binding.rvTimes.setAdapter(selectTimeAdapter);
                    selectTimeAdapter.setOnItemClickListener(new OnItemClickListener() {
                        @Override
                        public void onItemClick(int position) {
                            if (lastPost != position) {
                                selectTimeEntities.get(lastPost).setShowing(false);
                                selectTimeEntities.get(position).setShowing(true);
                                selectTimeEntities.get(lastPost).setShowing(false);
                                selectTimeAdapter.notifyItemChanged(lastPost);
                                selectTimeAdapter.notifyItemChanged(position);
                                selectTimeAdapter.notifyItemChanged(lastPost);
                                lastPost = position;
                            }
                        }
                    });
                }
            }
        });
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        justFinish();
    }

    private View.OnClickListener mClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.iv_close:
                    justFinish();
                    break;
            }
        }
    };

    private void justFinish() {
        finish();
        overridePendingTransition(0, 0);
    }
}