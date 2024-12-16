package com.aniltalariS3175001.fooddonation.fragments

object AppData {
    fun getMyDonations(): List<FoodData> {
        return listOf(
            FoodData("Vegetables", "5kg", "2024-12-01", "2024-11-18", "Available"),
            FoodData("Fruits", "3kg", "2024-11-25", "2024-11-18", "Available"),
            FoodData("Dairy", "2L", "2024-11-20", "2024-11-18", "Expired"),
            FoodData("Grains", "10kg", "2025-01-15", "2024-11-18", "Available"),
            FoodData("Meat", "4kg", "2024-11-19", "2024-11-18", "Available"),
            FoodData("Seafood", "2kg", "2024-11-21", "2024-11-18", "Available"),
            FoodData("Baked Goods", "6pcs", "2024-11-22", "2024-11-18", "Available"),
            FoodData("Snacks", "1kg", "2024-12-10", "2024-11-18", "Available"),
            FoodData("Beverages", "5L", "2025-02-01", "2024-11-18", "Available"),
            FoodData("Frozen Food", "3kg", "2025-03-01", "2024-11-18", "Available")
        )
    }


}

data class FoodData(
    var foodtype: String = "",
    var quantity: String = "",
    var expirationDate: String = "",
    var pickUpDate: String = "",
    var status: String = "",
    var userMail: String = "user1@gmail.com"
)