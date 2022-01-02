package com.example.sugnup_signin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.lang.Exception

class SignInActivity : AppCompatActivity() {
    private val databaseHelper by lazy { DatabaseHelper(applicationContext) }
    lateinit var etName: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        val authButton = findViewById<Button>(R.id.btNewSignIn)
        etName = findViewById<EditText>(R.id.etUserName)
        val etPassword = findViewById<EditText>(R.id.etPassword)

        authButton.setOnClickListener {
            val name = etName.text.toString()
            authorize(name, etPassword.text.toString())

        }
    }

    private fun authorize(userName: String, userPassword: String) {
        val userLog = databaseHelper.readData(this)
        //if user exist, allow sign in
        for (user in userLog) {
            if ((userName == user.name) && (userPassword == user.Password)) {
                Toast.makeText(this, "signed in successfully", Toast.LENGTH_SHORT).show()
                finish()
                val intent = Intent(this, DetailsActivity::class.java)
                intent.putExtra("current_user", etName.text.toString())
                startActivity(intent)
            }
        }

    }
}