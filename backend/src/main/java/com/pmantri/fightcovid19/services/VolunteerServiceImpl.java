package com.pmantri.fightcovid19.services;

import com.pmantri.fightcovid19.dao.VolunteerDao;
import com.pmantri.fightcovid19.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VolunteerServiceImpl implements VolunteerService {

    @Autowired
    VolunteerDao volunteerDao;

    @Override
    public Boolean validateUniqueVolunteer(String type, String value) {
        return volunteerDao.isContactVolUnique(type, value);
    }

    @Override
    public List<User> getVolunteers(String zip) {
        return volunteerDao.getVolunteers(zip);
    }


}
