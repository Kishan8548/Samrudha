package com.samrudha.app

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragement_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val diseaseCard = view.findViewById<CardView>(R.id.diseaseCard)
        val cropRecommendationCard = view.findViewById<CardView>(R.id.cropRecommendationCard)

        diseaseCard.setOnClickListener {
            replaceFragment(CropUploadFragment())
        }

        cropRecommendationCard.setOnClickListener {
            replaceFragment(CropRecommendationFragment()) // Navigating to CropRecommendationFragment
        }

    }


    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager: FragmentManager = requireActivity().supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()

        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.addToBackStack(null) // Add to back stack so user can navigate back
        fragmentTransaction.commit()
    }
}
