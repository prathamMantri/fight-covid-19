package com.pmantri.fightcovid19.services;

import com.pmantri.fightcovid19.models.User;

import java.util.List;

public interface VolunteerService {
    Boolean validateUniqueVolunteer(String type, String value);
    List<User> getVolunteers(String zip);
}
