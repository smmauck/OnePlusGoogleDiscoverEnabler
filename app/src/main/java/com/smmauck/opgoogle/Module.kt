package com.smmauck.opgoogle

import de.robv.android.xposed.IXposedHookLoadPackage
import de.robv.android.xposed.XposedBridge
import de.robv.android.xposed.XposedHelpers.findClass
import de.robv.android.xposed.XposedHelpers.setStaticBooleanField
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam

class Module: IXposedHookLoadPackage {
    override fun handleLoadPackage(lpparam: LoadPackageParam) {
        if (lpparam.packageName != "net.oneplus.launcher")
            return

        XposedBridge.log("[smmauck] Loaded app: " + lpparam.packageName)

        setStaticBooleanField(
            findClass("net.oneplus.launcher.config.FeatureFlags", lpparam.classLoader),
            "CUSTOMIZE_LEFT_MOST_SCREEN_ENABLED",
            true
        )
    }
}