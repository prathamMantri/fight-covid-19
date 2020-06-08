package com.pmantri.fightcovid19.models;

public class RequesterCredentials {
    Integer requesterCredId;
    String requesterUserName;
    String requesterPassword;

    public Integer getRequesterCredId() {
        return requesterCredId;
    }

    public void setRequesterCredId(Integer requesterCredId) {
        this.requesterCredId = requesterCredId;
    }

    public String getRequesterUserName() {
        return requesterUserName;
    }

    public void setRequesterUserName(String requesterUserName) {
        this.requesterUserName = requesterUserName;
    }

    public String getRequesterPassword() {
        return requesterPassword;
    }

    public void setRequesterPassword(String requesterPassword) {
        this.requesterPassword = requesterPassword;
    }

    @Override
    public String toString() {
        return "UserCredentials{" +
                "username='" + requesterUserName + '\'' +
                '}';
    }
}
