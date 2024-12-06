package com.aniltalari.fooddonation.fragments

import android.os.Bundle
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.aniltalari.fooddonation.R
import com.aniltalari.fooddonation.ui.theme.FoodDonationTheme


class RateFoodFragment : Fragment(R.layout.fragment_rate_food) {
    override fun onViewCreated(view: android.view.View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<ComposeView>(R.id.rateFoodFragment).setContent {
            FoodDonationTheme {
//                SelectionEvent(::navigateToBookSlotFragment, ::onProfileClicked)
            }
        }
    }

}