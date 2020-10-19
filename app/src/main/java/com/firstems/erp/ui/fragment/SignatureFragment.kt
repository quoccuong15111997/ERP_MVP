package com.firstems.erp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.firstems.erp.R
import com.firstems.erp.common.BaseFragment

class SignatureFragment : BaseFragment() {
    companion object{
        fun newInstance() : SignatureFragment {
            return SignatureFragment()
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.signature_fragment, container, false)
    }
}