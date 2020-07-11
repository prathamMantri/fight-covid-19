package com.pmantri.fightcovid19.dao;

import com.pmantri.fightcovid19.models.ApplicationConstants;
import com.pmantri.fightcovid19.models.ERole;
import com.pmantri.fightcovid19.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserDaoImpl implements UserDao {

    private static final String INSERT_VOLUNTEER_CRED = "INSERT INTO FC_USER_CREDENTIALS (" +
            "FC_USER_FIRSTNAME, " +
            "FC_USER_LASTNAME, " +
            "FC_USER_USERNAME, " +
            "FC_USER_PASSWORD, " +
            "FC_USER_ACTIVE, " +
            "FC_USER_VERIFIED) " +
            "VALUES(?, ?, ?, ?, ?, ?)";

    private static final String VALIDATE_UNIQUE_USERNAME = "select count(*) from FC_USER_CREDENTIALS where FC_VOL_USERNAME = ? \n" +
            "and ROLE_ID = (select FC_ROLE_ID from FC_USER_ROLES where FC_ROLE = ?)";

    private static final String GET_USER_ID = "SELECT FC_USER_ID FROM FC_USER_CREDENTIALS WHERE FC_USER_USERNAME = ?";
    private static final String GET_USER = "SELECT * FROM FC_USER_CREDENTIALS WHERE FC_USER_USERNAME = ?";
    private static final String GET_USER_TYPE = "SELECT * FROM " +
            "FC_USER_CREDENTIALS UC, " +
            "FC_USER_ROLES UR " +
            "WHERE UC.FC_USER_USERNAME = ? " +
            "AND UR.FC_ROLE = ?" +
            "AND UR.FC_ROLE_ID = UC.ROLE_ID ";

    private static final String INSERT_VOLUNTEER_CONTACT = "INSERT INTO FC_VOLUNTEER_CONTACT (" +
            "USER_ID, " +
            "FC_VOL_PHONE_NUMBER_1, " +
            "FC_VOL_EMAIL_ADDR_1) " +
            "VALUES(?, ?, ?)";

    private static final String INSERT_VOLUNTEER_ADDRESS = "INSERT INTO FC_VOLUNTEER_ADDRESS (" +
            "USER_ID, " +
            "FC_VOL_STREET_NUMBER_1, " +
            "FC_VOL_STREET_NUMBER_2, " +
            "FC_VOL_CITY, " +
            "FC_VOL_STATE, " +
            "FC_VOL_COUNTRY, " +
            "FC_VOL_ZIP )" +
            "VALUES(?, ?, ?, ?, ?, ?, ?)";

    private static final String INSERT_REQUESTER_CONTACT = "INSERT INTO FC_REQUESTER_CONTACT (" +
            "REQ_CRED_ID, " +
            "FC_REQ_PHONE_NUMBER_1, " +
            "FC_REQ_EMAIL_ADDR_1) " +
            "VALUES(?, ?, ?)";


    private static final String INSERT_REQUESTER_ADDRESS = "INSERT INTO FC_REQUESTER_ADDRESS (" +
            "REQ_CRED_ID, " +
            "FC_REQ_STREET_NUMBER_1, " +
            "FC_REQ_STREET_NUMBER_2, " +
            "FC_REQ_CITY, " +
            "FC_REQ_STATE, " +
            "FC_REQ_COUNTRY, " +
            "FC_REQ_ZIP )" +
            "VALUES(?, ?, ?, ?, ?, ?, ?)";

    private static final String INSERT_USER_ROLE = "INSERT INTO FC_USER_ROLES(" +
            "USER_ID, " +
            "ROLE_ID )" +
            "VALUES (?, ?)";


    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Optional<User> findByUsername(String username, String role) {

        try {
            return jdbcTemplate.queryForObject(GET_USER_TYPE, new Object[]{username, role}, (rs, rowNum) -> {
                User user = new User();
                user.setUserId(rs.getLong("FC_USER_ID"));
                user.setFirstName(rs.getString("FC_USER_FIRSTNAME"));
                user.setLastName(rs.getString("FC_USER_LASTNAME"));
                user.setUsername(rs.getString("FC_USER_USERNAME"));
                //user.setRoles(rs.getInt("ROLE_ID"));
                user.setActive(rs.getInt("FC_USER_ACTIVE"));
                user.setVerified(rs.getInt("FC_USER_VERIFIED"));
                return Optional.of(user);
            });
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Boolean isUserUnique(String username, String type) {
        try {
            Integer count = jdbcTemplate.queryForObject(VALIDATE_UNIQUE_USERNAME, new Object[]{username, type}, Integer.class);
            return count < 1;
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
        }
        return true;
    }


    @Override
    public Optional<User> findByUsername(String username) {
        try {
            return jdbcTemplate.queryForObject(GET_USER, new Object[]{username}, (rs, rowNum) -> {
                User user = new User();
                user.setUserId(rs.getLong("FC_USER_ID"));
                user.setPassword(rs.getString("FC_USER_PASSWORD"));
                user.setFirstName(rs.getString("FC_USER_FIRSTNAME"));
                user.setLastName(rs.getString("FC_USER_LASTNAME"));
                user.setUsername(rs.getString("FC_USER_USERNAME"));
                //user.setRoles(rs.getInt("ROLE_ID"));
                user.setActive(rs.getInt("FC_USER_ACTIVE"));
                user.setVerified(rs.getInt("FC_USER_VERIFIED"));
                return Optional.of(user);
            });
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Boolean existsByUsername(String username) {
        Long userId = jdbcTemplate.queryForObject(
                GET_USER_ID, new Object[]{username}, Long.class);
        return userId != null;
    }

    @Override
    public boolean registerUser(User user) {
        return jdbcTemplate.update(INSERT_VOLUNTEER_CRED,
                user.getFirstName(),
                user.getLastName(),
                user.getUsername(),
                user.getPassword(),
                user.getActive(),
                user.getVerified()) > 0;
    }

    @Override
    public Long getUserId(String username) {
        try {
            return jdbcTemplate.queryForObject(
                    GET_USER_ID, new Object[]{username}, Long.class);
        } catch (DataAccessException ere) {
            System.out.println(ere.getMessage());
            return -1L;
        }
    }

    @Override
    public boolean addRole(String userType, Long userId) {
        if (userType != null)
            return jdbcTemplate.update(INSERT_USER_ROLE,
                    userId,
                    userType.equals(ERole.ROLE_VOLUNTEER.getMessage()) ? ERole.ROLE_VOLUNTEER.getCode() : ERole.ROLE_REQUESTER.getCode()) > 0;
        else
            return false;
    }

    @Override
    public boolean updateContact(User user, String userType, Long userId) {
        if (userType != null && userType.equals(ApplicationConstants.VOLUNTEER))
            return updateVolunteerContact(user, userId);
        else
            return updateRequesterContact(user, userId);
    }

    @Override
    public boolean updateAddress(User user, String userType, Long userId) {
        if (userType != null && userType.equals(ApplicationConstants.VOLUNTEER))
            return updateVolunteerAddress(user, userId);
        else
            return updateRequesterAddress(user, userId);
    }

    Boolean updateVolunteerContact(User volunteer, Long volunteerId) {
        if (volunteerId != -1L) {
            jdbcTemplate.update(INSERT_VOLUNTEER_CONTACT, volunteerId,
                    volunteer.getPhoneNumber(),
                    volunteer.getEmailAddress());
            return true;
        } else {
            return false;
        }
    }

    Boolean updateVolunteerAddress(User volunteer, Long volunteerId) {
        if (volunteerId != -1L) {
            jdbcTemplate.update(INSERT_VOLUNTEER_ADDRESS,
                    volunteerId,
                    volunteer.getAddress1(),
                    volunteer.getAddress2(),
                    volunteer.getCity(),
                    volunteer.getState(),
                    volunteer.getCountry(),
                    volunteer.getZip());
            return true;

        } else {
            return false;
        }
    }

    Boolean updateRequesterContact(User volunteer, Long requesterId) {
        if (requesterId != -1L) {
            jdbcTemplate.update(INSERT_REQUESTER_CONTACT, requesterId,
                    volunteer.getPhoneNumber(),
                    volunteer.getEmailAddress());
            return true;
        } else {
            return false;
        }
    }

    Boolean updateRequesterAddress(User volunteer, Long requesterId) {
        if (requesterId != -1L) {
            jdbcTemplate.update(INSERT_REQUESTER_ADDRESS,
                    requesterId,
                    volunteer.getAddress1(),
                    volunteer.getAddress2(),
                    volunteer.getCity(),
                    volunteer.getState(),
                    volunteer.getCountry(),
                    volunteer.getZip());
            return true;
        } else {
            return false;
        }
    }
}
