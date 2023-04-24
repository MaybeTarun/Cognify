package com.example.cognify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException
import java.util.*


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

        val enterdata = findViewById<TextView>(R.id.enterdata)
        val data = findViewById<EditText>(R.id.data)
        val magicbutton = findViewById<Button>(R.id.magicbutton)
        val reset = findViewById<Button>(R.id.reset)
        val soon = findViewById<TextView>(R.id.soon)

        if (techname == "Feynman Technique"){
            enterdata.visibility = View.VISIBLE
            data.visibility = View.VISIBLE
            magicbutton.visibility = View.VISIBLE
            reset.visibility = View.VISIBLE
            soon.visibility = View.GONE

            magicbutton.setOnClickListener {
                val datatext = data.text.toString()
                val promptt = "Explain the following like 11 year old - " + datatext
                if (datatext.isEmpty()) {
                    Toast.makeText(this, "Enter some data first", Toast.LENGTH_SHORT).show()
                } else {
                    enterdata.text = "Simplified Data -"
                    getResponse(promptt){summary ->
                            data.setText(summary)
                    }
                }
            }

            reset.setOnClickListener {
                data.setText("")
                enterdata.text = "Enter Data/Topic -"
            }
        }

        else if (techname == "Mind Mapping") {
            enterdata.visibility = View.VISIBLE
            data.visibility = View.VISIBLE
            magicbutton.visibility = View.VISIBLE
            reset.visibility = View.VISIBLE
            soon.visibility = View.GONE

            magicbutton.setOnClickListener {
                val datatext = data.text.toString()
                val promptt = "Make mind map for following - " + datatext
                if (datatext.isEmpty()) {
                    Toast.makeText(this, "Enter some data first", Toast.LENGTH_SHORT).show()
                } else {
                    enterdata.text = "Mind Map -"
                    getResponse(promptt){summary ->
                        data.setText(summary)
                    }
                }
            }

            reset.setOnClickListener {
                data.setText("")
                enterdata.text = "Enter Data/Topic -"
            }
        }

        else if (techname == "Questions") {
            enterdata.visibility = View.VISIBLE
            data.visibility = View.VISIBLE
            magicbutton.visibility = View.VISIBLE
            reset.visibility = View.VISIBLE
            soon.visibility = View.GONE

            magicbutton.setOnClickListener {
                val datatext = data.text.toString()
                val promptt = "Give 10 Questions for following - " + datatext
                if (datatext.isEmpty()) {
                    Toast.makeText(this, "Enter some data first", Toast.LENGTH_SHORT).show()
                } else {
                    enterdata.text = "Questions for your Data -"
                    getResponse(promptt){summary ->
                        data.setText(summary)
                    }
                }
            }

            reset.setOnClickListener {
                data.setText("")
                enterdata.text = "Enter Data/Topic -"
            }
        }

        else if (techname == "Text to Speech") {
            enterdata.visibility = View.VISIBLE
            data.visibility = View.VISIBLE
            magicbutton.visibility = View.VISIBLE
            reset.visibility = View.VISIBLE
            soon.visibility = View.GONE

            magicbutton.setOnClickListener {
                val datatext = data.text.toString()
                if (datatext.isEmpty()) {
                    Toast.makeText(this, "Enter some data first", Toast.LENGTH_SHORT).show()
                } else {
                    enterdata.text = "Audio -"

                }
            }

            reset.setOnClickListener {
                data.setText("")
                enterdata.text = "Enter Data/Topic -"
            }
        }

        else {
            enterdata.visibility = View.GONE
            data.visibility = View.GONE
            magicbutton.visibility = View.GONE
            reset.visibility = View.GONE
            soon.visibility = View.VISIBLE
        }

    }
    fun getResponse(promptt: String, callback: (String) -> Unit) {
        val url = "https://api.openai.com/v1/engines/text-davinci-003/completions"
        val key = [YOUR_API_KEY]
        val requestBody = """
            {
              "prompt": "$promptt" ,
              "max_tokens": 500,
              "temperature": 0
            }
        """.trimIndent()

        val request = Request.Builder()
            .url(url)
            .header("Content-Type", "application/json")
            .addHeader("Authorization", "Bearer $key")
            .post(requestBody.toRequestBody("application/json".toMediaTypeOrNull()))
            .build()

        val client = OkHttpClient()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("error", "API failed", e)
            }

            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()
                if (body != null) {
                    Log.v("data", body)
                }
                else {
                    Log.v("data", "empty")
                }
                val jsonObject = JSONObject(body)
                val jsonArray: JSONArray = jsonObject.getJSONArray("choices")
                val text = jsonArray.getJSONObject(0).getString("text")
                callback(text)
            }
        })
    }
}
