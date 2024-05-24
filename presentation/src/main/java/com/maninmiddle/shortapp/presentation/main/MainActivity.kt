package com.maninmiddle.shortapp.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.maninmiddle.presentation.R
import com.maninmiddle.presentation.databinding.ActivityMainBinding
import com.maninmiddle.shortapp.presentation.fragments.anyway.AnywayFragment
import com.maninmiddle.shortapp.presentation.fragments.flights.FlightsFragment
import com.maninmiddle.shortapp.presentation.fragments.hotels.HotelsFragment
import com.maninmiddle.shortapp.presentation.fragments.profile.ProfileFragment
import com.maninmiddle.shortapp.presentation.fragments.subs.SubsFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        replaceFragment(FlightsFragment())

        binding.bottomNav.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menuFlights -> {
                    replaceFragment(FlightsFragment())
                    true
                }

                R.id.menuHotels -> {
                    replaceFragment(HotelsFragment())
                    true
                }

                R.id.menuProfile -> {
                    replaceFragment(ProfileFragment())
                    true
                }

                R.id.menuShort -> {
                    replaceFragment(AnywayFragment())
                    true
                }

                R.id.menuSubscriptions -> {
                    replaceFragment(SubsFragment())
                    true
                }

                else -> false
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(binding.frameLayout.id, fragment).commit()
    }

    fun addFragment(fragment: Fragment, addToBackStack: Boolean = true) {
        val supportFragmentManager = supportFragmentManager.beginTransaction()
        supportFragmentManager.add(binding.frameLayout.id, fragment)
        if (addToBackStack) {
            supportFragmentManager.addToBackStack(null)
        }

        supportFragmentManager.commit()
    }
}