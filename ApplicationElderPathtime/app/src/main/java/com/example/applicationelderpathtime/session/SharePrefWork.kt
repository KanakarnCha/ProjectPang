package com.example.applicationelderpathtime.session

import android.content.Context
import android.content.SharedPreferences
import com.example.applicationelderpathtime.model.RequestWorkModel
import com.google.gson.Gson

class SharePrefWork(context: Context){

    val keyPref = "dev.foam"
    val tagCode = "house_code"
    val tagRoad = "road"
    val tagProvince = "province"
    val tagDis = "disteic"
    val tagsubDistrict ="subDistrict"
    val tagpostCode = "postCode"
    val tagfirstName = "firstName"
    val taglastName = "lastName"
    val tagsex = "sex"
    val tagage = "age"
    val tagweight = "weight"
    val tagheight = "height"
    val tagsymptom = "symptom"
    val tagDataReqWork = "Data_Work"
    val gson:Gson = Gson()
    val prefs: SharedPreferences = context.getSharedPreferences(keyPref, Context.MODE_PRIVATE)

    fun saveWorkData(requestWorkModel: RequestWorkModel){
        val editor = prefs.edit()
//        editor.putString(tagCode,requestWorkModel.houseCode)
//        editor.putString(tagRoad,requestWorkModel.road)
//        editor.putString(tagProvince,requestWorkModel.province)
//        editor.putString(tagDis,requestWorkModel.district)
//        editor.putString(tagsubDistrict,requestWorkModel.subDistrict)
//        editor.putString(tagpostCode,requestWorkModel.postCode)
//        editor.putString(tagfirstName,requestWorkModel.firstName)
//        editor.putString(taglastName,requestWorkModel.lastName)
//        editor.putString(tagsex,requestWorkModel.sex)
//        editor.putString(tagage,requestWorkModel.age)
//        editor.putString(tagweight,requestWorkModel.weight)
//        editor.putString(tagheight,requestWorkModel.height)
//        editor.putString(tagsymptom,requestWorkModel.symptom)

        val valueJson = gson.toJson(requestWorkModel)
        editor.putString(tagDataReqWork,valueJson)
        editor.apply()
    }
    fun getWorkData(tag:String): String? {
        return prefs.getString(tag, "")
    }
    fun getData():RequestWorkModel{
        val dataJson = prefs.getString(tagDataReqWork,"")
        return gson.fromJson(dataJson,RequestWorkModel::class.java)

    }
    fun clearDate(){

    }
}