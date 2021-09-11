package com.shark.bullyshark;

import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

import dalvik.system.DexFile;
import dalvik.system.PathClassLoader;

public class MouduleUtils {
    public static final String TAG = "SharkChilli";

    public static void loadModule(String apk, ClassLoader topClassLoader) {
//        ClassLoader parent;
//        // 得到父ClassLoader
//        while ((parent = topClassLoader.getParent()) != null) {
//            topClassLoader = parent;
//        }

        Log.i(TAG, " apk："+apk);

        //判断注入模块文件是否存在
        if (!new File(apk).exists()) {
            Log.e(TAG, "  File does not exist");
            return;
        }

        ClassLoader mcl = new PathClassLoader(apk, topClassLoader);
        try {
            Class<?> moduleClass = mcl.loadClass("com.app.service.Entry");
            Object module = moduleClass.newInstance();
            Method onLoad = moduleClass.getDeclaredMethod("onLoad", ClassLoader.class);
            onLoad.invoke(module, topClassLoader);
            Log.e(TAG, " com.app.service.Entry success");

        } catch (Exception e) {
            Log.e(TAG, " getDeclaredMethod error");
        }


    }

    static void closeSilently(DexFile dexFile) {
        if (dexFile != null) {
            try {
                dexFile.close();
            } catch (IOException ignored) {
            }
        }
    }
}
