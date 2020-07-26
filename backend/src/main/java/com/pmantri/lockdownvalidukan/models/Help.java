package com.pmantri.lockdownvalidukan.models;

public class Help {

    HelpCategory helpCategory;
    HelpSubCategories helpSubCategories;

    public HelpCategory getHelpCategory() {
        return helpCategory;
    }

    public void setHelpCategory(HelpCategory helpCategory) {
        this.helpCategory = helpCategory;
    }

    public HelpSubCategories getHelpSubCategories() {
        return helpSubCategories;
    }

    public void setHelpSubCategories(HelpSubCategories helpSubCategories) {
        this.helpSubCategories = helpSubCategories;
    }

    @Override
    public String toString() {
        return "Help{" +
                "helpCategories=" + helpCategory +
                ", helpSubCategories=" + helpSubCategories +
                '}';
    }
}
