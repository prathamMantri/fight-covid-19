package com.pmantri.fightcovid19.services;

import com.pmantri.fightcovid19.models.Request;
import com.pmantri.fightcovid19.models.User;

public interface RequesterService {

    void postRequest(User requester, Request request);
    Boolean validateUniqueVolunteer(String type, String value);

}
