package com.smart.smartauth.smartauth.services;

import com.smart.smartauth.smartauth.entities.User;

public interface UserService {
    public User updateUser(User user);
    public void deleteUser();
}
