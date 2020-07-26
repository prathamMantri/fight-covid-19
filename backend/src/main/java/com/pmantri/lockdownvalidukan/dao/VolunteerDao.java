package com.pmantri.lockdownvalidukan.dao;

import com.pmantri.lockdownvalidukan.models.User;

import java.util.List;

public interface VolunteerDao {
    Boolean updateVolunteerInfo(User volunteer);
    Boolean isContactVolUnique(String type, String value);
    List<User> getVolunteers(String zip);
}
