package com.gustavozreis.meufolego

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.gustavozreis.meufolego.databinding.FragmentStartBinding

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private var binding: FragmentStartBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }


}