package com.firstems.erp.network.model.response.user


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class UserLoginApiResponse(
    @SerializedName("RETNCODE")
    var returnCode: Boolean,
    @SerializedName("RETNDATA")
    var returnData: UserLoginItemApiResponse,
    @SerializedName("RETNDATE")
    var returnDate: String,
    @SerializedName("RETNMSSG")
    var returnMessage: String
) : Serializable