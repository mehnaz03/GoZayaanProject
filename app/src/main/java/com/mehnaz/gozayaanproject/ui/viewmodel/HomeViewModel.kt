package com.mehnaz.gozayaanproject.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mehnaz.gozayaanproject.data.models.Property
import com.mehnaz.gozayaanproject.data.remote.ApiService
import com.mehnaz.gozayaanproject.data.remote.RetrofitInstance
import com.mehnaz.gozayaanproject.data.repository.Repository
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: Repository) : ViewModel() {

    private val _properties = MutableLiveData<List<Property>>()
    val properties: LiveData<List<Property>> get() = _properties

    fun fetchProperties() {
        viewModelScope.launch {
            try {
                // Fetch properties from the repository
                val result = repository.fetchProperties()
                _properties.postValue(result)
            } catch (e: Exception) {
                // Handle errors
            }
        }
    }
}

