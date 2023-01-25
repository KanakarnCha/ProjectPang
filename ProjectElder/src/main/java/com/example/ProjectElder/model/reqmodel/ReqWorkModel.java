package com.example.ProjectElder.model.reqmodel;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class ReqWorkModel {
    String houseCode;
    String postCode;
    String road;
    String province;
    String District;
    String SubDistrict ;
    String firstName;
    String lastName;
    String sex;
    String age;
    String weight;
    String Height;
    String symptom;
    String startDate;
    String endDate;
    String imageSlip;
    String userId;
    String elderId;
    String statusWork;
    String salary;
    String phoneElder;

}
