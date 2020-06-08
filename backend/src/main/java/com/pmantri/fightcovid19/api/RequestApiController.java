package com.pmantri.fightcovid19.api;

import com.pmantri.fightcovid19.models.Request;
import com.pmantri.fightcovid19.models.Requester;
import com.pmantri.fightcovid19.models.RequesterCredentials;
import com.pmantri.fightcovid19.services.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class RequestApiController {


    @Autowired
    RequestService requestService;


    @RequestMapping(value = "/postrequest", method = RequestMethod.POST)
    public ResponseEntity postRequest(
            @RequestBody Map<String, Object> headers) {

        Object responseBody = new LinkedHashMap<>();

        if (headers.get("username") != null && !(headers.get("username")).equals("")) {

            Requester requester = new Requester();
            Request request = new Request();
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

            RequesterCredentials requesterCredentials = new RequesterCredentials();
            requesterCredentials.setRequesterUserName((String) headers.get("username"));
            requesterCredentials.setRequesterPassword((String) headers.get("password"));
            requester.setRequesterCredentials(requesterCredentials);
            requester.setFirstName((String) headers.get("firstName"));
            requester.setLastName((String) headers.get("lastName"));
            requester.setPhoneNumber((String) headers.get("phoneNumber"));
            requester.setEmailAddress((String) headers.get("email"));
            requester.setAddress1((String) headers.get("address1"));
            requester.setAddress1((String) headers.get("address2"));
            requester.setCity((String) headers.get("city"));
            requester.setState((String) headers.get("state"));
            requester.setCountry((String) headers.get("country"));
            requester.setZip((String) headers.get("zip"));
            requester.setActive(1);
            requester.setVerified(0);
            //  volunteer.setHelpCategoryList(helpCategoryList);
            requestService.postRequest(requester, request);

        } else {
            Map errors = new HashMap();
            errors.put("notFound", "Wrong input");
            responseBody = new LinkedHashMap<>();
            ((HashMap) responseBody).put("errors", errors);
            return new ResponseEntity((Object) responseBody, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(responseBody);
    }

    @PostMapping("/checkUniqueRequester")
    public Boolean validateUniqueVolunteer(@RequestBody(required = false) Map params) {
        return requestService.validateUniqueVolunteer((String) params.get("type"), (String) params.get("value"));
    }


}
