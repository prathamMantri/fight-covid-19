package com.pmantri.fightcovid19.dao;

import com.pmantri.fightcovid19.models.Volunteer;

import java.util.List;

public interface VolunteerDao {
    Boolean insertVolunteer(Volunteer volunteer);
    Boolean updateVolunteer(Volunteer volunteer);
    Boolean validateUniqueVolunteer(String type, String value);
    List<Volunteer> getVolunteers(String zip);
}
