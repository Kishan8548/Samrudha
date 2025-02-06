package com.samrudha.app

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.content.Intent
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.samrudha.app.databinding.FragmentEditProfileBinding
import com.samrudha.app.network.LanguageLocationRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EditProfileFragment : Fragment() {

    private var _binding: FragmentEditProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPreferences = requireActivity().getSharedPreferences("UserProfile", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        binding.editFullName.setText(sharedPreferences.getString("fullName", ""))
        binding.editMobileNumber.setText(sharedPreferences.getString("mobileNumber", ""))
        binding.editEmail.setText(sharedPreferences.getString("email", ""))
        binding.editLocation.setText(sharedPreferences.getString("location", ""))
        binding.editLanguage.setText(sharedPreferences.getString("language", ""))

        val profileImageUri = sharedPreferences.getString("profileImage", null)
        profileImageUri?.let { binding.editProfileImage.setImageURI(Uri.parse(it)) }

        binding.btnUploadPhoto.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK).apply {
                type = "image/*"
            }
            startActivityForResult(intent, 100)
        }

        binding.btnSaveProfile.setOnClickListener {
            editor.putString("fullName", binding.editFullName.text.toString())
            editor.putString("mobileNumber", binding.editMobileNumber.text.toString())
            editor.putString("email", binding.editEmail.text.toString())
            editor.putString("location", binding.editLocation.text.toString())
            editor.putString("language", binding.editLanguage.text.toString())
            editor.apply()

            parentFragmentManager.popBackStack()
        }

        fetchLanguagesAndLocations()
    }

    private fun fetchLanguagesAndLocations() {
        val repository = LanguageLocationRepository()

        CoroutineScope(Dispatchers.IO).launch {
            repository.fetchLanguagesAndLocations { response ->
                response?.let {
                    val languages = it.tasks.flatMap { task -> task.result.languages }.map { lang -> lang.name }
                    val locations = it.tasks.flatMap { task -> task.result.locations }.map { loc -> loc.name }

                    CoroutineScope(Dispatchers.Main).launch {
                        binding.editLanguage.setAdapter(
                            ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, languages)
                        )
                        binding.editLocation.setAdapter(
                            ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, locations)
                        )
                    }
                }
            }
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100 && data?.data != null) {
            val selectedImageUri: Uri = data.data!!
            binding.editProfileImage.setImageURI(selectedImageUri)

            requireActivity().getSharedPreferences("UserProfile", Context.MODE_PRIVATE)
                .edit()
                .putString("profileImage", selectedImageUri.toString())
                .apply()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
