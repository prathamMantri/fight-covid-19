package com.pmantri.fightcovid19.models;

public class UserCredentials {
    Integer userCredId;
    String username;
    String password;


    public Integer getUserCredId() {
        return userCredId;
    }

    public void setUserCredId(Integer userCredId) {
        this.userCredId = userCredId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserCredentials{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
