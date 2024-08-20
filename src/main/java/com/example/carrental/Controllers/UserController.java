package com.example.carrental.Controllers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.carrental.Dto.Userdto;
import com.example.carrental.Services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/sign")
    public List<Userdto> getAllUsers(){
     return userService.getAllUsers();
    } 

    @PostMapping("/sign")
    public ResponseEntity<?> creatUser(@Valid @RequestBody Userdto data){
       return new ResponseEntity<>(userService.creatUser(data), HttpStatus.CREATED);
    }

    @GetMapping("/sign/{id}")
    public ResponseEntity<?> getuser(@PathVariable Long id){
        return new ResponseEntity<>(userService.getUser(id), HttpStatus.FOUND);
    }
    @PutMapping("/sign/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Userdto data, @PathVariable Long id){
        return new ResponseEntity<>(userService.updateUser(data, id), HttpStatus.OK);
    }

    @DeleteMapping("/sign/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        return new ResponseEntity<>(userService.deleteUser(id), HttpStatus.OK);
    }
} 