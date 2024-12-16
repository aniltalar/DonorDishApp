package com.aniltalariS3175001.fooddonation

import android.content.Context

object DonorDetails {

    private const val PREFS_NAME = "FoodDonation"

    fun saveDonorStatus(context: Context, value: Boolean) {
        val sharedPref = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putBoolean("DONOR_STATUS", value).apply()
    }

    fun getDonorStatus(context: Context): Boolean {
        val sharedPref = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return sharedPref.getBoolean("DONOR_STATUS", false)
    }

    fun saveDonorEmail(context: Context, email: String) {
        val sharedPref = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putString("DONOR_EMAIL", email).apply()
    }

    fun getDonorEmail(context: Context): String? {
        val sharedPref = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return sharedPref.getString("DONOR_EMAIL", null)
    }

}