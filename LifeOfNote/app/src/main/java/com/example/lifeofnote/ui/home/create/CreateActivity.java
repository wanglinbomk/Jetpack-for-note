package com.example.lifeofnote.ui.home.create;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.example.lifeofnote.R;
import com.example.lifeofnote.adapter.CreatePayAdapter;
import com.example.lifeofnote.base.APP;
import com.example.lifeofnote.callback.DialogInputCallback;
import com.example.lifeofnote.databinding.ActivityCreateBinding;
import com.example.lifeofnote.db.type.MoneyTypeEntity;
import com.example.lifeofnote.entity.CratePayEntity;
import com.example.lifeofnote.ui.width.InputDialog;
import com.example.lifeofnote.utils.DateTimeUtils;
import com.example.lifeofnote.utils.StatusBarUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CreateActivity extends AppCompatActivity {

    private boolean isPay;
    private ActivityCreateBinding binding;
    private CreateViewModel viewModel;
    private CreatePayAdapter adapter;
    private List<CratePayEntity> datas;
    private int lastPosition = 0;

    private InputDialog inputDialog;
    private String userTip = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_create);
        StatusBarUtil.setRootViewFitsSystemWindows(this, false);
        StatusBarUtil.setTranslucentStatus(this);
        StatusBarUtil.setStatusBarDarkTheme(this, true);
        viewModel = ViewModelProviders.of(this).get(CreateViewModel.class);
        binding.setCreateViewModel(viewModel);
        binding.setLifecycleOwner(this);
        initDatas();
        initViews();
    }

    private void initViews() {
        if (isPay) {
            binding.tvTitle.setText(getResources().getString(R.string.pay));
        } else {
            binding.tvTitle.setText(getResources().getString(R.string.gaid));
        }
        binding.tvToday.setText(DateTimeUtils.getCnNotYearDate());
    }

    private void initDatas() {
        isPay = getIntent().getBooleanExtra("isPay", false);
        binding.clChangeTime.setOnClickListener(mClick);
        binding.ivClose.setOnClickListener(mClick);
        binding.tvAddTip.setOnClickListener(mClick);
        if (isPay) {
            viewModel.getAllMoneyTypes(-1).observe(this, new Observer<List<MoneyTypeEntity>>() {
                @Override
                public void onChanged(List<MoneyTypeEntity> moneyTypeEntities) {
                    if (adapter == null) {
                        datas = new ArrayList<>();
                        setDatas(moneyTypeEntities);
                        adapter = new CreatePayAdapter(R.layout.item_pay_type, datas);
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(APP.get());
                        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                        binding.rvType.setLayoutManager(linearLayoutManager);
                        binding.rvType.setAdapter(adapter);
                        binding.hiDicators.bindRecyclerView(binding.rvType);
                        binding.hiDicators.setIndicatorColor(Color.parseColor("#51BC83"));
                        binding.hiDicators.setBgColor(Color.parseColor("#E6E6E6"));

                        adapter.addChildClickViewIds(R.id.cl_all_father);
                        adapter.setOnItemChildClickListener(new OnItemChildClickListener() {
                            @Override
                            public void onItemChildClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
                                if (position != lastPosition) {
                                    datas.get(lastPosition).setSelect(false);
                                    datas.get(position).setSelect(true);
                                }
                                adapter.notifyItemChanged(lastPosition);
                                adapter.notifyItemChanged(position);
                                lastPosition = position;
                            }
                        });
                    }
                }
            });
        } else {
            viewModel.getAllMoneyTypes(1).observe(this, new Observer<List<MoneyTypeEntity>>() {
                @Override
                public void onChanged(List<MoneyTypeEntity> moneyTypeEntities) {
                    if (adapter == null) {
                        datas = new ArrayList<>();
                        setInComeData(moneyTypeEntities);
                        adapter = new CreatePayAdapter(R.layout.item_pay_type, datas);
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(APP.get());
                        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                        binding.rvType.setLayoutManager(linearLayoutManager);
                        binding.rvType.setAdapter(adapter);
                        binding.hiDicators.bindRecyclerView(binding.rvType);
                        binding.hiDicators.setIndicatorColor(Color.parseColor("#51BC83"));
                        binding.hiDicators.setBgColor(Color.parseColor("#E6E6E6"));

                        adapter.addChildClickViewIds(R.id.cl_all_father);
                        adapter.setOnItemChildClickListener(new OnItemChildClickListener() {
                            @Override
                            public void onItemChildClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
                                if (position != lastPosition) {
                                    datas.get(lastPosition).setSelect(false);
                                    datas.get(position).setSelect(true);
                                }
                                adapter.notifyItemChanged(lastPosition);
                                adapter.notifyItemChanged(position);
                                lastPosition = position;
                            }
                        });
                    }
                }
            });
        }

        viewModel.getNumberSum().observe(this, new Observer<StringBuffer>() {
            @Override
            public void onChanged(StringBuffer stringBuffer) {
                binding.etInput.setText(stringBuffer.toString());
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(0, 0);
    }

    private View.OnClickListener mClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.iv_close:
                    finish();
                    overridePendingTransition(0, 0);
                    break;
                case R.id.cl_change_time:
                    showDatePicker();
                    break;
                case R.id.tv_add_tip:
                    if (inputDialog == null) {
                        inputDialog = new InputDialog(CreateActivity.this);
                        inputDialog.setDialogInputCallback(new DialogInputCallback() {
                            @Override
                            public void inputCallBack(String inputValue) {
                                userTip = inputValue;
                            }
                        });
                    }
                    inputDialog.showDialog();
                    break;
            }
        }
    };

    private void showDatePicker() {
        Calendar calendar = Calendar.getInstance();
        int mYear = calendar.get(Calendar.YEAR);
        int mMonth = calendar.get(Calendar.MONTH);
        int mDay = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(CreateActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                binding.tvToday.setText(i1 + 1 + "月" + i2 + "日");
            }
        }, mYear, mMonth, mDay);
        dialog.show();
    }

    private void setDatas(List<MoneyTypeEntity> moneyTypeEntities) {
        datas.add(new CratePayEntity(R.drawable.icon_pay_1, moneyTypeEntities.get(0).getName(), true, R.drawable.icon_pay_1a));
        datas.add(new CratePayEntity(R.drawable.icon_pay_2, moneyTypeEntities.get(1).getName(), false, R.drawable.icon_pay_2a));
        datas.add(new CratePayEntity(R.drawable.icon_pay_3, moneyTypeEntities.get(2).getName(), false, R.drawable.icon_pay_3a));
        datas.add(new CratePayEntity(R.drawable.icon_pay_4, moneyTypeEntities.get(3).getName(), false, R.drawable.icon_pay_4a));
        datas.add(new CratePayEntity(R.drawable.icon_pay_5, moneyTypeEntities.get(4).getName(), false, R.drawable.icon_pay_5a));
        datas.add(new CratePayEntity(R.drawable.icon_pay_6, moneyTypeEntities.get(5).getName(), false, R.drawable.icon_pay_6a));
        datas.add(new CratePayEntity(R.drawable.icon_pay_7, moneyTypeEntities.get(6).getName(), false, R.drawable.icon_pay_7a));
        datas.add(new CratePayEntity(R.drawable.icon_pay_8, moneyTypeEntities.get(7).getName(), false, R.drawable.icon_pay_8a));
        datas.add(new CratePayEntity(R.drawable.icon_pay_9, moneyTypeEntities.get(8).getName(), false, R.drawable.icon_pay_9a));
        datas.add(new CratePayEntity(R.drawable.icon_pay_10, moneyTypeEntities.get(9).getName(), false, R.drawable.icon_pay_10a));
        datas.add(new CratePayEntity(R.drawable.icon_pay_11, moneyTypeEntities.get(10).getName(), false, R.drawable.icon_pay_11a));
        datas.add(new CratePayEntity(R.drawable.icon_pay_12, moneyTypeEntities.get(11).getName(), false, R.drawable.icon_pay_12a));
        datas.add(new CratePayEntity(R.drawable.icon_pay_13, moneyTypeEntities.get(12).getName(), false, R.drawable.icon_pay_13a));
        datas.add(new CratePayEntity(R.drawable.icon_pay_14, moneyTypeEntities.get(13).getName(), false, R.drawable.icon_pay_14a));
        datas.add(new CratePayEntity(R.drawable.icon_pay_15, moneyTypeEntities.get(14).getName(), false, R.drawable.icon_pay_15a));
        datas.add(new CratePayEntity(R.drawable.icon_pay_16, moneyTypeEntities.get(15).getName(), false, R.drawable.icon_pay_16a));
        datas.add(new CratePayEntity(R.drawable.icon_pay_17, moneyTypeEntities.get(16).getName(), false, R.drawable.icon_pay_17a));
        datas.add(new CratePayEntity(R.drawable.icon_pay_18, moneyTypeEntities.get(17).getName(), false, R.drawable.icon_pay_18a));
        datas.add(new CratePayEntity(R.drawable.icon_pay_19, moneyTypeEntities.get(18).getName(), false, R.drawable.icon_pay_19a));
    }

    private void setInComeData(List<MoneyTypeEntity> moneyTypeEntities) {
        datas.add(new CratePayEntity(R.drawable.icon_income_a, moneyTypeEntities.get(0).getName(), true, R.drawable.icon_income_1a));
        datas.add(new CratePayEntity(R.drawable.icon_income_2, moneyTypeEntities.get(1).getName(), false, R.drawable.icon_income_2a));
        datas.add(new CratePayEntity(R.drawable.icon_income_4, moneyTypeEntities.get(2).getName(), false, R.drawable.icon_income_3a));
        datas.add(new CratePayEntity(R.drawable.icon_income_3, moneyTypeEntities.get(3).getName(), false, R.drawable.icon_income_4a));
        datas.add(new CratePayEntity(R.drawable.icon_income_5, moneyTypeEntities.get(4).getName(), false, R.drawable.icon_income_5a));
        datas.add(new CratePayEntity(R.drawable.item_income_6, moneyTypeEntities.get(5).getName(), false, R.drawable.icon_income_6a));
        datas.add(new CratePayEntity(R.drawable.icon_pay_16, moneyTypeEntities.get(6).getName(), false, R.drawable.icon_pay_16a));
        datas.add(new CratePayEntity(R.drawable.icon_pay_17, moneyTypeEntities.get(7).getName(), false, R.drawable.icon_pay_17a));
        datas.add(new CratePayEntity(R.drawable.icon_income_7, moneyTypeEntities.get(8).getName(), false, R.drawable.icon_income_7a));
        datas.add(new CratePayEntity(R.drawable.icon_income_8, moneyTypeEntities.get(9).getName(), false, R.drawable.icon_income_8a));
    }
}