package com.pmantri.fightcovid19.services;

import com.pmantri.fightcovid19.models.HelpCategory;

import java.util.List;

public interface HelpService {
    Boolean insertHelpCategory(HelpCategory helpCategory);
    List<HelpCategory> getHelpCategories();
}
