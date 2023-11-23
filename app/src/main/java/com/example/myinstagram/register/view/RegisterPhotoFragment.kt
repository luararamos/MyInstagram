package com.example.myinstagram.register.view

import android.content.Context
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import com.example.myinstagram.R
import com.example.myinstagram.common.base.DependencyInjector
import com.example.myinstagram.common.view.CustomDialog
import com.example.myinstagram.databinding.FragmentRegisterPhotoBinding
import com.example.myinstagram.register.RegisterPhoto
import com.example.myinstagram.register.presentation.RegisterPhotoPresenter
import com.example.myinstagram.register.view.CropperImageFragment.Companion.KEY_URI

class RegisterPhotoFragment : Fragment(R.layout.fragment_register_photo), RegisterPhoto.View{

    private var binding: FragmentRegisterPhotoBinding? = null
    private var fragmentAttachListener: FragmentAttachListener? = null
    override lateinit var  presenter: RegisterPhoto.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFragmentResultListener("cropKey") { requestKey, bundle ->
            val uri = bundle.getParcelable<Uri>(KEY_URI)
            onCropImageResult(uri)

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentRegisterPhotoBinding.bind(view)

        val repository = DependencyInjector.registerEmailRepository()
        presenter = RegisterPhotoPresenter(this, repository)

        binding?.let {
            with(it) {
                registerBtnNext.isEnabled = true

                registerBtnNext.setOnClickListener {
                    openDialog()
                }

                registerBtnJump.setOnClickListener {
                    fragmentAttachListener?.goToMainScreen()
                }
            }
        }

    }

    private fun openDialog() {
        val customDialog = CustomDialog(requireContext())
        customDialog.setTitle(R.string.define_photo_profile)
        customDialog.addButton(R.string.photo, R.string.gallery) {
            when (it.id) {
                R.string.photo -> {
                    fragmentAttachListener?.goToCameraScreen()
                }

                R.string.gallery -> {
                    fragmentAttachListener?.goToGalleryScreen()
                }
            }
        }
        customDialog.show()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentAttachListener) {
            fragmentAttachListener = context
        }
    }

    private fun onCropImageResult(uri: Uri?) {
        if (uri != null) {
            binding?.imgRegisterPhotoProfile?.setImageURI(uri)
            presenter.updateUser(uri)
        }

    }

    override fun showProgress(enabled: Boolean) {
        binding?.registerBtnNext?.showProgressBar(enabled)
    }

    override fun onUpdateFailure(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    override fun onUpdateSuccess() {
        fragmentAttachListener?.goToMainScreen()
    }

    override fun onDestroy() {
        binding = null
        presenter.onDestroy()
        super.onDestroy()
    }


}
