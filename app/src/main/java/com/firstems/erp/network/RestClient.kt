package com.firstems.erp.network

import com.firstems.erp.sharedpreferences.SharedPreferencesManager
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RestClient private constructor(){
    companion object{
        private val BASE_URL: String = SharedPreferencesManager.baseDomain!!
    }

    private val builder = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create()).client(getHttpClient().build())


    fun getHttpClient(): OkHttpClient.Builder {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)
        return httpClient
    }

    private val retrofit = builder.build()

    private val httpClient = OkHttpClient.Builder()

    public fun <S> createService(
        serviceClass: Class<S>?
    ): S {
        return retrofit.create(serviceClass)
    }

}
