package com.pmantri.lockdownvalidukan.dao;

import com.pmantri.lockdownvalidukan.models.Request;
import com.pmantri.lockdownvalidukan.models.User;

public interface RequesterDao {
    void postRequest(User requester, Request request);
    Boolean validateUniqueVolunteer(String type, String value);
}
