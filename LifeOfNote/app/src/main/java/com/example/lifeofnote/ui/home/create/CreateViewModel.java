package com.example.lifeofnote.ui.home.create;

import android.app.Application;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.lifeofnote.db.type.MoneyTypeEntity;
import com.example.lifeofnote.db.type.MoneyTypeRepository;

import java.util.List;

public class CreateViewModel extends AndroidViewModel {

    private MoneyTypeRepository moneyTypeRepository;
    private MutableLiveData<StringBuffer> numberSum;

    public MutableLiveData<StringBuffer> getNumberSum() {
        if (numberSum == null) {
            numberSum = new MutableLiveData<>();
        }
        return numberSum;
    }

    public CreateViewModel(@NonNull Application application) {
        super(application);
        moneyTypeRepository = new MoneyTypeRepository(getApplication());
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
}
