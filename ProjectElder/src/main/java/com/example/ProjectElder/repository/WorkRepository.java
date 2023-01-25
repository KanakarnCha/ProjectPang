package com.example.ProjectElder.repository;

import com.example.ProjectElder.Entity.WorkEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WorkRepository extends JpaRepository<WorkEntity,Long> {

    Optional<WorkEntity> findByElderIdAndStatusWork(String id,String statusWork);
    Optional<WorkEntity> findByUserIdAndStatusWork(String id,String statusWork);

    Optional<WorkEntity> findByElderId(String elderId);

    @Override
    Optional<WorkEntity> findById(Long aLong);

    List<WorkEntity> findAllByStatusWork(String statusType);
}
