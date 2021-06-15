package com.example.pestcontrolapp

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.appcompat.app.AlertDialog
import androidx.core.graphics.drawable.DrawableCompat.inflate
import androidx.recyclerview.widget.RecyclerView

internal class BookingsAdapter(private var bookingList: List<BookingsModel>,context:Context) :
    RecyclerView.Adapter<BookingsAdapter.MyViewHolder>() {
    private val mContext: Context = context
    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view){
        var id : TextView =  view.findViewById(R.id.b_id)
        var name: TextView = view.findViewById(R.id.b_name)
        var gender: TextView = view.findViewById(R.id.b_gender)
        var email: TextView = view.findViewById(R.id.b_email)
        var phone: TextView = view.findViewById(R.id.b_phone)
        var houseNo: TextView = view.findViewById(R.id.b_houseNo)
        var pests: TextView = view.findViewById(R.id.b_pests)
        var date: TextView = view.findViewById(R.id.b_date)

        var btnDelete: Button = view.findViewById(R.id.btnDelete)

        val btnUpdate: Button = view.findViewById(R.id.btnUpdated)
    }
    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_row, parent, false)
        return MyViewHolder(view)

    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val booking = bookingList[position]
        holder.id.text = booking.id.toString()
        holder.name.text = booking.name
        holder.gender.text = booking.gender
        holder.phone.text = booking.phone
        holder.email.text = booking.email
        holder.houseNo.text = booking.houseNo
        holder.pests.text = booking.pests
        holder.date.text = booking.date

        holder.btnDelete.setOnClickListener(View.OnClickListener {
            val id = Integer.parseInt(holder.id.text.toString())
            deleteBooking(id,mContext)
        })

        holder.btnUpdate.setOnClickListener(View.OnClickListener {
            val id = Integer.parseInt(holder.id.text.toString())
            updateBooking(id)
        })

    }
    override fun getItemCount(): Int {
        return bookingList.size
    }
    fun deleteBooking(id:Int, context:Context){
        val databaseHandler = DatabaseHandler(context)
        val status = databaseHandler.deleteBooking(BookingsModel(id,"","","","","","",""))
        if (status>-1){
            Toast.makeText(context,"Booking deleted!", Toast.LENGTH_LONG).show()
            val intentRestartActivity = Intent(context,MyBookings::class.java)
            mContext.startActivity(intentRestartActivity)
        }
    }

    fun updateBooking(id:Int){
        //
        val dialogBuilder = AlertDialog.Builder(mContext)
        val dialogView = LayoutInflater.from(dialogBuilder.context).inflate(R.layout.update_dialog,null)
        dialogBuilder.setView(dialogView)

        val name = dialogView.findViewById<EditText>(R.id.inputOccupantNAme)
        val email = dialogView.findViewById<EditText>(R.id.email)
        val gender = dialogView.findViewById<EditText>(R.id.gender)
        val houseNo = dialogView.findViewById<EditText>(R.id.houseNumber)
        val pests = dialogView.findViewById<EditText>(R.id.pest)
        val phone = dialogView.findViewById<EditText>(R.id.phone)
        val date = dialogView.findViewById<EditText>(R.id.dateOfVisit)
        dialogBuilder.setTitle("Update Record")
        dialogBuilder.setMessage("Enter data below")
        dialogBuilder.setPositiveButton("Update", DialogInterface.OnClickListener { _, _ ->

            val updateId = id
            val updateName = name.text.toString()
            val updateEmail = email.text.toString()
            val updateGender = gender.text.toString()
            val updatePhone = phone.text.toString()
            val updatePests = pests.text.toString()
            val updateDate = date.text.toString()
            val updateHouseNo = houseNo.text.toString()
            //creating the instance of DatabaseHandler class
            val databaseHandler: DatabaseHandler= DatabaseHandler(mContext)
            if(updatePhone.trim()!="" && updateName.trim()!="" && updateEmail.trim()!=""&& updateDate.trim()!=""&& updateGender.trim()!=""&& updateHouseNo.trim()!=""&& updatePests.trim()!=""){
                //calling the updateEmployee method of DatabaseHandler class to update record
                val status = databaseHandler.updateBooking(BookingsModel(updateId,updateName, updateGender,updatePhone,updateEmail,updateHouseNo,updatePests,updateDate))
                if(status > -1){
                    Toast.makeText(mContext,"Booking updated",Toast.LENGTH_LONG).show()
                    val intentRestartActivity = Intent(mContext,MyBookings::class.java)
                    mContext.startActivity(intentRestartActivity)
                }
            }else{
                Toast.makeText(mContext,"Input all information!!!",Toast.LENGTH_LONG).show()
            }

        })
        dialogBuilder.setNegativeButton("Cancel", DialogInterface.OnClickListener { _, _ ->
            //pass
        })
        val b = dialogBuilder.create()
        b.show()
    }


}
