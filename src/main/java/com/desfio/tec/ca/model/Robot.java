package com.desfio.tec.ca.model;

import javax.inject.Singleton;

import com.desfio.tec.ca.exception.InvalidException;
import com.desfio.tec.ca.validate.RobotValidator;

@Singleton
public class Robot extends AbstractRobot {
	
	@Override
	Coords moveRobot(String coords) throws Exception {
		
		char commands[] = coords.toCharArray();
		
		Coords newModel = new Coords(this.modelCoords.getX(),this.modelCoords.getY(), this.modelCoords.getDirection());
		
		for(char command : commands) {
			if(Commands.M.equals(command)) {
				newModel.commandMove();
			}
			
			if(Commands.L.equals(command) || Commands.R.equals(command)) {
				newModel.commandDirection(command);
			}
		}
		
		RobotValidator<Coords> validator = (Coords o) -> {
			if(o.getX() < 0 || o.getY() < 0) {
				throw new InvalidException("Coordenadas invalidas!");
			}
			
			if(o.getX() > this.getBoundX() || o.getY() > this.getBoundY()) {
				throw new InvalidException("Coordenadas invalidas!");
			}
		};
		
		validator.validate(newModel);
		this.modelCoords = newModel;
		this.coords = coords;
		
		return this.modelCoords;
	}
}
