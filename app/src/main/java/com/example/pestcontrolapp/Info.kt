package com.example.pestcontrolapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView

class Info : AppCompatActivity() {
    private lateinit var name : TextView
    private lateinit var email : TextView
    private lateinit var phone: TextView
    private lateinit var gender : TextView
    private lateinit var houseNo : TextView
    private lateinit var pests : TextView
    private lateinit var visitingDate : TextView
    private lateinit var button: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)
        val sharedPreferences: SharedPreferences = getSharedPreferences("sharedpreference",Context.MODE_PRIVATE)

        //view identification
        name = findViewById(R.id.occupantName)
        email = findViewById(R.id.occupantEmail)
        phone = findViewById(R.id.occupantPhone)
        gender = findViewById(R.id.occupantGender)
        houseNo = findViewById(R.id.occupantHouseNo)
        pests = findViewById(R.id.occupantPests)
        visitingDate = findViewById(R.id.dateofvisit)

        //setting strings from intent
        val occupantName : String? = sharedPreferences.getString("key_name","")
        Log.d("sharing received " ,"Received "+ occupantName)
        name.text = occupantName
        email.text = sharedPreferences.getString("key_email","")
        phone.text = sharedPreferences.getString("key_phone","")
        gender.text = sharedPreferences.getString("key_gender","")
        houseNo.text = sharedPreferences.getString("key_houseNo","")
        pests.text = sharedPreferences.getString("key_pests","")
        visitingDate.text = sharedPreferences.getString("key_date","")

        //getting data from shared pref

        button =findViewById(R.id.buttonDone)
        button.setOnClickListener(View.OnClickListener {
            val intentHome = Intent(this,MainActivity::class.java)
            startActivity(intentHome)
        })
    }
}