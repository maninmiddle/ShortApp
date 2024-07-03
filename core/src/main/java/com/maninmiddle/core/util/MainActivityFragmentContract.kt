package com.maninmiddle.core.util

import androidx.fragment.app.Fragment

interface MainActivityFragmentContract {
    fun addFragment(fragment: Fragment, addToBackStack: Boolean = true)
    fun replaceFragment(fragment: Fragment)
}