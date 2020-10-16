package com.firstems.erp.common

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.PersistableBundle
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.firstems.erp.R

open abstract class BaseActivity : AppCompatActivity() {
    protected lateinit var dialogLoadingMessageNon: Dialog
    protected lateinit var dialogLoadingMessageWith: Dialog
    protected lateinit var alertDialogConfirm : AlertDialog
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        initDialog();
    }

    private fun initDialog() {
        initDialogLoadingMessageNon()
    }

    private fun initDialogLoadingMessageNon() {
        dialogLoadingMessageNon = Dialog(this)
        dialogLoadingMessageNon.setContentView(R.layout.item_loading_dialog_non__message)
        dialogLoadingMessageNon.setCancelable(false)
        dialogLoadingMessageNon.window!!.attributes.windowAnimations = R.style.PauseDialogAnimation
        var lp = WindowManager.LayoutParams()
        lp.copyFrom(dialogLoadingMessageNon.window!!.attributes)
        lp!!.width = WindowManager.LayoutParams.WRAP_CONTENT
        lp!!.height = WindowManager.LayoutParams.WRAP_CONTENT
        dialogLoadingMessageNon.window!!.setBackgroundDrawable(ColorDrawable(android.graphics.Color.TRANSPARENT))
        dialogLoadingMessageNon.window!!.attributes = lp
    }

    protected fun showDialogLoadingMessageWith(mess: String?) {
        dialogLoadingMessageWith = Dialog(this)
        dialogLoadingMessageWith.setContentView(R.layout.item_loading_dialog)
        dialogLoadingMessageWith.setCancelable(false)
        dialogLoadingMessageWith.window!!.attributes.windowAnimations = R.style.PauseDialogAnimation
        var lp = WindowManager.LayoutParams()
        lp.copyFrom(dialogLoadingMessageWith.window!!.attributes)
        lp!!.width = WindowManager.LayoutParams.WRAP_CONTENT
        lp!!.height = WindowManager.LayoutParams.WRAP_CONTENT
        dialogLoadingMessageWith.window!!.setBackgroundDrawable(ColorDrawable(android.graphics.Color.TRANSPARENT))
        dialogLoadingMessageWith.window!!.attributes = lp
        var txtMess: TextView = dialogLoadingMessageWith.findViewById(R.id.tvLoading)
        txtMess.setText(mess)

        dialogLoadingMessageWith.window!!.attributes = lp
        dialogLoadingMessageWith.show()
    }

    protected fun hideDialogLoadingMessageWith() {
        if (dialogLoadingMessageWith != null) {
            dialogLoadingMessageWith.dismiss()
        }
    }

    protected fun showDialogError(title: String?, mess: String?, callback : () -> Unit) {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_warning)
        dialog.setCancelable(true)
        var lp = WindowManager.LayoutParams()
        lp.copyFrom(dialog.window!!.attributes)
        lp!!.width = WindowManager.LayoutParams.WRAP_CONTENT
        lp!!.height = WindowManager.LayoutParams.WRAP_CONTENT

        var txtTitle: TextView = dialog.findViewById(R.id.title)
        var txtMess: TextView = dialog.findViewById(R.id.content)
        var btnClose: AppCompatButton = dialog.findViewById(R.id.bt_close)

        txtTitle.setText(title)
        txtMess.setText(mess)

        btnClose.setOnClickListener(View.OnClickListener {
            if (dialog != null) {
                dialog.dismiss()
                callback.invoke()
            }
        })
        dialog.window!!.attributes = lp
        dialog.show()
    }

    protected fun showDialogSuccess(title: String?, mess: String?, closeCallback: () -> Unit) {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_warning)
        dialog.setCancelable(true)
        var lp = WindowManager.LayoutParams()
        lp.copyFrom(dialog.window!!.attributes)
        lp!!.width = WindowManager.LayoutParams.WRAP_CONTENT
        lp!!.height = WindowManager.LayoutParams.WRAP_CONTENT

        var txtTitle: TextView = dialog.findViewById(R.id.title)
        var txtMess: TextView = dialog.findViewById(R.id.content)
        var btnClose: AppCompatButton = dialog.findViewById(R.id.bt_close)

        txtTitle.setText(title)
        txtMess.setText(mess)

        btnClose.setOnClickListener(View.OnClickListener {
            if (dialog != null) {
                dialog.dismiss()
            }
            closeCallback.invoke()
        })
        dialog.window!!.attributes = lp
        dialog.show()
    }

    protected fun showDialogConfirm(
        title: String?,
        mess: String?,
        labelYes: String?,
        labelNo: String?,
        yesCallback: () -> Unit,
        noCallback: () -> Unit
    ) {
        var alertDialogBuilder: AlertDialog.Builder = AlertDialog.Builder(this)
        var layoutInflater = LayoutInflater.from(this)
        var dialogView = layoutInflater.inflate(R.layout.dialog_confirm, null)
        alertDialogBuilder.setView(dialogView)

        var txtTitle : TextView = dialogView.findViewById(R.id.lb_custom_view_alert_dialog_title)
        var txtMessage : TextView = dialogView.findViewById(R.id.lb_custom_view_alert_dialog_message)
        var btnYes : Button = dialogView.findViewById(R.id.btn_custom_view_alert_dialog_ok)
        var btnNo : Button = dialogView.findViewById(R.id.btn_custom_view_alert_dialog_close)

        txtTitle.setText(title)
        txtMessage.setText(mess)
        btnYes.setText(labelYes)
        btnNo.setText(labelNo)

        btnYes.setOnClickListener {
            if (alertDialogConfirm!=null){
                alertDialogConfirm.dismiss()
            }
            yesCallback.invoke()
        }
        btnNo.setOnClickListener {
            if (alertDialogConfirm!=null){
                alertDialogConfirm.dismiss()
            }
            noCallback.invoke()
        }
        alertDialogConfirm = alertDialogBuilder.create()
        alertDialogConfirm.show()
    }
    protected fun showDialogOutToken() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_warning)
        dialog.setCancelable(true)
        var lp = WindowManager.LayoutParams()
        lp.copyFrom(dialog.window!!.attributes)
        lp!!.width = WindowManager.LayoutParams.WRAP_CONTENT
        lp!!.height = WindowManager.LayoutParams.WRAP_CONTENT

        var txtTitle: TextView = dialog.findViewById(R.id.title)
        var txtMess: TextView = dialog.findViewById(R.id.content)
        var btnClose: AppCompatButton = dialog.findViewById(R.id.bt_close)

        txtTitle.setText("Thông báo")
        txtMess.setText("Phiên đăng nhập hết hạn, vui lòng đăng nhập lại")

        btnClose.setOnClickListener(View.OnClickListener {
            if (dialog != null) {
                dialog.dismiss()
            }
        })
        dialog.window!!.attributes = lp
        dialog.show()
    }
}