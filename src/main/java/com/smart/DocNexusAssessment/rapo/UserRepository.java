package com.smart.DocNexusAssessment.rapo;

import com.smart.DocNexusAssessment.entity.BlogEntity;
import com.smart.DocNexusAssessment.entity.UserEntity;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Integer> {

    UserEntity findByEmail(String email);

}

