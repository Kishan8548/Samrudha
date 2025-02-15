package com.samrudha.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputEditText
import android.content.Context
import android.view.inputmethod.InputMethodManager

class YieldPredictionFragment : Fragment() {

    private lateinit var etLatitude: TextInputEditText
    private lateinit var etLongitude: TextInputEditText
    private lateinit var etNDVI: TextInputEditText
    private lateinit var etSAVI: TextInputEditText
    private lateinit var etSoilMoisture: TextInputEditText
    private lateinit var etTemperature: TextInputEditText
    private lateinit var etRainfall: TextInputEditText
    private lateinit var autoCropType: AutoCompleteTextView
    private lateinit var btnFetchFromAPI: Button
    private lateinit var btnPredict: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_yield_prediction, container, false)

        // Initialize views
        etLatitude = view.findViewById(R.id.et_latitude)
        etLongitude = view.findViewById(R.id.et_longitude)
        etNDVI = view.findViewById(R.id.et_ndvi)
        etSAVI = view.findViewById(R.id.et_savi)
        etSoilMoisture = view.findViewById(R.id.et_soil_moisture)
        etTemperature = view.findViewById(R.id.et_temperature)
        etRainfall = view.findViewById(R.id.et_rainfall)
        autoCropType = view.findViewById(R.id.auto_crop_type)
        btnFetchFromAPI = view.findViewById(R.id.fetchFromApiButton)
        btnPredict = view.findViewById(R.id.btn_predict)

        // Setup AutoCompleteTextView for crop type
        setupAutoCompleteCropType()

        // Set button click listeners
        btnFetchFromAPI.setOnClickListener { fetchFromAPI() }
        btnPredict.setOnClickListener { predictYield() }

        return view
    }

    private fun setupAutoCompleteCropType() {
        val cropTypes = listOf(
            "Wheat", "Rice", "Maize", "Barley", "Soybean", "Cotton", "Sugarcane", "Sorghum",
            "Millets", "Chickpea", "Pigeon Pea", "Black Gram", "Green Gram", "Lentil", "Peas",
            "Groundnut", "Sesame", "Sunflower", "Mustard", "Linseed", "Castor", "Jute", "Tobacco",
            "Tea", "Coffee", "Rubber", "Coconut", "Banana", "Mango", "Grapes", "Apple"
        )

        val cropAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, cropTypes)
        autoCropType.setAdapter(cropAdapter)
        autoCropType.threshold = 1

        // Force show keyboard when clicked
        autoCropType.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                val imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.showSoftInput(autoCropType, InputMethodManager.SHOW_IMPLICIT)
            }
        }
    }

    private fun fetchFromAPI() {
        // Placeholder function for fetching data from API
        Toast.makeText(requireContext(), "Fetching data from API...", Toast.LENGTH_SHORT).show()
    }

    private fun predictYield() {
        // Get user input values
        val latitude = etLatitude.text.toString().toDoubleOrNull()
        val longitude = etLongitude.text.toString().toDoubleOrNull()
        val ndvi = etNDVI.text.toString().toDoubleOrNull()
        val savi = etSAVI.text.toString().toDoubleOrNull()
        val soilMoisture = etSoilMoisture.text.toString().toDoubleOrNull()
        val temperature = etTemperature.text.toString().toDoubleOrNull()
        val rainfall = etRainfall.text.toString().toDoubleOrNull()
        val cropType = autoCropType.text.toString()

        // Validate inputs
        if (latitude == null || longitude == null || ndvi == null || savi == null || soilMoisture == null ||
            temperature == null || rainfall == null || cropType.isEmpty()) {
            Toast.makeText(requireContext(), "Please fill all fields correctly", Toast.LENGTH_SHORT).show()
            return
        }

        // TODO: Call ML model or API for yield prediction
        Toast.makeText(requireContext(), "Predicting Yield for $cropType", Toast.LENGTH_SHORT).show()
    }
}
