package com.example.applicationelderpathtime.network

import com.example.applicationelderpathtime.model.*
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query
import java.io.InputStream

interface RetrofitService {
    @POST("control/api/register/submit")
    fun registerUser(@Body registerModel: RegisterModel):Call<RegisterModel>
    @POST("control/api/login/submit")
    fun loginUser(@Body loginModel: LoginModel):Call<ResponseLoginModel>

    @Multipart()
    @POST("control/api/image/submit")
    fun addImageProfile(@Query("userId") userId:String,@Part file:MultipartBody.Part):Call<String>
    @POST("control/api/work/submit")
    fun addWorkUser(@Body requestWorkModel: RequestWorkModel):Call<RequestWorkModel>
    @GET("control/api/work/all")
    fun findWorkAll():Call<List<ResponseWorkModel>>
    @POST("control/api/findword/detail")
    fun findWorkId(@Query("id") id:String):Call<ResponseWorkModel>


    @POST("control/api/searchWorkUser/submit")
    fun findWorkUserIdAndStatus(@Body reqUserAndStatus: ReqUserAndStatus):Call<ResponseWorkModel>
    @POST("control/api/searchWorkElder/submit")
    fun findWorkElderIdAndStatus(@Body reqUserAndStatus: ReqUserAndStatus):Call<ResponseWorkModel>

    @POST("control/api/change/statuswork")
    fun findChangeStatus(@Body reqUserElderStatusChange: ReqUserElderStatusChange):Call<ResponseWorkModel>
    @POST("control/api/get/user")
    fun findUser(@Query("userId") userId: String):Call<ResponseLoginModel>

    @POST("control/api/findWorkId/submit")
    fun workSuccess(@Query("workId") id: String,@Query("Status") status:String):Call<ResponseWorkModel>


    companion object{
        private const val BaseURL = "http://192.168.1.41:8080/"
        var retrofitService:RetrofitService? = null
        fun getInstance():RetrofitService{
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            val client = OkHttpClient.Builder()
            client.addInterceptor(logging)
            if (retrofitService == null){
                val retrofit = Retrofit.Builder()
                    .baseUrl(BaseURL)
                    .client(client.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(RetrofitService::class.java)
            }
            return retrofitService!!
        }
    }
}