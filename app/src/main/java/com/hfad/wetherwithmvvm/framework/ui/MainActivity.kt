package com.hfad.wetherwithmvvm.framework.ui

import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hfad.wetherwithmvvm.R
import com.hfad.wetherwithmvvm.framework.receivers.MainBroadcastReceiver
import com.hfad.wetherwithmvvm.framework.ui.main.MainFragment

class MainActivity : AppCompatActivity() {

    var broadcastReceiver: MainBroadcastReceiver? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
        broadcastReceiver = MainBroadcastReceiver()
    }

    override fun onStart() {
        super.onStart()
        registerReceiver(broadcastReceiver, IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"))
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(broadcastReceiver)
    }
}