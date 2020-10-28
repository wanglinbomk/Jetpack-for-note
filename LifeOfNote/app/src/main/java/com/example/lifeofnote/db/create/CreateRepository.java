package com.example.lifeofnote.db.create;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import androidx.lifecycle.LiveData;

import com.example.lifeofnote.base.APP;

import java.util.List;

public class CreateRepository {
    private LiveData<List<CreateEntity>> createEntity;
    private LiveData<List<CreateGroupEntity>> createCroupEntity;
    private CreateDao createDao;

    public CreateRepository(Context context) {
        CreateDatabase createDatabase = CreateDatabase.getInstance(context);
        createDao = createDatabase.getCreateDao();
    }
    //更具类型获取我的记录
    public LiveData<List<CreateEntity>> getCreateByType(int type) {
        switch (type) {
            case 0:
                createEntity = createDao.getAllTypeList();
                break;
            default:
                createEntity = createDao.getCreateByType(type);
                break;
        }
        return createEntity;
    }

    //获取记录
    public LiveData<List<CreateGroupEntity>> getHomeData(int startTime, int endTime){
        createCroupEntity = createDao.getHomeDay(startTime, endTime);
        return createCroupEntity;
    }


    //创建记录
    public void insertCreateEntity(CreateEntity createEntiy){
        new InsertCreateEntity(createDao).execute(createEntiy);
    }

    static class InsertCreateEntity extends AsyncTask<CreateEntity, Void, Void>{

        private CreateDao createDao;

        public InsertCreateEntity(CreateDao createDao) {
            this.createDao = createDao;
        }

        @Override
        protected Void doInBackground(CreateEntity... createEntities) {
            createDao.insertCreate(createEntities);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(APP.get().getApplicationContext(),"插入成功！",Toast.LENGTH_SHORT).show();
        }
    }

    static class deleteCreateEntity extends AsyncTask<CreateEntity, Void, Void>{

        private CreateDao createDao;

        public deleteCreateEntity(CreateDao createDao) {
            this.createDao = createDao;
        }

        @Override
        protected Void doInBackground(CreateEntity... createEntities) {
            createDao.deleteCreate(createEntities);
            return null;
        }
    }

  /*  static class upDataCreateEntity extends AsyncTask<CreateEntity, Void, Void>{

        private CreateDao createDao;

        public upDataCreateEntity(CreateDao createDao) {
            this.createDao = createDao;
        }

        @Override
        protected Void doInBackground(CreateEntity... createEntities) {
            createDao.updateCreate(createEntities);
            return null;
        }
    }*/
}
