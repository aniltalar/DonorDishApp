package com.aniltalariS3175001.fooddonation

import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.aniltalariS3175001.fooddonation.fragments.DonateFoodFragment
import com.aniltalariS3175001.fooddonation.fragments.HomeFragment
import com.aniltalariS3175001.fooddonation.fragments.MyDonationsFragment

class ContainerActivity : AppCompatActivity() {

    private lateinit var btMyDonations : LinearLayout
    private lateinit var btDonateFood : LinearLayout
    private lateinit var btFoodHome : LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)

        btMyDonations = findViewById(R.id.btMyDontaions)
        btDonateFood = findViewById(R.id.btDonateFood)
        btFoodHome = findViewById(R.id.btFoodHome)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, HomeFragment())
            .addToBackStack(null).commit()

        clickFoodNavigation()
    }

    private fun clickFoodNavigation()
    {
        btFoodHome.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, HomeFragment())
                .addToBackStack(null).commit()
        }

        btDonateFood.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, DonateFoodFragment())
                .addToBackStack(null).commit()
        }

        btMyDonations.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, MyDonationsFragment())
                .addToBackStack(null).commit()
        }
    }
}