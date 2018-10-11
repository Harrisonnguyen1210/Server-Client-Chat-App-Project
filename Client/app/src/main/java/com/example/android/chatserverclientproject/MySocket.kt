/**
 * Create by Hung Nguyen @author, ID: 1706198
 * MySocket singleton store the socket and have connecting funtion to connect to the server
 */
package com.example.android.chatserverclientproject

import java.net.InetAddress
import java.net.Socket

object MySocket {
    private const val ServerPort = 57025
    private const val IPADDRESS = "192.168.0.3"
    lateinit var mySocket: Socket
    var isConnect = false
    fun connecting() {
        isConnect = true
        try {
            val inetAddress = InetAddress.getByName(IPADDRESS)
            mySocket = Socket(inetAddress, ServerPort)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}