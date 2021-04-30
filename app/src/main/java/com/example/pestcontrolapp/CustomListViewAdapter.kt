package com.example.pestcontrolapp

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity

class CustomListViewAdapter(private val context : Activity, private val title:Array<String>,private val description:Array<String>,private val imagId : Array<Int>) :ArrayAdapter<String>(context,R.layout.custom_listview,title) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        //refference inflator
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.custom_listview,null,true)

        //refference the views inside the custom list view
        val serviceImage : ImageView = rowView.findViewById(R.id.serviceImage)
        val serviceTitle : TextView = rowView.findViewById(R.id.serviceTitle)
        val serviceDescription : TextView = rowView.findViewById(R.id.serviceDescription)
        val buttonDial : Button = rowView.findViewById(R.id.buttonDial)
        val buttonEmail : Button =rowView.findViewById(R.id.buttonEmail)

        //settig arguements passed to our constructor to the views
        serviceImage.setImageResource(imagId[position])
        serviceTitle.text = title[position]
        serviceDescription.text = description[position]

        //onclick listeners for buttons
        buttonDial.setOnClickListener(View.OnClickListener {
            // intent dial//intent for phone calling
            val intentDial = Intent(Intent.ACTION_DIAL)
            intentDial.data = Uri.parse( "tel:"+"0711000000")

            //start activity
            buttonDial.context.startActivity(intentDial)
        })

        buttonEmail.setOnClickListener(View.OnClickListener {
            //intent for email
            val address = "info@cleanHouse.org"
            val subject = "INQUIRY"
            val intentEmail  = Intent(Intent.ACTION_SENDTO)
            intentEmail.setData(Uri.parse("mailto:"))
            intentEmail.putExtra(Intent.EXTRA_EMAIL,address)
            intentEmail.putExtra(Intent.EXTRA_SUBJECT,subject)
            buttonEmail.context.startActivity(intentEmail)

        })


        return rowView
    }
}