package com.pmantri.fightcovid19.api;


import com.pmantri.fightcovid19.services.HelpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class HelpApiController {

    @Autowired
    HelpService helpService;

    @GetMapping("/gethelpcategories")
    public ResponseEntity getVolunteers(@RequestParam Map<String, Object> params) {
        Object responseBody = new LinkedHashMap<>();
        responseBody = helpService.getHelpCategories();
        return ResponseEntity.ok(responseBody);


    }


}
