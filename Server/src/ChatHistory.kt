/**
 * Create by Hung Nguyen @author, ID: 1706198
 * ChatHistory.kt file is to register, deregister, and notify all of the observers.
 * Furthermore, save all of the messages in one Set
 */
import java.time.LocalDateTime

object ChatHistory : ChatHistoryObservable {
    private val observers = mutableSetOf<ChatHistoryObserver>()
    private val current = LocalDateTime.now()
    private var listOfMessagesHistory = mutableListOf<String>()

    override fun registerObserver(observer: ChatHistoryObserver) {
        observers.add(observer)
    }

    override fun deRegisterObserver(observer: ChatHistoryObserver) {
        observers.remove(observer)
    }

    override fun notifyObservers(message: ChatMessages) {
        observers.forEach { it.newMessage(message) }
    }

    fun insert(message: ChatMessages) {
        listOfMessagesHistory.add(message.toString())
    }

    override fun toString(): String {
        var string = ""
        for (value in listOfMessagesHistory) {
            string += "$value at $current \n\r"
        }
        return string
    }
}