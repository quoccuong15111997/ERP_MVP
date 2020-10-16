package com.firstems.erp.presenter

import com.firstems.erp.network.model.response.user.UserLoginApiResponse

interface LoadingActivityPresenter {
    fun init()
    fun autoLogin()
    fun navigateToSettingActivity()
    fun navigateToMainActivity()
    fun navigateToLoginActivity()
    fun saveUserData(userLoginApiResponse: UserLoginApiResponse, completeCallback : () -> Unit)
    fun doLoginLocation(userLoginApiResponse: UserLoginApiResponse)
    interface View {
        fun onStopDialogLoading()
        fun onAutoLoginSucess()
        fun onAutoLoginFail(mess : String)
        fun onServerFail()
        fun onCompanyIsEmty()
    }
}