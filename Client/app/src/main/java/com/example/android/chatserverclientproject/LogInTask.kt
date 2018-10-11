/**
 * Create by Hung Nguyen @author, ID: 1706198
 * logInTask will handle the socket log in and username input
 */
package com.example.android.chatserverclientproject

import java.io.PrintStream
import java.util.*

class LogInTask(private val username: String, private val activity: LogInActivity): Runnable {
    override fun run() {
        MySocket.connecting()
        val printer = PrintStream(MySocket.mySocket.getOutputStream())
        printer.println(username)
        val scanner = Scanner(MySocket.mySocket.getInputStream())
        val string = scanner.nextLine()
        if(string == "User name already registered. Do it again"){
            activity.reTypeUserName(string)
        }else{
            activity.changeActivity(string)
        }
    }
}