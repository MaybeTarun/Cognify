package com.example.cognify

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val signup = findViewById<TextView>(R.id.signup)
        val login = findViewById<TextView>(R.id.login)
        val signuplayout = findViewById<LinearLayout>(R.id.signuplayout)
        val loginlayout = findViewById<LinearLayout>(R.id.loginlayout)
        val loginbutton = findViewById<Button>(R.id.loginbutton)
        val oro = findViewById<TextView>(R.id.oro)
        val alredy = findViewById<TextView>(R.id.alredy)
        val sss = findViewById<LinearLayout>(R.id.sss)
        val usernameEditText = findViewById<EditText>(R.id.username)
        val passwordEditText = findViewById<EditText>(R.id.password)

        signup.setOnClickListener {
            signup.background = resources.getDrawable(R.drawable.switch_trcks, null)
            signup.setTextColor(resources.getColor(R.color.white, null))
            login.background = null
            signuplayout.visibility = View.VISIBLE
            loginlayout.visibility = View.GONE
            oro.visibility = View.GONE
            sss.visibility = View.GONE
            alredy.visibility = View.VISIBLE
            login.setTextColor(resources.getColor(R.color.pastelgreen, null))
            loginbutton.text = "Sign Up"
        }
        login.setOnClickListener {
            signup.background = null
            signup.setTextColor(resources.getColor(R.color.pastelgreen, null))
            login.background = resources.getDrawable(R.drawable.switch_trcks, null)
            signuplayout.visibility = View.GONE
            loginlayout.visibility = View.VISIBLE
            oro.visibility = View.VISIBLE
            sss.visibility = View.VISIBLE
            alredy.visibility = View.GONE
            login.setTextColor(resources.getColor(R.color.white, null))
            loginbutton.text = "Log In"
        }
        loginbutton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()
            if (username == "tarun" && password == "tarun123") {
                startActivity(Intent(this@MainActivity,MainActivity2::class.java))
            }
            else {
                usernameEditText.setText("")
                passwordEditText.setText("")
            }
        }


    }
}