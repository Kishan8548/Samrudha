package com.samrudha.app

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val authourize = findViewById<Button>(R.id.btnAuthorize)
        authourize.setOnClickListener() {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
//        auth = FirebaseAuth.getInstance()
//
//        sendOtpButton = findViewById(R.id.btnSendOtp)
//        val authorizeButton = findViewById<Button>(R.id.btnAuthorize)
//        phoneNumberInput = findViewById(R.id.etPhone)
//        val otp1 = findViewById<EditText>(R.id.etOtp1)
//        val otp2 = findViewById<EditText>(R.id.etOtp2)
//        val otp3 = findViewById<EditText>(R.id.etOtp3)
//        val otp4 = findViewById<EditText>(R.id.etOtp4)

//        setupOtpInputs(arrayOf(otp1, otp2, otp3, otp4))
//
//        sendOtpButton.setOnClickListener {
//            Toast.makeText(this@LoginActivity, "testing", Toast.LENGTH_SHORT).show()
//            val phoneNumber = phoneNumberInput.text.toString().trim()
//            Log.d("LoginActivity", "Phone number entered: $phoneNumber")
//            if (phoneNumber.length == 10) {
//                val fullPhoneNumber = "+91$phoneNumber"
//                sendOtpButton.text = "Sent!"
//                sendOtpButton.isEnabled = false
//                startOtpCooldown()
//                sendVerificationCode(fullPhoneNumber)
//            } else {
//                Toast.makeText(this, "Enter a valid 10-digit phone number", Toast.LENGTH_SHORT).show()
//            }
//        }


//        authorizeButton.setOnClickListener {
//            val otp = otp1.text.toString() + otp2.text.toString() +
//                    otp3.text.toString() + otp4.text.toString()
//
//            if (otp.length == 4 && verificationId != null) {
//                verifyCode(otp)
//            } else {
//                Toast.makeText(this, "Enter the complete OTP", Toast.LENGTH_SHORT).show()
//            }
//        }
//    }

//    private fun setupOtpInputs(otpFields: Array<EditText>) {
//        for (i in otpFields.indices) {
//            otpFields[i].addTextChangedListener(object : TextWatcher {
//                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
//
//                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                    if (s?.length == 1 && i < otpFields.size - 1) {
//                        otpFields[i + 1].requestFocus() // Move to the next field
//                    } else if (s.isNullOrEmpty() && i > 0) {
//                        otpFields[i - 1].requestFocus() // Move to the previous field on backspace
//                    }
//                }
//
//                override fun afterTextChanged(s: Editable?) {}
//            })
//        }
//    }

//    private fun startOtpCooldown() {
//        sendOtpButton.isEnabled = false
//        object : CountDownTimer(10000, 1000) {
//            override fun onTick(millisUntilFinished: Long) {
//                sendOtpButton.text = "Wait ${millisUntilFinished / 1000}s"
//            }
//
//            override fun onFinish() {
//                sendOtpButton.isEnabled = true
//                sendOtpButton.text = "Resend OTP"
//            }
//        }.start()
//    }
//
//    private fun sendVerificationCode(phoneNumber: String) {
//        val options = PhoneAuthOptions.newBuilder(auth)
//            .setPhoneNumber(phoneNumber) // Send with country code
//            .setTimeout(60L, TimeUnit.SECONDS)
//            .setActivity(this)
//            .setCallbacks(object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
//                override fun onVerificationCompleted(credential: PhoneAuthCredential) {
//                    signInWithCredential(credential)
//                }
//
//                override fun onVerificationFailed(e: FirebaseException) {
//                    Log.e("LoginActivity", "Verification failed: ${e.message}")
//                    Toast.makeText(this@LoginActivity, "Verification failed: ${e.message}", Toast.LENGTH_SHORT).show()
//                    sendOtpButton.isEnabled = true  // Re-enable the button if failure occurs
//                    sendOtpButton.text = "Resend OTP"
//                }
//
//                override fun onCodeSent(id: String, token: PhoneAuthProvider.ForceResendingToken) {
//                    super.onCodeSent(id, token)
//                    verificationId = id
//                    Toast.makeText(this@LoginActivity, "OTP sent!", Toast.LENGTH_SHORT).show()
//                }
//            })
//            .build()
//        PhoneAuthProvider.verifyPhoneNumber(options)
//    }
//
//    private fun verifyCode(code: String) {
//        val credential = PhoneAuthProvider.getCredential(verificationId!!, code)
//        signInWithCredential(credential)
//    }
//
//    private fun signInWithCredential(credential: PhoneAuthCredential) {
//        auth.signInWithCredential(credential).addOnCompleteListener { task ->
//            if (task.isSuccessful) {
//                Toast.makeText(this, "Authentication successful!", Toast.LENGTH_SHORT).show()
//                // Navigate to next activity
//            } else {
//                Toast.makeText(this, "Authentication failed!", Toast.LENGTH_SHORT).show()
//            }
//        }
//    }

//}
///