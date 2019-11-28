package com.dishatech.myspringquartz.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dishatech.myspringquartz.service.MyQuartzService;

@Component
public class JobB implements Job {

	@Autowired
	MyQuartzService myQuartzService;

	@Override
	public void execute(JobExecutionContext context) {
		myQuartzService.jobB();
	}
}
