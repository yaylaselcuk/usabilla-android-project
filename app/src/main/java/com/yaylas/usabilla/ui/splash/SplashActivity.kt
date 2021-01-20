package com.yaylas.usabilla.ui.splash

import android.animation.Animator
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.yaylas.usabilla.databinding.ActivityDetailBinding
import com.yaylas.usabilla.databinding.ActivitySplashBinding
import com.yaylas.usabilla.domain.model.Feedback
import com.yaylas.usabilla.ui.list.FeedbackListActivity

class SplashActivity : AppCompatActivity() {

    lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.animationView.addAnimatorListener(object : Animator.AnimatorListener{
            override fun onAnimationEnd(animation: Animator?) {
                moveForward()
            }

            override fun onAnimationStart(animation: Animator?) {
            }

            override fun onAnimationCancel(animation: Animator?) {
            }

            override fun onAnimationRepeat(animation: Animator?) {
            }
        })

    }

    private fun moveForward() {
        val intent = Intent(this, FeedbackListActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onBackPressed() {

    }
}