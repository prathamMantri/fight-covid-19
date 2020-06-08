package com.pmantri.fightcovid19.api;


import com.pmantri.fightcovid19.models.VolunteerCredentials;
import com.pmantri.fightcovid19.models.Volunteer;
import com.pmantri.fightcovid19.services.HelpService;
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
    @Autowired
    HelpService helpService;

    @RequestMapping(value = "/registervolunteer", method = RequestMethod.POST)
    public ResponseEntity registerVolunteer(
            @RequestBody Map<String, Object> headers) {

        Object responseBody = new LinkedHashMap<>();

        if (headers.get("username") != null && !(headers.get("username")).equals("")) {

            Volunteer volunteer = new Volunteer();
          /*  List<LinkedHashMap<String, Object>> helpCategorySelected = (List<LinkedHashMap<String, Object>>) headers.get("helpCategorySelected");
            HelpCategory helpCategory;
            List<HelpCategory> helpCategoryList = new ArrayList<>();

            for(LinkedHashMap<String, Object> temp: helpCategorySelected){
                    helpCategory = new HelpCategory();
                    helpCategory.setHelpCategoryText((String)temp.get("helpCategoryText"));
                    helpCategory.setHelpCategoryDesc((String)temp.get("helpCategoryDesc"));
                    helpCategory.setHelpCategoryStatus((String)temp.get("helpCategoryStatus"));
                    helpCategory.setHelpCategoryVisibility((Integer)temp.get("helpCategoryVisibility"));
                    helpCategoryList.add(helpCategory);
                    helpService.insertHelpCategory(helpCategory);
            }*/

            VolunteerCredentials volunteerCredentials = new VolunteerCredentials();
            volunteerCredentials.setVolunteerUsername((String) headers.get("username"));
            volunteerCredentials.setVolunteerPassword((String) headers.get("password"));
            volunteer.setVolunteerCredentials(volunteerCredentials);
            volunteer.setFirstName((String) headers.get("firstName"));
            volunteer.setLastName((String) headers.get("lastName"));
            volunteer.setPhoneNumber((String) headers.get("phoneNumber"));
            volunteer.setEmailAddress((String) headers.get("email"));
            volunteer.setAddress1((String) headers.get("address1"));
            volunteer.setAddress1((String) headers.get("address2"));
            volunteer.setCity((String) headers.get("city"));
            volunteer.setState((String) headers.get("state"));
            volunteer.setCountry((String) headers.get("country"));
            volunteer.setZip((String) headers.get("zip"));
            volunteer.setActive(1);
            volunteer.setVerified(0);
            //  volunteer.setHelpCategoryList(helpCategoryList);
            volunteerService.registerVolunteer(volunteer, (Boolean) headers.get("terms"));

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
