package com.firstems.erp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.firstems.erp.R
import com.firstems.erp.common.BaseFragment
import com.firstems.erp.presenter.DashboardFragmentPresenter
import com.firstems.erp.presenter.impl.DashboardFragmentPresenterImpl

class DashboardFragment : BaseFragment(), DashboardFragmentPresenter.View{
    private lateinit var presenter: DashboardFragmentPresenter
    companion object{
        fun newInstance() : DashboardFragment {
            return DashboardFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        addControls()
        addEvents()
        return inflater.inflate(R.layout.dashboard_fragment, container, false)
    }

    private fun addEvents() {

    }

    private fun addControls() {
        presenter = DashboardFragmentPresenterImpl(context!!, this)
    }
}