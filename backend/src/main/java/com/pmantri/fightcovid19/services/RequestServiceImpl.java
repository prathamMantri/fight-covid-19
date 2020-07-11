package com.pmantri.fightcovid19.services;

import com.pmantri.fightcovid19.dao.RequesterDao;
import com.pmantri.fightcovid19.models.Request;
import com.pmantri.fightcovid19.models.User;
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
