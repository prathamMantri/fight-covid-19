package com.pmantri.fightcovid19.dao;

import com.pmantri.fightcovid19.models.HelpCategory;

import java.util.List;

public interface HelpDao {
    List<HelpCategory> getHelpCategories();
    Boolean insertHelpCategory(HelpCategory helpCategory);
}
