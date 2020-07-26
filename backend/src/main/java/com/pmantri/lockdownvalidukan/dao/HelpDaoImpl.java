package com.pmantri.lockdownvalidukan.dao;

import com.pmantri.lockdownvalidukan.models.HelpCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HelpDaoImpl implements HelpDao {

    private static final String GET_HELP_CATEGORIES= "select * from FC_HELP_CATEGORIES where FC_HELP_CAT_STATUS = 'APPROVED'";
    private static final String INSERT_HELP_CATEGORY = "INSERT INTO FC_HELP_CATEGORIES ( FC_HELP_CAT_TEXT, " +
            "FC_HELP_CAT_DESC, " +
            "FC_HELP_CAT_STATUS, " +
            "FC_HELP_CAT_VISIBILITY) VALUES(?, ?, 'PENDING', 0)";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<HelpCategory> getHelpCategories() {

        try {
            return jdbcTemplate.query(GET_HELP_CATEGORIES, (rs, rowNum) -> {
                HelpCategory helpCategory = new HelpCategory();
                helpCategory.setHelpCategoryId(rs.getInt("FC_HELP_CAT_ID"));
                helpCategory.setHelpCategoryText(rs.getString("FC_HELP_CAT_TEXT"));
                helpCategory.setHelpCategoryDesc(rs.getString("FC_HELP_CAT_DESC"));
                helpCategory.setHelpCategoryStatus(rs.getString("FC_HELP_CAT_STATUS"));
                helpCategory.setHelpCategoryVisibility(rs.getInt("FC_HELP_CAT_VISIBILITY"));
                return helpCategory;
            });
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    @Override
    public Boolean insertHelpCategory(HelpCategory helpCategory) {
            return jdbcTemplate.update(INSERT_HELP_CATEGORY,
                helpCategory.getHelpCategoryText(),
                helpCategory.getHelpCategoryDesc()
                )>0;
    }
   /* @Override
    public Boolean updateVolunteer(HelpCategories helpCategories) {
        return jdbcTemplate.update(UPDATE_VOLUNTEER_CRED, volunteer.getUserCredentials().getUsername(), volunteer.getUserCredentials().getPassword()) > 0;
    }
*/
}
