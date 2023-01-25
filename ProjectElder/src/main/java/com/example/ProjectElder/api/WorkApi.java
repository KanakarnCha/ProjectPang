package com.example.ProjectElder.api;

import com.example.ProjectElder.Entity.UserEntity;
import com.example.ProjectElder.Entity.WorkEntity;
import com.example.ProjectElder.model.reqmodel.ReqSearchWorkModel;
import com.example.ProjectElder.model.reqmodel.ReqUserElderStatusChange;
import com.example.ProjectElder.model.reqmodel.ReqWorkModel;
import com.example.ProjectElder.repository.UserRepository;
import com.example.ProjectElder.repository.WorkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/control/api")
public class WorkApi {
    @Autowired
    public UserRepository userRepository;

    @Autowired
    public WorkRepository workRepository;
    @PostMapping("/work/submit")
    public ResponseEntity<?> addWork(@RequestBody  ReqWorkModel reqWorkModel){
        WorkEntity workEntity = new WorkEntity();
        workEntity.setPostCode(reqWorkModel.getPostCode());
        workEntity.setRoad(reqWorkModel.getRoad());
        workEntity.setProvince(reqWorkModel.getProvince());
        workEntity.setDistrict(reqWorkModel.getDistrict());
        workEntity.setSubDistrict(reqWorkModel.getSubDistrict());
        workEntity.setFirstName(reqWorkModel.getFirstName());
        workEntity.setLastName(reqWorkModel.getLastName());
        workEntity.setSex(reqWorkModel.getSex());
        workEntity.setAge(reqWorkModel.getAge());
        workEntity.setWeight(reqWorkModel.getWeight());
        workEntity.setHeight(reqWorkModel.getHeight());
        workEntity.setSymptom(reqWorkModel.getSymptom());
        workEntity.setStartDate(reqWorkModel.getStartDate());
        workEntity.setEndDate(reqWorkModel.getEndDate());
        workEntity.setImageSlip(reqWorkModel.getImageSlip());
        workEntity.setUserId(reqWorkModel.getUserId());
        workEntity.setElderId(reqWorkModel.getElderId());
        workEntity.setStatusWork(reqWorkModel.getStatusWork());
        workEntity.setSalary(reqWorkModel.getSalary());
        workEntity.setHouseCode(reqWorkModel.getHouseCode());
        workEntity.setPhoneElder(reqWorkModel.getPhoneElder());
        return ResponseEntity.ok(workRepository.save(workEntity));
    }

    @PostMapping("/search/submit")
    public ResponseEntity<Optional<WorkEntity>> searchWorkNotMatch(@RequestBody  ReqSearchWorkModel reqSearchWorkModel){
        return new ResponseEntity<Optional<WorkEntity>>(workRepository.findByElderIdAndStatusWork(reqSearchWorkModel.getId(),reqSearchWorkModel.getStatusWork()),HttpStatus.OK);
    }
       @GetMapping("/work/all")
    public ResponseEntity<List<WorkEntity>> findWorkAllStatus0(){
        return new ResponseEntity<List<WorkEntity>>( workRepository.findAllByStatusWork("0"),HttpStatus.OK);
    }

    @PostMapping("/findword/detail")
    public ResponseEntity<Optional<WorkEntity>> findWordId(@RequestParam("id") String id){
        return new ResponseEntity<>(workRepository.findById(Long.valueOf(id)),HttpStatus.OK);
    }
    @PostMapping("/searchWorkUser/submit")
    public ResponseEntity<Optional<WorkEntity>> findWordIdUser(@RequestBody ReqSearchWorkModel reqSearchWorkModel){
        return new ResponseEntity<>(workRepository.findByUserIdAndStatusWork(reqSearchWorkModel.getId(),reqSearchWorkModel.getStatusWork()),HttpStatus.OK);
    }
    @PostMapping("/searchWorkElder/submit")
    public ResponseEntity<Optional<WorkEntity>> findWordIdElder(@RequestBody ReqSearchWorkModel reqSearchWorkModel){
         Optional<WorkEntity> check0 = workRepository.findByElderIdAndStatusWork(reqSearchWorkModel.getId(),"0");
         System.out.println(check0);
         Optional<WorkEntity> check1 = workRepository.findByElderIdAndStatusWork(reqSearchWorkModel.getId(),"1");
        System.out.println(check1);
        if (check0.isEmpty() && check1.isPresent()){
            return new ResponseEntity<>(check1,HttpStatus.OK);
        }else if (check1.isEmpty() && check0.isPresent()){
            return new ResponseEntity<>(check0,HttpStatus.OK);
        }else{
            return  new ResponseEntity<>(Optional.empty(),HttpStatus.OK);
        }   
    }
    @PostMapping("/change/statuswork")
    public ResponseEntity<?> changeStatusWork(@RequestBody ReqUserElderStatusChange reqUserElderStatusChange){
        Optional<WorkEntity> workOp = workRepository.findByElderIdAndStatusWork(reqUserElderStatusChange.getElderId(),"0");
        WorkEntity user = workOp.get();
        user.setUserId(reqUserElderStatusChange.getUserId());
        user.setStatusWork(reqUserElderStatusChange.getStatusWork());
        return new ResponseEntity<>(workRepository.save(user),HttpStatus.OK);
    }


    @PostMapping("findWorkId/submit")
    public ResponseEntity<?> workSuccess(@RequestParam("workId") String workId,@RequestParam("Status") String status){
        Optional<WorkEntity> workOp = workRepository.findById(Long.valueOf(workId));
        WorkEntity work = workOp.get();
        work.setStatusWork(status);
        return new ResponseEntity<>(workRepository.save(work),HttpStatus.OK);
    }
}
