package com.shark.bullyshark;

import android.util.Log;

import com.android.internal.os.RuntimeInit;
import com.android.internal.os.ZygoteInit;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;

public class BulllyBridge {
    public static final String TAG = "SharkChilli";
    /*package*/ static boolean isZygote = true;

    public static final ClassLoader BOOTCLASSLOADER = ClassLoader.getSystemClassLoader();

    public static void main(String[] args) {
        Log.i(TAG, "BulllyBridge main is call!!!");
//        SELinuxHelper.initOnce();
//        SELinuxHelper.initForProcess(null);
        // Call the original startup code
//        System.load("/system/lib/libsandhook.so");
//        XposedHelpers.findAndHookMethod("com.shark.bullyshark.MouduleUtils", BOOTCLASSLOADER, "loadModule",
//                String.class, ClassLoader.class, new XC_MethodHook() {
//                    @Override
//                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                        super.afterHookedMethod(param);
//                        Log.i(TAG, "afterHookedMethod: HOOK !!!!!!!!!!!!");
//                    }
//                });
//        MouduleUtils.loadModule("/system/framework/1.apk", BOOTCLASSLOADER);
        if (isZygote) {
            ZygoteInit.main(args);
        } else {
            RuntimeInit.main(args);
        }
    }
}
