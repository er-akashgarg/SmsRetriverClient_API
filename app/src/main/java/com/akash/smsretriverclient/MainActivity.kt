package com.akash.smsretriverclient

import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.gms.auth.api.phone.SmsRetriever
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val smsBroadcastReceiver by lazy { SMSBroadcastReceiver() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnOTP.setOnClickListener {
            startSMS()

        }
    }

    private fun startSMS() {
        val client = SmsRetriever.getClient(this)
        val retriever = client.startSmsRetriever()
        retriever.addOnSuccessListener {

            Toast.makeText(this@MainActivity, "Listener started", Toast.LENGTH_SHORT).show()

            val otpListener = object : SMSBroadcastReceiver.OTPListener {
                override fun onOTPReceived(otp: String) {
                    customCodeInput.setText(otp)
                }

                override fun onOTPTimeOut() {
                    Toast.makeText(this@MainActivity, "TimeOut", Toast.LENGTH_SHORT).show()
                }
            }
            smsBroadcastReceiver.injectOTPListener(otpListener)
            registerReceiver(
                smsBroadcastReceiver,
                IntentFilter(SmsRetriever.SMS_RETRIEVED_ACTION)
            )
        }
        retriever.addOnFailureListener {
            Toast.makeText(this@MainActivity, "Problem to start listener", Toast.LENGTH_SHORT)
                .show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(smsBroadcastReceiver)
    }
}
