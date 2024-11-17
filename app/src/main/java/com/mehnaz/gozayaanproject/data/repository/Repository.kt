package com.mehnaz.gozayaanproject.data.repository

import com.mehnaz.gozayaanproject.data.models.Property
import com.mehnaz.gozayaanproject.data.remote.ApiService


class Repository(private val apiService: ApiService) {
    suspend fun fetchProperties(): List<Property> {
        return apiService.getProperties()
    }
}