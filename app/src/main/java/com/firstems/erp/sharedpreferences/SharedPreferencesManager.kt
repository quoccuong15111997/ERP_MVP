package com.firstems.erp.sharedpreferences

import android.content.Context
import android.content.SharedPreferences
import com.firstems.erp.BuildConfig

object SharedPreferencesManager {
    private const val NAME = BuildConfig.APPLICATION_ID + "1ems_prd"
    private const val MODE = Context.MODE_PRIVATE
    private lateinit var preferences: SharedPreferences

    //Config
    private val PREF_IS_FIRST_RUN = "is_first_run"
    private val PREF_FCM_TOKEN = "fcm_token"
    private val PREF_BASE_DOMAIN = "base_domain"
    private val PREF_LANGUAGE_CODE = "language_code"

    // Login
    private val PREF_PASSWORD_REMEMBER = "password_remember"
    private val PREF_USERNAME_LOGIN = "user_name_login"
    private val PREF_PASSWORD_LOGIN = "user_password_login"

    // User info
    private val PREF_TOKEN = "token"
    private val PREF_LCT_CODE = "location_code"
    private val PREF_LCT_NAME = "location_name"
    private val PREF_LCT_BRIF = "location_brif"
    private val PREF_USER_CODE = "user_code"
    private val PREF_USER_NAME = "user_name"
    private val PREF_APP_RIGHT = "app_right"
    private val PREF_EMPL_CODE = "emp_code"
    private val PREF_DPT_CODE = "dpt_code"
    private val PREF_DPT_NAME = "dpt_name"
    private val PREF_DPSTN_CODE = "dpstn_code"
    private val PREF_DPSTN_NAME = "dpstn_name"
    private val PREF_JOB_CODE = "job_code"
    private val PREF_JOB_NAME = "job_name"
    private val PREF_LOGO_COMP = "logo_comp"
    private val PREF_COM_CODE = "com_code"
    private val PREF_COM_NAME = "com_name"


    fun init(context: Context) {
        preferences = context.getSharedPreferences(NAME, MODE)
    }

    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }

    var firstRun: Boolean
        // custom getter to get a preference of a desired type, with a predefined default value
        get() = preferences.getBoolean(PREF_IS_FIRST_RUN, true)
        // custom setter to save a preference back to preferences file
        set(value) = preferences.edit {
            it.putBoolean(PREF_IS_FIRST_RUN, value)
        }

    var fcmToken: String?
        get() = preferences.getString(PREF_FCM_TOKEN, "")
        set(value) = preferences.edit {
            it.putString(PREF_FCM_TOKEN, value)
        }
    var baseDomain: String?
        get() = preferences.getString(PREF_BASE_DOMAIN, "http://api-dev.firstems.com")
        set(value) = preferences.edit {
            it.putString(PREF_BASE_DOMAIN, value)
        }
    var langCode: String?
        get() = preferences.getString(PREF_LANGUAGE_CODE, "V")
        set(value) = preferences.edit {
            it.putString(PREF_LANGUAGE_CODE, value)
        }
    var passwordRemember: Boolean
        get() = preferences.getBoolean(PREF_PASSWORD_REMEMBER, false)
        set(value) = preferences.edit() {
            it.putBoolean(PREF_PASSWORD_REMEMBER, value)
        }
    var usernameLogin: String?
        get() = preferences.getString(PREF_USERNAME_LOGIN, "")
        set(value) = preferences.edit {
            it.putString(PREF_USERNAME_LOGIN, value)
        }
    var passwordLogin: String?
        get() = preferences.getString(PREF_PASSWORD_LOGIN, "")
        set(value) = preferences.edit {
            it.putString(PREF_PASSWORD_LOGIN, value)
        }
    var preftoken: String?
        get() = preferences.getString(PREF_TOKEN, "")
        set(value) = preferences.edit {
            it.putString(PREF_TOKEN, value)
        }
    var preflctcode: String?
        get() = preferences.getString(PREF_LCT_CODE, "")
        set(value) = preferences.edit {
            it.putString(PREF_LCT_CODE, value)
        }
    var preflctname: String?
        get() = preferences.getString(PREF_LCT_NAME, "")
        set(value) = preferences.edit {
            it.putString(PREF_LCT_NAME, value)
        }
    var preflctbrif: String?
        get() = preferences.getString(PREF_LCT_BRIF, "")
        set(value) = preferences.edit {
            it.putString(PREF_LCT_BRIF, value)
        }
    var prefusercode: String?
        get() = preferences.getString(PREF_USER_CODE, "")
        set(value) = preferences.edit {
            it.putString(PREF_USER_CODE, value)
        }
    var prefusername: String?
        get() = preferences.getString(PREF_USER_NAME, "")
        set(value) = preferences.edit {
            it.putString(PREF_USER_NAME, value)
        }
    var prefappright: Int?
        get() = preferences.getInt(PREF_APP_RIGHT, 0)
        set(value) = preferences.edit {
            it.putInt(PREF_APP_RIGHT, value!!)
        }
    var prefemplcode: String?
        get() = preferences.getString(PREF_EMPL_CODE, "")
        set(value) = preferences.edit {
            it.putString(PREF_EMPL_CODE, value)
        }
    var prefdptcode: String?
        get() = preferences.getString(PREF_DPT_CODE, "")
        set(value) = preferences.edit {
            it.putString(PREF_DPT_CODE, value)
        }
    var prefdptname: String?
        get() = preferences.getString(PREF_DPT_NAME, "")
        set(value) = preferences.edit {
            it.putString(PREF_DPT_NAME, value)
        }
    var prefdpstncode: String?
        get() = preferences.getString(PREF_DPSTN_CODE, "")
        set(value) = preferences.edit {
            it.putString(PREF_DPSTN_CODE, value)
        }
    var prefdpstnname: String?
        get() = preferences.getString(PREF_DPSTN_NAME, "")
        set(value) = preferences.edit {
            it.putString(PREF_DPSTN_NAME, value)
        }
    var prefjobcode: String?
        get() = preferences.getString(PREF_JOB_CODE, "")
        set(value) = preferences.edit {
            it.putString(PREF_JOB_CODE, value)
        }
    var prefjobname: String?
        get() = preferences.getString(PREF_JOB_NAME, "")
        set(value) = preferences.edit {
            it.putString(PREF_JOB_NAME, value)
        }
    var preflogocomp: String?
        get() = preferences.getString(PREF_LOGO_COMP, "")
        set(value) = preferences.edit {
            it.putString(PREF_LOGO_COMP, value)
        }
    var prefcomcode: String?
        get() = preferences.getString(PREF_COM_CODE, "")
        set(value) = preferences.edit {
            it.putString(PREF_COM_CODE, value)
        }
    var prefcomname: String?
        get() = preferences.getString(PREF_COM_NAME, "")
        set(value) = preferences.edit {
            it.putString(PREF_COM_NAME, value)
        }
}
