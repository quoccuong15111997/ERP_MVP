package com.firstems.erp.presenter.impl

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.view.Gravity
import android.view.WindowManager
import androidx.core.content.IntentCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firstems.erp.MainActivity
import com.firstems.erp.R
import com.firstems.erp.network.RestApi
import com.firstems.erp.network.RestClientJava
import com.firstems.erp.network.model.response.user.Company
import com.firstems.erp.network.model.response.user.Location
import com.firstems.erp.network.model.response.user.UserLoginApiResponse
import com.firstems.erp.presenter.SelectCompanyActivityPresenter
import com.firstems.erp.sharedpreferences.SharedPreferencesManager
import com.firstems.erp.ui.adapter.AdapterSelectLocation
import com.google.gson.JsonObject
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.internal.artificialFrame

class SelectCompanyActivityPresenterImpl(
    context: Context?,
    view: SelectCompanyActivityPresenter.View
) : SelectCompanyActivityPresenter {
    private var context = context
    private var view = view
    override fun init() {
        view.initTitle("Chọn công ty")
    }

    override fun invoke(list: List<Company>) {
        view.initAdapterCompany(list)
        view.initRecycleViewCompany()
    }

    override fun showDialogSelectLocation(company: Company) {
        var dialog : Dialog = Dialog(context!!)
        dialog.setContentView(R.layout.dialog_top_home)
        dialog.window!!.attributes.windowAnimations = R.style.PauseDialogAnimation
        var lp = WindowManager.LayoutParams()
        lp.copyFrom(dialog.window!!.attributes)
        lp.width = WindowManager.LayoutParams.MATCH_PARENT
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT
        lp.gravity = Gravity.CENTER

        var recyclerView = dialog.findViewById<RecyclerView>(R.id.recycle)
        var adapterLocation = AdapterSelectLocation(company.lCTNLIST)
        var  linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.orientation =  RecyclerView.VERTICAL
        recyclerView.adapter = adapterLocation
        recyclerView.layoutManager = linearLayoutManager

        adapterLocation.setOnClickListener {
            loginWithLocation(company = company.cOMPCODE,location = it.lCTNCODE)
            if (dialog!=null){
                dialog.dismiss()
            }
        }
        dialog.setCancelable(true)
        dialog.window!!.attributes = lp
        dialog.show()
    }

    override fun loginWithLocation(company: String, location: String) {
        var jsonObject = JsonObject()
        jsonObject.addProperty("COMPCODE", company)
        jsonObject.addProperty("LCTNCODE", location)
        view.onStartProcessBar("Đang đăng nhập")
        RestClientJava.createService(RestApi::class.java)
            .doLoginLocation(SharedPreferencesManager.preftoken, jsonObject)
            .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                Consumer {
                    Handler().postDelayed(Runnable {
                        view.onStopProcessBar()
                        if (it.returnCode){
                            saveUserData(it,completeCallback = {
                                view.onLoginSuccess()
                            })
                        }
                        else{
                            view.onLoginFail()
                        }
                    }, 750)
                }, {
                    view.onStopProcessBar()
                    view.onServerFail()
                })
    }

    override fun navigateToMainActivity() {
        var intent = Intent(context,MainActivity::class.java)
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