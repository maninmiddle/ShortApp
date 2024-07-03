package com.maninmiddle.core.util

import androidx.fragment.app.Fragment

interface FragmentProvider {
    fun getStubFragment(): Fragment
}