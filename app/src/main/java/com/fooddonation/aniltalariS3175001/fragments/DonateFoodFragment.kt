package com.fooddonation.aniltalariS3175001.fragments

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.util.Base64
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
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
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import coil.compose.rememberAsyncImagePainter
import com.fooddonation.aniltalariS3175001.DonorDetails
import com.fooddonation.aniltalariS3175001.R
import com.fooddonation.aniltalariS3175001.ui.theme.FoodDonationTheme
import com.google.firebase.database.FirebaseDatabase
import java.io.ByteArrayOutputStream
import java.io.File
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale


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
    FoodType()
}


@Composable
fun FoodType() {

    val activityContext = LocalContext.current

    var errorMessage by remember { mutableStateOf("") }

    var foodType by remember { mutableStateOf("Select") }
    var pickupPlace by remember { mutableStateOf("Select") }
    var preferredTime by remember { mutableStateOf("Select") }


    var quantity by remember { mutableStateOf("") }
    var expirationDate by remember { mutableStateOf("") }
    var pickupDate by remember { mutableStateOf("") }

    val calendar = Calendar.getInstance()
    val expirationDatePickerDialog = android.app.DatePickerDialog(
        activityContext,
        { _, year, month, dayOfMonth ->
            expirationDate = "$dayOfMonth/${month + 1}/$year"
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )

    val pickupDatePickerDialog = android.app.DatePickerDialog(
        activityContext,
        { _, year, month, dayOfMonth ->
            pickupDate = "$dayOfMonth/${month + 1}/$year"
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )

    Column(
        modifier = Modifier.fillMaxSize() // Full screen Column
    ) {
        // Donate Food Bar
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Blue)
                .padding(vertical = 6.dp, horizontal = 16.dp) // Padding inside the bar
        ) {
            Text(
                text = "Donate Food",
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                color = Color.White,
                modifier = Modifier.align(Alignment.Center) // Center the text vertically
            )
        }


        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        )
        {
            Spacer(modifier = Modifier.height(16.dp))

            PickupWhereDropDown(
                pickupwhere = listOf(
                    "Middlesbrough, United Kingdom",
                    "Billingham, United Kingdom",
                    "Thornaby, United Kingdom",
                    "Stockton-on-Tees, United Kingdom",
                    "Redcar, United Kingdom",
                    "Hartlepool, United Kingdom",
                    "Darlington, United Kingdom",
                    "Newton Aycliffe,UnitedKingdom",
                ),
                selectedpickupplace = pickupPlace,
                onpickupplaceSelected = { pickupPlace = it },
            )

            Spacer(modifier = Modifier.height(8.dp))

            FoodItemsDropDown(
                foodItems = listOf(
                    "10 AM to 11 AM",
                    "11 AM to 12 PM",
                    "12 PM to 1 PM",
                    "1 PM to 2 PM",
                    "5 PM to 6 PM",
                    "7 PM to 8 PM",
                    "8 PM to 9 PM"
                ),
                selectedFoodItem = preferredTime,
                onFoodItemSelected = { preferredTime = it },
                "Select Preferred Time"
            )

            Spacer(modifier = Modifier.height(8.dp))


            FoodItemsDropDown(
                foodItems = listOf(
                    "Sandwiches",
                    "Wraps",
                    "Pasta",
                    "Pizza",
                    "Dosa",
                    "Parathas",
                    "Chicken",
                    "Cheddar",
                    "Red Leicester",
                    "Breadcrumbs",
                    "Bay Leaves",
                    "Nutmeg"
                ),
                selectedFoodItem = foodType,
                onFoodItemSelected = { foodType = it },
                "Select Food Item"
            )

            Spacer(modifier = Modifier.height(8.dp))


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
                    text = expirationDate.ifEmpty { "Select Expiration Date" },
                    color = if (expirationDate.isEmpty()) Color.Gray else Color.Black
                )
                Icon(
                    imageVector = Icons.Default.DateRange, // Replace with your desired icon
                    contentDescription = "Calendar Icon",
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .size(24.dp)
                        .clickable {
                            expirationDatePickerDialog.show()
                        },
                    tint = Color.DarkGray
                )
            }


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
                            pickupDatePickerDialog.show()
                        },
                    tint = Color.DarkGray
                )
            }

            CaptureImageExample()

