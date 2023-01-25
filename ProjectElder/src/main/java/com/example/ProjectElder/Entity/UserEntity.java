package com.example.ProjectElder.Entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Data
@Entity
public class UserEntity {
    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    @Column(length = 36, nullable = false, updatable = false,unique = true)
    private String userId;
    @Column(length = 100, nullable = false,unique = true)
    private String emailUser;
    @Column(length = 36, nullable = false)
    private String passwordUser;
    @Column(length = 36, nullable = false)
    private String firstnameUser;
    @Column(length = 36, nullable = false)
    private String lastNameUser;
    @Column( nullable = false)
    private String roleUser;
    @Column(length = 100, nullable = false,unique = true)
    private String idCardUser;

    @Column(length = 100)
    private String imageUser;
    @Column()
    private String phoneNumber;
}
