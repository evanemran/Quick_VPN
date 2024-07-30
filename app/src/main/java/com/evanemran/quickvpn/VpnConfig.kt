package com.evanemran.quickvpn

object VpnConfig {
    fun createVpnProfile(): VpnProfile {
        val profile: VpnProfile = VpnProfile()
        profile.mName = "OpenVPNProfile"
        profile.mServerName = "public-vpn-135.opengw.net"
        profile.mServerPort = 1195
        profile.mUsername = "your_username"
        profile.mPassword = "your_password"
        profile.mAuthenticationType = VpnProfile.TYPE_PASSWORD
        profile.mProtocol = VpnProfile.PROTOCOL_UDP

        // Set additional parameters if necessary
        profile.mUseLzo = true // LZO Compression
        profile.mRemoteCN = "remote_server_cn" // Server Common Name

        return profile
    }
}