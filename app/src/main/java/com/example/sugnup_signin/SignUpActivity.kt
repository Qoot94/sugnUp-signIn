package com.example.sugnup_signin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class SignUpActivity : AppCompatActivity() {
    private val databaseHelper by lazy { DatabaseHelper(applicationContext) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val registerButton = findViewById<Button>(R.id.btSubmit)
        val etNewName = findViewById<EditText>(R.id.etNewName)
        val etNewMobile = findViewById<EditText>(R.id.etMobile)
        val etNewLocation = findViewById<EditText>(R.id.etLocation)
        val etNewPassword = findViewById<EditText>(R.id.etNewPassword)

        registerButton.setOnClickListener {
            try {
                val name = etNewName.text.toString()
                val mobile = etNewMobile.text.toString().toInt()
                val location = etNewLocation.text.toString()
                val password = etNewPassword.text.toString()

                databaseHelper.saveData(name, mobile, location, password)

                Toast.makeText(this, "User created successfully", Toast.LENGTH_SHORT).show()
                finish()
                val intent = Intent(this, DetailsActivity::class.java)
                intent.putExtra("current_user", name)
                startActivity(intent)
            } catch (e: Exception) {
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()
                Log.d("error-signUp", "$e")
            }
        }
    }
}