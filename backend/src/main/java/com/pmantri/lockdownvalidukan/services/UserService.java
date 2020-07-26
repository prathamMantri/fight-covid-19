package com.pmantri.lockdownvalidukan.services;

import com.pmantri.lockdownvalidukan.models.User;

public interface UserService {
    boolean isUserUnique(String username, String type);
    void registerUser(User user, String userType);
}
