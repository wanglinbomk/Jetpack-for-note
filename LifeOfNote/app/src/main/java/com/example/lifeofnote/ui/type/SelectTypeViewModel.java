package com.example.lifeofnote.ui.type;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.lifeofnote.db.type.MoneyTypeEntity;
import com.example.lifeofnote.db.type.MoneyTypeRepository;

import java.util.List;

public class SelectTypeViewModel extends AndroidViewModel {

    private MoneyTypeRepository moneyTypeRepository;

    public SelectTypeViewModel(@NonNull Application application) {
        super(application);
        moneyTypeRepository = new MoneyTypeRepository(getApplication());
    }

    public LiveData<List<MoneyTypeEntity>> getAllMoneyTypes(int type) {
        return moneyTypeRepository.getMoneyTypes(type);
    }

    public LiveData<List<MoneyTypeEntity>> getGettedMoneyType(){
        return moneyTypeRepository.getMoneyTypes(1);
    }

    public void insertAllDatas(List<MoneyTypeEntity> moneyTypeEntities) {
        for (int i = 0; i < moneyTypeEntities.size(); i++) {
            moneyTypeRepository.insertMoneyType(moneyTypeEntities.get(i));
        }
    }
}
