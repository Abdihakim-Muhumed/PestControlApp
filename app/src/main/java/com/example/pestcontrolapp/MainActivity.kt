package com.example.pestcontrolapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(),BottomNavigationView.OnNavigationItemSelectedListener {
    private lateinit var bottomNavigation: BottomNavigationView
    private lateinit var  buttonContact:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonContact = findViewById(R.id.buttonContact)
        buttonContact.setOnClickListener(View.OnClickListener {
            val intentContact = Intent(this,Form::class.java)
            startActivity(intentContact)
        })

        bottomNavigation = findViewById(R.id.bottomNAv)
        bottomNavigation.setOnNavigationItemSelectedListener(this)

    }
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.homeMenu -> {
                val intentHome = Intent(this,MainActivity::class.java)
                startActivity(intentHome)
            }
            R.id.contactFormMenu -> {
                val intentForm = Intent(this,Form::class.java)
                startActivity(intentForm)
            }
            R.id.menuAdvert -> {
                val intentAdvert = Intent(this,Advert::class.java)
                startActivity(intentAdvert)
            }
            R.id.myBookingsMenu->{
                val intenBookings = Intent(this,MyBookings::class.java)
                startActivity(intenBookings)
            }
        }
        return true
    }
}