package com.desfio.tec.ca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.desfio.tec.ca.model.AbstractRobot;
import com.desfio.tec.ca.model.Coords;
import com.desfio.tec.ca.model.RobotInterface;

@Controller
public class RobotController {
	
	@Autowired
	private RobotInterface robot;
	
	public Coords move(String coords) throws Exception {
		return this.robot.move(coords);
	}
	
	public Coords getCoords() {
		AbstractRobot robot = (AbstractRobot) this.robot;
		return robot.getModelCoords();
	}
	
}
