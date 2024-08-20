package com.example.carrental.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.carrental.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{
    public Boolean existsByEmail(String email);
}
