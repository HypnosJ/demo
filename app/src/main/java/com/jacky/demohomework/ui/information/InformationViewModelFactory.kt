package com.jacky.demohomework.ui.information

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jacky.demohomework.data.repository.MainRepository

class InformationViewModelFactory constructor(private val repository: MainRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(InformationViewModel::class.java)) {
            InformationViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}