package com.example.applicationelderpathtime.viewmodelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.applicationelderpathtime.repository.MainRepository
import com.example.applicationelderpathtime.viewmodel.MainViewModel
import com.example.applicationelderpathtime.viewmodel.UserViewModel

class MainViewModelFactory ():ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MainViewModel::class.java)){
            return MainViewModel() as T
        }else if (modelClass.isAssignableFrom(UserViewModel::class.java)){
            return UserViewModel() as T
        }else{
            throw IllegalArgumentException("ViewModel Not Found")
        }

    }
}