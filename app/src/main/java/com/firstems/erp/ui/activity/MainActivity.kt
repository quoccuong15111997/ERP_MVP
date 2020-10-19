package com.firstems.erp.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.firstems.erp.R
import com.firstems.erp.presenter.MainActivityPresenter
import com.firstems.erp.presenter.impl.MainActivityPresenterImpl
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ncapdevi.fragnav.FragNavController
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainActivityPresenter.View{
    private lateinit var persenter : MainActivityPresenter
    private val fragNavController: FragNavController = FragNavController(supportFragmentManager, R.id.container)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addControls()
        addEvents()
        fragNavController.initialize(FragNavController.TAB1,savedInstanceState)
    }

    private fun addEvents() {
        nav_main.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navDashboard -> {
                    fragNavController.switchTab(FragNavController.TAB1)
                }
                R.id.navSignature -> {
                    fragNavController.switchTab(FragNavController.TAB2)
                }
                R.id.navApproved -> {
                    fragNavController.switchTab(FragNavController.TAB3)
                }
                R.id.navDocument -> {
                    fragNavController.switchTab(FragNavController.TAB4)
                }
                R.id.navMore -> {
                    fragNavController.switchTab(FragNavController.TAB5)
                }
            }
            true
        }
    }

    private fun addControls() {
        persenter =  MainActivityPresenterImpl(this, this)
        persenter.init(fragNavController)
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        fragNavController.onSaveInstanceState(outState)
    }
}