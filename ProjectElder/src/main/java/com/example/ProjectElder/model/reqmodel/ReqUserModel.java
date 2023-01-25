package com.example.ProjectElder.model.reqmodel;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class ReqUserModel {
    private String emailUser;
    private String passwordUser;
    private String firstnameUser;
    private String lastNameUser;
    private String roleUser;
    private String idCardUser;
    private String phoneNumber;
}


