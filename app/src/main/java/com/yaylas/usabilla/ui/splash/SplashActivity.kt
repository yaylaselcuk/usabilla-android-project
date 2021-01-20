package com.yaylas.usabilla.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.yaylas.usabilla.databinding.ActivitySplashBinding
import com.yaylas.usabilla.ui.list.FeedbackListActivity

class SplashActivity : AppCompatActivity() {

    lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.animationView.addAnimatorUpdateListener { valueAnimator ->
            val progress = (valueAnimator.animatedValue as Float * 100).toInt()
            if (progress >= 60) {
                binding.animationView.removeAllUpdateListeners()
                moveForward()
            }

        }
    }

    private fun moveForward() {
        val intent = Intent(this, FeedbackListActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onBackPressed() {

    }

}