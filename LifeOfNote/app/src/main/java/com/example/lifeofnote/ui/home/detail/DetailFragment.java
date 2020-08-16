package com.example.lifeofnote.ui.home.detail;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.lifeofnote.R;
import com.example.lifeofnote.adapter.HomeAdapter;
import com.example.lifeofnote.base.APP;
import com.example.lifeofnote.databinding.DetailFragmentBinding;
import com.example.lifeofnote.db.create.CreateEntity;
import com.example.lifeofnote.db.time.SelectTimeEntity;
import com.example.lifeofnote.db.type.MoneyTypeEntity;
import com.example.lifeofnote.ui.home.create.CreateActivity;
import com.example.lifeofnote.ui.time.SelectTimesActivity;
import com.example.lifeofnote.ui.type.SelectTypeActivity;
import com.example.lifeofnote.utils.DateTimeUtils;
import com.example.lifeofnote.utils.ToastMaster;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

public class DetailFragment extends Fragment {

    private boolean isPAY = false;
    private boolean isShowing = false;
    private DetailViewModel mViewModel;
    private DetailFragmentBinding binding;
    private HomeAdapter adapter;

    public static DetailFragment newInstance() {
        return new DetailFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.detail_fragment, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(DetailViewModel.class);
        binding.setDateilViewModel(mViewModel);
        binding.setLifecycleOwner(getActivity());
        EventBus.getDefault().register(this);
        initViews();
        setListener();
        initObservice();
    }

    private void initObservice() {
        mViewModel.getCreateDate(Integer.parseInt(DateTimeUtils.getServenEnData().replaceAll("-", ""))
                , Integer.parseInt(DateTimeUtils.getEnDate().replaceAll("-", ""))).observe(this, new Observer<List<CreateEntity>>() {
            @Override
            public void onChanged(List<CreateEntity> createEntities) {
                if (adapter == null) {
                    adapter = new HomeAdapter(createEntities, LayoutInflater.from(APP.get()));
                    binding.rvAllData.setLayoutManager(new LinearLayoutManager(APP.get()));
                    binding.rvAllData.setAdapter(adapter);
                }
            }
        });
    }

    private void initViews() {
        binding.ivGetted.setAlpha(0f);
        binding.ivPay.setAlpha(0f);
        binding.tvSelectTime.setText(DateTimeUtils.getYMCnData());
    }

    private void setListener() {
        binding.clSelectType.setOnClickListener(mClick);
        binding.clSelectTime.setOnClickListener(mClick);
        binding.ivCreate.setOnClickListener(mClick);
        binding.ivGetted.setOnClickListener(mClick);
        binding.ivPay.setOnClickListener(mClick);
    }

    private View.OnClickListener mClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.cl_select_type:
                    startActivity(new Intent(getActivity(), SelectTypeActivity.class));
                    break;
                case R.id.cl_select_time:
                    startActivity(new Intent(getActivity(), SelectTimesActivity.class));
                    break;
                case R.id.iv_create:
                    playAnim();
                    break;
                case R.id.iv_pay:
                    isPAY = true;
                    Intent payIntent = new Intent(APP.get(), CreateActivity.class);
                    payIntent.putExtra("isPay", isPAY);
                    startActivity(payIntent);
                    break;
                case R.id.iv_getted:
                    isPAY = false;
                    Intent gaidIntent = new Intent(APP.get(), CreateActivity.class);
                    gaidIntent.putExtra("isPay", isPAY);
                    startActivity(gaidIntent);
                    break;
            }
        }
    };

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMoneyTypeEntity(MoneyTypeEntity moneyTypeEntity) {
        binding.tvCurrType.setText(moneyTypeEntity.getName());
        ToastMaster.toast(moneyTypeEntity.getName());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSelectTime(SelectTimeEntity selectTimeEntity) {
        String temp = selectTimeEntity.getName();
        temp.replace("月", "");
        binding.tvSelectTime.setText(DateTimeUtils.getCnNotYearDate());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void playAnim() {
        if (!isShowing) {
            isShowing = !isShowing;
            AnimatorSet animatorSet = new AnimatorSet();
            ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(binding.ivPay,
                    "translationY", 0, -binding.ivPay.getWidth());
            ObjectAnimator objectAnimator2 = ObjectAnimator.ofFloat(binding.ivGetted,
                    "translationY", 0, -binding.ivGetted.getWidth() * 2);
            ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(binding.ivPay, "alpha", 0, 1);
            ObjectAnimator objectAnimator4 = ObjectAnimator.ofFloat(binding.ivGetted, "alpha", 0, 1);
            animatorSet.setDuration(800);
            animatorSet.playTogether(objectAnimator, objectAnimator2, objectAnimator1, objectAnimator4);
            animatorSet.start();
        } else {
            isShowing = !isShowing;
            AnimatorSet animatorSet = new AnimatorSet();
            ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(binding.ivPay,
                    "translationY", -binding.ivPay.getWidth(), 0);
            ObjectAnimator objectAnimator2 = ObjectAnimator.ofFloat(binding.ivGetted,
                    "translationY", -binding.ivGetted.getWidth() * 2, 0);
            ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(binding.ivPay, "alpha", 1, 0);
            ObjectAnimator objectAnimator4 = ObjectAnimator.ofFloat(binding.ivGetted, "alpha", 1, 0);
            animatorSet.setDuration(800);
            animatorSet.playTogether(objectAnimator, objectAnimator2, objectAnimator1, objectAnimator4);
            animatorSet.start();
        }
    }
}