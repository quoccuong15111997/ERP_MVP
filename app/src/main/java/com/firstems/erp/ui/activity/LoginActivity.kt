package com.firstems.erp.ui.activity

import android.os.Bundle
import com.firstems.erp.R
import com.firstems.erp.common.BaseActivity
import com.firstems.erp.network.model.response.user.UserLoginApiResponse
import com.firstems.erp.presenter.LoginActivityPresenter
import com.firstems.erp.presenter.impl.LoginActivityPresenterImpl
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity(), LoginActivityPresenter.View {
    lateinit var persenter: LoginActivityPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        addControls();
        addEvents();
    }

    private fun addEvents() {
        btnLogin.setOnClickListener {
            requestLogin(edtUserName.text.toString(), edtPassword.text.toString(), chkRemember.isChecked)
        }
    }

    private fun addControls() {
        persenter = LoginActivityPresenterImpl(this, this)
        persenter.init()
    }

    override fun initUserName(userName: String?) {
        edtUserName.setText(userName)
    }

    override fun initPassword(password: String?) {
        edtPassword.setText(password)
    }

    override fun initRemember(isRemember: Boolean) {
        chkRemember.isChecked = isRemember
    }

    override fun onValidDataFail(mess: String?) {
        showDialogError("Thông báo", mess, callback = {

        })
    }

    override fun onLoginPending() {

    }

    override fun onLoginSuccess() {
        persenter.navigateToMainActivity()
        finish()
    }

    override fun onLoginFail(messs: String?) {
        showDialogError("Không thành công", messs,callback = {

        })
    }

    override fun onStartProcessBar(message: String?) {
        showDialogLoadingMessageWith(message)
    }

    override fun onStopProcessBar() {
        hideDialogLoadingMessageWith()
    }

    override fun requestLogin(username: String?, password: String?, isRemember: Boolean) {
        persenter.doLogin(username, password, isRemember)
    }

    override fun onServerFail() {
        showDialogOutToken()
    }

    override fun onMultipleCompany(userLoginApiResponse: UserLoginApiResponse) {
        persenter.navigateToSelectCompanyActivity(userLoginApiResponse)
        finish()
    }
}