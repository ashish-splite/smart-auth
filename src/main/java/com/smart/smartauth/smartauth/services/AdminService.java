package com.smart.smartauth.smartauth.services;

import com.smart.smartauth.smartauth.requestDTOs.UserIds;

public interface AdminService {
    public void deleteAllUsers(UserIds userIds);
    public void updateRole(Integer userid, String role);
}
