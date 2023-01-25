package com.example.applicationelderpathtime.model

class Model {
}


data class RegisterModel(
    val emailUser: String,
    val passwordUser: String,
    val idCardUser: String,
    val firstnameUser: String,
    val lastNameUser: String,
    val roleUser: String,
    val phoneNumber:String
)



data class LoginModel(
    val emailUser:String,
    val passwordUser:String
)

data class ReqUserAndStatus(
    val id:String,
    val statusWork: String
)
data class ResponseLoginModel(
    val emailUser: String,
    val firstnameUser: String,
    val idCardUser: String,
    val imageUser: String,
    val lastNameUser: String,
    val passwordUser: String,
    val roleUser: String,
    val userId: String,
    val phoneNumber:String
)


data class  RequestWorkModel(
    val age: String,
    val district: String,
    val elderId: String,
    var endDate: String,
    val firstName: String,
    val height: String,
    val imageSlip: String,
    val lastName: String,
    val postCode: String,
    val province: String,
    val road: String,
    var salary: String,
    val sex: String,
    var startDate: String,
    var statusWork: String,
    val subDistrict: String,
    val symptom: String,
    val userId: String?,
    val weight: String,
    val houseCode:String,
    val phoneElder:String
)

data class  ResponseWorkModel(
    val id:Long,
    val age: String,
    val district: String,
    val elderId: String,
    var endDate: String,
    val firstName: String,
    val height: String,
    val imageSlip: String,
    val lastName: String,
    val postCode: String,
    val province: String,
    val road: String,
    var salary: String,
    val sex: String,
    var startDate: String,
    var statusWork: String,
    val subDistrict: String,
    val symptom: String,
    val userId: String?,
    val weight: String,
    val houseCode:String,
    val phoneElder:String
)

data class ReqUserElderStatusChange(
    val elderId:String,
    val userId:String,
    val statusWork: String
)