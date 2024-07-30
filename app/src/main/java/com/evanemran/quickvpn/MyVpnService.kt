package com.evanemran.quickvpn

import android.content.Intent
import android.net.VpnService
import android.os.ParcelFileDescriptor
import java.io.IOException
import java.net.InetSocketAddress
import java.nio.channels.DatagramChannel


class MyVpnService : VpnService(), Runnable {
    private var thread: Thread? = null
    private var vpnInterface: ParcelFileDescriptor? = null

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        if (thread != null) {
            thread!!.interrupt()
        }
        thread = Thread(this, "VPNServiceThread")
        thread!!.start()
        return START_STICKY
    }

    override fun onDestroy() {
        if (thread != null) {
            thread!!.interrupt()
        }
        closeVpnInterface()
        super.onDestroy()
    }

    override fun run() {
        try {
            // Configure the VPN interface
            val builder: Builder = Builder()
            builder.setMtu(1500)
            builder.addAddress("10.0.0.2", 32)
            builder.addRoute("0.0.0.0", 0)
            builder.addDnsServer("8.8.8.8")
            builder.addDnsServer("8.8.4.4")

            builder.setSession("MyVPN")
            builder.setConfigureIntent(null)

            // Apply configuration
            vpnInterface = builder.establish()

            // Connect to the VPN server
            val tunnel = DatagramChannel.open()
            tunnel.connect(
                InetSocketAddress(
                    "219.100.37.93",
                    1701
                )
            ) // Use your VPN server IP and port

            // Implement the VPN data transmission here
            // ...
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun closeVpnInterface() {
        if (vpnInterface != null) {
            try {
                vpnInterface!!.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }
}