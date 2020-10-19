package com.firstems.erp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.firstems.erp.R
import com.firstems.erp.common.BaseFragment
import com.ncapdevi.fragnav.FragNavController
import kotlinx.android.synthetic.main.document_fragment.*
import java.lang.IllegalStateException

class DocumentFragment : BaseFragment(),FragNavController.RootFragmentListener {
    private lateinit var fragNavController: FragNavController
    private lateinit var fragments : List<Fragment>
    companion object{
        fun newInstance() : DocumentFragment {
            return DocumentFragment()
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        addControls()
        return inflater.inflate(R.layout.document_fragment, container, false)
    }
    private fun addEvents(savedInstanceState: Bundle?) {
        textView2.setOnClickListener(View.OnClickListener {
            fragNavController = FragNavController(activity!!.supportFragmentManager,R.id.container)
            fragNavController.rootFragments = fragments
            fragNavController.initialize(FragNavController.TAB1,savedInstanceState)
        })
    }

    private fun addControls() {
        fragments = listOf(
            DocumentListFragment.newInstance()
        )
    }

    override val numberOfRootFragments: Int
        get() = fragments.size

    override fun getRootFragment(index: Int): Fragment {
        when(index){
            0 -> return DocumentListFragment.newInstance()
    }
        throw IllegalStateException("Need to send an index that we know")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        fragNavController.onSaveInstanceState(outState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        addEvents(savedInstanceState)
    }
}