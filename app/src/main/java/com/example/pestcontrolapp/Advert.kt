package com.example.pestcontrolapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ListView

class Advert : AppCompatActivity() {
    private lateinit var titles : Array<String>
    private lateinit var imageIds : Array<Int>
    private lateinit var descriptions : Array<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_advert)
        titles = arrayOf("Cockroach Control","Mosquito Control","Bedbug Control","Termites Control","Flying Pests Control")
        imageIds = arrayOf(R.drawable.cockroach,R.drawable.mosquito ,R.drawable.bed_bug,R.drawable.termites,R.drawable.flying_pests)
        descriptions = resources.getStringArray(R.array.serviceDescriptions)

        //creating refference to adapter
        val customListViewAdapter = CustomListViewAdapter(this,titles,descriptions,imageIds)
        val listView : ListView = findViewById(R.id.customList)
        listView.adapter = customListViewAdapter
    }
}