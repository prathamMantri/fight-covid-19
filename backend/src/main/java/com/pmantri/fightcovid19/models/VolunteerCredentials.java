package com.pmantri.fightcovid19.models;

public class VolunteerCredentials {
    Integer volunteerCredId;
    String volunteerUsername;
    String volunteerPassword;


    public Integer getVolunteerCredId() {
        return volunteerCredId;
    }

    public void setVolunteerCredId(Integer volunteerCredId) {
        this.volunteerCredId = volunteerCredId;
    }

    public String getVolunteerUsername() {
        return volunteerUsername;
    }

    public void setVolunteerUsername(String volunteerUsername) {
        this.volunteerUsername = volunteerUsername;
    }

    public String getVolunteerPassword() {
        return volunteerPassword;
    }

    public void setVolunteerPassword(String volunteerPassword) {
        this.volunteerPassword = volunteerPassword;
    }

    @Override
    public String toString() {
        return "UserCredentials{" +
                "username='" + volunteerUsername + '\'' +
                '}';
    }
}
