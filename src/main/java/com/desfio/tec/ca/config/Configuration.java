package com.desfio.tec.ca.config;

import org.springframework.context.annotation.Bean;

import com.desfio.tec.ca.controller.RobotController;
import com.desfio.tec.ca.model.Robot;
import com.desfio.tec.ca.model.RobotInterface;

@org.springframework.context.annotation.Configuration
public class Configuration {
	
	@Bean
	public RobotInterface robotInterface() {
		return new Robot();
	}
	
	@Bean
	public RobotController robotController() {
		return new RobotController();
	}
	
}
