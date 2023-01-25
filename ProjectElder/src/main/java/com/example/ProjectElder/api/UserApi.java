package com.example.ProjectElder.api;

import com.example.ProjectElder.Entity.UserEntity;
import com.example.ProjectElder.model.reqmodel.ReqLoginUserModel;
import com.example.ProjectElder.model.reqmodel.ReqUserModel;
import com.example.ProjectElder.model.resmodel.ResLoginUserModel;
import com.example.ProjectElder.model.resmodel.ResUserModel;
import com.example.ProjectElder.repository.UserRepository;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/control/api")
public class UserApi {
    @Autowired
    public UserRepository userRepository;


    @PostMapping("/register/submit")
    public  ResponseEntity<UserEntity> registerUser(@RequestBody ReqUserModel reqUserModel){
        UserEntity resUserModel = new UserEntity();
        resUserModel.setEmailUser(reqUserModel.getEmailUser());
        resUserModel.setPasswordUser(reqUserModel.getPasswordUser());
        resUserModel.setFirstnameUser(reqUserModel.getFirstnameUser());
        resUserModel.setLastNameUser(reqUserModel.getLastNameUser());
        resUserModel.setIdCardUser(reqUserModel.getIdCardUser());
        resUserModel.setRoleUser(reqUserModel.getRoleUser());
        resUserModel.setPhoneNumber(reqUserModel.getPhoneNumber());
        userRepository.save(resUserModel);
        return  new ResponseEntity<UserEntity>(resUserModel, HttpStatus.OK);
    }

    @PostMapping("/login/submit")
    public  ResponseEntity<ResLoginUserModel> loginUser(@RequestBody ReqLoginUserModel reqLoginUserModel){
        ResLoginUserModel resLoginUserModel = new ResLoginUserModel();
        String email = reqLoginUserModel.getEmailUser();
        Optional<UserEntity> userOp= userRepository.findByEmailUser(email);
        if (userOp.isEmpty()){
            resLoginUserModel.setEmailUser(reqLoginUserModel.getEmailUser());
            resLoginUserModel.setPasswordUser(reqLoginUserModel.getPasswordUser());
            return  new ResponseEntity<>(resLoginUserModel,HttpStatus.EXPECTATION_FAILED);
        }
        UserEntity userData = userOp.get();
        if (!Objects.equals(reqLoginUserModel.getPasswordUser(), userData.getPasswordUser())){

            return  new ResponseEntity<>(resLoginUserModel,HttpStatus.EXPECTATION_FAILED);
        }
        resLoginUserModel.setUserId(userData.getUserId());
        resLoginUserModel.setEmailUser(userData.getEmailUser());
        resLoginUserModel.setPasswordUser(userData.getPasswordUser());
        resLoginUserModel.setFirstnameUser(userData.getFirstnameUser());
        resLoginUserModel.setLastNameUser(userData.getLastNameUser());
        resLoginUserModel.setIdCardUser(userData.getIdCardUser());
        resLoginUserModel.setRoleUser(userData.getRoleUser());
        resLoginUserModel.setImageUser(userData.getImageUser());
        return new ResponseEntity<ResLoginUserModel>(resLoginUserModel, HttpStatus.OK);
    }

    @PostMapping("/image/submit")
    private ResponseEntity<String> addImage(@RequestParam("userId") String userId, @RequestParam("file") MultipartFile file) throws IOException {
        file.transferTo(new File("C:\\Users\\MyKanakarn\\Desktop\\UploadImage\\"+userId+".jpg"));
        Optional<UserEntity> user = userRepository.findById(userId);
        UserEntity userData = user.get();
        userData.setImageUser(userId+".jpg");
        userRepository.save(userData);
        return ResponseEntity.ok("File uploaded successfully.");
    }

    @PostMapping("/get/user")
    private ResponseEntity<Optional<UserEntity>> findUser(@RequestParam("userId") String userId){
        return new ResponseEntity<>(userRepository.findById(userId),HttpStatus.OK);
    }




}
