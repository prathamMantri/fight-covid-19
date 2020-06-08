package com.pmantri.fightcovid19.dao.impl;


import com.pmantri.fightcovid19.base.BaseTestCase;
import com.pmantri.fightcovid19.dao.VolunteerDao;
import com.pmantri.fightcovid19.models.VolunteerCredentials;
import com.pmantri.fightcovid19.models.Volunteer;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class VolunteerDaoImplTest extends BaseTestCase {

	@Autowired
	VolunteerDao volunteerDao;

	@Test
	public void saveConversation() {
		Volunteer volunteer = new Volunteer();
		VolunteerCredentials volunteerCredentials = new VolunteerCredentials();
		volunteerCredentials.setVolunteerCredId(002);
		volunteerCredentials.setVolunteerUsername("SHREE2");
		volunteerCredentials.setVolunteerPassword("GANESH2");
		volunteer.setVolunteerCredentials(volunteerCredentials);

		volunteerDao.registerVolunteer(volunteer);
	}
}
