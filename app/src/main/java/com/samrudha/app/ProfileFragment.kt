package com.samrudha.app


import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.samrudha.app.databinding.FragmentProfileBinding


class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPreferences = requireActivity().getSharedPreferences("UserProfile", Context.MODE_PRIVATE)

        binding.profileName.text = sharedPreferences.getString("fullName", "Farmer Name")
        binding.profileMobile.text = sharedPreferences.getString("mobileNumber", "XXXXXXXXXX")
        binding.profileEmail.text = sharedPreferences.getString("email", "Not Provided")
        binding.profileLocation.text = sharedPreferences.getString("location", "Unknown")
        binding.profileLanguage.text = sharedPreferences.getString("language", "Not Set")

        val profileImageUri = sharedPreferences.getString("profileImage", null)
        if (profileImageUri != null) {
            binding.profileImage.setImageURI(Uri.parse(profileImageUri))
        }

        binding.btnEditProfile.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, EditProfileFragment())
                .addToBackStack(null)
                .commit()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

