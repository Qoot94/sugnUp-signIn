package com.example.sugnup_signin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView

class DetailsActivity : AppCompatActivity() {
    private val databaseHelper by lazy { DatabaseHelper(applicationContext) }
    lateinit var tvName: TextView
    lateinit var tvMobile: TextView
    lateinit var tvLocation: TextView
    lateinit var tvPassword: TextView
    lateinit var ibShowPassword:ImageButton
    var currentUserName = ""
    var isHidden= true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val signOutBt = findViewById<Button>(R.id.btSignOut)
        ibShowPassword=findViewById(R.id.ibShow)
        tvName = findViewById<TextView>(R.id.tvName)
        tvMobile = findViewById<TextView>(R.id.tvMobile)
        tvLocation = findViewById<TextView>(R.id.tvLocation)
        tvPassword = findViewById<TextView>(R.id.tvPassword)

        currentUserName = intent.extras?.getString("current_user")!!
        getUser()

        signOutBt.setOnClickListener {
            finish()
            startActivity(Intent(this, MainActivity::class.java))
        }

        ibShowPassword.setOnClickListener {
            when(isHidden){
                true->{tvPassword.transformationMethod=PasswordTransformationMethod.getInstance()
                    isHidden=false
                }
                false-> {tvPassword.transformationMethod=HideReturnsTransformationMethod.getInstance()
                    isHidden=true
                }
            }
        }
    }

    private fun getUser() {
        val users = databaseHelper.readData(applicationContext)

        for (user in users) {
            when (user.name) {
                this.currentUserName -> {
                    tvName.text = "Name: ${user.name}"
                    tvMobile.text = "Mobile: ${user.Mobile}"
                    tvLocation.text = "Location: ${user.Location}"
                    tvPassword.text = user.Password
                }
            }
        }
    }
}