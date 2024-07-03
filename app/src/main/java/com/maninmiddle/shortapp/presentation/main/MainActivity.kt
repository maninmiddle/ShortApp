package com.maninmiddle.shortapp.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.maninmiddle.core.util.MainActivityFragmentContract
import com.maninmiddle.feature_flights.presentation.flights.FlightsFragment
import com.maninmiddle.shortapp.R
import com.maninmiddle.shortapp.databinding.ActivityMainBinding
import com.maninmiddle.shortapp.presentation.fragments.anyway.AnywayFragment
import com.maninmiddle.shortapp.presentation.fragments.hotels.HotelsFragment
import com.maninmiddle.shortapp.presentation.fragments.profile.ProfileFragment
import com.maninmiddle.shortapp.presentation.fragments.subs.SubsFragment

class MainActivity : AppCompatActivity(), MainActivityFragmentContract {
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


    override fun addFragment(fragment: Fragment, addToBackStack: Boolean) {
        val supportFragmentManager = supportFragmentManager.beginTransaction()
        supportFragmentManager.add(binding.frameLayout.id, fragment)
        if (addToBackStack) {
            supportFragmentManager.addToBackStack(null)
        }

        supportFragmentManager.commit()
    }

    override fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(binding.frameLayout.id, fragment).commit()
    }
}