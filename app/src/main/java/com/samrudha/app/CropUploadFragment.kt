package com.samrudha.app

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton
import java.io.File
import java.io.IOException

class CropUploadFragment : Fragment() {

    private lateinit var imageView: ImageView
    private lateinit var openCameraButton: MaterialButton
    private lateinit var selectFileCard: CardView
    private lateinit var continueButton: Button
    private var imageUri: Uri? = null

    // 🔹 Camera Result Launcher
    private val cameraLauncher =
        registerForActivityResult(ActivityResultContracts.TakePicture()) { success ->
            if (success) {
                imageView.setImageURI(imageUri) // Show captured image
            } else {
                Toast.makeText(requireContext(), "Failed to capture image", Toast.LENGTH_SHORT).show()
            }
        }

    // 🔹 Gallery Result Launcher (Moved outside the click listener)
    private val galleryLauncher =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            uri?.let {
                imageView.setImageURI(it) // Display selected image
            }
        }

    // 🔹 Permission Request Launcher
    private val permissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            val cameraGranted = permissions[Manifest.permission.CAMERA] ?: false
            val storageGranted = permissions[Manifest.permission.READ_MEDIA_IMAGES] ?: false

            if (cameraGranted && storageGranted) {
                openCamera()
            } else {
                Toast.makeText(requireContext(), "Camera & Storage permissions are required", Toast.LENGTH_SHORT).show()
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_crop_upload, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imageView = view.findViewById(R.id.imageView)
        openCameraButton = view.findViewById(R.id.openCameraButton)
        selectFileCard = view.findViewById(R.id.selectFileCard)
        continueButton = view.findViewById(R.id.continueButton)

        openCameraButton.setOnClickListener {
            checkPermissionsAndOpenCamera()
        }

        selectFileCard.setOnClickListener {
            openGallery()  // Gallery open logic now calls this function
        }

        continueButton.setOnClickListener {
            Toast.makeText(requireContext(), "Continue button clicked!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun checkPermissionsAndOpenCamera() {
        val cameraPermission = ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA)
        val storagePermission = ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_MEDIA_IMAGES)

        if (cameraPermission == PackageManager.PERMISSION_GRANTED && storagePermission == PackageManager.PERMISSION_GRANTED) {
            openCamera()
        } else {
            permissionLauncher.launch(arrayOf(Manifest.permission.CAMERA, Manifest.permission.READ_MEDIA_IMAGES))
        }
    }

    private fun openCamera() {
        try {
            val photoFile: File = createImageFile()
            imageUri = FileProvider.getUriForFile(
                requireContext(),
                "${requireContext().packageName}.fileprovider",
                photoFile
            )

            cameraLauncher.launch(imageUri)
        } catch (e: IOException) {
            e.printStackTrace()
            Toast.makeText(requireContext(), "Error creating image file", Toast.LENGTH_SHORT).show()
        }
    }

    private fun openGallery() {
        galleryLauncher.launch("image/*")  // Open gallery
    }

    @Throws(IOException::class)
    private fun createImageFile(): File {
        val storageDir: File? = requireContext().getExternalFilesDir(null)
        return File.createTempFile(
            "JPEG_${System.currentTimeMillis()}_",
            ".jpg",
            storageDir
        )
    }
}

