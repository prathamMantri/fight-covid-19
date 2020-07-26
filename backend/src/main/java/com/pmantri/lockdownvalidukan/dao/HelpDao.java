package com.pmantri.lockdownvalidukan.dao;

import com.pmantri.lockdownvalidukan.models.HelpCategory;

import java.util.List;

public interface HelpDao {
    List<HelpCategory> getHelpCategories();
    Boolean insertHelpCategory(HelpCategory helpCategory);
}
