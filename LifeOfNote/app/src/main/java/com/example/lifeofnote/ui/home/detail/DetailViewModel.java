package com.example.lifeofnote.ui.home.detail;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.lifeofnote.db.create.CreateEntity;
import com.example.lifeofnote.db.create.CreateRepository;

import java.util.List;

public class DetailViewModel extends AndroidViewModel {
    private CreateRepository createRepository;

    public DetailViewModel(@NonNull Application application) {
        super(application);
        createRepository = new CreateRepository(application.getApplicationContext());
    }

    public LiveData<List<CreateEntity>> getCreateDate(int startTime, int endTime) {
        Log.i("xyz","create"+startTime);
        Log.i("xyz","end"+endTime);
        return (createRepository.getHomeData(startTime-1,endTime+1));
    }

}