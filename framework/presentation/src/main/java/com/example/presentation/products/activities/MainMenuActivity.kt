package com.example.presentation.products.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.presentation.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainMenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)
    }
}