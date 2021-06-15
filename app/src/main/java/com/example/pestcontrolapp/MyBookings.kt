package com.example.pestcontrolapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MyBookings : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_bookings)

        val databaseHandler: DatabaseHandler= DatabaseHandler(this)
        val bookingList: List<BookingsModel> = databaseHandler.viewBookings()
        //if no bookings
        if (bookingList.isEmpty()){
            Toast.makeText(this,"No bookings made!!!",Toast.LENGTH_LONG).show()
        }
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        val bookingAdapter = BookingsAdapter(bookingList,this)
        val layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.layoutManager = layoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = bookingAdapter
    }

}