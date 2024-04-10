package com.smart.smartauth.smartauth.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smart.smartauth.smartauth.entities.RoleUserMapping;
import com.smart.smartauth.smartauth.repositories.RoleUserMappingRepository;
import com.smart.smartauth.smartauth.repositories.UserRepository;
import com.smart.smartauth.smartauth.responseDTOs.TaskResponse;

import jakarta.persistence.EntityNotFoundException;

@Service
@Transactional
public class AdminService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleUserMappingRepository roleUserMappingRepository;

    public TaskResponse<Object> deleteAllUsers(List<Integer> userids) {
        userRepository.deleteAllById(userids);
        return new TaskResponse<>("All users have been deleted", null);
        
    }

    public TaskResponse<Object> updateRole(Integer userid, String role) {

        RoleUserMapping roleUserMapping = roleUserMappingRepository.findByUserid(userid)
                .orElseThrow(() -> new EntityNotFoundException("User does not exist"));
        roleUserMapping.setRole(role);
        roleUserMappingRepository.save(roleUserMapping);
        return new TaskResponse<>("Role of user " + userid + " is updated to " + role, null);
    }
}
