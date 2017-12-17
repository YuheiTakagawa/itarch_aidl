package com.itarch.roto.modifyspell

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import java.util.*

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
            var ret : String = ""
            for (c in text) {
                if(c.isUpperCase())
                    ret += c.toLowerCase()
                else
                    ret += c.toUpperCase()
            }
            Log.d(TAG, "ret:" + ret)
            return ret
        }

        override fun calcColor() : String {
            Log.d(TAG, "startCalcColor")
            val calendar = Calendar.getInstance()
            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)
            val second = calendar.get(Calendar.SECOND)

            var no = second + minute * 60 + hour * 60 * 60
            var now = no * Integer.parseInt("ffffff", 16) / 86400
            var ret = String.format("%06x", now)
            Log.d(TAG, "color:" + ret + ", now:" + no)
            //return ret
            return "#" + ret
        }
    }
}
