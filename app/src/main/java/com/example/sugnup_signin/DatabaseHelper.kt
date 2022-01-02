package com.example.sugnup_signin

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, "UsersDatabase.db", null, 4) {

    private val sqliteDatabase: SQLiteDatabase = writableDatabase

    override fun onCreate(db: SQLiteDatabase?) {
        //pk INTEGER PRIMARY KEY AUTOINCREMENT,
        db?.execSQL("create table Users(pk INTEGER PRIMARY KEY AUTOINCREMENT, Name text, Mobile INTEGER, Location text, Password text)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS Users")
        onCreate(db)
    }

    fun saveData(Name:String, Mobile:Int,  Location:String,  Password:String) {
        val contentValues = ContentValues()
        contentValues.put("Name", Name)
        contentValues.put("Mobile", Mobile)
        contentValues.put("Location", Location)
        contentValues.put("Password", Password)
        sqliteDatabase.insert("Users", null, contentValues)
    }

    fun readData(context: Context): ArrayList<User> {
        val userBook = arrayListOf<User>()
        val cursor: Cursor = sqliteDatabase.rawQuery("SELECT * FROM Users", null)

        if (cursor.count < 1) {
            Toast.makeText(context, "user not found, make sure name and password is correct or go sign up", Toast.LENGTH_SHORT).show()

            println("no data")
        } else {
            while (cursor.moveToNext()) {
                val pk = cursor.getInt(0)
                val user = cursor.getString(1)
                val mobile = cursor.getInt(2)
                val location = cursor.getString(3)
                val password = cursor.getString(4)

                val user1 = User(pk, user, mobile,location,password)
                userBook.add(user1)

            }
        }
        return userBook
    }

}