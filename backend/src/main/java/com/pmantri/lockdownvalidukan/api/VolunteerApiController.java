package com.pmantri.lockdownvalidukan.api;


import com.pmantri.lockdownvalidukan.services.HelpService;
import com.pmantri.lockdownvalidukan.services.VolunteerService;
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
    @Autowired
    HelpService helpService;

    @PostMapping("/checkUniqueVolunteer")
    public Boolean validateUniqueVolunteer(@RequestBody(required = false) Map params) {
        return volunteerService.validateUniqueVolunteer((String) params.get("type"), (String) params.get("value"));
    }

    @GetMapping("/getvolunteers")
    public ResponseEntity getVolunteers(@RequestParam(required = false) Map params) {
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
