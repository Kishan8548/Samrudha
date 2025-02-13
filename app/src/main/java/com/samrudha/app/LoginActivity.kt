package com.samrudha.app

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.samrudha.app.model.LoginRequest
import com.samrudha.app.network.RetrofitClient
import com.samrudha.app.utils.SessionManager
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {
    private lateinit var etEmail: TextInputEditText
    private lateinit var etPassword: TextInputEditText
    private lateinit var btnLogin: MaterialButton
    private lateinit var tvRegister: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // If user is already logged in, redirect to MainActivity
        if (SessionManager.isLoggedIn(this)) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
            return
        }

        setContentView(R.layout.activity_login)

        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        btnLogin = findViewById(R.id.btnLogin)
        tvRegister = findViewById(R.id.tvRegister)

        btnLogin.setOnClickListener { loginUser() }

        tvRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    private fun loginUser() {
        val email = etEmail.text.toString().trim()
        val password = etPassword.text.toString().trim()

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            return
        }

        // ✅ Temporary Login for Testing (Remove this when real API is ready)
        if (email == "test@samrudha.com" && password == "123456") {
            Toast.makeText(this, "Login Successful!", Toast.LENGTH_SHORT).show()
            SessionManager.saveToken(this, "dummy_token")  // Save a fake session
            startActivity(Intent(this, MainActivity::class.java))
            finish()
            return
        } else {
            Toast.makeText(this, "Invalid test credentials", Toast.LENGTH_SHORT).show()
            return
        }

        // ❌ Actual API Call (Commented until backend is ready)
//        lifecycleScope.launch {
//            try {
//                val response = RetrofitClient.instance.login(LoginRequest(email, password))
//                if (response.isSuccessful && response.body()?.success == true) {
//                    SessionManager.saveToken(this@LoginActivity, response.body()?.token ?: "")
//                    startActivity(Intent(this@LoginActivity, MainActivity::class.java))
//                    finish()
//                } else {
//                    Toast.makeText(this@LoginActivity, "Login Failed", Toast.LENGTH_SHORT).show()
//                }
//            } catch (e: Exception) {
//                Toast.makeText(this@LoginActivity, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
//            }
//        }
    }
}
