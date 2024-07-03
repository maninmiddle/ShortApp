package com.maninmiddle.shortapp.util.fragmentProvider

import androidx.fragment.app.Fragment
import com.maninmiddle.core.util.FragmentProvider
import com.maninmiddle.shortapp.presentation.fragments.stub.StubFragment

class FragmentProviderImpl : FragmentProvider {
    override fun getStubFragment(): Fragment {
        return StubFragment()
    }
}