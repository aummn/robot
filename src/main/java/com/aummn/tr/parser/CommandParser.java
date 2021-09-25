package com.aummn.tr.parser;


import com.aummn.tr.model.Position;

/**
 * This class defining methods for checking and parsing commands. 
 *
 * @author James Jin
 */
public interface CommandParser {

	boolean isPlaceCommand(String input);
	
	boolean isMoveCommand(String input);
	
	boolean isLeftCommand(String input);
	
	boolean isRightCommand(String input);
	
	boolean isReportCommand(String input);
	
	Position parsePlaceCommand(String input);

}
