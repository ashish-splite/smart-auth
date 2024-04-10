package com.smart.smartauth.smartauth.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smart.smartauth.smartauth.entities.User;
import com.smart.smartauth.smartauth.repositories.UserRepository;
import com.smart.smartauth.smartauth.responseDTOs.TaskResponse;

import jakarta.persistence.EntityNotFoundException;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User updateUser(User user) {

        User existedUser = userRepository.findById(user.getId())
                .orElseThrow(() -> new EntityNotFoundException("User does not exist"));

        if (user.getEmail() != null)
            existedUser.setEmail(user.getEmail());
        if (user.getName() != null)
            existedUser.setName(user.getName());

        if (user.getMobileNo() != null)
            existedUser.setMobileNo(user.getMobileNo());

        if (user.getProfilePic() != null)
            existedUser.setProfilePic(user.getProfilePic());

        userRepository.save(existedUser);
        return existedUser;

    }

    public TaskResponse<Integer> deleteUser(){
           User user  =  (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
           userRepository.deleteById(user.getId());
           return new TaskResponse<>("User is deleted", user.getId());
    }

}
