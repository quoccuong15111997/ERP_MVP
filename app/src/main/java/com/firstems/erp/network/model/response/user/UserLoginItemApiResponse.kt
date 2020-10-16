package com.firstems.erp.network.model.response.user


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class UserLoginItemApiResponse(
    @SerializedName("APP_RGHT")
    var aPPRGHT: Int,
    @SerializedName("COMPLIST")
    var cOMPLIST: List<Company>,
    @SerializedName("EXPIREDTIME")
    var eXPIREDTIME: String,
    @SerializedName("LOADLGIN")
    var lOADLGIN: Int,
    @SerializedName("TOKEN")
    var tOKEN: String,
    @SerializedName("USERLGIN")
    var uSERLGIN: UserLogin
) : Serializable