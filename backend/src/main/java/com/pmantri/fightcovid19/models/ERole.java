package com.pmantri.fightcovid19.models;

public enum ERole {
    ROLE_VOLUNTEER(5005, "VOLUNTEER"),
    ROLE_REQUESTER(5006, "REQUESTER"),
    ROLE_ADMIN(5000, "ADMIN");

    private final Integer code;
    private final String message;

    ERole(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
