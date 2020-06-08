package com.pmantri.fightcovid19.services;

import com.pmantri.fightcovid19.dao.VolunteerDao;
import com.pmantri.fightcovid19.models.Volunteer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VolunteerServiceImpl implements VolunteerService {

    @Autowired
    VolunteerDao volunteerDao;

    @Override
    public void registerVolunteer(Volunteer volunteer, Boolean newVolunteer) {
        if (newVolunteer) {
            volunteerDao.registerVolunteer(volunteer);
        } else {
            volunteerDao.updateVolunteerInfo(volunteer);
        }
    }

    @Override
    public Boolean validateUniqueVolunteer(String type, String value) {
        return volunteerDao.validateUniqueVolunteer(type, value);
    }

    @Override
    public List<Volunteer> getVolunteers(String zip) {
        return volunteerDao.getVolunteers(zip);
    }


}
