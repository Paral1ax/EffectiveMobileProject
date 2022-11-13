package com.parallax.effectivemobileproject.main.mainpage.hotsales

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.parallax.effectivemobileproject.main.mainpage.MainViewModel
import com.parallax.effectivemobileproject.main.repository.Repository

class MainViewModelFactory(private val repository: Repository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }
}