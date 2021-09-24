package com.aummn.tr.model;

import java.util.Optional;

/**
 * This enum representing the robot's facing direction. 
 * 
 * @author James Jin
 * 
 */
public enum Facing {
	NORTH("North"),
	SOUTH("South"),
	EAST("East"),
	WEST("West"),
	INVALID("INVALID");
	
	private final String facing;
	
	Facing(String facing) {
		this.facing = facing;
	}
	
	public static Facing getFacing(String aFacing) {
		if(!Optional.ofNullable(aFacing).isPresent()) {
			return Facing.INVALID;
		}
		
		for(Facing facing : Facing.values()) {
			if(facing.getFacing().equalsIgnoreCase(aFacing)) {
				return facing;
			}
		}
		return Facing.INVALID;
	}

	public String getFacing() {
		return facing;
	}
	
	@Override
	public String toString() {
		return facing;
	}

}
