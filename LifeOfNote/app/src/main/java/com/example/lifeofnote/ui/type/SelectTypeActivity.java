package com.example.lifeofnote.ui.type;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.lifeofnote.R;
import com.example.lifeofnote.adapter.SelectTypeAdapter;
import com.example.lifeofnote.base.BoggleConstants;
import com.example.lifeofnote.base.OnItemClickListener;
import com.example.lifeofnote.base.PrefUtils;
import com.example.lifeofnote.databinding.ActivitySelectTypeBinding;
import com.example.lifeofnote.db.type.MoneyTypeEntity;
import com.example.lifeofnote.local.SelectTypeData;
import com.example.lifeofnote.utils.GridSpacingItemDecoration;
import com.example.lifeofnote.utils.Screenutils;
import com.example.lifeofnote.utils.StatusBarUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class SelectTypeActivity extends AppCompatActivity {

    private ActivitySelectTypeBinding binding;
    private SelectTypeViewModel viewModel;
    private SelectTypeAdapter adapter;
    private SelectTypeAdapter gettedAdapter;
    private List<MoneyTypeEntity> payTypeEntitys;
    private List<MoneyTypeEntity> gaidTypeEntitys;
    private int lastPosition = 0;
    private int lastGaidPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_select_type);
        viewModel = ViewModelProviders.of(this).get(SelectTypeViewModel.class);
        binding.setSelectTypeVM(viewModel);
        StatusBarUtil.setRootViewFitsSystemWindows(this, false);
        StatusBarUtil.setTranslucentStatus(this);
        StatusBarUtil.setStatusBarDarkTheme(this, true);
        initViews();
    }

    private void initViews() {
        binding.ivClose.setOnClickListener(mClick);
        binding.tvAll.setOnClickListener(mClick);

        if (PrefUtils.isFirstIn()) {
            viewModel.insertAllDatas(new SelectTypeData().getLocal());
            PrefUtils.setInDefault(BoggleConstants.FIRST_IN, false);
        }
        viewModel.getAllMoneyTypes(-1).observe(this, new Observer<List<MoneyTypeEntity>>() {
            @Override
            public void onChanged(List<MoneyTypeEntity> moneyTypeEntities) {
                if (adapter == null && moneyTypeEntities.size() != 0) {
                    payTypeEntitys = moneyTypeEntities;
                    adapter = new SelectTypeAdapter(payTypeEntitys, getLayoutInflater());
                    binding.rvPay.setLayoutManager(new GridLayoutManager(getApplicationContext(), 3) {
                        @Override
                        public boolean canScrollVertically() {
                            return false;
                        }
                    });
                    binding.rvPay.addItemDecoration(new GridSpacingItemDecoration(3, Screenutils.dip2px(getApplicationContext(), 12), false));
                    binding.rvPay.setAdapter(adapter);
                    adapter.setOnItemClickListener(new OnItemClickListener() {
                        @Override
                        public void onItemClick(int position) {
                            if (lastPosition != position || position == 0) {
                                payTypeEntitys.get(lastPosition).setShowIng(false);
                                payTypeEntitys.get(position).setShowIng(true);
                                gaidTypeEntitys.get(lastGaidPosition).setShowIng(false);
                                gettedAdapter.notifyItemChanged(lastGaidPosition);
                                adapter.notifyItemChanged(position);
                                adapter.notifyItemChanged(lastPosition);
                                lastPosition = position;
                                binding.tvAll.setBackground(getResources().getDrawable(R.drawable.bg_item_select_while));
                                binding.tvAll.setTextColor(Color.parseColor("#444444"));
                                EventBus.getDefault().post(payTypeEntitys.get(position));
                            }
                        }
                    });
                }
            }
        });

        viewModel.getGettedMoneyType().observe(this, new Observer<List<MoneyTypeEntity>>() {
            @Override
            public void onChanged(List<MoneyTypeEntity> moneyTypeEntities) {
                if (gettedAdapter == null && moneyTypeEntities != null) {
                    gaidTypeEntitys = moneyTypeEntities;
                    gettedAdapter = new SelectTypeAdapter(gaidTypeEntitys, getLayoutInflater());
                    binding.rvGetted.setLayoutManager(new GridLayoutManager(getApplicationContext(), 3) {
                        @Override
                        public boolean canScrollVertically() {
                            return false;
                        }
                    });
                    binding.rvGetted.addItemDecoration(new GridSpacingItemDecoration(3, Screenutils.dip2px(getApplicationContext(), 12), false));
                    binding.rvGetted.setAdapter(gettedAdapter);
                }

                gettedAdapter.setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        if (lastGaidPosition != position || position == 0) {
                            gaidTypeEntitys.get(lastGaidPosition).setShowIng(false);
                            gaidTypeEntitys.get(position).setShowIng(true);
                            gettedAdapter.notifyItemChanged(position);
                            gettedAdapter.notifyItemChanged(lastGaidPosition);
                            lastGaidPosition = position;
                            binding.tvAll.setBackground(getResources().getDrawable(R.drawable.bg_item_select_while));
                            binding.tvAll.setTextColor(Color.parseColor("#444444"));
                            payTypeEntitys.get(lastPosition).setShowIng(false);
                            adapter.notifyItemChanged(lastPosition);
                            EventBus.getDefault().post(gaidTypeEntitys.get(position));
                        }
                    }
                });
            }
        });
    }

    @Override
    public void onBackPressed() {
        justFinshAnim();
    }

    private View.OnClickListener mClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.iv_close:
                    justFinshAnim();
                    break;
                case R.id.tv_all:
                    justFinshAnim();
                    break;
            }
        }
    };

    private void justFinshAnim() {
        finish();
        overridePendingTransition(0, 0);
    }
}