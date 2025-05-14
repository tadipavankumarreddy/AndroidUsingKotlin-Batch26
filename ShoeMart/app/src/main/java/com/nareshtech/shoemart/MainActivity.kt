package com.nareshtech.shoemart

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import com.nareshtech.shoemart.data.ShoeViewModel
import com.nareshtech.shoemart.screens.ShoeApp
import com.nareshtech.shoemart.ui.theme.ShoeMartTheme

class MainActivity : ComponentActivity() {
    lateinit var viewModel: ShoeViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ShoeMartTheme {
                viewModel = ViewModelProvider(this)[ShoeViewModel::class.java]
                ShoeApp(viewModel)
            }
        }
    }
}


