package com.pmantri.lockdownvalidukan.base;

import com.pmantri.lockdownvalidukan.LockdownValiDukanApplication;
import com.pmantri.lockdownvalidukan.config.MySqlDevConfig;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {LockdownValiDukanApplication.class, MySqlDevConfig.class})
@ActiveProfiles("dev")
public class BaseTestCase {
	{
		System.setProperty("server.name", "test");
	}
}
