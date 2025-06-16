package com.yanschool.finapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.yanschool.ui.theme.FinAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            val systemUiController = rememberSystemUiController()
            FinAppTheme {
                systemUiController.setStatusBarColor(MaterialTheme.colorScheme.primary)
                systemUiController.setNavigationBarColor(MaterialTheme.colorScheme.surface)
                MainScreen()
            }
        }
    }
}