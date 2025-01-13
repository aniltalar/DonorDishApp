package com.fooddonation.aniltalariS3175001.fragments

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fooddonation.aniltalariS3175001.DonorDetails
import com.fooddonation.aniltalariS3175001.R
import com.google.firebase.database.FirebaseDatabase

class FAQActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            if (SelectedItem.selectedOption == 0) {
                FAQActivityScreen()
            } else if (SelectedItem.selectedOption == 1) {
                AcceptableFoodScreen()
            } else if (SelectedItem.selectedOption == 2) {
                FoodHandlingGuideLines()
            } else if (SelectedItem.selectedOption == 3) {
                ContactUsForm()
            }
        }
    }
}


@Composable
fun FAQActivityScreen() {

    val activityContext = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Blue)
                .padding(vertical = 6.dp, horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {

            Image(painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                contentDescription = "Back",
                modifier = Modifier
                    .clickable {
                        (activityContext as Activity).finish()
                    }
                    .padding(start = 8.dp)
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

    val activityContext = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Blue)
                .padding(vertical = 6.dp, horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {

            Image(painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                contentDescription = "Back",
                modifier = Modifier

                    .clickable {
                        (activityContext as Activity).finish()
                    }
                    .padding(start = 8.dp)
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
                width = 1.dp,
                color = Color.Black,
                shape = RoundedCornerShape(4.dp)
            )
            .padding(horizontal = 8.dp, vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically,
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

@Composable
fun FoodHandlingGuideLines() {

    val activityContext = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Blue)
                .padding(vertical = 6.dp, horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {

            Image(painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                contentDescription = "Back",
                modifier = Modifier
                    .clickable {
                        (activityContext as Activity).finish()
                    }
                    .padding(start = 8.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))


            Text(
                text = "Food Handling Guidelines",
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                color = Color.White
            )

        }

        Spacer(modifier = Modifier.height(12.dp))

        FoodWasteReductionInfo()

    }

}


@Composable
fun FoodWasteReductionInfo() {
    Column(
        modifier = Modifier
            .padding(horizontal = 12.dp)
            .verticalScroll(rememberScrollState())
    ) {
        // Title or Heading
        Text(
            text = "Food Waste Reduction",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        // Waste Hierarchy
        Text(
            text = "Waste Hierarchy:",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            modifier = Modifier.padding(top = 8.dp)
        )
        Text(
            text = "The UK follows a waste hierarchy that prioritizes preventing food waste, followed by redistribution, recycling (e.g., composting), and, as a last resort, disposal."
        )

        // Regulations
        Text(
            text = "Regulations:",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            modifier = Modifier.padding(top = 8.dp)
        )
        Text(
            text = "Businesses producing over 50kg of food waste per week are mandated to segregate and arrange for separate collection of this waste, aiming to reduce landfill use and promote recycling."
        )

        // Voluntary Initiatives
        Text(
            text = "Voluntary Initiatives:",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            modifier = Modifier.padding(top = 8.dp)
        )
        Text(
            text = "Programs like the Food Waste Reduction Roadmap encourage businesses to commit to reducing food waste in line with the UN’s Sustainable Development Goal 12.3, which aims for a 50% per capita reduction by 2030."
        )

        // Best Practices for Food Handling to Minimize Waste
        Text(
            text = "Best Practices for Food Handling to Minimize Waste:",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            modifier = Modifier.padding(top = 8.dp)
        )

        // Best Practices List
        Text(
            text = "• Inventory Management: Implementing the First In, First Out (FIFO) method ensures older stock is used before newer items, reducing spoilage."
        )
        Text(
            text = "• Proper Storage: Storing food at appropriate temperatures and conditions prolongs shelf life and maintains quality."
        )
        Text(
            text = "• Portion Control: Serving appropriate portion sizes can reduce plate waste and the need for disposal."
        )
        Text(
            text = "• Utilizing Leftovers: Incorporating surplus ingredients into new dishes or donating edible surplus to food redistribution organizations can effectively reduce waste."
        )
    }
}


@Composable
fun ContactUsForm() {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }

    val activityContext = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Blue)
                .padding(vertical = 6.dp, horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {

            Image(painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                contentDescription = "Back",
                modifier = Modifier
                    .clickable {
                        (activityContext as Activity).finish()
                    }
                    .padding(start = 8.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))


            Text(
                text = "Contact Us",
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                color = Color.White
            )

        }

        Column(
            modifier = Modifier.padding(horizontal = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(12.dp))

            TextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Name") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            )

            TextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(onNext = { /* Move to next field */ })
            )

            TextField(
                value = message,
                onValueChange = { message = it },
                label = { Text("Message") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                maxLines = 5,
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(onDone = { /* Handle Done action */ })
            )

            Button(
                onClick = {
                    if (name.isNotEmpty() && email.isNotEmpty() && message.isNotEmpty()) {
                        sendMessage(name, email, message, activityContext)
                    } else {
                        Toast.makeText(activityContext, "Please fill out all fields", Toast.LENGTH_SHORT)
                            .show()
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Send", fontSize = 18.sp)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "Anil Talari DonorDish Food Donation App", fontSize = 16.sp)
            Text(text = "S3175001", fontSize = 16.sp)
            Text(text = "anil.talari246@gmail.com", fontSize = 16.sp)
            Text(text = "Teesside University, Middlesbrough", fontSize = 16.sp)

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Open Location",
                color = Color.Blue,
                modifier = Modifier.clickable {
                    openMapLink(activityContext)
                }
            )
        }
    }
}

data class Contact(
    var name: String = "",
    var email: String = "",
    var message: String = ""
)

fun sendMessage(name: String, email: String, message: String, activityContext: android.content.Context) {
    val fireDB = FirebaseDatabase.getInstance()
    val databaseRef = fireDB.getReference("ContactUs")

    val userNode = DonorDetails.getDonorEmail(activityContext)!!.replace(".", ",")

    val contactInfo = Contact(name, email, message)

    try {
        databaseRef.child(userNode).child(userNode).setValue(contactInfo)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(activityContext, "Message sent: $message", Toast.LENGTH_SHORT).show()
                    (activityContext as Activity).finish()
                } else {
                    Toast.makeText(
                        activityContext,
                        "Message send failed:${task.exception?.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            .addOnFailureListener { exception ->
                Toast.makeText(
                    activityContext,
                    "Message Failed ${exception.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
    } catch (e: Exception) {
    }


}

fun openMapLink(activityContext: android.content.Context) {
    val uri = Uri.parse("https://maps.app.goo.gl/NX3moR6o33rXKCSS6")
    val intent = Intent(Intent.ACTION_VIEW, uri)
    activityContext.startActivity(intent)
}

object SelectedItem {
    var selectedOption = 0
}