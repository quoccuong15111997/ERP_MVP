package com.firstems.erp.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firstems.erp.R
import com.firstems.erp.common.BaseActivity
import com.firstems.erp.common.Constant
import com.firstems.erp.network.model.response.user.Company
import com.firstems.erp.network.model.response.user.UserLoginApiResponse
import com.firstems.erp.presenter.SelectCompanyActivityPresenter
import com.firstems.erp.presenter.impl.LoginActivityPresenterImpl
import com.firstems.erp.presenter.impl.SelectCompanyActivityPresenterImpl
import com.firstems.erp.ui.adapter.AdapterCompany
import kotlinx.android.synthetic.main.activity_select_company.*
import kotlinx.android.synthetic.main.custom_toolbar.*

class SelectCompanyActivity : BaseActivity(), SelectCompanyActivityPresenter.View {
    private lateinit var adapterCompany: AdapterCompany
    private lateinit var persenter : SelectCompanyActivityPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_company)
        addControls()
        addEvents()
    }

    private fun addEvents() {
        imgBack.setOnClickListener {
            finish()
        }
        adapterCompany.setOnClickListentner {
            if (it.lCTNLIST.size == 1){
                persenter.loginWithLocation(it.cOMPCODE,it.lCTNLIST[0].lCTNCODE)
            }
            else{
                persenter.showDialogSelectLocation(it)
            }
        }
    }

    private fun addControls() {
        persenter = SelectCompanyActivityPresenterImpl(this, this);
        persenter.init()
        var userLoginApiResponse : UserLoginApiResponse =
            intent.getSerializableExtra(Constant.NAME_PUT_USER_LOGIN) as UserLoginApiResponse;
        if (userLoginApiResponse!=null){
            persenter.invoke(userLoginApiResponse.returnData.cOMPLIST)
        }
    }

    override fun initRecycleViewCompany() {
        var linearLayoutManager : LinearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = RecyclerView.VERTICAL
        recycleCompany.layoutManager = linearLayoutManager
        recycleCompany.adapter = adapterCompany
    }

    override fun initAdapterCompany(list: List<Company>) {
        adapterCompany = AdapterCompany(list)
    }

    override fun initTitle(s: String?) {
        txtTitle.setText(s)
    }

    override fun onServerFail() {
        showDialogOutToken()
    }

    override fun onLoginSuccess() {
        persenter.navigateToMainActivity()
        finish()
    }

    override fun onLoginFail() {

    }

    override fun onStartProcessBar(message: String?) {
        showDialogLoadingMessageWith(message)
    }

    override fun onStopProcessBar() {
       hideDialogLoadingMessageWith()
    }
}