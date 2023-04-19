package com.example.cognify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.aallam.openai.api.edits.EditsRequest
import com.aallam.openai.api.model.ModelId
import com.aallam.openai.client.OpenAI
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONException
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
        val apiKey = "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"
        val openAI = OpenAI(apiKey)

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
                    val cleanedText = cleanText(datatext)
                    val summary = summarizeText(cleanedText)
                    data.setText(summary)
//                    val client = OkHttpClient()
//
//                    val request = Request.Builder()
//                        .url("https://api.openai.com/v1/engines/davinci-codex/completions")
//                        .header("Content-Type", "application/json")
//                        .header("Authorization", "BEARER $apiKey")
//                        .post(
//                            "{\"prompt\": \"$prompt\", \"max_tokens\": 60}".toRequestBody(
//                                "application/json".toMediaTypeOrNull()
//                            )
//                        )
//                        .build()
//
//                    client.newCall(request).enqueue(object : Callback {
//                        override fun onFailure(call: Call, e: IOException) {
//                            runOnUiThread {
//                                data.setText("Error: ${e.message}")
//                            }
//                        }
//
//                        override fun onResponse(call: Call, response: Response) {
//                            if (!response.isSuccessful) {
//                                runOnUiThread {
//                                    data.setText("Error: ${response.code}")
//                                }
//                                return
//                            }
//
//                            val body = response.body?.string()
//                            try {
//                                val json = JSONObject(body)
//                                val choices = json.getJSONArray("choices")
//                                val text = choices.getJSONObject(0).getString("text")
//
//                                runOnUiThread {
//                                    data.setText(text)
//                                }
//                            } catch (e: JSONException) {
//                                runOnUiThread {
//                                    data.setText("Error: ${e.message}")
//                                }
//                            }
//                        }
//                    })
                }
            }

            reset.setOnClickListener {
                data.setText("")
                enterdata.text = "Enter Data/Topic to understand -"
            }
        }

        else if (techname == "Text to Speech") {
            enterdata.visibility = View.VISIBLE
            data.visibility = View.VISIBLE
            magicbutton.visibility = View.VISIBLE
            reset.visibility = View.VISIBLE
            soon.visibility = View.GONE
        }

    }

    fun cleanText(text: String): String {
        val stopWords = arrayOf("a", "an", "the", "in", "on", "at", "to", "for", "of", "with", "and", "or", "but")
        val sentences = text.split(".")
        val cleanedSentences = ArrayList<String>()
        for (sentence in sentences) {
            val words = sentence.split(" ")
            val cleanedWords = ArrayList<String>()
            for (word in words) {
                if (!stopWords.contains(word.toLowerCase())) {
                    cleanedWords.add(word)
                }
            }
            cleanedSentences.add(cleanedWords.joinToString(" "))
        }
        return cleanedSentences.joinToString(".")
    }

    fun summarizeText(text: String): String {
        val sentences = text.split(".")
        val scores = HashMap<String, Double>()
        for (sentence in sentences) {
            scores[sentence] = rankSentence(sentence, sentences)
        }
        val summarySentences = ArrayList<String>()
        for (i in 0 until 3) {
            val topSentence = scores.maxByOrNull { it.value }?.key
            if (topSentence != null) {
                summarySentences.add(topSentence)
                scores.remove(topSentence)
            }
        }
        return summarySentences.joinToString(".")
    }

    fun rankSentence(sentence: String, sentences: List<String>): Double {
        val words = sentence.split(" ")
        val score = words.sumByDouble { word ->
            val wordFreq = sentences.count { it.contains(word) }
            val sentenceLength = sentence.split(" ").size
            wordFreq.toDouble() / sentenceLength.toDouble()
        }
        return score
    }
}