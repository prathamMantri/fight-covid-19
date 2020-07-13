package com.pmantri.fightcovid19.dao;

import com.pmantri.fightcovid19.models.HelpCategory;
import com.pmantri.fightcovid19.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public class VolunteerDaoImpl implements VolunteerDao {


    private static final String UPDATE_VOLUNTEER_CRED = "UPDATE FC_VOLUNTEER_CREDENTIALS SET FC_VOL_USERNAME = ? AND FC_VOL_PASSWORD = sha1(?)";
    private static final String VALIDATE_UNIQUE_EMAIL = "select count(*) from FC_VOLUNTEER_CONTACT where FC_VOL_EMAIL_ADDR_1 = ?";
    private static final String VALIDATE_UNIQUE_PHONE = "select count(*) from FC_VOLUNTEER_CONTACT where FC_VOL_PHONE_NUMBER_1 = ?";
    private static final String GET_PENDING_HELP_CATEGORIES = "select * from FC_HELP_CATEGORIES where FC_HELP_CAT_STATUS = 'PENDING'";
    private static final String INSERT_VOL_CRED_HELP_CAT = "INSERT INTO FC_VOLUNTEER_HELP_CATEGORIES (" +
            "VOL_CRED_ID, " +
            "CAT_HELP_ID) VALUES(?, ?)";
    private static final String GET_VOLUNTEERS = "select USER.FC_USER_FIRSTNAME, " +
            "USER.FC_USER_LASTNAME, " +
            "CONTACT.FC_VOL_PHONE_NUMBER_1, " +
            "CONTACT.FC_VOL_EMAIL_ADDR_1, " +
            "ADDRESS.FC_VOL_CITY, " +
            "ADDRESS.FC_VOL_STATE, " +
            "ADDRESS.FC_VOL_COUNTRY, " +
            "ADDRESS.FC_VOL_ZIP, " +
            "ADDRESS.FC_VOL_STREET_NUMBER_1, " +
            "ADDRESS.FC_VOL_STREET_NUMBER_2, " +
            "USER.FC_USER_ACTIVE, " +
            "USER.FC_USER_VERIFIED " +
            "from FC_USER_CREDENTIALS USER, FC_VOLUNTEER_CONTACT CONTACT, FC_VOLUNTEER_ADDRESS ADDRESS " +
            "where " +
            "USER.FC_USER_ID = CONTACT.USER_ID " +
            "and USER.FC_USER_ID = ADDRESS.USER_ID " +
            "and ADDRESS.FC_VOL_ZIP = ?";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    UserDao userDao;

    @Override
    public List<User> getVolunteers(String zip) {

        try {
            return jdbcTemplate.query(GET_VOLUNTEERS, (rs, rowNum) -> {
                User volunteer = new User();
                volunteer.setFirstName(rs.getString("FC_USER_FIRSTNAME"));
                volunteer.setLastName(rs.getString("FC_USER_LASTNAME"));
                volunteer.setPhoneNumber(rs.getString("FC_VOL_PHONE_NUMBER_1"));
                volunteer.setEmailAddress(rs.getString("FC_VOL_EMAIL_ADDR_1"));
                volunteer.setCity(rs.getString("FC_VOL_CITY"));
                volunteer.setState(rs.getString("FC_VOL_STATE"));
                volunteer.setCountry(rs.getString("FC_VOL_COUNTRY"));
                volunteer.setZip(rs.getString("FC_VOL_ZIP"));
                volunteer.setActive(rs.getInt("FC_USER_ACTIVE"));
                volunteer.setVerified(rs.getInt("FC_USER_VERIFIED"));
                volunteer.setAddress1(rs.getString("FC_VOL_STREET_NUMBER_1"));
                volunteer.setAddress1(rs.getString("FC_VOL_STREET_NUMBER_2"));
                return volunteer;
            }, zip);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Boolean updateVolunteerInfo(User volunteer) {
        return jdbcTemplate.update(UPDATE_VOLUNTEER_CRED, volunteer.getUsername(), volunteer.getPassword()) > 0;
    }

    @Override
    public Boolean isContactVolUnique(String type, String value) {
        try {
            Integer count = jdbcTemplate.queryForObject(type.equals("email") ? VALIDATE_UNIQUE_EMAIL : VALIDATE_UNIQUE_PHONE, new Object[]{value}, Integer.class);
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

    /*public void insertIntoVolunteerHelpCategories(User volunteer, Integer volunteerCredId) {
        List<HelpCategory> pendingHelpCategories = getPendingHelpCategories();
        Map<String, Integer> tempMap = new HashMap<>();
        for (HelpCategory helpCategory : pendingHelpCategories) {
            tempMap.put(helpCategory.getHelpCategoryText(), helpCategory.getHelpCategoryId());
        }
        for (HelpCategory helpCategory : volunteer.getHelpCategoryList()) {
            jdbcTemplate.update(INSERT_VOL_CRED_HELP_CAT, volunteerCredId, tempMap.getOrDefault(helpCategory.getHelpCategoryText(), 0000));
        }
    }*/


}
