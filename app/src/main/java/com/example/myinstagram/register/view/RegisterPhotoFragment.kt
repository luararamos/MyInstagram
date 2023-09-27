package com.example.myinstagram.register.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.myinstagram.R
import com.example.myinstagram.common.view.CustomDialog
import com.example.myinstagram.databinding.ActivityLoginBinding
import com.example.myinstagram.databinding.FragmentRegisterPhotoBinding

class RegisterPhotoFragment : Fragment(R.layout.fragment_register_photo) {

    private var binding: FragmentRegisterPhotoBinding?= null
    private var fragmentAttachListener: FragmentAttachListener? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentRegisterPhotoBinding.bind(view)

        binding?.let {
            with(it){
                btnNext.isEnabled = true
                btnNext.setOnClickListener {

                    val customDialog = CustomDialog(requireContext())
                    customDialog.setTitle(R.string.define_photo_profile)
                    customDialog.addButton(R.string.photo, R.string.gallery) {
                        when(it.id){
                            R.string.photo->{
                                Log.i("Teste", "foto")
                            }
                            R.string.gallery->{
                                Log.i("Teste", "galeria")
                            }
                        }
                    }
                    customDialog.show()
                }
                registerBtnJump.setOnClickListener {
                    fragmentAttachListener?.goToMainScreen()
                }
            }
        }

    }
    override fun onDestroy() {
        binding= null
        super.onDestroy()
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentAttachListener){
            fragmentAttachListener = context
        }
    }
}
