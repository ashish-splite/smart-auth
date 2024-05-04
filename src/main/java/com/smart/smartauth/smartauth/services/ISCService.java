package com.smart.smartauth.smartauth.services;

import java.util.List;

import com.smart.smartauth.smartauth.entities.User;
import com.smart.smartauth.smartauth.requestDTOs.UserIds;

public interface ISCService {
     public List<User> fetchAllUsers(UserIds userIds);
}
