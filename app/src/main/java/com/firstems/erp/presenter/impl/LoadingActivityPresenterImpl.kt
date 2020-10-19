package com.firstems.erp.presenter.impl

import android.content.Context
import android.content.Intent
import android.os.Handler
import com.firstems.erp.ui.activity.MainActivity
import com.firstems.erp.network.RestApi
import com.firstems.erp.network.RestClientJava
import com.firstems.erp.network.model.response.user.UserLoginApiResponse
import com.firstems.erp.presenter.LoadingActivityPresenter
import com.firstems.erp.sharedpreferences.SharedPreferencesManager
import com.firstems.erp.ui.activity.LoginActivity
import com.google.gson.JsonObject
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

open class LoadingActivityPresenterImpl(context: Context?, view: LoadingActivityPresenter.View?) :
    LoadingActivityPresenter {
    private var context: Context? = context
    private var view: LoadingActivityPresenter.View? = view
    override fun init() {
        if (SharedPreferencesManager.firstRun) {
            if (SharedPreferencesManager.passwordRemember) {
                autoLogin()
            } else {
                navigateToLoginActivity()
            }
        } else {
            navigateToSettingActivity()
        }
    }

    override fun autoLogin() {
        var jsonObject = JsonObject()
        jsonObject.addProperty("USERLGIN", SharedPreferencesManager.usernameLogin)
        jsonObject.addProperty("PASSWORD", SharedPreferencesManager.passwordLogin)
        jsonObject.addProperty("LGINTYPE", 1)
        jsonObject.addProperty("PHONNUMB", 0)
        jsonObject.addProperty("TKENDEVC", SharedPreferencesManager.fcmToken)
        RestClientJava.createService(RestApi::class.java).doLoginSystem(jsonObject)
            .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe(Consumer {
                if (it.returnCode) {
                    if (!SharedPreferencesManager.prefcomcode.equals("") && !SharedPreferencesManager.preflctcode.equals("")){
                        doLoginLocation(it)
                    }
                    else{
                        Handler().postDelayed(Runnable {
                            view!!.onStopDialogLoading()
                            view!!.onCompanyIsEmty()
                        }, 700)
                    }
                } else {
                    view!!.onStopDialogLoading()
                    view!!.onAutoLoginFail(it.returnMessage)
                }
            }, {
                view!!.onStopDialogLoading()
                view!!.onServerFail()
            })
    }

    override fun navigateToSettingActivity() {

    }

    override fun navigateToMainActivity() {
        var intent = Intent(context, MainActivity::class.java)
        context!!.startActivity(intent)
    }

    override fun navigateToLoginActivity() {
        var intent = Intent(context, LoginActivity::class.java)
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

    override fun doLoginLocation(userLoginApiResponse: UserLoginApiResponse) {
        var jsonObject = JsonObject()
        jsonObject.addProperty("COMPCODE", SharedPreferencesManager.prefcomcode)
        jsonObject.addProperty(
            "LCTNCODE",
            SharedPreferencesManager.preflctcode
        )
        RestClientJava.createService(RestApi::class.java)
            .doLoginLocation(userLoginApiResponse.returnData.tOKEN, jsonObject)
            .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                Consumer {
                    Handler().postDelayed(Runnable {
                        view!!.onStopDialogLoading()
                        if (it.returnCode) {
                            saveUserData(it, completeCallback = {
                                view!!.onAutoLoginSucess()
                            })
                        } else {
                            view!!.onAutoLoginFail(it.returnMessage)
                        }
                    }, 700)
                }, {
                    view!!.onStopDialogLoading()
                    view!!.onServerFail()
                })
    }
}