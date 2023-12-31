package com.example.myinstagram.add.view


import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import com.example.myinstagram.R
import com.example.myinstagram.common.util.Files
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class CameraFragment : Fragment() {

    private lateinit var previewView: PreviewView

    private var imageCapture: ImageCapture? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_camera, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        previewView = view.findViewById(R.id.camera_img)
        view.findViewById<Button>(R.id.camera_img_picture).setOnClickListener {
            takePhoto()
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFragmentResultListener("cameraKey") { requestKey, bundle ->
            val shouldStart = bundle.getBoolean("startCamera")

            if (shouldStart) {
                startCamera()
            }
        }
    }

    private fun takePhoto(){

        val imageCapture = imageCapture ?: return

        val photoFile = Files.generateFile(requireActivity())
        Log.i("Teste", "oi")

        val timestamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
        val p = File.createTempFile("JPEG_${timestamp}_", ".jpg", dir)

        val outputOptions = ImageCapture.OutputFileOptions.Builder(p).build()



        imageCapture.takePicture(
            outputOptions, ContextCompat.getMainExecutor(requireContext()), object : ImageCapture.OnImageSavedCallback{
                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                    val savedUri = Uri.fromFile(p)
                    setFragmentResult("takePhotoKey", bundleOf("uri" to savedUri))
                    Log.i("Teste", savedUri.toString())
                }

                override fun onError(exception: ImageCaptureException) {
                    val teste = "oi"
                    Log.e("Teste", "Failure to take picture", exception)
                }

            })
    }

    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())

        cameraProviderFuture.addListener({
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()
            val preview = Preview.Builder()
                .build()
                .also {
                    it.setSurfaceProvider(previewView.surfaceProvider)
                }
            imageCapture = ImageCapture.Builder()
                .build()

            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

            try {
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(this, cameraSelector, preview, imageCapture)
            } catch (e: Exception) {
                Log.e("Teste", "Failure initialize camera", e)

            }
        }, ContextCompat.getMainExecutor(requireContext()))
    }


}