package com.example.optly

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.optly.ui.BudgetFragment
import com.example.optly.ui.FitnessFragment
import com.example.optly.ui.HomeFragment
import com.example.optly.ui.ScheduleFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        // Default fragment
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, HomeFragment())
                .commit()
        }

        bottomNav.setOnItemSelectedListener { item ->
            val fragment = when (item.itemId) {
                R.id.nav_home -> HomeFragment()
                R.id.nav_budget -> BudgetFragment()
                R.id.nav_fitness -> FitnessFragment()
                R.id.nav_schedule -> ScheduleFragment()
                R.id.nav_filemanager -> FileManagerFragment()
                else -> null
            }

            fragment?.let {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, it)
                    .commit()
                true
            } ?: false
        }

    }
}
