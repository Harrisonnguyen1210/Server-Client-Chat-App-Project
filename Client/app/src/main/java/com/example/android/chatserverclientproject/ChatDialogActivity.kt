/**
 * Create by Hung Nguyen @author, ID: 1706198
 * Chat dialog activity is to receiving and sending messages between client and server
 * Moreover, it inflates the menu in toolBar and handles the camera intent
 */
package com.example.android.chatserverclientproject

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_chat_dialog.*
import java.util.*

class ChatDialogActivity : AppCompatActivity() {
    private val REQUEST_IMAGE_CAPTURE = 1
    private val myArrayList = ArrayList<Message>()
    private lateinit var messageListCustomAdapter: MessageListCustomAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_dialog)
        setSupportActionBar(toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        val user = intent.getStringExtra("Username")
        toolBar_title.text = user
        messageListCustomAdapter = MessageListCustomAdapter(this, myArrayList, user)
        listViewMessage.adapter = messageListCustomAdapter
        receiveMess()
        buttonEnter.setOnClickListener {
            if (editText.text.toString().startsWith(':')) {
                Toast.makeText(this, "\":\" in the beginning of the message is not accepted", Toast.LENGTH_LONG).show()
            } else {
                val t = Thread(ClientSend(editText.text.toString()))
                t.start()
                editText.text = null
            }
        }
    }

    //Handle the back button on toolBar
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    //Disconnecting the socket when back button is pressed
    override fun onBackPressed() {
        MySocket.isConnect = false
        val t = Thread(ClientSend(":quit"))
        t.start()
        super.onBackPressed()
    }

    //Inflating menu option on toolBar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.chat_dialog_action_bar_menu, menu)
        return true
    }

    //Handle the event when items on menu option are clicked
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when {
            item!!.itemId == R.id.mainActionCamera -> dispatchTakePictureIntent()
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
        return true
    }

    //Checking if there is an application to handle the camera intent and start the image capture action
    private fun dispatchTakePictureIntent() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(packageManager)?.also {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
            }
        }
    }

    //Retrieve the image back from intent and set the imageView
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            val imageBitmap = data!!.extras.get("data") as Bitmap
            profile_image.setImageBitmap(imageBitmap)
        }
    }

    //Run a thread to receive message
    private fun receiveMess() {
        val t = Thread(ClientReceive(this))
        t.start()
    }

    //This function will update the listView every time there is a new message
    fun upDateList(message: Message) {
        runOnUiThread {
            myArrayList.add(message)
            messageListCustomAdapter.notifyDataSetChanged()
            listViewMessage.setSelection(listViewMessage.adapter.count - 1)
        }
    }
}












