package com.pmantri.fightcovid19.services;

import com.pmantri.fightcovid19.dao.UserDao;
import com.pmantri.fightcovid19.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserDetailsServiceImpl implements UserDetailsService, UserService {

    @Autowired
    UserDao userDao;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
        return UserDetailsImpl.build(user);
    }

    @Override
    public boolean isUserUnique(String username, String type) {
        return userDao.isUserUnique(username, type);
    }

    @Override
    public void registerUser(User user, String type) {
        if (userDao.registerUser(user)) {
            Long userId = userDao.getUserId(user.getUsername());
            if(userId != null && userId != -1L) {
                userDao.addRole(type, userId);
                userDao.updateAddress(user, type, userId);
                userDao.updateContact(user, type, userId);
            }
        }

    }


}
