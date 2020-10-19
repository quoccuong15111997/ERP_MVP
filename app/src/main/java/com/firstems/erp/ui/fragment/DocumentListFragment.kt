package com.firstems.erp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.firstems.erp.R
import com.firstems.erp.common.BaseFragment
import com.ncapdevi.fragnav.FragNavController

class DocumentListFragment : BaseFragment() {
    companion object{
        fun newInstance() : DocumentListFragment {
            return DocumentListFragment()
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.document_list_fragment, container, false)
    }


}