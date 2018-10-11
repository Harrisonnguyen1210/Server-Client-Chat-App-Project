/**
 * Create by Hung Nguyen @author, ID: 1706198
 * LogInActivity will handle the socket connection, user log in and checking if there is internet connection
 */
package com.example.android.chatserverclientproject

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*

class LogInActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        buttonLogIn.setOnClickListener {
            val cm = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
            val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true
            if (isConnected) {
                if (!editTextName.text.isEmpty()) {
                    val t = Thread(LogInTask(":user ${editTextName.text}", this))
                    t.start()
                } else {
                    Toast.makeText(this, "Please type user name", Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(this, "Please check your internet connection", Toast.LENGTH_LONG).show()
            }

        }
    }

    //If username has already registered
    fun reTypeUserName(string: String) {
        runOnUiThread {
            Toast.makeText(this, string, Toast.LENGTH_LONG).show()
        }
    }

    //Change to ChatDialog activity
    fun changeActivity(string: String) {
        runOnUiThread {
            Toast.makeText(this, string, Toast.LENGTH_LONG).show()
            val intent = Intent(this, ChatDialogActivity::class.java)
            intent.putExtra("Username", editTextName.text.toString())
            editTextName.text = null
            startActivity(intent)
        }
    }
}
