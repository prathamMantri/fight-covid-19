package com.pmantri.fightcovid19.services;

import com.pmantri.fightcovid19.dao.RequestDao;
import com.pmantri.fightcovid19.models.Request;
import com.pmantri.fightcovid19.models.Requester;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequestServiceImpl implements RequestService {

    @Autowired
    RequestDao requestDao;

    @Override
    public void postRequest(Requester requester, Request request) {
        requestDao.postRequest(requester, request);
    }

    @Override
    public Boolean validateUniqueVolunteer(String type, String value) {
        return requestDao.validateUniqueVolunteer(type, value);
    }
}
