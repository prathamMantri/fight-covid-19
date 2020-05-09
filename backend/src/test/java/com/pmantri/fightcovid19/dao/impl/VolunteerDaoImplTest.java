package com.pmantri.fightcovid19.dao.impl;


import com.pmantri.fightcovid19.base.BaseTestCase;
import com.pmantri.fightcovid19.dao.VolunteerDao;
import com.pmantri.fightcovid19.models.UserCredentials;
import com.pmantri.fightcovid19.models.Volunteer;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class VolunteerDaoImplTest extends BaseTestCase {

	@Autowired
	VolunteerDao volunteerDao;

	@Test
	public void saveConversation() {
		Volunteer volunteer = new Volunteer();
		UserCredentials userCredentials = new UserCredentials();
		userCredentials.setUserCredId(002);
		userCredentials.setUsername("SHREE2");
		userCredentials.setPassword("GANESH2");
		volunteer.setUserCredentials(userCredentials);

		volunteerDao.insertVolunteer(volunteer);
	}
}
