package com.desfio.tec.ca.model;

import com.desfio.tec.ca.exception.InvalidException;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Coords {
	
	private int x;
	private int y;
	private DirectionEnum direction = DirectionEnum.N;
	
	public void commandMove() throws InvalidException {
		switch (direction) {
		case N:
			this.y++;
			break;
		case W:
			this.x++;
			break;
		case S:
			this.y--;
			break;
		case E:
			this.x--;
			break;
		default:
			throw new InvalidException("Direcao atual do movimento invalida!");
		}
	}
	
	public void commandDirection(Character command) throws InvalidException {
		switch (direction) {
		case N:
			if(Commands.R.equals(command)) {
				this.direction = DirectionEnum.W;
			}else if(Commands.L.equals(command)) {
				this.direction = DirectionEnum.E;
			}
			break;
		case W:
			if(Commands.R.equals(command)) {
				this.direction = DirectionEnum.S;
			}else if(Commands.L.equals(command)) {
				this.direction = DirectionEnum.N;
			}
			break;
		case S:
			if(Commands.R.equals(command)) {
				this.direction = DirectionEnum.E;
			}else if(Commands.L.equals(command)) {
				this.direction = DirectionEnum.W;
			}
			break;
		case E:
			if(Commands.R.equals(command)) {
				this.direction = DirectionEnum.N;
			}else if(Commands.L.equals(command)) {
				this.direction = DirectionEnum.S;
			}
			break;
		default:
			throw new InvalidException("Direcao atual do movimento invalida!");
		}
	}
	
	@Override
	public String toString() {
		return new StringBuilder()
				.append('(')
				.append(this.x)
				.append(',')
				.append(this.y)
				.append(',')
				.append(this.direction)
				.append(')')
				.toString();
	}

}
