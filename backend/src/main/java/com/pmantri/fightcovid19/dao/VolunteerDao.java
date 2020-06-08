package com.pmantri.fightcovid19.dao;

import com.pmantri.fightcovid19.models.Volunteer;

import java.util.List;

public interface VolunteerDao {
    Boolean registerVolunteer(Volunteer volunteer);
    Boolean updateVolunteerInfo(Volunteer volunteer);
    Boolean validateUniqueVolunteer(String type, String value);
    List<Volunteer> getVolunteers(String zip);
}
