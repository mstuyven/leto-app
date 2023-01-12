package com.mirostuyven.leto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mirostuyven.leto.network.LetoApi
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}