package com.smart.smartauth.smartauth.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smart.smartauth.smartauth.entities.User;
import com.smart.smartauth.smartauth.repositories.UserRepository;

@Service
@Transactional
public class ISCService {

    @Autowired
    private UserRepository userRepository;

    public List<User> fetchAllUsers(List<Integer> userids) {
        return (List<User>) userRepository.findAllById(userids);
    }

}
