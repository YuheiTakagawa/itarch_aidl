package com.itarch.roto.modifyspell

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.*
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    private var mService : IMyAidlInterface? = null
    val TAG : String = "app"

    private val mConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            Log.d(TAG, "service connected")
            mService = IMyAidlInterface.Stub.asInterface(service)
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            Log.d(TAG, "service disconnected")
            mService = null
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById(R.id.button) as Button
        val show = findViewById(R.id.textView) as TextView
        val editText = findViewById(R.id.editText) as EditText

        val intent = Intent(this, MyService::class.java)
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE)

        button.setOnClickListener {
            var text = editText.text.toString()
            val ret : String = mService?.modifySpell(text) ?: "error"
            val color : String = mService?.calcColor() ?: "#000000"
            Log.d(TAG, "Spell:" + ret + ", Color:" + color)

            show.text = ret
            show.setTextColor(Color.parseColor(color))
        }

    }
}
