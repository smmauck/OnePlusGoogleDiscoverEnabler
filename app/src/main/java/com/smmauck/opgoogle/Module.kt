package com.smmauck.opgoogle

import de.robv.android.xposed.IXposedHookLoadPackage
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedBridge
import de.robv.android.xposed.XposedHelpers
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam


class Module : IXposedHookLoadPackage {
    override fun handleLoadPackage(lpparam: LoadPackageParam) {
        if (lpparam.packageName != "net.oneplus.launcher")
            return

        XposedBridge.log("[smmauck] Loaded app: " + lpparam.packageName)

        XposedHelpers.findAndHookMethod(
            "net.oneplus.launcher.config.SkuHelper",
            lpparam.classLoader,
            "showDiscover",
            object : XC_MethodHook() {
                @Throws(Throwable::class)
                override fun afterHookedMethod(param: MethodHookParam) {
                    param.result = true
                }
            }
        )
    }
}