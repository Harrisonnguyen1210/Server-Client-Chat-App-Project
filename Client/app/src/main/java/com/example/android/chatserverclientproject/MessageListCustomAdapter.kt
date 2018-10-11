/**
 * Create by Hung Nguyen @author, ID: 1706198
 * MessageListCustomAdapter handles the inflation of 3 layouts: item_listview_right, item_admin and item_listview_left
 */
package com.example.android.chatserverclientproject

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class MessageListCustomAdapter(private val context: Context, private val array: ArrayList<Message>, private val user: String) : BaseAdapter() {
    private val MESSAGE_RIGHT = 0
    private val MESSAGE_ADMIN = 1
    private val MESSAGE_LEFT = 2

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var myConvertView = convertView
        val holderLeft: ViewHolderLeft
        val holderRight: ViewHolderRight
        val holderAdmin: ViewHolderAdmin
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        when {
            getItemViewType(position) == 0 -> {
                if (myConvertView == null) {
                    holderRight = ViewHolderRight()
                    myConvertView = inflater.inflate(R.layout.item_listview_right, null, true)
                    holderRight.textViewMessageHolder = myConvertView!!.findViewById(R.id.textViewMessage)
                    holderRight.textViewTimeHolder = myConvertView.findViewById(R.id.textViewTime)
                    myConvertView.tag = holderRight

                } else {
                    holderRight = myConvertView.tag as ViewHolderRight
                }
                holderRight.textViewTimeHolder!!.text = array[position].time
                holderRight.textViewMessageHolder!!.text = array[position].message
                return myConvertView
            }
            getItemViewType(position) == 1 -> {
                if (myConvertView == null) {
                    holderAdmin = ViewHolderAdmin()
                    myConvertView = inflater.inflate(R.layout.item_admin, null, true)
                    holderAdmin.textViewLogInHolder = myConvertView!!.findViewById(R.id.textViewMessage)
                    myConvertView.tag = holderAdmin

                } else {
                    holderAdmin = myConvertView.tag as ViewHolderAdmin
                }
                holderAdmin.textViewLogInHolder!!.text = array[position].message
                return myConvertView
            }
            else -> {
                if (myConvertView == null) {
                    holderLeft = ViewHolderLeft()
                    myConvertView = inflater.inflate(R.layout.item_listview_left, null, true)
                    holderLeft.textViewMessageHolder = myConvertView!!.findViewById(R.id.textViewMessage)
                    holderLeft.textViewUserHolder = myConvertView.findViewById(R.id.textViewMessageName)
                    holderLeft.textViewTimeHolder = myConvertView.findViewById(R.id.textViewTime)
                    myConvertView.tag = holderLeft
                } else {
                    holderLeft = myConvertView.tag as ViewHolderLeft
                }
                holderLeft.textViewMessageHolder!!.text = array[position].message
                holderLeft.textViewUserHolder!!.text = array[position].user
                holderLeft.textViewTimeHolder!!.text = array[position].time
                return myConvertView
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when {
            array[position].user == user -> MESSAGE_RIGHT
            array[position].user == "admin" -> MESSAGE_ADMIN
            else -> {
                MESSAGE_LEFT
            }
        }
    }

    override fun getViewTypeCount(): Int {
        return 3
    }

    override fun getItem(position: Int): Any {
        return array[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return array.size
    }

}

class ViewHolderAdmin {
    var textViewLogInHolder: TextView? = null
}

class ViewHolderRight {
    var textViewMessageHolder: TextView? = null
    var textViewTimeHolder: TextView? = null
}

private class ViewHolderLeft {

    var textViewMessageHolder: TextView? = null
    var textViewUserHolder: TextView? = null
    var textViewTimeHolder: TextView? = null

}
