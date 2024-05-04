package com.smart.smartauth.smartauth.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smart.smartauth.smartauth.entities.User;
import com.smart.smartauth.smartauth.repositories.PasswordMappingRepository;
import com.smart.smartauth.smartauth.repositories.RoleUserMappingRepository;
import com.smart.smartauth.smartauth.repositories.UserRepository;
import com.smart.smartauth.smartauth.services.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordMappingRepository passwordMappingRepository;

    @Autowired
    private RoleUserMappingRepository roleUserMappingRepository;

    @Override
    public User updateUser(User user) {

        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (user.getEmail() != null)
            currentUser.setEmail(user.getEmail());
        if (user.getName() != null)
            currentUser.setName(user.getName());

        if (user.getMobileNo() != null)
            currentUser.setMobileNo(user.getMobileNo());

        if (user.getProfilePic() != null)
            currentUser.setProfilePic(user.getProfilePic());

        userRepository.save(currentUser);
        return currentUser;

    }

    @Override
    public void deleteUser() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Integer userid = user.getId();

        userRepository.deleteById(userid);
        passwordMappingRepository.deleteByUserid(userid);
        roleUserMappingRepository.deleteByUserid(userid);
        
    }

}
