package com.example.lifeofnote.base;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import androidx.annotation.NonNull;

import java.util.Set;

public class PrefUtils {

    private static SharedPreferences getDefaultSp() {
        return PreferenceManager.getDefaultSharedPreferences(APP.get());
    }

    private static SharedPreferences getUserInfoSp() {
        return APP.get().getSharedPreferences(BoggleConstants.FILE_USER_INFO, Context.MODE_PRIVATE);
    }

    public static void setInUserInfo(@NonNull String key, @NonNull Object value) {
        SharedPreferences.Editor edit = getUserInfoSp().edit();
        set(edit, key, value);
    }

    public static void setInDefault(@NonNull String key, @NonNull Object value) {
        SharedPreferences.Editor edit = getDefaultSp().edit();
        set(edit, key, value);
    }

    private static void set(SharedPreferences.Editor edit, @NonNull String key, @NonNull Object value) {
        if (value instanceof String) {
            edit.putString(key, (String) value);
        } else if (value instanceof Integer) {
            edit.putInt(key, (Integer) value);
        } else if (value instanceof Long) {
            edit.putLong(key, (Long) value);
        } else if (value instanceof Boolean) {
            edit.putBoolean(key, (Boolean) value);
        } else if (value instanceof Float) {
            edit.putFloat(key, (Float) value);
        } else if (value instanceof Set) {
            edit.putStringSet(key, (Set<String>) value);
        } else {
            throw new IllegalArgumentException(String.format("Type of value unsupported key=%s, value=%s", key, value));
        }
        edit.apply();
    }


    public static boolean isFirstIn() {
        return getDefaultSp().getBoolean(BoggleConstants.FIRST_IN, true);
    }

}
