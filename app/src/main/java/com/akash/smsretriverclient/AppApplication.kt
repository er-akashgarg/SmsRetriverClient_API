package com.akash.smsretriverclient

import android.app.Application
import android.util.Log

/**
 * @akashgarg
 */
class AppApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        Log.e("##AppApplication","appSignatures = ${AppSignatureHelper(this).getAppSignatures()}")

    }
}