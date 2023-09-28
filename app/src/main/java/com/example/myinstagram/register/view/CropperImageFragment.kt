package com.example.myinstagram.register.view

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import com.example.myinstagram.R

import com.example.myinstagram.databinding.FragmentImageCropperBinding
import java.io.File

class CropperImageFragment : Fragment(R.layout.fragment_image_cropper) {
    private var binding: FragmentImageCropperBinding? = null
    private var fragmentAttachListener: FragmentAttachListener? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentImageCropperBinding.bind(view)

        val uri = arguments?.getParcelable<Uri>(KEY_URI)

        binding?.let {
            with(it) {
                cropperContainer.setAspectRatio(1, 1)
                cropperContainer.setFixedAspectRatio(true)
                cropperContainer.setImageUriAsync(uri)

                cropperBtnCancelImage.setOnClickListener {
                    parentFragmentManager.popBackStack()
                }

                cropperContainer.setOnCropImageCompleteListener { view, result ->
                    setFragmentResult("cropKey", bundleOf(KEY_URI to result.uri))
                    parentFragmentManager.popBackStack()
                }

                cropperBtnSaveImage.setOnClickListener {
                    val dir = context?.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
                    if (dir != null) {
                        val uriToSaved = Uri.fromFile(
                            File(
                                dir.path,
                                System.currentTimeMillis().toString() + ".jpeg"
                            )
                        )
                        cropperContainer.saveCroppedImageAsync(uriToSaved)
                    }
                }
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentAttachListener) {
            fragmentAttachListener = context
        }
    }

    override fun onDestroy() {
        binding = null
        fragmentAttachListener = null
        super.onDestroy()
    }

    companion object {
        const val KEY_URI = "key_uri"
    }
}