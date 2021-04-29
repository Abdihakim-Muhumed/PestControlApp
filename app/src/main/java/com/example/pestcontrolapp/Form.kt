package com.example.pestcontrolapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText

class Form : AppCompatActivity() {
    private  lateinit var  submitButton: Button
    private lateinit var  occupantName:EditText
    private lateinit var  occupantGender:EditText
    private lateinit var  occupantEmail:EditText
    private lateinit var  occupantPhone:EditText
    private lateinit var  occupantHouseNo:EditText
    private lateinit var  occupantPests:EditText
    private lateinit var  inputDateOfVisit:EditText

    private  lateinit var name : String
    private lateinit var email  : String
    private lateinit var phone  : String
    private lateinit var gender  : String
    private lateinit var houseNo  : String
    private lateinit var pests  : String
    private lateinit var dateOfVisit  : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)

        submitButton = findViewById(R.id.buttonSubmit)
        occupantName = findViewById(R.id.inputOccupantNAme)
        occupantEmail = findViewById(R.id.email)
        occupantGender = findViewById(R.id.gender)
        occupantHouseNo = findViewById(R.id.houseNumber)
        occupantPests = findViewById(R.id.pest)
        occupantPhone = findViewById(R.id.phone)
        inputDateOfVisit = findViewById(R.id.dateOfVisit)

        submitButton.setOnClickListener(View.OnClickListener {
            name = occupantName.text.toString()
            email = occupantEmail.text.toString()
            phone = occupantPhone.text.toString()
            gender = occupantGender.text.toString()
            houseNo = occupantHouseNo.text.toString()
            pests = occupantPests.text.toString()
            dateOfVisit = inputDateOfVisit.text.toString()

            val intentInfo = Intent(this@Form,Info::class.java)
            intentInfo.putExtra("Name :",name)
            intentInfo.putExtra("Email:",email)
            intentInfo.putExtra("Phone : ",phone)
            intentInfo.putExtra("Gender :",gender)
            intentInfo.putExtra("House No. :",houseNo)
            intentInfo.putExtra("Pest to control : ",pests)
            intentInfo.putExtra("Date of visit :",dateOfVisit)
            startActivity(intentInfo)
        })

    }
}