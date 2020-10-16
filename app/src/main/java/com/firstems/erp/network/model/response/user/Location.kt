package com.firstems.erp.network.model.response.user


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Location(
    @SerializedName("LCTNCODE")
    var lCTNCODE: String,
    @SerializedName("LCTNNAME")
    var lCTNNAME: String
) : Serializable