package com.dishatech.myspringquartz.job;

import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Jobconfig {

	@Autowired
	Scheduler scheduler;

	@Bean
	public JobDetail jobADetails() {
		JobKey jobKeyA = new JobKey("JobA", "sftpGroup");
		return JobBuilder.newJob(JobA.class).withIdentity(jobKeyA).storeDurably().build();
	}

	@Bean
	public Trigger jobATrigger(JobDetail jobADetails) {
		return TriggerBuilder.newTrigger().forJob(jobADetails).withIdentity("JobATrigger")
				.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(5).repeatForever()).build();
		// .withSchedule(CronScheduleBuilder.cronSchedule("0/2 * * ? * * *"))
	}
	
	@Bean
	public JobDetail jobBDetails() {
		JobKey jobKeyB = new JobKey("JobB", "sftpGroup");
		return JobBuilder.newJob(JobB.class).withIdentity(jobKeyB).storeDurably().build();
	}

	@Bean
	public Trigger jobBTrigger(JobDetail jobBDetails) {
		return TriggerBuilder.newTrigger().forJob(jobBDetails).withIdentity("JobBTrigger")
				.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(5).repeatForever()).build();
		// .withSchedule(CronScheduleBuilder.cronSchedule("0/2 * * ? * * *"))
	}


	@Bean
	public void ScheduleJobs() {
		try {
			scheduler.start();
			if (scheduler.checkExists(jobADetails().getKey())) {
				scheduler.deleteJob(jobADetails().getKey());
				System.out.println("deleted old job");
			}
			scheduler.scheduleJob(jobADetails(), jobATrigger(jobADetails()));
			scheduler.scheduleJob(jobBDetails(), jobBTrigger(jobBDetails()));
			//scheduler.shutdown();

		} catch (SchedulerException se) {
			se.printStackTrace();
		}
	}
}