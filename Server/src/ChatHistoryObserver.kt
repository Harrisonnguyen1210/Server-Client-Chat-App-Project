/**
 * Create by Hung Nguyen @author, ID: 1706198
 * ChatHistoryObserver.kt interface has 1 function to interact with observers
 */
interface ChatHistoryObserver {
    fun newMessage(message: ChatMessages)
}