package com.firstems.erp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.firstems.erp.R
import com.firstems.erp.common.BaseFragment

class ApprovedFragment : BaseFragment() {
    companion object{
        fun newInstance() : ApprovedFragment {
            return ApprovedFragment()
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.approved_fragment, container, false)
    }
}