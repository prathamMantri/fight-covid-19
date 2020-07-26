package com.pmantri.lockdownvalidukan.services;

import com.pmantri.lockdownvalidukan.dao.HelpDao;
import com.pmantri.lockdownvalidukan.models.HelpCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HelpServiceImpl implements HelpService {

    @Autowired
    HelpDao helpDao;

    @Override
    public Boolean insertHelpCategory(HelpCategory helpCategory) {
        return helpDao.insertHelpCategory(helpCategory);
    }

    @Override
    public List<HelpCategory> getHelpCategories() {
        return helpDao.getHelpCategories();
    }

}
