package com.dishatech.myspringquartz.service;

import org.springframework.stereotype.Component;

@Component
public class MyQuartzService {

	public void jobA() {
		System.out.println("This is Job A");
	}
	
	public void jobB() {
		System.out.println("This is Job B");
	}
}
