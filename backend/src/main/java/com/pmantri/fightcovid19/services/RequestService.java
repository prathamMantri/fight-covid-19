package com.pmantri.fightcovid19.services;

import com.pmantri.fightcovid19.models.Request;
import com.pmantri.fightcovid19.models.Requester;

public interface RequestService {
    void postRequest(Requester requester, Request request);
    Boolean validateUniqueVolunteer(String type, String value);
}
