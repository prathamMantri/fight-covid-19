package com.pmantri.fightcovid19.models;

import java.util.List;

public class Requester {

    RequesterCredentials RequesterCredentials;
    String firstName;
    String lastName;
    String phoneNumber;
    String emailAddress;
    String address1;
    String address2;
    String city;
    String state;
    String country;
    String zip;
    List<HelpCategory> helpCategoryList;
    Integer active;
    Integer verified;

    public RequesterCredentials getRequesterCredentials() {
        return RequesterCredentials;
    }

    public void setRequesterCredentials(RequesterCredentials volunteerCredentials) {
        this.RequesterCredentials = volunteerCredentials;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public List<HelpCategory> getHelpCategoryList() {
        return helpCategoryList;
    }

    public void setHelpCategoryList(List<HelpCategory> helpCategoryList) {
        this.helpCategoryList = helpCategoryList;
    }

    public Integer getVerified() {
        return verified;
    }

    public void setVerified(Integer verified) {
        this.verified = verified;
    }

    @Override
    public String toString() {
        return "Requester{" +
                "requesterCredentials=" + RequesterCredentials +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", address='" + address1 + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", zip='" + zip + '\'' +
                '}';
    }
}
