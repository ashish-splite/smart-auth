package com.smart.smartauth.smartauth.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smart.smartauth.smartauth.entities.RoleUserMapping;
import com.smart.smartauth.smartauth.repositories.PasswordMappingRepository;
import com.smart.smartauth.smartauth.repositories.RoleUserMappingRepository;
import com.smart.smartauth.smartauth.repositories.UserRepository;
import com.smart.smartauth.smartauth.requestDTOs.UserIds;
import com.smart.smartauth.smartauth.services.AdminService;

import jakarta.persistence.EntityNotFoundException;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordMappingRepository passwordMappingRepository;

    @Autowired
    private RoleUserMappingRepository roleUserMappingRepository;

    @Override
    public void deleteAllUsers(UserIds userIds) {
        userRepository.deleteAllById(userIds.getUserIds());
        passwordMappingRepository.deleteAllByUserids(userIds.getUserIds());
        roleUserMappingRepository.deleteAllByUserids(userIds.getUserIds());
    }

    @Override
    public void updateRole(Integer userid, String role) {

        RoleUserMapping roleUserMapping = roleUserMappingRepository.findByUserid(userid)
                .orElseThrow(() -> new EntityNotFoundException("User does not exist"));
        roleUserMapping.setRole(role);
        roleUserMappingRepository.save(roleUserMapping);
    }

}
