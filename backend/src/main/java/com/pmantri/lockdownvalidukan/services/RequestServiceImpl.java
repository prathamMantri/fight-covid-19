package com.pmantri.lockdownvalidukan.services;

import com.pmantri.lockdownvalidukan.dao.RequesterDao;
import com.pmantri.lockdownvalidukan.models.Request;
import com.pmantri.lockdownvalidukan.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequestServiceImpl implements RequesterService {

    @Autowired
    RequesterDao requesterDao;

    @Override
    public void postRequest(User requester, Request request) {
        requesterDao.postRequest(requester, request);
    }

    @Override
    public Boolean validateUniqueVolunteer(String type, String value) {
        return requesterDao.validateUniqueVolunteer(type, value);
    }
}
