/**
 * Create by Hung Nguyen @author, ID: 1706198
 * CommandInterpreter.kt file is to control the user inputs and executes each command from the users,
 * including login, logout processes, register, deregister each command interpreter
 */
import java.io.InputStream
import java.io.OutputStream
import java.io.PrintStream
import java.util.Scanner

class CommandInterpreter(inputStream: InputStream, outputStream: OutputStream) : Runnable, ChatHistoryObserver {
    private var string: String = ""
    private var input = Scanner(inputStream)
    private val printer = PrintStream(outputStream, true)
    private var userName: String = ""
    private var numberOfMess = 0
    override fun run() {

        //printer.println("Welcome to 2018 chat server")
        //printer.println("Please, enter your command")
        string = input.nextLine()
        //Register Observer for CommandInterpreter
        ChatHistory.registerObserver(this)
        typeUserName()
        startingMessageDialog()
        //Deregister Observer for CommandInterpreter when userName is set
        ChatHistory.deRegisterObserver(this)
        endingMessageDialog()
        printer.println("Goodbye\n\r" + "Connection closed by foreign host.")
        input.close()
    }

    //Ask the user to set userName
    private fun typeUserName() {
        if (isTheUserNameValid()) {
            val userLogInMessages = ChatMessages("User $userName has logged in", "admin")
            ChatHistory.notifyObservers(userLogInMessages)
            //printer.println("User name set to $userName")
        } else {
            string = input.nextLine()
            typeUserName()
        }
    }

    //Starting the message dialog when userName is set until user type ":quit"
    private fun startingMessageDialog() {
        string = input.nextLine()
        while (string != ":quit") {
            if (string.trim().startsWith(':')) {
                if (string.contains(":messages") || string.contains(":users") || string.contains(":help")) {
                    when (string.trim()) {
                        ":messages" -> printer.println(ChatHistory)
                        ":users" -> printer.println(Users)
                        ":help" -> printer.println("Type :messages to see messages history\n\r" +
                                "Type :users to see the active user names\n\r" +
                                "Type :help to see the command hint")
                    }
                } else {
                    printer.println("Did not get it $string\n\rType :help to see the command hint\n\r")
                }
            } else {
                val myChatMessage = ChatMessages(string, userName)
                numberOfMess++
                TopChatter.updateListTopChatter(userName, numberOfMess)
                ChatHistory.notifyObservers(myChatMessage)
                ChatHistory.insert(myChatMessage)
            }
            string = input.nextLine()
        }
    }

    //Ending the message dialog
    private fun endingMessageDialog() {
        val userQuitMessages = ChatMessages("User $userName has left", "admin")
        ChatHistory.notifyObservers(userQuitMessages)
        Users.removeUsername(userName)
        notifyUserLogOutSuccessfully()
    }

    //Checking if the userName is valid or not
    private fun isTheUserNameValid(): Boolean {
        try {
            if (string.substring(0..4) != ":user") {
                printer.println("User name not set. Use command :user to set it\n")
                return false
            } else if (string.trim() == ":user") {
                printer.println("User name not set: no user name specified\n")
                return false
            } else if (string.substring(0..4) == ":user" && string[5] != ' ') {
                printer.println("User name must follow syntax :user name\n")
                return false
            } else {
                userName = string.substring(6)
                if (Users.checkUsernameNotExist(userName)) {
                    Users.insertUsername(userName)

                    notifyUserLogInSuccessfully()

                } else {
                    printer.println("User name already registered. Do it again\n")
                    return false
                }
                return true
            }
        } catch (e: Exception) {
            printer.println("User name not set. Use command :user to set it\n")
            return false
        }
    }

    //Notify Admin when userName is set successfully
    private fun notifyUserLogInSuccessfully() {
        println("User: $userName has logged in")
        TopChatter.updateListTopChatter(userName, numberOfMess)
        TopChatter.printListOfTopChatter()
    }

    //Notify Admin when user log out successfully
    private fun notifyUserLogOutSuccessfully() {
        println("\nUser $userName ends chat")
        TopChatter.removeUserInTopChatterList(userName)
        TopChatter.printListOfTopChatter()
    }

    // Override ChatHistoryObserver method print messages for each commandInterpreter
    override fun newMessage(message: ChatMessages) {
        printer.println(message.toString())
    }
}