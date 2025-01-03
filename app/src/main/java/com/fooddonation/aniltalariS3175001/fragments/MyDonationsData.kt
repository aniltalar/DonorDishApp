package com.fooddonation.aniltalariS3175001.fragments



data class MyDonationsData (
    val orderId: String = "",
    val items: Map<String, FoodData> = emptyMap()
)
