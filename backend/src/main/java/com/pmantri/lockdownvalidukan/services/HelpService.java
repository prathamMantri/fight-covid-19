package com.pmantri.lockdownvalidukan.services;

import com.pmantri.lockdownvalidukan.models.HelpCategory;

import java.util.List;

public interface HelpService {
    Boolean insertHelpCategory(HelpCategory helpCategory);
    List<HelpCategory> getHelpCategories();
}
