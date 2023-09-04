package com.example.myinstagram.register.view

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
    override fun onDestroy() {
        binding= null
        super.onDestroy()
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_register_photo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentRegisterPhotoBinding.bind(view)

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
}