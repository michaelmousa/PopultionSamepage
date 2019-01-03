package com.treehouse.popultion

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PopServices {
    @GET ("population/{country}/{age}/")
    fun getPopulitionDetails(@Path ("country") country: String,
                             @Path ("age") age: Int
    ): Call<PopResponse>

}