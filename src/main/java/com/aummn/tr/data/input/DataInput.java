package com.aummn.tr.data.input;

import java.util.List;

/**
 * This interface defining the methods for reading input commands. 
 *
 * @author James Jin
 * 
 */
public interface DataInput {
	
	/**
	 * Reads input commands
	 * 
	 * @Return a list of command strings
	 * 
	 */
	List<String> getInput();
	
	/**
	 * Checks whether next command is ready
	 * 
	 * @return true if next command is available, false otherwise. 
	 * 
	 */
	boolean hasNextInput();
	
}
