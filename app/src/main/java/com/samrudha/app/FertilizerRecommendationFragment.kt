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

class FertilizerRecommendationFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_fertilizer_recommendation, container, false)

        // Crop Type AutoCompleteTextView
        val autoCropType = view.findViewById<AutoCompleteTextView>(R.id.auto_crop_type)
        val cropTypes = listOf(
            "Rice", "Wheat", "Maize", "Barley", "Millet", "Ragi", "Sorghum", "Oats",
            "Sugarcane", "Cotton", "Soybean", "Groundnut", "Sunflower", "Rapeseed", "Mustard",
            "Chickpea", "Lentil", "Pigeon Pea", "Black Gram", "Green Gram", "Cowpea",
            "Tomato", "Potato", "Onion", "Garlic", "Carrot", "Brinjal", "Cabbage",
            "Cauliflower", "Peas", "Spinach", "Coriander"
        )
        val cropAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, cropTypes)
        autoCropType.setAdapter(cropAdapter)
        autoCropType.threshold = 1
        enableKeyboardOnFocus(autoCropType)

        // Soil Type AutoCompleteTextView
        val autoSoilType = view.findViewById<AutoCompleteTextView>(R.id.auto_soil_type)
        val soilTypes = listOf("Sandy", "Clayey", "Loamy", "Silty", "Peaty")
        val soilAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, soilTypes)
        autoSoilType.setAdapter(soilAdapter)
        autoSoilType.threshold = 1
        enableKeyboardOnFocus(autoSoilType)

        return view
    }

    // Function to force show keyboard when AutoCompleteTextView is focused
    private fun enableKeyboardOnFocus(autoCompleteTextView: AutoCompleteTextView) {
        autoCompleteTextView.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                val imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.showSoftInput(autoCompleteTextView, InputMethodManager.SHOW_IMPLICIT)
            }
        }
    }
}
