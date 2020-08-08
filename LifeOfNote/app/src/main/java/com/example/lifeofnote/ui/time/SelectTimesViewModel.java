package com.example.lifeofnote.ui.time;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.lifeofnote.db.time.SelectTimeEntity;

import java.util.ArrayList;
import java.util.List;

public class SelectTimesViewModel extends ViewModel {

    private MutableLiveData<List<SelectTimeEntity>> selectTimeEntity;

    public MutableLiveData<List<SelectTimeEntity>> getSelectTimeEntity() {
        if (selectTimeEntity == null) {
            selectTimeEntity = new MutableLiveData<>();
            justLoadData();
        }
        return selectTimeEntity;
    }

    private void justLoadData() {
        List<SelectTimeEntity> datas = new ArrayList<>();
        datas.add(new SelectTimeEntity("一月"));
        datas.add(new SelectTimeEntity("二月"));
        datas.add(new SelectTimeEntity("三月"));
        datas.add(new SelectTimeEntity("四月"));
        datas.add(new SelectTimeEntity("五月"));
        datas.add(new SelectTimeEntity("六月"));
        datas.add(new SelectTimeEntity("七月"));
        datas.add(new SelectTimeEntity("八月"));
        datas.add(new SelectTimeEntity("九月"));
        datas.add(new SelectTimeEntity("十月"));
        datas.add(new SelectTimeEntity("十一月"));
        datas.add(new SelectTimeEntity("十二月"));
        selectTimeEntity.setValue(datas);
    }
}
