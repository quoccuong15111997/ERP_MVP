package com.firstems.erp.ui.activity

import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.firstems.erp.R
import com.firstems.erp.common.BaseActivity
import com.firstems.erp.presenter.LoadingActivityPresenter
import com.firstems.erp.presenter.impl.LoadingActivityPresenterImpl
import kotlinx.android.synthetic.main.activity_loading.*

class LoadingActivity : BaseActivity(), LoadingActivityPresenter.View {
    private lateinit var presenter: LoadingActivityPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading)
        addControls()
    }

    private fun addControls() {
        presenter = LoadingActivityPresenterImpl(this, this);
        presenter.init()
    }

    override fun onStopDialogLoading() {
        progressBar.visibility = View.GONE
    }

    override fun onAutoLoginSucess() {
        presenter.navigateToMainActivity()
    }

    override fun onAutoLoginFail(mess: String) {
        showDialogError("Không thành công",mess, callback = {
            presenter.navigateToLoginActivity()
            finish()
        })
    }

    override fun onServerFail() {
        showDialogOutToken()
    }

    override fun onCompanyIsEmty() {
        presenter.navigateToLoginActivity()
        finish()
    }
}