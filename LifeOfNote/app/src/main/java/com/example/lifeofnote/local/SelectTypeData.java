package com.example.lifeofnote.local;

import com.example.lifeofnote.db.type.MoneyTypeEntity;

import java.util.ArrayList;
import java.util.List;

public class SelectTypeData {

    private List<MoneyTypeEntity> local;

    public SelectTypeData() {
        justLoadData();
    }

    private void justLoadData() {
        if(local == null){
            local = new ArrayList<>();
            local.add(new MoneyTypeEntity(-1,"服饰美容")); //1
            local.add(new MoneyTypeEntity(-1,"餐饮"));  //2
            local.add(new MoneyTypeEntity(-1,"交通"));  //3
            local.add(new MoneyTypeEntity(-1,"住房缴费"));//4
            local.add(new MoneyTypeEntity(-1,"购物"));//5
            local.add(new MoneyTypeEntity(-1,"生活服务"));//6
            local.add(new MoneyTypeEntity(-1,"学习"));///7
            local.add(new MoneyTypeEntity(-1,"娱乐"));//8
            local.add(new MoneyTypeEntity(-1,"运动"));//9
            local.add(new MoneyTypeEntity(-1,"旅游"));//10
            local.add(new MoneyTypeEntity(-1,"酒店"));//11
            local.add(new MoneyTypeEntity(-1,"亲子"));//12
            local.add(new MoneyTypeEntity(-1,"宠物"));//13
            local.add(new MoneyTypeEntity(-1,"医疗"));//14
            local.add(new MoneyTypeEntity(-1,"其他人情"));//15
            local.add(new MoneyTypeEntity(-1,"红包"));//16
            local.add(new MoneyTypeEntity(-1,"转账"));//17
            local.add(new MoneyTypeEntity(-1,"亲属卡"));//18
            local.add(new MoneyTypeEntity(-1,"其他"));//19
            local.add(new MoneyTypeEntity(1,"其他人情"));//19
            local.add(new MoneyTypeEntity(1,"生意"));//19
            local.add(new MoneyTypeEntity(1,"退款"));//19
            local.add(new MoneyTypeEntity(1,"工资"));//19
            local.add(new MoneyTypeEntity(1,"奖金"));//19
            local.add(new MoneyTypeEntity(1,"其他"));//19
            local.add(new MoneyTypeEntity(1,"红包"));//19
            local.add(new MoneyTypeEntity(1,"转账"));//19
            local.add(new MoneyTypeEntity(1,"商家转账"));//19
            local.add(new MoneyTypeEntity(1,"神秘收入"));//19
        }
    }

    public List<MoneyTypeEntity> getLocal() {
        return local;
    }

    public void setLocal(List<MoneyTypeEntity> local) {
        this.local = local;
    }
}
