/**
 * Create by Hung Nguyen @author, ID: 1706198
 * ChatConsole.kt file implements the ChatHistoryObserver to print out every message for each user's dialog
 */
import java.time.LocalDateTime

class ChatConsole : ChatHistoryObserver {
    private val current = LocalDateTime.now()

    override fun newMessage(message: ChatMessages) {
        println("$message at $current")
    }
}