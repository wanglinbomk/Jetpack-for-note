package com.example.lifeofnote.utils;

import android.content.Context;
import android.widget.Toast;

import com.example.lifeofnote.base.APP;

/**
 * author : Boggle
 * e-mail : 1105059963@qq.com
 * date   : 2019-11-2514:41
 * desc   :
 * version: 1.0
 */
public class ToastMaster {

    private static Toast sToast = null;

    private ToastMaster() {

    }

    public static void toast(String content) {
        showToast(Toast.makeText(APP.get(), content, Toast.LENGTH_SHORT));
    }

    public static void toastNetWorkError(){
        showToast(Toast.makeText(APP.get(),"网络连接失败，请检查您的网络连接", Toast.LENGTH_SHORT));
    }

    public static void toastNotKnowError(){
        showToast(Toast.makeText(APP.get(),"由于未知原因，网络请求失败", Toast.LENGTH_SHORT));
    }

    public static void toast(Context context, String content) {
        showToast(Toast.makeText(context, content, Toast.LENGTH_SHORT));
    }

    public static void toast(Context context, String content, int duration) {
        showToast(Toast.makeText(context, content, duration));
    }

    public static void showToast(Toast toast) {
        if (sToast != null)
            sToast.cancel();
        sToast = toast;
        sToast.show();
    }

    public static void cancelToast() {
        if (sToast != null)
            sToast.cancel();
        sToast = null;
    }
}
