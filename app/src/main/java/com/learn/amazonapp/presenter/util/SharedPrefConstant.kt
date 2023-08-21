package com.learn.amazonapp.presenter.util

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys


object SharedPrefConstant {
    private const val  SECURED_PREF_FILE_NAME ="confidential_info"

    const val EMAIL_ID="email"
    const val MOBILE_NO="mobile_no"
    const val USER_ID="user_id"
    const val FULL_NAME="full_name"

    fun getSecuredSharedPref(context: Context): SharedPreferences {
        val masterKeys =MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
        return EncryptedSharedPreferences.create(
            SECURED_PREF_FILE_NAME,
            masterKeys,context,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM)
    }
}