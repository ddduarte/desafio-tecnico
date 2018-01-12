package com.desfio.tec.ca.model;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;

import com.desfio.tec.ca.exception.InvalidException;
import com.desfio.tec.ca.exception.RepeatedException;
import com.desfio.tec.ca.validate.CoordsValidator;

import lombok.Getter;

public abstract class AbstractRobot implements RobotInterface {
	
	@Value("${desafio.tec.ca.boundX}")
	@Getter
	private int boundX;
	
	@Value("${desafio.tec.ca.boundY}")
	@Getter
	private int boundY;
	
	protected String coords;
	
	// Inicia as coordenadas em 0,0,N
	@Getter
	protected Coords modelCoords = new Coords(0,0, DirectionEnum.N);
	
	@Override
	public Coords move(String coords) throws Exception {
		// Valida as coordenadas
		CoordsValidator validator = (String newCoords, String oldCoords) -> {
			if(StringUtils.isEmpty(newCoords)) {
				throw new InvalidException("Coordenadas invalidas!");
			}
			
			String validate = newCoords.toUpperCase();
			
			if(validate.equals(oldCoords)) {
				throw new RepeatedException("Coordenadas sao iguais as anteriores!");
			}
			
			if(Pattern.compile("[^LRM]").matcher(validate).find()) {
				throw new InvalidException("Coordenadas contem caracteres invalidos!");
			}
		};
		
		validator.validateCoorrds(coords, this.coords);
		return moveRobot(coords.toUpperCase());
	}

	abstract Coords moveRobot(String coords) throws Exception;

}
