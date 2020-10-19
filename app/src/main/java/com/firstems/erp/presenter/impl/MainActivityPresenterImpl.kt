package com.firstems.erp.presenter.impl

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentController
import com.firstems.erp.presenter.MainActivityPresenter
import com.firstems.erp.ui.fragment.*
import com.ncapdevi.fragnav.FragNavController
import java.lang.IllegalStateException

class MainActivityPresenterImpl(context: Context, view : MainActivityPresenter.View) : MainActivityPresenter, FragNavController.RootFragmentListener{
    private lateinit var fragments : List<Fragment>
    override fun init(fragNavController: FragNavController) {
         fragments = listOf<Fragment>(
            DashboardFragment.newInstance(),
            SignatureFragment.newInstance(),
            ApprovedFragment.newInstance(),
            DocumentFragment.newInstance(),
            MoreFragment.newInstance(),
        )
        fragNavController.rootFragments = fragments
    }

    override val numberOfRootFragments: Int
        get() = fragments.size

    override fun getRootFragment(index: Int): Fragment {
        when(index){
            0 -> return DashboardFragment.newInstance()
            1 -> return SignatureFragment.newInstance()
            2 -> return ApprovedFragment.newInstance()
            3 -> return DocumentFragment.newInstance()
            4 -> return MoreFragment.newInstance()
        }
        throw IllegalStateException("Need to send an index that we know")
    }
}