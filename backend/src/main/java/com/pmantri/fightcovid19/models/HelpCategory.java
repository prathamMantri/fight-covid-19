package com.pmantri.fightcovid19.models;

public class HelpCategory {

    Integer helpCategoryId;
    String helpCategoryText;
    String helpCategoryDesc;
    String helpCategoryStatus;
    Integer helpCategoryVisibility;
    HelpSubCategories helpSubCategories;

    public Integer getHelpCategoryId() {
        return helpCategoryId;
    }

    public void setHelpCategoryId(Integer helpCategoryId) {
        this.helpCategoryId = helpCategoryId;
    }

    public String getHelpCategoryText() {
        return helpCategoryText;
    }

    public void setHelpCategoryText(String helpCategoryText) {
        this.helpCategoryText = helpCategoryText;
    }

    public String getHelpCategoryDesc() {
        return helpCategoryDesc;
    }

    public void setHelpCategoryDesc(String helpCategoryDesc) {
        this.helpCategoryDesc = helpCategoryDesc;
    }

    public String getHelpCategoryStatus() {
        return helpCategoryStatus;
    }

    public void setHelpCategoryStatus(String helpCategoryStatus) {
        this.helpCategoryStatus = helpCategoryStatus;
    }

    public Integer getHelpCategoryVisibility() {
        return helpCategoryVisibility;
    }

    public void setHelpCategoryVisibility(Integer helpCategoryVisibility) {
        this.helpCategoryVisibility = helpCategoryVisibility;
    }

    public HelpSubCategories getHelpSubCategories() {
        return helpSubCategories;
    }

    public void setHelpSubCategories(HelpSubCategories helpSubCategories) {
        this.helpSubCategories = helpSubCategories;
    }

    @Override
    public String toString() {
        return "HelpCategories{" +
                "helpCategoryId='" + helpCategoryId + '\'' +
                ", helpCategoryText='" + helpCategoryText + '\'' +
                ", helpCategoryDesc='" + helpCategoryDesc + '\'' +
                ", helpCategoryStatus='" + helpCategoryStatus + '\'' +
                ", helpCategoryVisibility='" + helpCategoryVisibility + '\'' +
                ", helpSubCategories=" + helpSubCategories +
                '}';
    }
}
