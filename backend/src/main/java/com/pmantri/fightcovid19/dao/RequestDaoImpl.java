package com.pmantri.fightcovid19.dao;

import com.pmantri.fightcovid19.models.Request;
import com.pmantri.fightcovid19.models.Requester;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RequestDaoImpl implements RequestDao {


    private static final String GET_REQUESTER_CRED_ID = "SELECT FC_REQ_CRED_ID FROM FC_REQUESTER_CREDENTIALS WHERE FC_REQ_CRED_USERNAME = ?";

    private static final String INSERT_REQUESTER_CRED = "INSERT INTO FC_REQUESTER_CREDENTIALS (" +
            "FC_REQ_CRED_FIRSTNAME, " +
            "FC_REQ_CRED_LASTNAME, " +
            "FC_REQ_CRED_USERNAME, " +
            "FC_REQ_CRED_PASSWORD, " +
            "FC_REQ_CRED_ACTIVE, " +
            "FC_REQ_CRED_VERIFIED) " +
            "VALUES(?,?,?, sha1(?), ?, ?)";


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

    @Override
    public void postRequest(Requester requester, Request request) {

        boolean userCredInserted = jdbcTemplate.update(INSERT_REQUESTER_CRED,
                requester.getFirstName(),
                requester.getLastName(),
                requester.getRequesterCredentials().getRequesterUserName(),
                requester.getRequesterCredentials().getRequesterPassword(),
                requester.getActive(),
                requester.getVerified()) > 0;

        if (userCredInserted) {
            Integer volunteerCredId = jdbcTemplate.queryForObject(
                    GET_REQUESTER_CRED_ID, new Object[]{requester.getRequesterCredentials().getRequesterUserName()}, Integer.class);

            jdbcTemplate.update(INSERT_REQUESTER_ADDRESS,
                    volunteerCredId,
                    requester.getAddress1(),
                    requester.getAddress2(),
                    requester.getCity(),
                    requester.getState(),
                    requester.getCountry(),
                    requester.getZip());

            jdbcTemplate.update(INSERT_REQUESTER_CONTACT, volunteerCredId,
                    requester.getPhoneNumber(),
                    requester.getEmailAddress());

            jdbcTemplate.update(INSERT_REQUEST_DETAILS, volunteerCredId,
                    request.getRequesterType(),
                    request.getRequestDescription(),
                    request.getRequestVerified(),
                    request.getRequestStatus(),
                    request.getRequestCreatedDttm(),
                    request.getRequestStartedDttm(),
                    request.getRequestModifiedDttm(),
                    request.getRequestResolvedDttm());

        }
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
