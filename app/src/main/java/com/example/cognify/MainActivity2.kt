package com.example.cognify

import android.animation.Animator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.airbnb.lottie.LottieAnimationView

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val myAnimationView = findViewById<LottieAnimationView>(R.id.loadingg)
        myAnimationView.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(p0: Animator) {
                // This method is called when the animation starts
            }
            override fun onAnimationEnd(p0: Animator) {
                // This method is called when the animation ends
                val intent = Intent(this@MainActivity2, MainActivity3::class.java)
                startActivity(intent)
                finish()
            }
            override fun onAnimationCancel(p0: Animator) {
                // This method is called when the animation is canceled
            }
            override fun onAnimationRepeat(p0: Animator) {
                // This method is called when the animation repeats
            }
        })

    }
}