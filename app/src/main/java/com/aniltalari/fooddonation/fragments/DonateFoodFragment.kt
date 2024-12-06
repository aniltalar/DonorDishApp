package com.aniltalari.fooddonation.fragments

import android.os.Bundle
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import com.aniltalari.fooddonation.R
import com.aniltalari.fooddonation.ui.theme.FoodDonationTheme
import java.util.Calendar


class DonateFoodFragment : Fragment(R.layout.fragment_donate_food) {
    override fun onViewCreated(view: android.view.View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<ComposeView>(R.id.donateFoodView).setContent {
            FoodDonationTheme {
                FoodType()
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun DonateFoodFragmentP() {
//    FoodType()
}


@Composable
fun FoodType() {

    val context = LocalContext.current

    var foodType by remember { mutableStateOf("") }
    var quantity by remember { mutableStateOf("") }
    var expirationDate by remember { mutableStateOf("") }
    var pickupDate by remember { mutableStateOf("") }

    // DatePicker dialog for pickup date
    val calendar = Calendar.getInstance()
    val datePickerDialog = android.app.DatePickerDialog(
        context,
        { _, year, month, dayOfMonth ->
            pickupDate = "$dayOfMonth/${month + 1}/$year"
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        // Food Type input
        Text(text = "Food Type")
        BasicTextField(
            value = foodType,
            onValueChange = { foodType = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .height(50.dp),
            singleLine = true,
            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.LightGray, MaterialTheme.shapes.medium)
                        .padding(horizontal = 16.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    if (foodType.isEmpty()) {
                        Text(text = "Enter Food Type", color = Color.Gray)
                    }
                    innerTextField()
                }
            }
        )

        // Quantity input
        Text(text = "Quantity")

        BasicTextField(
            value = quantity,
            onValueChange = { quantity = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .height(50.dp),
            singleLine = true,
            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.LightGray, MaterialTheme.shapes.medium)
                        .padding(horizontal = 16.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    if (quantity.isEmpty()) {
                        Text(text = "Enter Quantity", color = Color.Gray)
                    }
                    innerTextField()
                }
            }
        )

        // Expiration Date input
        Text(text = "Expiration Date")

        BasicTextField(
            value = expirationDate,
            onValueChange = { expirationDate = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .height(50.dp),
            singleLine = true,
            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.LightGray, MaterialTheme.shapes.medium)
                        .padding(horizontal = 16.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    if (expirationDate.isEmpty()) {
                        Text(text = "Enter Expiration Date", color = Color.Gray)
                    }
                    innerTextField()
                }
            }
        )

        // Pickup Date picker
        Text(text = "Select Pickup Date")

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .height(50.dp)
                .clickable {
                    // Handle the click event, e.g., show a date picker
                }
                .background(Color.LightGray, MaterialTheme.shapes.medium)
                .padding(horizontal = 16.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                text = pickupDate.ifEmpty { "Select Pickup Date" },
                color = if (pickupDate.isEmpty()) Color.Gray else Color.Black
            )
            Icon(
                imageVector = Icons.Default.DateRange, // Replace with your desired icon
                contentDescription = "Calendar Icon",
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .size(24.dp)
                    .clickable {
                        datePickerDialog.show()
                    },
                tint = Color.DarkGray
            )
        }



        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { /* Handle Submit */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = MaterialTheme.shapes.medium,
            colors = ButtonDefaults.buttonColors(Color(0xFF5D3FD3))
        ) {
            Text(
                text = "Submit", color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
