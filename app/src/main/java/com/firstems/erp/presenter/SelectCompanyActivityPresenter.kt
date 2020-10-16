package com.firstems.erp.presenter

import com.firstems.erp.network.model.response.user.Company
import com.firstems.erp.network.model.response.user.Location
import com.firstems.erp.network.model.response.user.UserLoginApiResponse

interface SelectCompanyActivityPresenter {
    fun init()
    fun invoke(list: List<Company>)
    fun showDialogSelectLocation(company: Company)
    fun loginWithLocation(companyCode: String, locationCode: String)
    fun navigateToMainActivity()
    fun saveUserData(userLoginApiResponse: UserLoginApiResponse, completeCallback : () -> Unit)
    interface View{
        fun initRecycleViewCompany()
        fun initAdapterCompany(list: List<Company>)
        fun initTitle(s : String?)
        fun onServerFail()
        fun onLoginSuccess()
        fun onLoginFail()
        fun onStartProcessBar(message: String?)
        fun onStopProcessBar()
    }
}