package com.example.pestcontrolapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView

class Info : AppCompatActivity() {
    private lateinit var name : TextView
    private lateinit var email : TextView
    private lateinit var phone: TextView
    private lateinit var gender : TextView
    private lateinit var houseNo : TextView
    private lateinit var pests : TextView
    private lateinit var visitingDate : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)
        //view identification
        name = findViewById(R.id.occupantName)
        email = findViewById(R.id.occupantEmail)
        phone = findViewById(R.id.occupantPhone)
        gender = findViewById(R.id.occupantGender)
        houseNo = findViewById(R.id.occupantHouseNo)
        pests = findViewById(R.id.occupantPests)
        visitingDate = findViewById(R.id.dateofvisit)

        //setting strings from intent
        val occupantName : String? = intent.getStringExtra("Name :")
        Log.d("sharing received " ,"Received "+ occupantName)
        name.text = occupantName
        email.text = intent.getStringExtra("Email:")
        phone.text = intent.getStringExtra("Phone : ")
        gender.text = intent.getStringExtra("Gender :")
        houseNo.text = intent.getStringExtra("House No. :")
        pests.text = intent.getStringExtra("Pest to control : ")
        visitingDate.text = intent.getStringExtra("Date of visit :")
    }
}