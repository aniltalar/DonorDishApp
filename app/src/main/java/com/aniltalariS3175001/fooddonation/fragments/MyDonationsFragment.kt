package com.aniltalariS3175001.fooddonation.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import com.aniltalariS3175001.fooddonation.AccountAccessActivity
import com.aniltalariS3175001.fooddonation.DonorDetails
import com.aniltalariS3175001.fooddonation.R
import com.aniltalariS3175001.fooddonation.ui.theme.FoodDonationTheme
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


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
    val context = LocalContext.current as Activity

    val donorEmail = DonorDetails.getDonorEmail(context)!!

    var orderList by remember { mutableStateOf(listOf<FoodData>()) }

    // Fetch orders
    LaunchedEffect(donorEmail) {
        getDonationsList(donorEmail) { orders ->
            orderList = orders
        }
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Blue)
                .padding(vertical = 6.dp, horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween, // Space between elements in the row
            verticalAlignment = Alignment.CenterVertically, // Align vertically to center
        ) {

            Text(
                text = "My Donations",
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                color = Color.White
            )
            Image(painter = painterResource(id = R.drawable.baseline_logout_24),
                contentDescription = "Logout",
                modifier = Modifier

                    .clickable {
                        // Navigate to LoginActivity when clicked
                        DonorDetails.saveDonorStatus(context, false)

                        val intent = Intent(context, AccountAccessActivity::class.java)
                        context.startActivity(intent)
                        context.finish()
                    }
                    .padding(start = 8.dp) // Optional spacing // Optional spacing


            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        if (orderList.isEmpty()) {
            Spacer(modifier = Modifier.weight(1f))

            Text(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                text = "No Donations Found",
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                color = Color.Black
            )

            Spacer(modifier = Modifier.weight(1f))


        } else
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(horizontal = 12.dp)
            ) {

                items(orderList.size) { index ->
                    FoodItemRow(
                        orderList[index].foodtype,
                        orderList[index].expirationDate,
                        orderList[index].quantity,
                        orderList[index].status
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

fun getDonationsList(userEmail: String, callback: (List<FoodData>) -> Unit) {
    // Convert email to valid Firebase key format
    val emailKey = userEmail.replace(".", ",")

    // Reference to the Donations branch
    val databaseReference = FirebaseDatabase.getInstance().getReference("Donations/$emailKey")

    databaseReference.addListenerForSingleValueEvent(object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            val donationsList = mutableListOf<FoodData>()
            for (donationSnapshot in snapshot.children) {
                val donation = donationSnapshot.getValue(FoodData::class.java)
                donation?.let { donationsList.add(it) }
            }
            callback(donationsList)
        }

        override fun onCancelled(error: DatabaseError) {
            println("Error: ${error.message}")
            callback(emptyList()) // Return an empty list in case of error
        }
    })
}

@Preview(showBackground = true)
@Composable
fun MyDonationsPreview() {
    // FoodItemRow()
    FoodDonationTheme {}

    MyDonations()


}
