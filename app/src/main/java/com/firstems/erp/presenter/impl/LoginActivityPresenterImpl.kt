package com.firstems.erp.presenter.impl

import android.content.Context
import android.content.Intent
import android.os.Handler
import com.firstems.erp.MainActivity
import com.firstems.erp.common.Constant
import com.firstems.erp.network.RestApi
import com.firstems.erp.network.RestClientJava
import com.firstems.erp.network.model.response.user.UserLoginApiResponse
import com.firstems.erp.presenter.LoginActivityPresenter
import com.firstems.erp.sharedpreferences.SharedPreferencesManager
import com.firstems.erp.ui.activity.SelectCompanyActivity
import com.google.gson.JsonObject
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_login.*

open class LoginActivityPresenterImpl(context: Context?, view: LoginActivityPresenter.View?) :
    LoginActivityPresenter {
    private var context: Context? = context
    private var view: LoginActivityPresenter.View? = view


    override fun init() {
        view!!.initUserName(SharedPreferencesManager.usernameLogin)
        view!!.initPassword(SharedPreferencesManager.passwordLogin)
        view!!.initRemember(SharedPreferencesManager.passwordRemember)
    }

    override fun doLogin(userName: String?, password: String?, isRemember: Boolean) {
        if (!userName.equals("")) {
            if (!password.equals("")) {
                view!!.onStartProcessBar("Đang đăng nhập")
                var jsonObject = JsonObject()
                jsonObject.addProperty("USERLGIN", userName)
                jsonObject.addProperty("PASSWORD", password)
                jsonObject.addProperty("LGINTYPE", 1)
                jsonObject.addProperty("PHONNUMB", 0)
                jsonObject.addProperty("TKENDEVC", "")
                RestClientJava
                    .createService(RestApi::class.java)
                    .doLoginSystem(jsonObject)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(Consumer {
                        android.os.Handler().postDelayed(Runnable {
                            view!!.onStopProcessBar()
                            SharedPreferencesManager.preftoken = it.returnData.tOKEN
                            if (it.returnCode) {
                                doSaveUserInfo(isRemember,userName,password)
                                if (it.returnData.cOMPLIST.size == 1) {
                                    // Một công ty
                                    if (it.returnData.cOMPLIST[0].lCTNLIST.size == 1) {
                                        // Một chi nhánh
                                        saveUserData(it, completeCallback = {
                                            Handler().postDelayed(Runnable {
                                                view!!.onLoginSuccess()
                                            }, 750)
                                        })
                                    } else {
                                        // Nhiều chi nhánh
                                        view!!.onMultipleCompany(it)
                                    }
                                } else {
                                    // Nhiều công ty
                                    view!!.onMultipleCompany(it)
                                }

                            } else {
                                view!!.onLoginFail(it.returnMessage)
                            }
                        }, 1000)
                    }, {
                        view!!.onServerFail()
                    })
            } else {
                view!!.onValidDataFail("Vui lòng nhập mật khẩu")
            }
        } else {
            view!!.onValidDataFail("Vui lòng nhập tên đăng nhập")
        }
    }

    override fun doLoginLocation(userLoginApiResponse: UserLoginApiResponse) {
        var jsonObject = JsonObject()
        jsonObject.addProperty("COMPCODE", userLoginApiResponse.returnData.cOMPLIST[0].cOMPCODE)
        jsonObject.addProperty(
            "LCTNCODE",
            userLoginApiResponse.returnData.cOMPLIST[0].lCTNLIST[0].lCTNCODE
        )
        RestClientJava.createService(RestApi::class.java)
            .doLoginLocation(userLoginApiResponse.returnData.tOKEN, jsonObject)
            .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                Consumer {
                    if (it.returnCode){
                        saveUserData(it, completeCallback = {
                            Handler().postDelayed(Runnable {
                                navigateToMainActivity()
                            }, 750)
                        })
                    }
                    else{
                        view!!.onLoginFail(it.returnMessage)
                    }
                }, {
                    view!!.onServerFail()
                })
    }

    override fun doSaveUserInfo(isRemember: Boolean, username: String?, password: String?) {
        if (isRemember) {
            SharedPreferencesManager.usernameLogin = username
            SharedPreferencesManager.passwordLogin = password
            SharedPreferencesManager.passwordRemember = true
        }
    }

    override fun navigateToMainActivity() {
        var intent = Intent(context, MainActivity::class.java)
        context!!.startActivity(intent)
    }

    override fun navigateToSelectCompanyActivity(userLoginApiResponse: UserLoginApiResponse) {
        var intent = Intent(context, SelectCompanyActivity::class.java)
        intent.putExtra(Constant().NAME_PUT_USER_LOGIN, userLoginApiResponse)
        context!!.startActivity(intent)
    }

    override fun saveUserData(
        user: UserLoginApiResponse,
        completeCallback: () -> Unit
    ) {
        if (user.returnData.uSERLGIN != null) {
            SharedPreferencesManager.prefusercode = user.returnData.uSERLGIN.uSERCODE
            SharedPreferencesManager.prefusername = user.returnData.uSERLGIN.uSERNAME
            SharedPreferencesManager.prefemplcode = user.returnData.uSERLGIN.eMPLCODE
            SharedPreferencesManager.prefdptcode = user.returnData.uSERLGIN.dPTMCODE
            SharedPreferencesManager.prefdptname = user.returnData.uSERLGIN.dPTMNAME
            SharedPreferencesManager.prefdpstncode = user.returnData.uSERLGIN.pSTNCODE
            SharedPreferencesManager.prefdpstnname = user.returnData.uSERLGIN.pSTNNAME
            SharedPreferencesManager.prefjobcode = user.returnData.uSERLGIN.jOBCODE
            SharedPreferencesManager.prefjobname = user.returnData.uSERLGIN.jOBNAME
            SharedPreferencesManager.preflogocomp = user.returnData.uSERLGIN.lOGOCOMP
            SharedPreferencesManager.prefcomcode = user.returnData.cOMPLIST[0].cOMPCODE
            SharedPreferencesManager.preflctcode = user.returnData.cOMPLIST[0].lCTNLIST[0].lCTNCODE

            completeCallback.invoke()
        }
    }

}