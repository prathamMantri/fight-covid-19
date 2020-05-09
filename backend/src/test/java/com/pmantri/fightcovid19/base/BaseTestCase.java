package com.pmantri.fightcovid19.base;

import com.pmantri.fightcovid19.FightCovid19Application;
import com.pmantri.fightcovid19.config.MySqlDevConfig;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {FightCovid19Application.class, MySqlDevConfig.class})
@ActiveProfiles("dev")
public class BaseTestCase {
	{
		System.setProperty("server.name", "test");
	}
}
