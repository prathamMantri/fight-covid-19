package com.pmantri.lockdownvalidukan.models;

public class HelpSubCategories {

    String helpSubCategoryId;
    String helpCategoryId;
    String helpSubCategoryText;
    String helpSubCategoryDesc;
    String helpSubCategoryStatus;
    String helpSubCategoryVisibility;

    public String getHelpSubCategoryId() {
        return helpSubCategoryId;
    }

    public void setHelpSubCategoryId(String helpSubCategoryId) {
        this.helpSubCategoryId = helpSubCategoryId;
    }

    public String getHelpCategoryId() {
        return helpCategoryId;
    }

    public void setHelpCategoryId(String helpCategoryId) {
        this.helpCategoryId = helpCategoryId;
    }

    public String getHelpSubCategoryText() {
        return helpSubCategoryText;
    }

    public void setHelpSubCategoryText(String helpSubCategoryText) {
        this.helpSubCategoryText = helpSubCategoryText;
    }

    public String getHelpSubCategoryDesc() {
        return helpSubCategoryDesc;
    }

    public void setHelpSubCategoryDesc(String helpSubCategoryDesc) {
        this.helpSubCategoryDesc = helpSubCategoryDesc;
    }

    public String getHelpSubCategoryStatus() {
        return helpSubCategoryStatus;
    }

    public void setHelpSubCategoryStatus(String helpSubCategoryStatus) {
        this.helpSubCategoryStatus = helpSubCategoryStatus;
    }

    public String getHelpSubCategoryVisibility() {
        return helpSubCategoryVisibility;
    }

    public void setHelpSubCategoryVisibility(String helpSubCategoryVisibility) {
        this.helpSubCategoryVisibility = helpSubCategoryVisibility;
    }

    @Override
    public String toString() {
        return "HelpSubCategories{" +
                "helpSubCategoryId='" + helpSubCategoryId + '\'' +
                ", helpCategoryId='" + helpCategoryId + '\'' +
                ", helpSubCategoryText='" + helpSubCategoryText + '\'' +
                ", helpSubCategoryDesc='" + helpSubCategoryDesc + '\'' +
                ", helpSubCategoryStatus='" + helpSubCategoryStatus + '\'' +
                ", helpSubCategoryVisibility='" + helpSubCategoryVisibility + '\'' +
                '}';
    }
}
