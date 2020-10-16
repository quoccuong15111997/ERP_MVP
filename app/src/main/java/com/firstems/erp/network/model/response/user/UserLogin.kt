package com.firstems.erp.network.model.response.user


import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.lang.StringBuilder

data class UserLogin(
    @SerializedName("APP_RGHT")
    var aPPRGHT: Int,
    @SerializedName("DEF_DSBR")
    var dEFDSBR: String,
    @SerializedName("DPTMCODE")
    var dPTMCODE: String,
    @SerializedName("DPTMNAME")
    var dPTMNAME: String,
    @SerializedName("EMPLCODE")
    var eMPLCODE: String,
    @SerializedName("JOB_CODE")
    var jOBCODE: String,
    @SerializedName("JOB_NAME")
    var jOBNAME: String,
    @SerializedName("LCTNBRIF")
    var lCTNBRIF: String,
    @SerializedName("LCTNCODE")
    var lCTNCODE: String,
    @SerializedName("LCTNNAME")
    var lCTNNAME: String,
    @SerializedName("LOGOCOMP")
    var lOGOCOMP: String,
    @SerializedName("PSTNCODE")
    var pSTNCODE: String,
    @SerializedName("PSTNNAME")
    var pSTNNAME: String,
    @SerializedName("USERCODE")
    var uSERCODE: String,
    @SerializedName("USERNAME")
    var uSERNAME: String
) : Serializable