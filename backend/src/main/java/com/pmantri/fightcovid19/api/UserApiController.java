package com.pmantri.fightcovid19.api;

import com.pmantri.fightcovid19.models.User;
import com.pmantri.fightcovid19.services.RequesterService;
import com.pmantri.fightcovid19.services.UserService;
import com.pmantri.fightcovid19.services.VolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


@RestController
@RequestMapping("/api")
public class UserApiController {
    @Autowired
    VolunteerService volunteerService;

    @Autowired
    RequesterService requesterService;

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder encoder;

    @RequestMapping(value = "/registeruser", method = RequestMethod.POST)
    public ResponseEntity registerVolunteer(
            @RequestBody Map<String, Object> headers) {

        Object responseBody = new LinkedHashMap<>();

        if (headers.get("username") != null && !(headers.get("username")).equals("")) {

            User user = new User();
            user.setUsername((String) headers.get("username"));
            user.setPassword(encoder.encode((String) headers.get("password")));
            user.setFirstName((String) headers.get("firstName"));
            user.setLastName((String) headers.get("lastName"));
            user.setPhoneNumber((String) headers.get("phoneNumber"));
            user.setEmailAddress((String) headers.get("email"));
            user.setAddress1((String) headers.get("address1"));
            user.setAddress1((String) headers.get("address2"));
            user.setCity((String) headers.get("city"));
            user.setState((String) headers.get("state"));
            user.setCountry((String) headers.get("country"));
            user.setZip((String) headers.get("zip"));
            user.setActive(1);
            user.setVerified(0);

            String userType = (String) headers.get("userType");
            userService.registerUser(user, userType);

        } else {
            Map errors = new HashMap();
            errors.put("notFound", "Wrong input");
            responseBody = new LinkedHashMap<>();
            ((HashMap) responseBody).put("errors", errors);
            return new ResponseEntity(responseBody, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(responseBody);
    }

    @PostMapping("/checkUniqueUser")
    public Boolean validateUniqueVolunteer(@RequestBody(required = false) Map params) {
        return userService.isUserUnique((String) params.get("value"), (String) params.get("type"));
    }

}
