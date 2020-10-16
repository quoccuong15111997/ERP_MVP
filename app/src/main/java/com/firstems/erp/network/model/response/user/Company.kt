package com.firstems.erp.network.model.response.user


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Company(
    @SerializedName("COMPCODE")
    var cOMPCODE: String,
    @SerializedName("COMPNAME")
    var cOMPNAME: String,
    @SerializedName("LCTNLIST")
    var lCTNLIST: List<Location>
) : Serializable