//            Image(
//                painter = painterResource(id = R.drawable.ic_add_image), contentDescription = "Food",
//                modifier = Modifier
//                    .width(100.dp)
//                    .height(100.dp)
//                    .align(Alignment.CenterHorizontally)
//                    .clickable {
//                        CaptureImageExample()
//                    }
//            )


            Spacer(modifier = Modifier.height(24.dp))

            if (errorMessage.isNotEmpty()) {
                Text(
                    text = errorMessage,
                    color = Color.Red,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }

            Button(
                onClick = {
                    when {
                        foodType.isBlank() || foodType == "Select" -> {
                            errorMessage = "Select food item"
                        }

                        quantity.isBlank() -> {
                            errorMessage = "Quantity is required."
                        }

                        expirationDate.isBlank() -> {
                            errorMessage = "Expiration Date is required."
                        }

                        pickupDate.isBlank() -> {
                            errorMessage = "Pickup Date is required."
                        }

                        else -> {
                            errorMessage = ""
                            val foodData = FoodData(
                                foodtype = foodType,
                                quantity = quantity,
                                expirationDate = expirationDate,
                                pickUpDate = pickupDate,
                                status = "Pending",
                                userMail = DonorDetails.getDonorEmail(activityContext)!!
                            )
                            saveFoodToDBWithBase64Image(
                                foodData,
                                SelectedImage.selImageUri,
                                activityContext
                            )

                        }
                    }


                },

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
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoodItemsDropDown(
    foodItems: List<String>,
    selectedFoodItem: String,
    onFoodItemSelected: (String) -> Unit,
    label: String = "Select Item"
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Transparent)
    ) {
        TextField(
            value = selectedFoodItem,
            onValueChange = {},
            readOnly = true,
            label = { Text(label) },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth()
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray)

        ) {
            foodItems.forEach { option ->
                DropdownMenuItem(
                    onClick = {
                        onFoodItemSelected(option)
                        expanded = false
                    },
                    text = { Text(option) }
                )
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PickupWhereDropDown(
    pickupwhere: List<String>,
    selectedpickupplace: String,
    onpickupplaceSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Transparent)
    ) {
        TextField(
            value = selectedpickupplace,
            onValueChange = {},
            readOnly = true,
            label = { Text("Select Pickup Place") },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth()
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray)

        ) {
            pickupwhere.forEach { option ->
                DropdownMenuItem(
                    onClick = {
                        onpickupplaceSelected(option)
                        expanded = false
                    },
                    text = { Text(option) }
                )
            }
        }
    }
}


private fun saveFoodToDBOld(foodData: FoodData, activityContext: Context) {

    val fireDB = FirebaseDatabase.getInstance()
    val databaseRef = fireDB.getReference("Donations")

    val dateFormat = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault())
    val orderId = dateFormat.format(Date())

    databaseRef.child(foodData.userMail.replace(".", ",")).child(orderId).setValue(foodData)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {


                Toast.makeText(activityContext, "Added Successfully", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(
                    activityContext,
                    "User Registration Failed: ${task.exception?.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        .addOnFailureListener { exception ->
            Toast.makeText(
                activityContext,
                "User Registration Failed: ${exception.message}",
                Toast.LENGTH_SHORT
            ).show()
        }
}

private fun saveFoodToDBWithBase64Image(foodData: FoodData, imageUri: Uri, activityContext: Context) {
    val fireDB = FirebaseDatabase.getInstance()
    val databaseRef = fireDB.getReference("Donations")

    val dateFormat = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault())
    val orderId = dateFormat.format(Date())
    val userNode = foodData.userMail.replace(".", ",")

    try {
        val inputStream = activityContext.contentResolver.openInputStream(imageUri)
        val bitmap = BitmapFactory.decodeStream(inputStream)
        val outputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
        val base64Image = Base64.encodeToString(outputStream.toByteArray(), Base64.DEFAULT)

        val updatedFoodData = foodData.copy(imageUrl = base64Image)

        databaseRef.child(userNode).child(orderId).setValue(updatedFoodData)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(activityContext, "Added Successfully", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(
                        activityContext,
                        "Failed to add data: ${task.exception?.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            .addOnFailureListener { exception ->
                Toast.makeText(
                    activityContext,
                    "Data save failed: ${exception.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
    } catch (e: Exception) {
        Toast.makeText(activityContext, "Image conversion failed: ${e.message}", Toast.LENGTH_SHORT).show()
    }
}


@Composable
fun CaptureImageExample() {
    val activityContext = LocalContext.current

    var imageUri by remember { mutableStateOf<Uri?>(null) }

    val captureImageLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture(),
        onResult = { success ->
            if (success) {
                imageUri = getImageUri(activityContext)
                SelectedImage.selImageUri = imageUri as Uri

//                Toast.makeText(activityContext, "Image Captured", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(activityContext, "Capture Failed", Toast.LENGTH_SHORT).show()
            }
        }
    )

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            if (isGranted) {
                Toast.makeText(activityContext, "Permission Granted", Toast.LENGTH_SHORT).show()
                captureImageLauncher.launch(getImageUri(activityContext)) // Launch the camera
            } else {
                Toast.makeText(activityContext, "Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = if (imageUri != null) {
                rememberAsyncImagePainter(model = imageUri)
            } else {
                painterResource(id = R.drawable.ic_add_image)
            },
            contentDescription = "Captured Image",
            modifier = Modifier
                .width(100.dp)
                .height(100.dp)
                .clickable {
                    if (ContextCompat.checkSelfPermission(
                            activityContext,
                            Manifest.permission.CAMERA
                        ) == PackageManager.PERMISSION_GRANTED
                    ) {
                        captureImageLauncher.launch(getImageUri(activityContext))
                    } else {
                        permissionLauncher.launch(Manifest.permission.CAMERA)
                    }
                }
        )
        Spacer(modifier = Modifier.height(16.dp))
        if (imageUri == null) {
            Text(text = "Tap the image to capture")
        }
    }
}

fun getImageUri(activityContext: Context): Uri {
    val file = File(activityContext.filesDir, "captured_image.jpg")
    return FileProvider.getUriForFile(
        activityContext,
        "${activityContext.packageName}.fileprovider",
        file
    )
}


object SelectedImage {
    lateinit var selImageUri: Uri
}


