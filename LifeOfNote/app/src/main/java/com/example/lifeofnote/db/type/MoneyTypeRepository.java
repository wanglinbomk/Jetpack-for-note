package com.example.lifeofnote.db.type;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class MoneyTypeRepository {
    private LiveData<List<MoneyTypeEntity>> typeEntitys;
    private MoneyTypeDao moneyTypeDao;

    public MoneyTypeRepository(Context context) {
        MoneyDatabase moneyDatabase = MoneyDatabase.getInstance(context);
        moneyTypeDao = moneyDatabase.getMoneyTypeDao();
    }

    public LiveData<List<MoneyTypeEntity>> getMoneyTypes(int type) {
        typeEntitys = moneyTypeDao.getTypeList(type);
        return typeEntitys;
    }

    public void insertMoneyType(MoneyTypeEntity data) {
        new InsertAsyncTask(moneyTypeDao).execute(data);
    }

    static class InsertAsyncTask extends AsyncTask<MoneyTypeEntity, Void, Void> {

        private MoneyTypeDao moneyTypeDao;

        public InsertAsyncTask(MoneyTypeDao moneyTypeDao) {
            this.moneyTypeDao = moneyTypeDao;
        }

        @Override
        protected Void doInBackground(MoneyTypeEntity... moneyTypeEntities) {
            moneyTypeDao.insertTypes(moneyTypeEntities);
            return null;
        }
    }

}
