package com.pmantri.fightcovid19.dao;

import com.pmantri.fightcovid19.models.Request;
import com.pmantri.fightcovid19.models.User;

public interface RequesterDao {
    void postRequest(User requester, Request request);
    Boolean validateUniqueVolunteer(String type, String value);
}
