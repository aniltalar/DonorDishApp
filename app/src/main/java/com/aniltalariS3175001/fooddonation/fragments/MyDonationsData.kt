package com.aniltalariS3175001.fooddonation.fragments



data class MyDonationsData (
    val orderId: String = "",
    val items: Map<String, FoodData> = emptyMap()
)
