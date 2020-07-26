package com.pmantri.lockdownvalidukan.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

public class Session {

    @GetMapping("/user/logout")
    public @ResponseBody
    String userLogout() {

        return "logout";
    }


}
