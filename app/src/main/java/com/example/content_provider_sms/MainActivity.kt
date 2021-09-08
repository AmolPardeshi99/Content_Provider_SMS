package com.example.content_provider_sms

import android.app.Activity
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ListView
import android.widget.SimpleCursorAdapter
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : Activity(), View.OnClickListener {
    // GUI Widget
//    var btnSent: Button? = null
//    var btnInbox: Button? = null
//    var btnDraft: Button? = null
//    var lvMsg: ListView? = null

    // Cursor Adapter
    var adapter: SimpleCursorAdapter? = null

    /** Called when the activity is first created.  */
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Init GUI Widget
        btnInbox!!.setOnClickListener(this)
        btnSentBox!!.setOnClickListener(this)
        btnDraft!!.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        if (v === btnInbox) {

            // Create Inbox box URI
            val inboxURI = Uri.parse("content://sms/inbox")

            // List required columns
            val reqCols = arrayOf("_id", "address", "body")

            // Get Content Resolver object, which will deal with Content
            // Provider
            val cr = contentResolver

            // Fetch Inbox SMS Message from Built-in Content Provider
            val c = cr.query(inboxURI, reqCols, null, null, null)

            // Attached Cursor with adapter and display in listview
            adapter = SimpleCursorAdapter(
                this, R.layout.item_layout, c, arrayOf("body", "address"), intArrayOf(
                    R.id.tvMsg, R.id.tvNumber
                )
            )
            lvMsg!!.adapter = adapter
        }
        if (v === btnSentBox) {

            // Create Sent box URI
            val sentURI = Uri.parse("content://sms/sent")

            // List required columns
            val reqCols = arrayOf("_id", "address", "body")

            // Get Content Resolver object, which will deal with Content
            // Provider
            val cr = contentResolver

            // Fetch Sent SMS Message from Built-in Content Provider
            val c = cr.query(sentURI, reqCols, null, null, null)

            // Attached Cursor with adapter and display in listview
            adapter = SimpleCursorAdapter(
                this, R.layout.item_layout, c, arrayOf("body", "address"), intArrayOf(
                    R.id.tvMsg, R.id.tvNumber
                )
            )
            lvMsg!!.adapter = adapter
        }
        if (v === btnDraft) {
            // Create Draft box URI
            val draftURI = Uri.parse("content://sms/draft")

            // List required columns
            val reqCols = arrayOf("_id", "address", "body")

            // Get Content Resolver object, which will deal with Content
            // Provider
            val cr = contentResolver

            // Fetch Sent SMS Message from Built-in Content Provider
            val c = cr.query(draftURI, reqCols, null, null, null)

            // Attached Cursor with adapter and display in listview
            adapter = SimpleCursorAdapter(
                this, R.layout.item_layout, c, arrayOf("body", "address"), intArrayOf(
                    R.id.tvMsg, R.id.tvNumber
                )
            )
            lvMsg!!.adapter = adapter
        }
    }
}