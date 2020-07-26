package com.pmantri.lockdownvalidukan.models;

import java.sql.Timestamp;

public class Request {

    String requestId;
    String requesterId;
    String requesterType;
    String requestDescription;
    String requestVerified;
    String requestStatus;
    Timestamp requestCreatedDttm;
    Timestamp requestStartedDttm;
    Timestamp requestModifiedDttm;
    Timestamp requestResolvedDttm;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getRequesterId() {
        return requesterId;
    }

    public void setRequesterId(String requesterId) {
        this.requesterId = requesterId;
    }

    public String getRequesterType() {
        return requesterType;
    }

    public void setRequesterType(String requesterType) {
        this.requesterType = requesterType;
    }

    public String getRequestDescription() {
        return requestDescription;
    }

    public void setRequestDescription(String requestDescription) {
        this.requestDescription = requestDescription;
    }

    public String getRequestVerified() {
        return requestVerified;
    }

    public void setRequestVerified(String requestVerified) {
        this.requestVerified = requestVerified;
    }

    public String getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }

    public Timestamp getRequestCreatedDttm() {
        return requestCreatedDttm;
    }

    public void setRequestCreatedDttm(Timestamp requestCreatedDttm) {
        this.requestCreatedDttm = requestCreatedDttm;
    }

    public Timestamp getRequestStartedDttm() {
        return requestStartedDttm;
    }

    public void setRequestStartedDttm(Timestamp requestStartedDttm) {
        this.requestStartedDttm = requestStartedDttm;
    }

    public Timestamp getRequestModifiedDttm() {
        return requestModifiedDttm;
    }

    public void setRequestModifiedDttm(Timestamp requestModifiedDttm) {
        this.requestModifiedDttm = requestModifiedDttm;
    }

    public Timestamp getRequestResolvedDttm() {
        return requestResolvedDttm;
    }

    public void setRequestResolvedDttm(Timestamp requestResolvedDttm) {
        this.requestResolvedDttm = requestResolvedDttm;
    }

    @Override
    public String toString() {
        return "Request{" +
                "requestId='" + requestId + '\'' +
                ", requesterId='" + requesterId + '\'' +
                ", requesterType='" + requesterType + '\'' +
                ", requestDescription='" + requestDescription + '\'' +
                ", requestVerified='" + requestVerified + '\'' +
                ", requestStatus='" + requestStatus + '\'' +
                ", requestCreatedDttm=" + requestCreatedDttm +
                ", requestStartedDttm=" + requestStartedDttm +
                ", requestModifiedDttm=" + requestModifiedDttm +
                ", requestResolvedDttm=" + requestResolvedDttm +
                '}';
    }
}
