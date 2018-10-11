/**
 * Create by Hung Nguyen @author, ID: 1706198
 * User.kt stores each user in a hashSet and prints it out when needed
 */
object Users {
    private val userList = hashSetOf<String>()
    fun insertUsername(usersAdd: String) {
        userList.add(usersAdd)
    }

    fun removeUsername(usersRemove: String) {
        userList.remove(usersRemove)
    }

    fun checkUsernameNotExist(userName: String): Boolean {
        return !userList.contains(userName)
    }

    override fun toString(): String {
        var usersString = ""
        for (value in userList) {
            usersString += value + "\n\r"
        }
        return "Users:\n\r${usersString.trimStart()}"
    }
}