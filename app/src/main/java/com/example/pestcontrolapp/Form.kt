package com.example.pestcontrolapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class Form : AppCompatActivity() {

    private val sharedPrefFile = "sharedpreference"

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

        //storing data into shared preferences
        val sharedPreferences: SharedPreferences = this.getSharedPreferences(sharedPrefFile,Context.MODE_PRIVATE)
        submitButton.setOnClickListener(View.OnClickListener {
            name = occupantName.text.toString()
            email = occupantEmail.text.toString()
            phone = occupantPhone.text.toString()
            gender = occupantGender.text.toString()
            houseNo = occupantHouseNo.text.toString()
            pests = occupantPests.text.toString()
            dateOfVisit = inputDateOfVisit.text.toString()

            val editor:SharedPreferences.Editor = sharedPreferences.edit()
            editor.putString("key_name",name)
            editor.putString("key_email",email)
            editor.putString("key_phone",phone)
            editor.putString("key_gender",gender)
            editor.putString("key_houseNo",houseNo)
            editor.putString("key_pests",pests)
            editor.putString("key_date",dateOfVisit)
            editor.apply()
            editor.commit()

            val id = (0..1000).random()
            val databaseHandler: DatabaseHandler = DatabaseHandler(this)
            if(name.trim()!="" && phone.trim()!="" && email.trim()!="" && gender.trim()!="" && houseNo.trim()!="" && pests.trim()!="" && dateOfVisit.trim()!="" ) {
                val status = databaseHandler.addBookings(BookingsModel(id,name,gender,phone,email,houseNo,pests,dateOfVisit))
                if (status > -1) {
                    Toast.makeText(applicationContext, "Record saved", Toast.LENGTH_LONG).show()
                    //info details
                    val intentInfo = Intent(this@Form,Info::class.java)
                    intentInfo.putExtra("Name :",name)
                    intentInfo.putExtra("Email:",email)
                    intentInfo.putExtra("Phone : ",phone)
                    intentInfo.putExtra("Gender :",gender)
                    intentInfo.putExtra("House No. :",houseNo)
                    intentInfo.putExtra("Pest to control : ",pests)
                    intentInfo.putExtra("Date of visit :",dateOfVisit)
                    startActivity(intentInfo)
                }
            }else {
                Toast.makeText(
                        applicationContext,
                        "Fill all the fields!!!",
                        Toast.LENGTH_LONG
                ).show()

            }

        })

    }
}