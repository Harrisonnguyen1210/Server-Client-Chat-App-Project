/**
 * Create by Hung Nguyen @author, ID: 1706198
 * ClientSend is responsible for printing the messages to server
 */
package com.example.android.chatserverclientproject

import java.io.PrintStream

class ClientSend(val message: String): Runnable {
    override fun run() {
        val printer = PrintStream(MySocket.mySocket.getOutputStream())
        printer.println(message)
    }
}