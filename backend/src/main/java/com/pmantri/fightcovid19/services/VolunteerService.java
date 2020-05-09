package com.pmantri.fightcovid19.services;

import com.pmantri.fightcovid19.models.Volunteer;

import java.util.List;

public interface VolunteerService {
    void insertUpdateVolunteer(Volunteer volunteer, Boolean newVolunteer);
    Boolean validateUniqueVolunteer(String type, String value);
    List<Volunteer> getVolunteers(String zip);
}
