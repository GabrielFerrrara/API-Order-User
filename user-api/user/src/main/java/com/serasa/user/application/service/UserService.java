package com.serasa.user.application.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.serasa.user.domain.exception.UserInvalidException;
import com.serasa.user.domain.exception.UserNotFoundException;
import com.serasa.user.domain.model.User;
import com.serasa.user.domain.repository.JpaUserRepository;

@Service
public class UserService {

    @Autowired
    private JpaUserRepository userRepository;

    
    public List<User> findAll() {
        List<User> users = userRepository.findAll();
        return users;
    }

    public User findById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            return userOptional.get();
        }
        throw new UserNotFoundException("User not found with id: " + id);
    }
    
    public User save(User user) {
        try {
            return userRepository.save(user);
        } catch (Exception e) {
            throw new UserInvalidException("User attribute invalid, expected: \n name, \n cpf, \nemail, \nphoneNumber");
        }
    }

    public User updateUser(Long id, User user){
        //verify if id is exist
        findById(id);

        user.setId(id);
        return save(user);
    }

    public void deleteUser(Long id){
        findById(id);
        userRepository.deleteById(id);
    } 

}
