package com.firstems.erp.network

import com.firstems.erp.network.model.response.user.UserLoginApiResponse
import com.google.gson.JsonObject
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface RestApi {
    // Đăng nhập hệ thống
    @POST("/Api/data/runApi_syst?run_Code=SYS001.02.001")
    fun doLoginSystem(@Body body: JsonObject?): Observable<UserLoginApiResponse>

    // Đăng nhập theo công ty - chi nhánh
    @POST("/Api/data/runApi_data?run_Code=SYS001.02.002")
    fun doLoginLocation(@Header("TOKEN") token: String?, @Body body: JsonObject?): Observable<UserLoginApiResponse>
}