package com.ppi.trackventory.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ppi.trackventory.models.User;
import com.ppi.trackventory.repositories.ProfileRepository;
import com.ppi.trackventory.repositories.RolRepository;
import com.ppi.trackventory.repositories.UserRepository;
import com.ppi.trackventory.services.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RolRepository rolRepository;
    
    @Autowired
    private ProfileRepository profileRepository;
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public User saveUser(User user) throws Exception {
    	user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        User userLocal = userRepository.findByUsername(user.getUsername());
        if(userLocal != null){
            System.out.println("User already exists");
            throw new Exception("User already exist");
        }
        else{
            rolRepository.save(user.getProfile().getRol_prf());
            profileRepository.save(user.getProfile());
            userLocal = userRepository.save(user);
        }
        return userLocal;
    }

    @Override
    public User getUser(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

}