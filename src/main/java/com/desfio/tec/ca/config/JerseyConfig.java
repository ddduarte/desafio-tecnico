package com.desfio.tec.ca.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.desfio.tec.ca.resources.RobotResource;

@Component
public class JerseyConfig extends ResourceConfig {
	public JerseyConfig() {
		register(RobotResource.class);
	}
}
