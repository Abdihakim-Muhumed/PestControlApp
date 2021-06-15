package com.example.pestcontrolapp

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHandler(context: Context):SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION) {
    companion object {
        private val DATABASE_VERSION= 1
        private val DATABASE_NAME = "MyBookings"
        private  val TABLE_BOOKINGS = "BookingTable"
        private  val KEY_ID = "id"
        private val  KEY_NAME = "name"
        private  val  KEY_EMAIL = "email"
        private val KEY_GENDER = "gender"
        private val KEY_PHONE = "phone"
        private val KEY_PESTS = "pests"
        private val KEY_DATE = "date"
        private val KEY_HOUSENUMBER = "house_number"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_BOOKINGS_TABLE = ("CREATE TABLE "+ TABLE_BOOKINGS+"("+KEY_ID + " INTEGER PRIMARY KEY,"+KEY_NAME+" TEXT,"
                +KEY_EMAIL+" TEXT,"+ KEY_GENDER+" TEXT,"+ KEY_PHONE+" TEXT,"+ KEY_PESTS+" TEXT,"+ KEY_HOUSENUMBER+" TEXT,"
                + KEY_DATE+" TEXT"+")")
        db?.execSQL(CREATE_BOOKINGS_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        //IN CASE OF A CHANGE
        db!!.execSQL("DROP TABLE IF EXISTS"+ TABLE_BOOKINGS)
        onCreate(db)
    }
    //method to save data
    fun addBookings(BookingsModel: BookingsModel):Long{
        val db = this.writableDatabase
        //placing content
        val contentValues = ContentValues()
        contentValues.put(KEY_ID,BookingsModel.id)
        contentValues.put(KEY_NAME,BookingsModel.name)
        contentValues.put(KEY_EMAIL,BookingsModel.email)
        contentValues.put(KEY_GENDER,BookingsModel.gender)
        contentValues.put(KEY_PHONE,BookingsModel.phone)
        contentValues.put(KEY_HOUSENUMBER,BookingsModel.houseNo)
        contentValues.put(KEY_PESTS,BookingsModel.pests)
        contentValues.put(KEY_DATE,BookingsModel.date)
        //inserting row
        val success = db.insert(TABLE_BOOKINGS,null,contentValues)
        //closing database
        db.close()

        return  success
    }
    //method to read data
    fun viewBookings():List<BookingsModel>{
        val bookingList:ArrayList<BookingsModel> = ArrayList<BookingsModel>()
        val selectQuery = "SELECT * FROM $TABLE_BOOKINGS"
        val db = this.readableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery(selectQuery,null)

        }catch (e: SQLiteException){
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var id: Int
        var name: String
        var email: String
        var gender: String
        var phone : String
        var houseNo :String
        var pests : String
        var date : String
        if(cursor.moveToFirst()){
            do{
                id = cursor.getInt(cursor.getColumnIndex("id"))
                name =  cursor.getString(cursor.getColumnIndex("name"))
                email = cursor.getString(cursor.getColumnIndex("email"))
                gender = cursor.getString(cursor.getColumnIndex("gender"))
                phone = cursor.getString(cursor.getColumnIndex("phone"))
                pests = cursor.getString(cursor.getColumnIndex("pests"))
                date = cursor.getString(cursor.getColumnIndex("date"))
                houseNo = cursor.getString(cursor.getColumnIndex("house_number"))

                val booking = BookingsModel(id,name,gender,phone,email,houseNo,pests, date)
                bookingList.add(booking)

            }while (cursor.moveToNext())

        }
        return bookingList
    }
    //method to update the records
    fun updateBooking(bookingsModel: BookingsModel):Int{
        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(KEY_ID,bookingsModel.id)
        contentValues.put(KEY_NAME,bookingsModel.name)
        contentValues.put(KEY_EMAIL,bookingsModel.email)
        contentValues.put(KEY_GENDER,bookingsModel.gender)
        contentValues.put(KEY_PHONE,bookingsModel.phone)
        contentValues.put(KEY_HOUSENUMBER,bookingsModel.houseNo)
        contentValues.put(KEY_PESTS,bookingsModel.pests)
        contentValues.put(KEY_DATE,bookingsModel.date)
        //inserting row
        val success = db.update(TABLE_BOOKINGS, contentValues,"id="+bookingsModel.id,null)
        //closing database
        db.close()
        return success
    }

    //method to delete a record
    fun deleteBooking(bookingsModel: BookingsModel):Int{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_ID,bookingsModel.id)
        //deleting Row
        val success = db.delete(TABLE_BOOKINGS,"id="+bookingsModel.id,null)

        db.close()

        return success
    }
}