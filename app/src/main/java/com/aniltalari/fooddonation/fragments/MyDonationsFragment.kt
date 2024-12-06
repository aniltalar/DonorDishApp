package com.aniltalari.fooddonation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aniltalari.fooddonation.R
import com.aniltalari.fooddonation.ui.theme.FoodDonationTheme


class MyDonationsFragment : Fragment(R.layout.fragment_my_donations) {
    override fun onViewCreated(view: android.view.View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<ComposeView>(R.id.myDonationsFragment).setContent {
            FoodDonationTheme {
                MyDonations()
            }
        }
    }

}


@Composable
fun MyDonations() {
    val myDonations = AppData.getMyDonations()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(horizontal = 12.dp)
        ) {

            items(myDonations.size) { index ->
                FoodItemRow(
                    myDonations[index].foodtype,
                    myDonations[index].expirationDate,
                    myDonations[index].quantity,
                    myDonations[index].status
                )
            }
        }
    }
}

@Composable
fun FoodItemRow(
    foodType: String = "Apple",
    expirationDate: String = "Fruit",
    foodQuantity: String = "5 kg",
    status: String = "Available"
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxSize()
            .padding(8.dp)
            .border(
                width = 2.dp, // Stroke width
                color = Color.Black, // Stroke color
                shape = RoundedCornerShape(8.dp) // Optional: For rounded corners
            ),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_food_item),
            contentDescription = "Food Image",
            modifier = Modifier
                .size(100.dp)
                .padding(end = 8.dp)
        )
        Spacer(modifier = Modifier.width(6.dp))

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(vertical = 18.dp),
        ) {
            Text(text = foodType, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(6.dp))
            Text(text = "Expiration Date:$expirationDate", fontSize = 14.sp, color = Color.Black)
            Spacer(modifier = Modifier.height(6.dp))
            Text(text = "Quantity:$foodQuantity ", fontSize = 14.sp, color = Color.Black)
        }

        // Column for status and Confirm button
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(vertical = 18.dp, horizontal = 7.dp)
        ) {
            Text(text = "STATUS", fontSize = 14.sp, color = Color.Red)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = status, fontSize = 14.sp, color = Color.Black)

        }
    }
}