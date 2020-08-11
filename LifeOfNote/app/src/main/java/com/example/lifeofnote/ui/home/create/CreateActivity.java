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
            changeViewsStyle();
        }
        binding.tvToday.setText(DateTimeUtils.getCnNotYearDate());
    }

    private void changeViewsStyle() {
        binding.btConfig.setBackgroundColor(getResources().getColor(R.color.colorIncomeBg));
        binding.tvAddTip.setTextColor(getResources().getColor(R.color.colorIncomeBg));
        binding.clChangeTime.setBackground(getDrawable(R.drawable.bg_tv_today_change));
        binding.ibCancel.setBackground(getDrawable(R.drawable.icon_clear_change));
    }

    private void initDatas() {
        isPay = getIntent().getBooleanExtra("isPay", false);
        binding.clChangeTime.setOnClickListener(mClick);
        binding.ivClose.setOnClickListener(mClick);
        binding.tvAddTip.setOnClickListener(mClick);
        binding.btConfig.setOnClickListener(mClick);
        if (isPay) {
            observePay();
        } else {
            observeGet();
        }

        viewModel.getNumberSum().observe(this, new Observer<StringBuffer>() {
            @Override
            public void onChanged(StringBuffer stringBuffer) {
                binding.etInput.setText(stringBuffer.toString());
            }
        });
    }

    private void observeGet() {
        viewModel.getAllMoneyTypes(1).observe(this, new Observer<List<MoneyTypeEntity>>() {
            @Override
            public void onChanged(List<MoneyTypeEntity> moneyTypeEntities) {
                if (adapter == null) {
                    setIncomeAdapter(moneyTypeEntities);
                }
            }
        });
    }

    private void observePay() {
        viewModel.getAllMoneyTypes(-1).observe(this, new Observer<List<MoneyTypeEntity>>() {
            @Override
            public void onChanged(List<MoneyTypeEntity> moneyTypeEntities) {
                if (adapter == null) {
                    loadIncomeData(moneyTypeEntities);
                }
            }
        });
    }

    private void loadIncomeData(List<MoneyTypeEntity> moneyTypeEntities) {
        viewModel.getIncomeData(moneyTypeEntities).observe(CreateActivity.this, new Observer<List<CratePayEntity>>() {
            @Override
            public void onChanged(List<CratePayEntity> cratePayEntities) {
                datas = cratePayEntities;
                adapter = new CreatePayAdapter(R.layout.item_pay_type, datas, isPay);
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
        });
    }

    private void setIncomeAdapter(List<MoneyTypeEntity> moneyTypeEntities) {
        viewModel.getMoneyTypeEntities(moneyTypeEntities).observe(CreateActivity.this, new Observer<List<CratePayEntity>>() {
            @Override
            public void onChanged(List<CratePayEntity> cratePayEntities) {
                datas = cratePayEntities;
                adapter = new CreatePayAdapter(R.layout.item_pay_type, datas, isPay);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(APP.get());
                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                binding.rvType.setLayoutManager(linearLayoutManager);
                binding.rvType.setAdapter(adapter);
                binding.hiDicators.bindRecyclerView(binding.rvType);
                binding.hiDicators.setIndicatorColor(Color.parseColor("#e8b854"));
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
                                binding.tvTipMessage.setText(userTip);
                                binding.tvAddTip.setText("修改");
                            }
                        });
                    }
                    inputDialog.showDialog();
                    break;
                case R.id.bt_config:

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
}