package com.example.ProjectElder.Entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Data
@Entity
public class WorkEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
     Long id;
    @Column()
    String postCode;
    @Column
    String road;
    @Column
    String province;
    @Column
    String District;
    @Column
    String SubDistrict ;
    @Column
    String firstName;
    @Column
    String lastName;
    @Column
    String sex;
    @Column
    String age;
    @Column
    String weight;
    @Column
    String Height;
    @Column
    String symptom;
    @Column
    String startDate;
    @Column
    String endDate;
    @Column
    String imageSlip;
    @Column
    String userId;
    @Column
    String elderId;
    @Column
    String statusWork;
    @Column
    String salary;
    @Column
    String houseCode;
    @Column
    String phoneElder;
}

