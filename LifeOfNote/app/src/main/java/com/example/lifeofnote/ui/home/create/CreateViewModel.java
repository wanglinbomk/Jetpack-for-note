package com.example.lifeofnote.ui.home.create;

import android.app.Application;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.lifeofnote.R;
import com.example.lifeofnote.db.create.CreateEntity;
import com.example.lifeofnote.db.create.CreateRepository;
import com.example.lifeofnote.db.type.MoneyTypeEntity;
import com.example.lifeofnote.db.type.MoneyTypeRepository;
import com.example.lifeofnote.entity.CratePayEntity;

import java.util.ArrayList;
import java.util.List;

public class CreateViewModel extends AndroidViewModel {

    private MoneyTypeRepository moneyTypeRepository;
    private CreateRepository createRepository;
    private MutableLiveData<StringBuffer> numberSum;
    private MutableLiveData<List<CratePayEntity>> getData;
    private MutableLiveData<List<CratePayEntity>> incomeData;

    public MutableLiveData<List<CratePayEntity>> getIncomeData(List<MoneyTypeEntity> moneyTypeEntities) {
        if(incomeData == null){
            incomeData = new MutableLiveData<>();
            loadIncomeData(moneyTypeEntities);
        }
        return incomeData;
    }

    public MutableLiveData<List<CratePayEntity>> getMoneyTypeEntities(List<MoneyTypeEntity> moneyTypeEntities) {
        if(getData == null){
            getData = new MutableLiveData<>();
            setInComeData(moneyTypeEntities);
        }
        return getData;
    }

    public MutableLiveData<StringBuffer> getNumberSum() {
        if (numberSum == null) {
            numberSum = new MutableLiveData<>();
        }
        return numberSum;
    }

    public CreateViewModel(@NonNull Application application) {
        super(application);
        moneyTypeRepository = new MoneyTypeRepository(getApplication());
        createRepository = new CreateRepository(getApplication());
    }

    public void insertCreateEntity(CreateEntity createEntity){
      createRepository.insertCreateEntity(createEntity);
    }

    public LiveData<List<MoneyTypeEntity>> getAllMoneyTypes(int type) {
        return moneyTypeRepository.getMoneyTypes(type);
    }

    public void justGo(View view) {
        if (numberSum.getValue() == null) {
            if (((Button) view).getText().toString().equals(".")) {
                numberSum.setValue(new StringBuffer("0" + ((Button) view).getText().toString()));
                return;
            }
            numberSum.setValue(new StringBuffer(((Button) view).getText().toString()));
        } else {
            if (((Button) view).getText().toString().equals(".")) {
                if (!numberSum.getValue().toString().contains(".")) {
                    if (numberSum.getValue().toString().length() == 0) {
                        numberSum.setValue(new StringBuffer("0" + ((Button) view).getText().toString()));
                        return;
                    }
                    numberSum.setValue(numberSum.getValue().append(((Button) view).getText().toString()));
                }
                return;
            }
            numberSum.setValue(numberSum.getValue().append(((Button) view).getText().toString()));
        }
    }

    public void deleteLast(View view) {
        if (numberSum.getValue() == null) {
            return;
        }
        if (numberSum.getValue().length() != 0) {
            numberSum.setValue(numberSum.getValue().deleteCharAt(numberSum.getValue().length() - 1));
        }
    }

    public void clearAll(View view) {
        if (numberSum != null && numberSum.getValue() != null) {
            numberSum.setValue(numberSum.getValue().delete(0, numberSum.getValue().length()));
        }
    }

    private void setInComeData(List<MoneyTypeEntity> moneyTypeEntities) {
        List<CratePayEntity> datas = new ArrayList<>();
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
        getData.setValue(datas);
    }

    private void loadIncomeData(List<MoneyTypeEntity> moneyTypeEntities) {
        List<CratePayEntity> datas = new ArrayList<>();
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
        incomeData.setValue(datas);
    }

}
