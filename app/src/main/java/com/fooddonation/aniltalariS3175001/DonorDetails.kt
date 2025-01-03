package com.fooddonation.aniltalariS3175001

import android.content.Context

object DonorDetails {

    var isFirstLogin = false

    private const val DONATION_NAME = "FoodDonation"

    fun saveDonorStatus(context: Context, value: Boolean) {
        val donorStatus = context.getSharedPreferences(DONATION_NAME, Context.MODE_PRIVATE)
        val statusE = donorStatus.edit()
        statusE.putBoolean("DONOR_STATUS", value).apply()
    }

    fun getDonorStatus(context: Context): Boolean {
        val donorStatus = context.getSharedPreferences(DONATION_NAME, Context.MODE_PRIVATE)
        return donorStatus.getBoolean("DONOR_STATUS", false)
    }

    fun saveDonorEmail(context: Context, email: String) {
        val donorEmail = context.getSharedPreferences(DONATION_NAME, Context.MODE_PRIVATE)
        val emailE = donorEmail.edit()
        emailE.putString("DONOR_EMAIL", email).apply()
    }

    fun getDonorEmail(context: Context): String? {
        val donorEmail = context.getSharedPreferences(DONATION_NAME, Context.MODE_PRIVATE)
        return donorEmail.getString("DONOR_EMAIL", null)
    }

}