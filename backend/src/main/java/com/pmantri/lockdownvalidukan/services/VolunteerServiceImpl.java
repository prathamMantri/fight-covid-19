package com.pmantri.lockdownvalidukan.services;

import com.pmantri.lockdownvalidukan.dao.VolunteerDao;
import com.pmantri.lockdownvalidukan.models.User;
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
