package com.pmantri.lockdownvalidukan.dao;

import com.pmantri.lockdownvalidukan.models.User;

import java.util.Optional;

public interface UserDao {

    Optional<User> findByUsername(String username, String role);

    boolean addRole(String userType, Long userId);

    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    boolean registerUser(User user);

    boolean updateContact(User user, String userType, Long userId);

    boolean updateAddress(User user, String userType, Long userId);

    Long getUserId(String userId);

    Boolean isUserUnique(String type, String value);

}
