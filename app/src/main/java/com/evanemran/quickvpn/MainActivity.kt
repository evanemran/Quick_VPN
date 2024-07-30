package com.evanemran.quickvpn

import android.content.Intent
import android.net.VpnService
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val connectButton = findViewById<Button>(R.id.connect_button)
        connectButton.setOnClickListener { prepareVpn() }
    }

    private fun prepareVpn() {
        val intent = VpnService.prepare(this)
        if (intent != null) {
            startActivityForResult(intent, 0)
        } else {
            onActivityResult(0, RESULT_OK, null)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == RESULT_OK) {
            connectToL2tpIpsecVpn()
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun connectToL2tpIpsecVpn() {
        val intent = Intent(
            this,
            MyVpnService::class.java
        )
        startService(intent)
    }
}