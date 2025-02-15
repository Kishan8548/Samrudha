package com.samrudha.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.fragment.app.Fragment
import android.content.Context
import android.view.inputmethod.InputMethodManager

class CropRecommendationFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_crop_recommendation, container, false)

        val autoCompleteTextView = view.findViewById<AutoCompleteTextView>(R.id.auto_soil_type) // ✅

        // List of soil types
        val soilTypes = listOf(
            "Sandy", "Clayey", "Loamy", "Silty", "Peaty", "Chalky", "Saline"
        )

        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, soilTypes)
        autoCompleteTextView.setAdapter(adapter)
        autoCompleteTextView.threshold = 1

        // Show keyboard when focused
        autoCompleteTextView.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                val imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.showSoftInput(autoCompleteTextView, InputMethodManager.SHOW_IMPLICIT)
            }
        }

        return view
    }
}
