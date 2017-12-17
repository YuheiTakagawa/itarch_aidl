package com.itarch.roto.modifyspell

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

/**
 * Created by roto on 2017/12/17.
 */


class MyService : Service() {
    override fun onCreate(){
        super.onCreate()
    }

    override fun onBind(intent : Intent): IBinder {
        return mBinder
    }

    val TAG = "Service"
    private val mBinder = object : IMyAidlInterface.Stub() {
        override fun modifySpell(text: String) : String {
            Log.d(TAG, "StartModifySpell")
            return text
        }

        override fun calcColor() : Int {
            Log.d(TAG, "startCalcColor")
            return 0
        }
    }
}
