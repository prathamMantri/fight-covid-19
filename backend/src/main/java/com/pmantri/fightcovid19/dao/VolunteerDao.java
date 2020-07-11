package com.pmantri.fightcovid19.dao;

import com.pmantri.fightcovid19.models.User;

import java.util.List;

public interface VolunteerDao {
    Boolean updateVolunteerInfo(User volunteer);
    Boolean isContactVolUnique(String type, String value);
    List<User> getVolunteers(String zip);
}
