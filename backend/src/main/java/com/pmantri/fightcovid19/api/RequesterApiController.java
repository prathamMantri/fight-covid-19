package com.pmantri.fightcovid19.api;

import com.pmantri.fightcovid19.models.Request;
import com.pmantri.fightcovid19.services.RequesterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class RequesterApiController {


    @Autowired
    RequesterService requestService;


    @RequestMapping(value = "/postrequest", method = RequestMethod.POST)
    public ResponseEntity postRequest(
            @RequestBody Map<String, Object> headers) {

        Object responseBody = new LinkedHashMap<>();

        if (headers.get("username") != null && !(headers.get("username")).equals("")) {
            Request request = new Request();
            //requestService.postRequest(request);

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
