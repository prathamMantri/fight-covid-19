package com.pmantri.lockdownvalidukan.services;

import com.pmantri.lockdownvalidukan.models.Request;
import com.pmantri.lockdownvalidukan.models.User;

public interface RequesterService {

    void postRequest(User requester, Request request);
    Boolean validateUniqueVolunteer(String type, String value);

}
