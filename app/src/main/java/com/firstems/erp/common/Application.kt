package com.firstems.erp.common

import android.app.Application
import com.firstems.erp.sharedpreferences.SharedPreferencesManager

class MyApplication : Application()  {
    override fun onCreate() {
        super.onCreate()
        SharedPreferencesManager.init(this)
        if (!SharedPreferencesManager.baseDomain!!.equals("")){
            //ApiService.initClient(SharedPreferencesManager.baseDomain!!)
        }
    }
}