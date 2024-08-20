package com.example.carrental.Services;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;


import com.example.carrental.Dto.Userdto;
import com.example.carrental.Repository.UserRepository;
import com.example.carrental.entity.User;
import com.example.carrental.enums.userRole;
import com.example.carrental.exception.EmailExistException;
import com.example.carrental.exception.resourceNotfound;
@Service
public class UserServiceImpl implements UserService {
   
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private UserRepository userRepo;
    @Override
    public Userdto creatUser(@RequestBody Userdto data) {
        User user = mapper.map(data, User.class);
        if(userRepo.existsByEmail(data.getEmail())){
            throw new EmailExistException(data.getEmail());
        }
        user.setUserRole(userRole.CUSTOMER);
        return mapper.map(userRepo.save(user), Userdto.class);
    }
    @Override
    public Userdto getUser(Long id) {
        User user = userRepo.findById(id)
        .orElseThrow(()->  new resourceNotfound("User not found with this id: "+id));
        return mapper.map(user, Userdto.class);
    }
    @Override
    public List<Userdto> getAllUsers() {
        //    Pageable p = PageRequest.of(1, 4, Sort.by("id"));
        //  Page<User> pageUser = userRepo.findAll(p);
        List<User> users = userRepo.findAll();
        //  System.out.println(pageUser.getNumber());
        //  System.out.println(pageUser.getSize());
        //  System.out.println(pageUser.getTotalPages());
        //  System.out.println(pageUser.getTotalElements());
        //  System.out.println(pageUser.isFirst());
        return users.stream().map(e-> mapper.map(e, Userdto.class)).collect(Collectors.toList());
    }
    @Override
    public Userdto updateUser(Userdto data, Long id) {
        User exits = userRepo.findById(id)
        .orElseThrow(()-> new resourceNotfound("User not found with this id: "+id));
        exits.setName(data.getName());
        exits.setEmail(data.getEmail());
        exits.setPassword(data.getPassword());
        userRepo.save(exits);
        return mapper.map(exits, Userdto.class);
    }
    @Override
    public Userdto deleteUser(Long id) {
        User exist = userRepo.findById(id)
        .orElseThrow(()-> new resourceNotfound("User not found with this id: "+id));
        userRepo.delete(exist);
        return mapper.map(exist, Userdto.class);
    }
    
    
}
