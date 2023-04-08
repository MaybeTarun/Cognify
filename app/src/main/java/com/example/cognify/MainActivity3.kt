package com.example.cognify

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
//import com.amazonaws.auth.BasicAWSCredentials
//import com.amazonaws.services.s3.AmazonS3Client
//import com.amazonaws.services.s3.model.PutObjectRequest
//import java.io.File

class MainActivity3 : AppCompatActivity() {

    private lateinit var newRecyclerView : RecyclerView
    private lateinit var newArrayList : ArrayList<LearningTechs>
    lateinit var tech_name : Array<String>
    lateinit var tech_detail : Array<String>
//    val accessKey = "your-access-key"
//    val secretKey = "your-secret-key"
//    val bucketName = "your-bucket-name"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        tech_name = arrayOf(
            "Spaced Retrieval",
            "Feynman Technique",
            "Leitner System",
            "Mind Mapping",
            "Text to Speech",
            "Text to Video",
            "Questions"
        )

        tech_detail = arrayOf(
            "It encourages students to study over a longer period of time instead of cramming the night before. When our brains almost forget something, they work harder to recall that information. Spacing out your studying allows your mind to make connections between ideas and build upon the knowledge that can be easily recalled later.",
            "It is an efficient method of learning a concept quickly by explaining it in plain and simple terms. It’s based on the idea, 'If you want to understand something well, try to explain it simply'. What that means is, by attempting to explain a concept in our own words, we are likely to understand it a lot faster.",
            "It is a learning technique based on flashcards. Ideally, you keep your cards in several different boxes to track when you need to study each set. Every card starts in Box 1. If you get a card right, you move it to the next box. If you get a card wrong, you either move it down a box or keep it in Box 1 (if it’s already there).",
            "It allows you to visually organize information in a diagram. First, you write a word in the center of a blank page. From there, you write major ideas and keywords and connect them directly to the central concept. Other related ideas will continue to branch out.",
            "It converts your given data into speech. This method is for people who can concentrate better while listening instead of reading",
            "It converts your data into videos. This method is for people who can concentrate better while watching instead of reading",
            "It gives you practice questions"
        )

        newRecyclerView = findViewById(R.id.techniques)
        newRecyclerView.layoutManager = LinearLayoutManager (this)
        newRecyclerView.setHasFixedSize(true)

        newArrayList = arrayListOf<LearningTechs>()
        getUserdata()

        val fab: FloatingActionButton = findViewById(R.id.fab)
        val uploadtext: FloatingActionButton = findViewById(R.id.uploadtext)
        val uploadspeech: FloatingActionButton = findViewById(R.id.uploadspeech)
        val uploadimage: FloatingActionButton = findViewById(R.id.uploadimage)
        var isCross = false
        fab.setOnClickListener { view ->
            if (isCross) {
                fab.setImageResource(R.drawable.plus)
                uploadtext.visibility = View.GONE
                uploadimage.visibility = View.GONE
                uploadspeech.visibility = View.GONE
            } else {
                fab.setImageResource(R.drawable.cross)
                uploadtext.visibility = View.VISIBLE
                uploadimage.visibility = View.VISIBLE
                uploadspeech.visibility = View.VISIBLE
            }
            isCross = !isCross
        }

    }

    private fun getUserdata() {
        for(i in tech_name.indices) {
            val oof = LearningTechs(tech_name[i], tech_detail[i])
            newArrayList.add(oof)
        }

        var adapter = MyAdapter(newArrayList)
        newRecyclerView.adapter = adapter
        adapter.setOnClickListener(object : MyAdapter.OnClickListener{
            override fun onItemClick(position : Int) {
                val intent = Intent(this@MainActivity3, MainActivity4::class.java)
                intent.putExtra("tech_name", newArrayList[position].tech_name)
                intent.putExtra("tech_detail", newArrayList[position].tech_detail)
                startActivity(intent)
            }
        })
    }

//    fun uploadImage(file: File) {
//        val credentials = BasicAWSCredentials(accessKey, secretKey)
//        val s3client = AmazonS3Client(credentials)
//
//        val key = "images/${file.name}"
//        val request = PutObjectRequest(bucketName, key, file)
//
//        s3client.putObject(request)
//    }
//
//    fun uploadTextFileToS3(file: File, bucketName: String, s3Client: AmazonS3) {
//        val keyName = file.name
//        val putObjectRequest = PutObjectRequest(bucketName, keyName, file)
//        s3Client.putObject(putObjectRequest)
//    }
//
//    fun uploadAudioFileToS3(file: File, bucketName: String, s3Client: AmazonS3) {
//        val keyName = file.name
//        val metadata = ObjectMetadata().apply {
//            contentType = "audio/mpeg"
//        }
//        val putObjectRequest = PutObjectRequest(bucketName, keyName, file)
//            .withMetadata(metadata)
//        s3Client.putObject(putObjectRequest)
//    }

}