package com.example.applicationelderpathtime.viewmodel

import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.applicationelderpathtime.model.*
import com.example.applicationelderpathtime.network.RetrofitService
import com.example.applicationelderpathtime.repository.MainRepository
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class MainViewModel constructor():ViewModel() {
    val mainRepository = MainRepository(RetrofitService.getInstance())
    val responseWorkDetailModelSuccess = MutableLiveData<ResponseWorkModel>()
    val resRegisterSuccess = MutableLiveData<RegisterModel>()
    val resLoginSuccess = MutableLiveData<ResponseLoginModel>()
    var statusCheck = MutableLiveData<String?>()

    val resError =  MutableLiveData<String>()
    val requestWorkModelSuccess = MutableLiveData<RequestWorkModel>()
    fun  registerUser(registerModel: RegisterModel){
        val response = mainRepository.registerUser(registerModel)
        response.enqueue(object :Callback<RegisterModel>{
            override fun onResponse(call: Call<RegisterModel>, response: Response<RegisterModel>) {
                if (response.isSuccessful){
                    resRegisterSuccess.postValue(response.body())
                }else{
                    resError.value = "ERROR"
                }
            }

            override fun onFailure(call: Call<RegisterModel>, t: Throwable) {
                resError.value = "ERROR"
            }
        })
    }

    fun loginUser(loginModel: LoginModel){
        val resLoginModel = mainRepository.loginUser(loginModel)
        resLoginModel.enqueue(object :Callback<ResponseLoginModel>{
            override fun onResponse(
                call: Call<ResponseLoginModel>,
                response: Response<ResponseLoginModel>
            ) {
                if (response.isSuccessful){
                    resLoginSuccess.postValue(response.body())
                }else{
                    resError.value = "ERROR"
                }
            }

            override fun onFailure(call: Call<ResponseLoginModel>, t: Throwable) {
                resError.value = "ERROR"
            }
        })
    }

    fun addWork(requestWorkModel: RequestWorkModel){
        val response = mainRepository.addWorkUser(requestWorkModel)
        response.enqueue(object :Callback<RequestWorkModel>{
            override fun onResponse(
                call: Call<RequestWorkModel>,
                response: Response<RequestWorkModel>
            ) {
                if (response.isSuccessful){
                    requestWorkModelSuccess.postValue(response.body())
                }else{
                    resError.value = "ERROR"
                }
            }

            override fun onFailure(call: Call<RequestWorkModel>, t: Throwable) {
                resError.value = "ERROR"
            }
        })
    }

    fun searchWorkElder(reqUserAndStatus: ReqUserAndStatus){
        val response = mainRepository.findWorkElderIdAndStatus(reqUserAndStatus)
        response.enqueue(object :Callback<ResponseWorkModel>{
            override fun onResponse(
                call: Call<ResponseWorkModel>,
                response: Response<ResponseWorkModel>
             ) {
                responseWorkDetailModelSuccess.postValue(response.body())
            }

            override fun onFailure(call: Call<ResponseWorkModel>, t: Throwable) {

            }
        })
    }


    fun findUser(userId:String){
        val response =mainRepository.findUser(userId)
        response.enqueue(object :Callback<ResponseLoginModel>{
            override fun onResponse(
                call: Call<ResponseLoginModel>,
                response: Response<ResponseLoginModel>
            ) {
                if (response.isSuccessful){
                    resLoginSuccess.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<ResponseLoginModel>, t: Throwable) {

            }
        })
    }


    fun uploadImage(name:String,file:MultipartBody.Part){
        val response = mainRepository.addImageProfile(name,file)
        response.enqueue(object :Callback<String>{
            override fun onResponse(call: Call<String>, response: Response<String>) {
                statusCheck.postValue("success")
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                statusCheck.postValue("success")
            }
        })

    }
}