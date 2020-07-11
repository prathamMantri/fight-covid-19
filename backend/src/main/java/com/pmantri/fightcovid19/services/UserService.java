package com.pmantri.fightcovid19.services;

import com.pmantri.fightcovid19.models.User;

public interface UserService {
    boolean isUserUnique(String username, String type);
    void registerUser(User user, String userType);
}
