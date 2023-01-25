package com.example.ProjectElder.model.resmodel;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
public class ResLoginUserModel {
    private String userId;
    private String emailUser;
    private String passwordUser;
    private String firstnameUser;
    private String lastNameUser;
    private String roleUser;
    private String idCardUser;
    private String imageUser;
}
