/**
 * Create by Hung Nguyen @author, ID: 1706198
 * ChatServer.kt creates a socket and start a thread for every connection to the socket. Each thread will take in a CommandInterpreter object
 */
import java.net.ServerSocket

class ChatServer {
    fun serve() {
        val ss = ServerSocket(57025, 5)
        println("We have port " + ss.localPort)
        val myChatConsole = ChatConsole()
        ChatHistory.registerObserver(myChatConsole)
        while (true) {
            val s = ss.accept()
            println("new connection " + s.inetAddress.hostAddress + " " + s.port)
            val t = Thread(CommandInterpreter(s.getInputStream(), s.getOutputStream()))
            t.start()
        }
    }
}