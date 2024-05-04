package com.smart.smartauth.smartauth.servicesImpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smart.smartauth.smartauth.entities.User;
import com.smart.smartauth.smartauth.repositories.UserRepository;
import com.smart.smartauth.smartauth.requestDTOs.UserIds;
import com.smart.smartauth.smartauth.services.ISCService;

@Service
@Transactional
public class ISCServiceImpl implements ISCService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> fetchAllUsers(UserIds userIds) {
        return (List<User>) userRepository.findAllById(userIds.getUserIds());
    }

}
