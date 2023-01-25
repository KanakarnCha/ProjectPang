package com.example.applicationelderpathtime.session

import android.content.Context
import android.content.SharedPreferences
import com.example.applicationelderpathtime.model.ResponseLoginModel

class SharePref(val context: Context) {
    val keyPref = "dev.foam"
    val tagUserId = "USERID"
    val tagRoleUser = "USERROLE"
    val prefs: SharedPreferences = context.getSharedPreferences(keyPref, Context.MODE_PRIVATE)


    fun saveDataUser(UserId: String,roleUser:String){
        val editor = prefs.edit()
        editor.putString(tagUserId,UserId)
        editor.putString(tagRoleUser,roleUser)
        editor.apply()

    }

    fun clearDataUser(){
        val editor = prefs.edit()
        editor.remove(tagUserId)
        editor.remove(tagRoleUser)
        editor.apply()
    }


    fun getUserId(): String?{
        return prefs.getString(tagUserId, "")
    }
    fun getRoleUser(): String?{
        return prefs.getString(tagRoleUser, "")
    }


    fun stateLogin(state:String){
        val  editor = prefs.edit()
        editor.putString("state",state)
        editor.apply()
    }
    fun stateLoginRemove(){
        val  editor = prefs.edit()
        editor.remove("state")
        editor.apply()
    }
    fun getLoginState(): String? {
        return prefs.getString("state","")
    }
}