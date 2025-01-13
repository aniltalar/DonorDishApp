package com.fooddonation.aniltalariS3175001.fragments

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.ExifInterface
import android.os.Bundle
import android.util.Base64
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
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import com.fooddonation.aniltalariS3175001.AccountAccessActivity
import com.fooddonation.aniltalariS3175001.DonorDetails
import com.fooddonation.aniltalariS3175001.R
import com.fooddonation.aniltalariS3175001.ui.theme.FoodDonationTheme
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.io.ByteArrayInputStream
import java.io.IOException


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
    val activityContext = LocalContext.current as Activity

    val donorEmail = DonorDetails.getDonorEmail(activityContext)!!

    var orderList by remember { mutableStateOf(listOf<FoodData>()) }
    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(donorEmail) {
        getDonationsList(donorEmail) { orders ->
            orderList = orders

            isLoading = false
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
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
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
                        DonorDetails.saveDonorStatus(activityContext, false)

                        val intent = Intent(activityContext, AccountAccessActivity::class.java)
                        activityContext.startActivity(intent)
                        activityContext.finish()
                    }
                    .padding(start = 8.dp)


            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        } else {

            if (orderList.isEmpty()) {
                Spacer(modifier = Modifier.weight(1f))

                Text(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    text = "No Donations Found",
                    style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                    color = Color.Black
                )

                Spacer(modifier = Modifier.weight(1f))


            } else {
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
                            orderList[index].status,
                            orderList[index].imageUrl
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun FoodItemRow(
    foodType: String = "Apple",
    expirationDate: String = "Fruit",
    foodQuantity: String = "5 kg",
    status: String = "Available",
    foodImage: String = ""
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxSize()
            .padding(8.dp)
            .border(
                width = 2.dp,
                color = Color.Black,
                shape = RoundedCornerShape(8.dp)
            ),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.SpaceAround
    ) {

        if (foodImage == "") {
            Image(
                painter = painterResource(id = R.drawable.ic_food_item),
                contentDescription = "Food Image",
                modifier = Modifier
                    .size(100.dp)
                    .padding(vertical = 8.dp, horizontal = 4.dp)
            )
        } else {

            Image(
                bitmap = decodeBase64ToBitmap(foodImage)!!.asImageBitmap(),
                contentDescription = "Food Image",
                modifier = Modifier
                    .size(100.dp)
                    .padding(end = 8.dp)
            )
        }
        Spacer(modifier = Modifier.width(6.dp))

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(vertical = 8.dp),
        ) {
            Text(text = foodType, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(3.dp))
            Text(text = "Expiration Date:$expirationDate", fontSize = 14.sp, color = Color.Black)
            Spacer(modifier = Modifier.height(3.dp))
            Text(text = "Quantity:$foodQuantity ", fontSize = 14.sp, color = Color.Black)
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 4.dp)
        ) {
            Text(text = "STATUS", fontSize = 14.sp, color = Color.Red)
            Spacer(modifier = Modifier.height(3.dp))
            Text(text = status, fontSize = 14.sp, color = Color.Black)

        }
    }
}

fun getDonationsList(userEmail: String, callback: (List<FoodData>) -> Unit) {
    val emailKey = userEmail.replace(".", ",")

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
            callback(emptyList())
        }
    })
}

fun decodeBase64ToBitmap(base64String: String): Bitmap? {
    val decodedString = Base64.decode(base64String, Base64.DEFAULT)

    // Convert byte array to Bitmap
    val originalBitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)

    // Correct the orientation of the image using ExifInterface
    return rotateImageToRight(originalBitmap)
}


// Function to rotate the image to the right (90 degrees clockwise)
fun rotateImageToRight(bitmap: Bitmap): Bitmap {
    val matrix = Matrix()
    matrix.postRotate(90f) // Rotate 90 degrees clockwise
    return Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
}

fun correctOrientation(bitmap: Bitmap, imageBytes: ByteArray): Bitmap {
    try {
        // Using ExifInterface to get the orientation of the image
        val exif = ExifInterface(ByteArrayInputStream(imageBytes))

        // Get the orientation tag from EXIF metadata
        val orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL)

        // Rotate the image based on the orientation tag
        return when (orientation) {
            ExifInterface.ORIENTATION_ROTATE_90 -> rotateBitmap(bitmap, 90f)
            ExifInterface.ORIENTATION_ROTATE_180 -> rotateBitmap(bitmap, 180f)
            ExifInterface.ORIENTATION_ROTATE_270 -> rotateBitmap(bitmap, 270f)
            ExifInterface.ORIENTATION_FLIP_HORIZONTAL -> flipBitmap(bitmap, horizontal = true, false)
            ExifInterface.ORIENTATION_FLIP_VERTICAL -> flipBitmap(bitmap, horizontal = false, true)
            else -> bitmap // No rotation needed
        }
    } catch (e: IOException) {
        e.printStackTrace()
    }
    return bitmap
}

fun rotateBitmap(source: Bitmap, angle: Float): Bitmap {
    val matrix = android.graphics.Matrix()
    matrix.postRotate(angle)
    return Bitmap.createBitmap(source, 0, 0, source.width, source.height, matrix, true)
}

fun flipBitmap(source: Bitmap, horizontal: Boolean, vertical: Boolean): Bitmap {
    val matrix = android.graphics.Matrix()
    if (horizontal) matrix.postScale(-1f, 1f)
    if (vertical) matrix.postScale(1f, -1f)
    return Bitmap.createBitmap(source, 0, 0, source.width, source.height, matrix, true)
}

@Preview(showBackground = true)
@Composable
fun MyDonationsPreview() {
    FoodDonationTheme {}
    MyDonations()
}
