/**
 * Create by Hung Nguyen @author, ID: 1706198
 * ChatMessages.kt represents each message of users
 */
class ChatMessages(private val message: String, private val userName: String) {
    override fun toString(): String {
        return "$userName: $message"
    }
}