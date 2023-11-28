package com.example.myinstagram.splash.view

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.myinstagram.R
import com.example.myinstagram.common.base.DependencyInjector
import com.example.myinstagram.common.extension.animationEnd
import com.example.myinstagram.databinding.ActivitySplashBinding
import com.example.myinstagram.login.view.LoginActivity
import com.example.myinstagram.main.view.MainActivity
import com.example.myinstagram.splash.Splash
import com.example.myinstagram.splash.presentation.SplashPresenter
import java.util.zip.Inflater

class SplashActivity : AppCompatActivity(), Splash.View {
    private lateinit var binding: ActivitySplashBinding
    override lateinit var presenter: Splash.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = DependencyInjector.splashRepository()
        presenter = SplashPresenter(this, repository)

        binding.splashImg.animate().apply {
            setListener(animationEnd {
                presenter.authenticated()
            })
            duration = 1000
            alpha(1.0f)
            start()
        }

    }

    override fun goToMainScreen() {
        binding.splashImg.animate().apply {
            setListener(animationEnd {
                val intent = Intent(baseContext, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            })
            startDelay = 1000
            duration = 1000
            alpha(0.0f)
            start()
        }
    }


    override fun goToLoginScreen() {

        binding.splashImg.animate().apply {
            setListener(animationEnd {
                val intent = Intent(baseContext,LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            })
            startDelay = 1000
            duration = 1000
            alpha(0.0f)
            start()
        }
    }


}