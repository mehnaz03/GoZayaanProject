package com.mehnaz.gozayaanproject.data.models

import androidx.annotation.Keep
import java.io.Serializable

@Keep
data class Property(
    val property_name: String,
    val location: String,
    val rating: Double,
    val description: String,
    val fare: Double,
    val fare_unit: String,
    val is_available: Boolean,
    val hero_image: String,
    val detail_images: List<String>,
    val currency: String
) : Serializable