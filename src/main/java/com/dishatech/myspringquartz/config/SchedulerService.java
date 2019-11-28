package com.dishatech.myspringquartz.config;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

@Service
public class SchedulerService {

	@Autowired
	private SchedulerFactoryBean schedulerFactory;

	@PostConstruct
	private void init() {
		schedulerFactory.getScheduler();
	}
}