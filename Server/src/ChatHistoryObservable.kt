/**
 * Create by Hung Nguyen @author, ID: 1706198
 * ChatHistoryObservable.kt interface has 3 functions to interact with observable object
 */
interface ChatHistoryObservable {
    fun registerObserver(observer: ChatHistoryObserver)
    fun deRegisterObserver(observer: ChatHistoryObserver)
    fun notifyObservers(message: ChatMessages)
}