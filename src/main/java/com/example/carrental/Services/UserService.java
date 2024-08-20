package com.example.carrental.Services;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;

import com.example.carrental.Dto.Userdto;
import com.example.carrental.entity.User;


public interface UserService {
   public List<Userdto> getAllUsers(); 
   public Userdto creatUser(Userdto data);
   public Userdto getUser(Long id);
   public Userdto updateUser(Userdto data, Long id);
   public Userdto deleteUser(Long id);
}
