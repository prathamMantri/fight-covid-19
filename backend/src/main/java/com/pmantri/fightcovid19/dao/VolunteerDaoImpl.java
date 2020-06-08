package com.pmantri.fightcovid19.dao;

import com.pmantri.fightcovid19.models.HelpCategory;
import com.pmantri.fightcovid19.models.Volunteer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional
@Repository
public class VolunteerDaoImpl implements VolunteerDao {


    private static final String GET_VOLUNTEER_CRED_ID = "SELECT FC_VOL_CRED_ID FROM FC_VOLUNTEER_CREDENTIALS WHERE FC_VOL_CRED_USERNAME = ?";
    private static final String UPDATE_VOLUNTEER_CRED = "UPDATE FC_VOLUNTEER_CREDENTIALS SET FC_VOL_CRED_USERNAME = ? AND FC_VOL_PASSWORD = sha1(?)";
    private static final String VALIDATE_UNIQUE_EMAIL = "select count(*) from FC_VOLUNTEER_CONTACT where FC_VOL_EMAIL_ADDR_1 = ?";
    private static final String VALIDATE_UNIQUE_PHONE = "select count(*) from FC_VOLUNTEER_CONTACT where FC_VOL_PHONE_NUMBER_1 = ?";
    private static final String VALIDATE_UNIQUE_USERNAME = "select count(*) from FC_VOLUNTEER_CREDENTIALS where FC_VOL_CRED_USERNAME = ?";
    private static final String GET_VOLUNTEERS = "select * from FC_VOLUNTEER_ADDRESS where FC_VOL_ZIP = ?";
    private static final String INSERT_VOLUNTEER_CRED = "INSERT INTO FC_VOLUNTEER_CREDENTIALS (" +
            "FC_VOL_CRED_FIRSTNAME, " +
            "FC_VOL_CRED_LASTNAME, " +
            "FC_VOL_CRED_USERNAME, " +
            "FC_VOL_CRED_PASSWORD, " +
            "FC_VOL_CRED_ACTIVE, " +
            "FC_VOL_CRED_VERIFIED) " +
            "VALUES(?,?,?, sha1(?), ?, ?)";

    private static final String INSERT_VOLUNTEER_CONTACT = "INSERT INTO FC_VOLUNTEER_CONTACT (" +
            "VOL_CRED_ID, " +
            "FC_VOL_PHONE_NUMBER_1, " +
            "FC_VOL_EMAIL_ADDR_1) " +
            "VALUES(?, ?, ?)";


    private static final String INSERT_VOLUNTEER_ADDRESS = "INSERT INTO FC_VOLUNTEER_ADDRESS (" +
            "VOL_CRED_ID, " +
            "FC_VOL_STREET_NUMBER_1, " +
            "FC_VOL_STREET_NUMBER_2, " +
            "FC_VOL_CITY, " +
            "FC_VOL_STATE, " +
            "FC_VOL_COUNTRY, " +
            "FC_VOL_ZIP )" +
            "VALUES(?, ?, ?, ?, ?, ?, ?)";


    private static final String GET_PENDING_HELP_CATEGORIES = "select * from FC_HELP_CATEGORIES where FC_HELP_CAT_STATUS = 'PENDING'";

