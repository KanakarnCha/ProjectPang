package com.example.applicationelderpathtime.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.applicationelderpathtime.model.ReqUserAndStatus
import com.example.applicationelderpathtime.model.ReqUserElderStatusChange
import com.example.applicationelderpathtime.model.ResponseWorkModel
import com.example.applicationelderpathtime.network.RetrofitService
import com.example.applicationelderpathtime.repository.MainRepository
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class UserViewModel:ViewModel()   {
    val mainRepository = MainRepository(RetrofitService.getInstance())
    val responseWorkModelSuccess = MutableLiveData<List<ResponseWorkModel>>()
    val responseWorkDetailModelSuccess = MutableLiveData<ResponseWorkModel>()
    fun findAllWorkSt0(){
        val response = mainRepository.findWorkAll()
        response.enqueue(object :retrofit2.Callback<List<ResponseWorkModel>>{
            override fun onResponse(
                call: Call<List<ResponseWorkModel>>,
                response: Response<List<ResponseWorkModel>>
            ) {
                if (response.isSuccessful){
                    responseWorkModelSuccess.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<List<ResponseWorkModel>>, t: Throwable) {

            }
        })
    }


    fun findIdWork(id:String){
        val response = mainRepository.findWorkId(id)
        response.enqueue(object :retrofit2.Callback<ResponseWorkModel>{
            override fun onResponse(
                call: Call<ResponseWorkModel>,
                response: Response<ResponseWorkModel>
            ) {
                if (response.isSuccessful){
                    responseWorkDetailModelSuccess.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<ResponseWorkModel>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

    fun findWorkUserIdAndStatus(reqUserAndStatus: ReqUserAndStatus){
        val response = mainRepository.findWorkUserIdAndStatus(reqUserAndStatus)
        response.enqueue(object :retrofit2.Callback<ResponseWorkModel>{
            override fun onResponse(
                call: Call<ResponseWorkModel>,
                response: Response<ResponseWorkModel>
            ) {
                if (response.isSuccessful){
                    responseWorkDetailModelSuccess.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<ResponseWorkModel>, t: Throwable) {

            }
        })
    }



    fun changStatusMatch(reqUserElderStatusChange: ReqUserElderStatusChange){
        val response = mainRepository.findChangeStatus(reqUserElderStatusChange)
        response.enqueue(object :retrofit2.Callback<ResponseWorkModel>{
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
    fun workSuccess(id: String,status:String){
        val  response = mainRepository.workSuccess(id,status)
        response.enqueue(object :retrofit2.Callback<ResponseWorkModel>{
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
}