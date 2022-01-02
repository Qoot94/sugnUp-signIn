package com.example.sugnup_signin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val signInBt = findViewById<Button>(R.id.btSignIn)
        val signupBt = findViewById<Button>(R.id.btSignUp)

        signInBt.setOnClickListener {
            finish()
            startActivity(Intent(this, SignInActivity::class.java))
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

        }
        signupBt.setOnClickListener {
            finish()
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }
}