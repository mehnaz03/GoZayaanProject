package com.mehnaz.gozayaanproject.data.remote

import com.mehnaz.gozayaanproject.data.models.Property
import retrofit2.http.GET

interface ApiService {
    @GET("properties")
    suspend fun getProperties(): List<Property>


}