    private static final String INSERT_VOL_CRED_HELP_CAT = "INSERT INTO FC_VOLUNTEER_HELP_CATEGORIES (" +
            "VOL_CRED_ID, " +
            "CAT_HELP_ID) VALUES(?, ?)";


    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Volunteer> getVolunteers(String zip) {

        try {
            return jdbcTemplate.query(GET_VOLUNTEERS, (rs, rowNum) -> {
                Volunteer volunteer = new Volunteer();
                volunteer.setFirstName(rs.getString("FC_VOL_FIRSTNAME"));
                volunteer.setLastName(rs.getString("FC_VOL_LASTNAME"));
                volunteer.setPhoneNumber(rs.getString("FC_VOL_PHONE_NUMBER_1"));
                volunteer.setEmailAddress(rs.getString("FC_VOL_EMAIL_ADDR_1"));
                volunteer.setCity(rs.getString("FC_VOL_CITY"));
                volunteer.setState(rs.getString("FC_VOL_STATE"));
                volunteer.setCountry(rs.getString("FC_VOL_COUNTRY"));
                volunteer.setZip(rs.getString("FC_VOL_ZIP"));
                volunteer.setActive(rs.getInt("FC_VOL_ACTIVE"));
                volunteer.setVerified(rs.getInt("FC_VOL_VERIFIED"));
                volunteer.setAddress1(rs.getString("FC_VOL_ADDR"));
                volunteer.setAddress1(rs.getString("FC_VOL_ADDR"));
                return volunteer;
            }, zip);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Boolean registerVolunteer(Volunteer volunteer) {
        boolean userCredInserted = jdbcTemplate.update(INSERT_VOLUNTEER_CRED,
                volunteer.getFirstName(),
                volunteer.getLastName(),
                volunteer.getVolunteerCredentials().getVolunteerUsername(),
                volunteer.getVolunteerCredentials().getVolunteerPassword(),
                volunteer.getActive(),
                volunteer.getVerified()) > 0;

        if (userCredInserted) {
            Integer volunteerCredId = jdbcTemplate.queryForObject(
                    GET_VOLUNTEER_CRED_ID, new Object[]{volunteer.getVolunteerCredentials().getVolunteerUsername()}, Integer.class);

            jdbcTemplate.update(INSERT_VOLUNTEER_ADDRESS,
                    volunteerCredId,
                    volunteer.getAddress1(),
                    volunteer.getAddress2(),
                    volunteer.getCity(),
                    volunteer.getState(),
                    volunteer.getCountry(),
                    volunteer.getZip());

            jdbcTemplate.update(INSERT_VOLUNTEER_CONTACT, volunteerCredId,
                    volunteer.getPhoneNumber(),
                    volunteer.getEmailAddress());

            //insertIntoVolunteerHelpCategories(volunteer, volunteerDetId);

            return true;

        } else {
            return false;
        }
    }

    @Override
    public Boolean updateVolunteerInfo(Volunteer volunteer) {
        return jdbcTemplate.update(UPDATE_VOLUNTEER_CRED, volunteer.getVolunteerCredentials().getVolunteerUsername(), volunteer.getVolunteerCredentials().getVolunteerPassword()) > 0;
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


    public List<HelpCategory> getPendingHelpCategories() {
        try {
            return jdbcTemplate.query(GET_PENDING_HELP_CATEGORIES, (rs, rowNum) -> {
                HelpCategory helpCategory = new HelpCategory();
                helpCategory.setHelpCategoryId(rs.getInt("FC_HELP_CAT_ID"));
                helpCategory.setHelpCategoryText(rs.getString("FC_HELP_CAT_TEXT"));
                helpCategory.setHelpCategoryDesc(rs.getString("FC_HELP_CAT_DESC"));
                helpCategory.setHelpCategoryStatus(rs.getString("FC_HELP_CAT_STATUS"));
                helpCategory.setHelpCategoryVisibility(rs.getInt("FC_HELP_CAT_VISIBILITY"));
                return helpCategory;
            });
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public void insertIntoVolunteerHelpCategories(Volunteer volunteer, Integer volunteerCredId) {
        List<HelpCategory> pendingHelpCategories = getPendingHelpCategories();
        Map<String, Integer> tempMap = new HashMap<>();
        for (HelpCategory helpCategory : pendingHelpCategories) {
            tempMap.put(helpCategory.getHelpCategoryText(), helpCategory.getHelpCategoryId());
        }
        for (HelpCategory helpCategory : volunteer.getHelpCategoryList()) {
            jdbcTemplate.update(INSERT_VOL_CRED_HELP_CAT, volunteerCredId, tempMap.getOrDefault(helpCategory.getHelpCategoryText(), 0000));
        }
    }


}
