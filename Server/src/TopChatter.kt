/**
 * Create by Hung Nguyen @author, ID: 1706198
 * TopChatter.kt file serves as a monitor to print out the list of top 4 chatter with the most messages
 */
object TopChatter {
    private var listOfTopChatter = mutableMapOf<String, Int>()
    fun updateListTopChatter(username: String, number: Int) {
        listOfTopChatter[username] = number
    }

    fun removeUserInTopChatterList(username: String) {
        listOfTopChatter.remove(username)
    }

    fun printListOfTopChatter() {
        val myMap = listOfTopChatter.map { Pair(it.key, it.value) }.sortedByDescending { it.second }.take(4)
        var list = ""
        for ((key, value) in myMap) {
            list += "User $key has $value messages\n"
        }
        println("Active Users:\n$list")
    }
}