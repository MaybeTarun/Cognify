package com.example.cognify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity3 : AppCompatActivity() {

    private lateinit var newRecyclerView : RecyclerView
    private lateinit var newArrayList : ArrayList<LearningTechs>
    lateinit var tech_name : Array<String>
    lateinit var tech_detail : Array<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        tech_name = arrayOf(
            "Spaced Retrieval",
            "Feynman Technique",
            "Leitnar System",
            "Mind Mapping",
            "Questions"
        )

        tech_detail = arrayOf(
            "It encourages students to study over a longer period of time instead of cramming the night before. When our brains almost forget something, they work harder to recall that information. Spacing out your studying allows your mind to make connections between ideas and build upon the knowledge that can be easily recalled later.",
            "It is an efficient method of learning a concept quickly by explaining it in plain and simple terms. It’s based on the idea, “If you want to understand something well, try to explain it simply.” What that means is, by attempting to explain a concept in our own words, we are likely to understand it a lot faster.",
            "It is a learning technique based on flashcards. Ideally, you keep your cards in several different boxes to track when you need to study each set. Every card starts in Box 1. If you get a card right, you move it to the next box. If you get a card wrong, you either move it down a box or keep it in Box 1 (if it’s already there).",
            "It allows you to visually organize information in a diagram. First, you write a word in the center of a blank page. From there, you write major ideas and keywords and connect them directly to the central concept. Other related ideas will continue to branch out.",
            "It gives you practice questions"
        )

        newRecyclerView = findViewById(R.id.techniques)
        newRecyclerView.layoutManager = LinearLayoutManager (this)
        newRecyclerView.setHasFixedSize(true)

        newArrayList = arrayListOf<LearningTechs>()
        getUserdata()

    }

    private fun getUserdata() {
        for(i in tech_name.indices) {
            val oof = LearningTechs(tech_name[i], tech_detail[i])
            newArrayList.add(oof)
        }
        newRecyclerView.adapter = MyAdapter(newArrayList)
    }
}