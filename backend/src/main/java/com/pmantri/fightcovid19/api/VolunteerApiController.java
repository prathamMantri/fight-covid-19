package com.pmantri.fightcovid19.api;


import com.pmantri.fightcovid19.models.UserCredentials;
import com.pmantri.fightcovid19.models.Volunteer;
import com.pmantri.fightcovid19.services.VolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class VolunteerApiController {

    @Autowired
    VolunteerService volunteerService;

    @RequestMapping(value = "/insertupdateuser", method = RequestMethod.POST)
    public ResponseEntity insertUser(
            @RequestBody Map<String, Object> headers) {

        Object responseBody = new LinkedHashMap<>();

        if (headers.get("username") != null && !(headers.get("username")).equals("")) {
            Volunteer volunteer = new Volunteer();
            UserCredentials userCredentials = new UserCredentials();
            userCredentials.setUsername((String) headers.get("username"));
            userCredentials.setPassword((String) headers.get("password"));
            volunteer.setUserCredentials(userCredentials);
            volunteer.setFirstName((String) headers.get("firstName"));
            volunteer.setLastName((String) headers.get("lastName"));
            volunteer.setPhoneNumber((String) headers.get("phoneNumber"));
            volunteer.setEmailAddress((String) headers.get("email"));
            volunteer.setAddress((String) headers.get("address"));
            volunteer.setCity((String) headers.get("city"));
            volunteer.setState((String) headers.get("state"));
            volunteer.setCountry((String) headers.get("country"));
            volunteer.setZip((String) headers.get("zip"));
            volunteer.setActive(1);
            volunteerService.insertUpdateVolunteer(volunteer, (Boolean) headers.get("terms"));
        } else {
            Map errors = new HashMap();
            errors.put("notFound", "Wrong input");
            responseBody = new LinkedHashMap<>();
            ((HashMap) responseBody).put("errors", errors);
            return new ResponseEntity((Object) responseBody, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(responseBody);
    }

    @PostMapping("/checkUniqueVolunteer")
    public Boolean validateUniqueVolunteer(@RequestBody(required = false) Map params) {
        return volunteerService.validateUniqueVolunteer((String) params.get("type"), (String) params.get("value"));
    }

    @GetMapping("/getVolunteers")
    public ResponseEntity getVolunteers(@RequestBody(required = false) Map params) {
        Object responseBody = new LinkedHashMap<>();
        if (params.get("zip") != null) {
            responseBody = volunteerService.getVolunteers((String) params.get("zip"));
        } else {
            Map errors = new HashMap();
            errors.put("notFound", "Wrong input");
            responseBody = new LinkedHashMap<>();
            ((HashMap) responseBody).put("errors", errors);
            return new ResponseEntity((Object) responseBody, HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(responseBody);


    }


}
