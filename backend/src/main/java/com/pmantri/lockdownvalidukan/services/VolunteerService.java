package com.pmantri.lockdownvalidukan.services;

import com.pmantri.lockdownvalidukan.models.User;

import java.util.List;

public interface VolunteerService {
    Boolean validateUniqueVolunteer(String type, String value);
    List<User> getVolunteers(String zip);
}
