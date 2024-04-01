package com.finathon.repository;

import com.finathon.model.LoginUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginRepository extends JpaRepository<LoginUser,String> {
    boolean existsByUserName(String userName);
}