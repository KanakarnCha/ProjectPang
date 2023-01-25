package com.example.applicationelderpathtime.repository

import com.example.applicationelderpathtime.model.*
import com.example.applicationelderpathtime.network.RetrofitService
import okhttp3.MultipartBody
import retrofit2.http.Body
import retrofit2.http.Part
import retrofit2.http.Query

class MainRepository constructor(private val retrofitService: RetrofitService){
    fun registerUser(registerModel: RegisterModel) = retrofitService.registerUser(registerModel)
    fun loginUser(loginModel: LoginModel) = retrofitService.loginUser(loginModel)
    fun addWorkUser(requestWorkModel: RequestWorkModel) = retrofitService.addWorkUser(requestWorkModel)

    fun findWorkAll() = retrofitService.findWorkAll()

    fun findWorkId(id:String) = retrofitService.findWorkId(id)

    fun findWorkUserIdAndStatus(reqUserAndStatus: ReqUserAndStatus) = retrofitService.findWorkUserIdAndStatus(reqUserAndStatus)
    fun findWorkElderIdAndStatus(reqUserAndStatus: ReqUserAndStatus) = retrofitService.findWorkElderIdAndStatus(reqUserAndStatus)
    fun findChangeStatus(reqUserElderStatusChange: ReqUserElderStatusChange)= retrofitService.findChangeStatus(reqUserElderStatusChange)


    fun findUser(userId:String) = retrofitService.findUser(userId)

    fun workSuccess(id: String,status:String) = retrofitService.workSuccess(id,status)



    fun addImageProfile(userId:String, file: MultipartBody.Part) = retrofitService.addImageProfile(userId,file)
}