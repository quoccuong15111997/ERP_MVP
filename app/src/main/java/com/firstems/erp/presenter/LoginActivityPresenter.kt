package com.firstems.erp.presenter

import com.firstems.erp.network.model.response.user.UserLoginApiResponse

interface LoginActivityPresenter {
    fun init()
    fun doLogin(userName: String?, password: String?, isRemember: Boolean)
    fun doLoginLocation(userLoginApiResponse: UserLoginApiResponse)
    fun doSaveUserInfo(isRemember: Boolean, username: String?, password: String?)
    fun navigateToMainActivity()
    fun navigateToSelectCompanyActivity(userLoginApiResponse: UserLoginApiResponse)
    fun saveUserData(userLoginApiResponse: UserLoginApiResponse, completeCallback : () -> Unit)
    interface View {
        fun initUserName(userName: String?)
        fun initPassword(password: String?)
        fun initRemember(isRemember: Boolean)
        fun onValidDataFail(mess: String?)
        fun onLoginPending()
        fun onLoginSuccess()
        fun onLoginFail(messs: String?)
        fun onStartProcessBar(message: String?)
        fun onStopProcessBar()
        fun requestLogin(username: String?, password: String?, isRemember: Boolean)
        fun onServerFail()
        fun onMultipleCompany(userLoginApiResponse: UserLoginApiResponse)
    }
}