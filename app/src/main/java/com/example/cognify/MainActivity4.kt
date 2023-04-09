package com.example.cognify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException


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
        val apiKey = "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"

        if (techname == "Feynman Technique"){
            enterdata.visibility = View.VISIBLE
            data.visibility = View.VISIBLE
            magicbutton.visibility = View.VISIBLE
            reset.visibility = View.VISIBLE
            soon.visibility = View.GONE

            magicbutton.setOnClickListener {
                val datatext = data.text.toString()
                val prompt = "Explain like 11 yr old - " + datatext
                if (datatext.isEmpty()) {
                    Toast.makeText(this, "Enter some data first", Toast.LENGTH_SHORT).show()
                } else {
                    enterdata.text = "Simplified Data -"
                    val client = OkHttpClient()

                    val request = Request.Builder()
                        .url("https://api.openai.com/v1/engines/davinci-codex/completions")
                        .header("Content-Type", "application/json")
                        .header("Authorization", "BEARER $apiKey")
                        .post(
                            "{\"prompt\": \"$prompt\", \"max_tokens\": 60}".toRequestBody(
                                "application/json".toMediaTypeOrNull()
                            )
                        )
                        .build()

                    client.newCall(request).enqueue(object : Callback {
                        override fun onFailure(call: Call, e: IOException) {
                            runOnUiThread {
                                data.setText("Error: ${e.message}")
                            }
                        }

                        override fun onResponse(call: Call, response: Response) {
                            if (!response.isSuccessful) {
                                runOnUiThread {
                                    data.setText("Error: ${response.code}")
                                }
                                return
                            }

                            val body = response.body?.string()
                            try {
                                val json = JSONObject(body)
                                val choices = json.getJSONArray("choices")
                                val text = choices.getJSONObject(0).getString("text")

                                runOnUiThread {
                                    data.setText(text)
                                }
                            } catch (e: JSONException) {
                                runOnUiThread {
                                    data.setText("Error: ${e.message}")
                                }
                            }
                        }
                    })
                }
            }

            reset.setOnClickListener {
                data.setText("")
                enterdata.text = "Enter Data/Topic to understand -"
            }
        }

    }
}