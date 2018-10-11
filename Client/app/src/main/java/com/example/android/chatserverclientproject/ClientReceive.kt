/**
 * Create by Hung Nguyen @author, ID: 1706198
 * ClientReceive will receive message to server and update list of message to listView
 */
package com.example.android.chatserverclientproject

import java.text.DateFormat
import java.text.DateFormat.SHORT
import java.util.*

class ClientReceive(private val activity: ChatDialogActivity) : Runnable {

    override fun run() {
        val scanner = Scanner(MySocket.mySocket.getInputStream())
        while (MySocket.isConnect) {
            val input = scanner.nextLine()
            val user = input.substringBefore(':', "Hung")
            val string = input.substringAfter(':', "Haha")
            val timeFormat = DateFormat.getTimeInstance(SHORT)
            val message = Message(user, string, timeFormat.format(Date()))
            activity.upDateList(message)
        }
    }
}
