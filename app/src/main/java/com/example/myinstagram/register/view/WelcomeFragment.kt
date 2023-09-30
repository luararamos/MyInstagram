package com.example.myinstagram.register.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myinstagram.R
import com.example.myinstagram.databinding.FragmentWelcomeBinding
import java.lang.IllegalArgumentException

class WelcomeFragment: Fragment(R.layout.fragment_welcome) {
    private var binding: FragmentWelcomeBinding? = null
    private var fragmentAttachListener: FragmentAttachListener? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentWelcomeBinding.bind(view)
        val name = arguments?.getString(KEY_NAME)?: throw  IllegalArgumentException("Name not found")
        binding?.textWelcome?.text = getString(R.string.welcome_to_instagram,name)
        binding?.welcomeBtnNext?.isEnabled = true
        binding?.welcomeBtnNext?.setOnClickListener {
            fragmentAttachListener?.goToPhotoScreen()
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        binding = null

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is FragmentAttachListener ){
            fragmentAttachListener = context
        }

    }

    companion object{
        const val KEY_NAME = "key_name"
    }
}