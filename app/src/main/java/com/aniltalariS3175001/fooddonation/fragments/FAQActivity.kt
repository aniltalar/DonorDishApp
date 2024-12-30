package com.aniltalariS3175001.fooddonation.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.aniltalariS3175001.fooddonation.AccountAccessActivity
import com.aniltalariS3175001.fooddonation.DonorDetails
import com.aniltalariS3175001.fooddonation.R

class FAQActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            if (SelectedItem.selectedOption == 0) {
                FAQActivityScreen()
            } else {
                AcceptableFoodScreen()
            }
        }
    }
}


@Composable
fun FAQActivityScreen() {

    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Blue)
                .padding(vertical = 6.dp, horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically, // Align vertically to center
        ) {

            Image(painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                contentDescription = "Back",
                modifier = Modifier
                    .clickable {
                        (context as Activity).finish()
                    }
                    .padding(start = 8.dp) // Optional spacing // Optional spacing
            )
            Spacer(modifier = Modifier.width(12.dp))


            Text(
                text = "FAQ",
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                color = Color.White
            )

        }

        Spacer(modifier = Modifier.height(12.dp))

        Column(
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = "What is a Donor Dish App?",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )

            Text(
                text = "Donor Dish App is a non-profit, charitable app that collects food parcels and targets to distribute  to those who have difficulty purchasing enough to avoid hunger.",
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "What is in a food parcel?",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )

            Text(
                text = "A typical food parcel includes cereal, soup, pasta, rice, tinned tomatoes, pasta sauce, beans, tinned meat, tinned vegetables, tea or coffee, tinned fruit, biscuits, UHT milk and fruit juice.",
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Which foodbanks can use this app?",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )

            Text(
                text = "Donor Dish App is open to all UK foodbanks. As long as they're registered with Donor Dish App, you'll be able to make a donation or possibly volunteer with them.",
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "How do I make a donation?",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )

            Text(
                text = "You can donate within the app by selecting products in the app. Just you need to do login and donate the food. ",
                fontSize = 14.sp
            )
        }
    }
}

@Composable
fun AcceptableFoodScreen() {

    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Blue)
                .padding(vertical = 6.dp, horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically, // Align vertically to center
        ) {

            Image(painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                contentDescription = "Back",
                modifier = Modifier

                    .clickable {
                        (context as Activity).finish()
                    }
                    .padding(start = 8.dp) // Optional spacing // Optional spacing
            )

            Spacer(modifier = Modifier.width(12.dp))

            Text(
                text = "Acceptable Food",
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                color = Color.White
            )

        }

        Spacer(modifier = Modifier.height(12.dp))

        Column(
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .verticalScroll(rememberScrollState())
        ) {

            FoodItemDetails(itemName = "Sandwiches")
            Spacer(modifier = Modifier.height(8.dp))

            FoodItemDetails(itemName = "Wraps")
            Spacer(modifier = Modifier.height(8.dp))

            FoodItemDetails(itemName = "Pasta")
            Spacer(modifier = Modifier.height(8.dp))

            FoodItemDetails(itemName = "Pizza")
            Spacer(modifier = Modifier.height(8.dp))

            FoodItemDetails(itemName = "Dosa")
            Spacer(modifier = Modifier.height(8.dp))

            FoodItemDetails(itemName = "Parathas")
            Spacer(modifier = Modifier.height(8.dp))

            FoodItemDetails(itemName = "Chicken")
            Spacer(modifier = Modifier.height(8.dp))

            FoodItemDetails(itemName = "Cheddar")
            Spacer(modifier = Modifier.height(8.dp))

            FoodItemDetails(itemName = "Red Leicester")
            Spacer(modifier = Modifier.height(8.dp))

            FoodItemDetails(itemName = "Breadcrumbs")
            Spacer(modifier = Modifier.height(8.dp))

            FoodItemDetails(itemName = "Bay Leaves")
            Spacer(modifier = Modifier.height(8.dp))

            FoodItemDetails(itemName = "Nutmeg")
            Spacer(modifier = Modifier.height(8.dp))

        }
    }
}

@Composable
fun FoodItemDetails(
    itemName: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 1.dp, // Stroke width
                color = Color.Black, // Stroke color
                shape = RoundedCornerShape(4.dp) // Optional: For rounded corners
            )
            .padding(horizontal = 8.dp, vertical = 6.dp)
        ,
        verticalAlignment = Alignment.CenterVertically, // Align vertically to center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_food_item),
            contentDescription = "FoodItem",
            modifier = Modifier
        )

        Spacer(modifier = Modifier.width(12.dp))

        Text(
            text = itemName,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
    }
}

object SelectedItem {
    var selectedOption = 0
}