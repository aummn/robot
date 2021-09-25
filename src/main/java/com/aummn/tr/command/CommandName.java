package com.aummn.tr.command;

import java.util.Optional;

/**
 * This enum representing names of robot commands. 
 * 
 * @author James Jin
 * 
 */
public enum CommandName {
	PLACE("PLACE"),
	MOVE("MOVE"),
	LEFT("LEFT"),
	RIGHT("RIGHT"),
	REPORT("REPORT"),
	INVALID("INVALID");
	
	private final String name;
	
	CommandName(String name) {
		this.name = name;
	}
	
	/**
	 * Get the CommandName based on the input name string.
	 * 
	 * @param aName the input string
	 * @return a CommandName associated with the input name
	 * 
	 */
	public static CommandName getCommandName(String aName) {
		if(!Optional.ofNullable(aName).isPresent()) {
			return CommandName.INVALID;
		}
		
		for(CommandName name : CommandName.values()) {
			if(name.getName().equalsIgnoreCase(aName)) {
				return name;
			}
		}
		return CommandName.INVALID;
	}

	public String getName() {
		return name;
	}
	
	public String toString() {
		return name;
	}
}
