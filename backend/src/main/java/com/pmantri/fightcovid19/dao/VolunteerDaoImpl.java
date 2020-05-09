package com.pmantri.fightcovid19.dao;

import com.pmantri.fightcovid19.models.Volunteer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class VolunteerDaoImpl implements VolunteerDao {

    private static final String INSERT_VOLUNTEER_CRED = "INSERT INTO FC_VOLUNTEER_CREDENTIALS (FC_VLTR_USER_NAME, FC_VLTR_PASSWORD) VALUES(?, sha1(?))";
    private static final String GET_VOLUNTEER_CRED_ID = "SELECT FC_VLTR_CRED_ID FROM FC_VOLUNTEER_CREDENTIALS WHERE FC_VLTR_USER_NAME = ?";
    private static final String UPDATE_VOLUNTEER_CRED = "UPDATE FC_VOLUNTEER_CREDENTIALS SET FC_VLTR_USER_NAME = ? AND FC_VLTR_PASSWORD = sha1(?)";
    private static final String VALIDATE_UNIQUE_EMAIL = "select count(*) from FC_VOLUNTEER_DETAILS where FC_VLTR_EMAIL_ID = ?";
    private static final String VALIDATE_UNIQUE_PHONE = "select count(*) from FC_VOLUNTEER_DETAILS where FC_VLTR_PHONE_NUMBER = ?";
    private static final String VALIDATE_UNIQUE_USERNAME = "select count(*) from FC_VOLUNTEER_CREDENTIALS where FC_VLTR_USER_NAME = ?";
    private static final String GET_VOLUNTEERS= "select * from FC_VOLUNTEER_DETAILS where FC_VLTR_ZIP = ?";
    private static final String INSERT_VOLUNTEER_DETAILS = "INSERT INTO FC_VOLUNTEER_DETAILS (" +
            "VLTR_CRED_ID, " +
            "FC_VLTR_FIRST_NAME, " +
            "FC_VLTR_LAST_NAME, " +
            "FC_VLTR_PHONE_NUMBER, " +
            "FC_VLTR_EMAIL_ID, " +
            "FC_VLTR_ADDR, " +
            "FC_VLTR_CITY, " +
            "FC_VLTR_STATE, " +
            "FC_VLTR_COUNTRY, " +
            "FC_VLTR_ZIP, " +
            "FC_VLTR_ACTIVE) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    @Override
    public List<Volunteer> getVolunteers(String zip) {

        try {
            return jdbcTemplate.query(GET_VOLUNTEERS, (rs, rowNum) -> {
                Volunteer volunteer = new Volunteer();
                volunteer.setFirstName(rs.getString("FC_VLTR_FIRST_NAME"));
                volunteer.setLastName(rs.getString("FC_VLTR_LAST_NAME"));
                volunteer.setPhoneNumber(rs.getString("FC_VLTR_PHONE_NUMBER"));
                volunteer.setEmailAddress(rs.getString("FC_VLTR_EMAIL_ID"));
                volunteer.setCity(rs.getString("FC_VLTR_CITY"));
                volunteer.setState(rs.getString("FC_VLTR_STATE"));
                volunteer.setCountry(rs.getString("FC_VLTR_COUNTRY"));
                volunteer.setZip(rs.getString("FC_VLTR_ZIP"));
                volunteer.setActive(rs.getInt("FC_VLTR_ACTIVE"));
                volunteer.setAddress(rs.getString("FC_VLTR_ADDR"));
                return volunteer;
            }, zip);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Override
    public Boolean insertVolunteer(Volunteer volunteer) {
        boolean userCredInserted = jdbcTemplate.update(INSERT_VOLUNTEER_CRED, volunteer.getUserCredentials().getUsername(), volunteer.getUserCredentials().getPassword()) > 0;

        if (userCredInserted) {
            Integer userCredId = jdbcTemplate.queryForObject(
                    GET_VOLUNTEER_CRED_ID, new Object[]{volunteer.getUserCredentials().getUsername()}, Integer.class);

            return jdbcTemplate.update(INSERT_VOLUNTEER_DETAILS, userCredId,
                    volunteer.getFirstName(),
                    volunteer.getLastName(),
                    volunteer.getPhoneNumber(),
                    volunteer.getEmailAddress(),
                    volunteer.getAddress(),
                    volunteer.getCity(),
                    volunteer.getState(),
                    volunteer.getCountry(),
                    volunteer.getZip(),
                    volunteer.getActive()) > 0;
        } else {
            return false;
        }
    }
    @Override
    public Boolean updateVolunteer(Volunteer volunteer) {
        return jdbcTemplate.update(UPDATE_VOLUNTEER_CRED, volunteer.getUserCredentials().getUsername(), volunteer.getUserCredentials().getPassword()) > 0;
    }
    @Override
    public Boolean validateUniqueVolunteer(String type, String value) {
        try {
            Integer count = jdbcTemplate.queryForObject(type.equals("email") ? VALIDATE_UNIQUE_EMAIL : type.equals("phone") ? VALIDATE_UNIQUE_PHONE : VALIDATE_UNIQUE_USERNAME, new Object[]{value}, Integer.class);
            return count < 1;
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
        }
        return true;
    }
}
