package com.pmantri.lockdownvalidukan.dao;

import com.pmantri.lockdownvalidukan.models.Request;
import com.pmantri.lockdownvalidukan.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RequestDaoImpl implements RequesterDao {


    private static final String GET_REQUESTER_CRED_ID = "SELECT FC_REQ_CRED_ID FROM FC_REQUESTER_CREDENTIALS WHERE FC_REQ_CRED_USERNAME = ?";

    private static final String INSERT_REQUESTER_CRED = "INSERT INTO FC_REQUESTER_CREDENTIALS (" +
            "FC_REQ_CRED_FIRSTNAME, " +
            "FC_REQ_CRED_LASTNAME, " +
            "FC_REQ_CRED_USERNAME, " +
            "FC_REQ_CRED_PASSWORD, " +
            "FC_REQ_CRED_ACTIVE, " +
            "FC_REQ_CRED_VERIFIED) " +
            "VALUES(?,?,?, sha1(?), ?, ?)";



    private static final String INSERT_REQUEST_DETAILS = "INSERT INTO FC_REQUEST_DETAILS(" +
            "FC_REQUESTER_ID, " +
            "FC_REQUEST_TYPE, " +
            "FC_REQUEST_DESCRIPTION, " +
            "FC_REQUEST_VERIFIED, " +
            "FC_REQUEST_STATUS, " +
            "FC_REQUEST_CREATED_DTTM, " +
            "FC_REQUEST_STARTED_DTTM, " +
            "FC_REQUEST_MODIFIED_DTTM, " +
            "FC_REQUEST_RESOLVED_DTTM )" +
            "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String VALIDATE_UNIQUE_EMAIL = "select count(*) from FC_REQUESTER_CONTACT where FC_REQ_EMAIL_ADDR_1 = ?";
    private static final String VALIDATE_UNIQUE_PHONE = "select count(*) from FC_REQUESTER_CONTACT where FC_REQ_PHONE_NUMBER_1 = ?";
    private static final String VALIDATE_UNIQUE_USERNAME = "select count(*) from FC_REQUESTER_CREDENTIALS where FC_REQ_CRED_USERNAME = ?";


    @Autowired
    JdbcTemplate jdbcTemplate;


    @Autowired
    UserDao userDao;

    @Override
    public void postRequest(User requester, Request request) {
            jdbcTemplate.update(INSERT_REQUEST_DETAILS, requester.getUserId(),
                    request.getRequesterType(),
                    request.getRequestDescription(),
                    request.getRequestVerified(),
                    request.getRequestStatus(),
                    request.getRequestCreatedDttm(),
                    request.getRequestStartedDttm(),
                    request.getRequestModifiedDttm(),
                    request.getRequestResolvedDttm());
            //insertIntoVolunteerHelpCategories(volunteer, volunteerDetId);
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
