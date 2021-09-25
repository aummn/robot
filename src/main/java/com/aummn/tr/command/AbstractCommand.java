package com.aummn.tr.command;

/**
 * This class defining the method for handling an input command. 
 *
 * @author James Jin
 * 
 */
public abstract class AbstractCommand {
	
	/**
	 * Executes the command logic
	 * 
	 */		
	public abstract void execute();
	
	/**
	 * Gets the command name
	 * 
	 */		
	public abstract String getName();
	
	/**
	 * Sets the command input
	 * 
	 */	
	public void setInput(String input) {};
	
	/**
	 * Is the input valid
	 * 
	 */	
	public abstract boolean isValidInput(String input);
}
