package com.example.cognify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import org.w3c.dom.Text

class MainActivity4 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)

        val techName : TextView = findViewById(R.id.tech_name)
        val techDetail : TextView = findViewById(R.id.tech_detail)

        val bundle : Bundle?= intent.extras
        val techname = bundle!!.getString("tech_name")
        val techdetail = bundle!!.getString("tech_detail")

        techName.text = techname
        techDetail.text = techdetail

    }
}