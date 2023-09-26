package com.example.myinstagram.register.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myinstagram.R
import com.example.myinstagram.common.base.DependencyInjector
import com.example.myinstagram.login.Login
import com.example.myinstagram.login.presentation.LoginPresenter
import com.example.myinstagram.register.RegisterEmail
import com.example.myinstagram.register.presentation.RegisterEmailPresenter

class RegisterActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val fragment = RegisterEmailFragment()


        supportFragmentManager.beginTransaction().apply {
            add(R.id.register_fragment,fragment)
            commit()
        }
    }
}