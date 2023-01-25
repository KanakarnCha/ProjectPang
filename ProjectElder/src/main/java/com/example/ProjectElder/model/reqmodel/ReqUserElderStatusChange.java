package com.example.ProjectElder.model.reqmodel;

import lombok.Data;

@Data
public class ReqUserElderStatusChange {
    String elderId;
    String userId;
    String statusWork;
}
