package com.aummn.tr.parser;

import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.aummn.tr.exception.CommandParsingException;
import com.aummn.tr.exception.ErrorStatus;
import com.aummn.tr.model.Facing;
import com.aummn.tr.model.Position;

/**
 * This class checking the input commands based on predefined patterns. 
 *
 * @author James Jin
 * 
 */
public class RobotCommandParser implements CommandParser {

	// regular expression patterns for command matching
	public static final String PATTERN_PLACE_COMMAND = "pattern.place.command";
	public static final String PATTERN_MOVE_COMMAND = "pattern.move.command";
	public static final String PATTERN_LEFT_COMMAND = "pattern.left.command";
	public static final String PATTERN_RIGHT_COMMAND = "pattern.right.command";
	public static final String PATTERN_REPORT_COMMAND = "pattern.report.command";
	
	public static final String ERROR_MESSAGE_PARSING_PLACE_COMMAND = "error.message.parsing.place.command";
	public static final String ERROR_CODE_PARSING_PLACE_COMMAND = "error.code.parsing.place.command";
	
	public Properties prop; 
	
	public RobotCommandParser(Properties prop) {
		this.prop = prop;
	}
	
	public boolean isPlaceCommand(String input) {
		return validateInput(this.getProp().getProperty(PATTERN_PLACE_COMMAND), input);
	}
	
	public boolean isMoveCommand(String input) {
		return validateInput(this.getProp().getProperty(PATTERN_MOVE_COMMAND), input);
	}
	
	public boolean isLeftCommand(String input) {
		return validateInput(this.getProp().getProperty(PATTERN_LEFT_COMMAND), input);
	}
	
	public boolean isRightCommand(String input) {
		return validateInput(this.getProp().getProperty(PATTERN_RIGHT_COMMAND), input);
	}
	
	public boolean isReportCommand(String input) {
		return validateInput(this.getProp().getProperty(PATTERN_REPORT_COMMAND), input);
	}
	
	private boolean validateInput(String regex, String input) {
		Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
	    Matcher matcher = pattern.matcher(input);
	    return matcher.find();
	}
	
	public Position parsePlaceCommand(String input) {
		// check the input command string with the predefined PLACE command pattern
		Pattern pattern = Pattern.compile(this.getProp().getProperty(PATTERN_PLACE_COMMAND), Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(input.trim());

		// get the location and facing via matcher group
		if (matcher.find()) {
			String x = matcher.group(3).trim();
			String y = matcher.group(4).trim();

			// judge whether the robot is on the table
			int xPosition = Integer.parseInt(x);
			int yPosition = Integer.parseInt(y);
			String facing = matcher.group(5).trim().toUpperCase();

			return Position.builder().x(xPosition).y(yPosition).facing(Facing.getFacing(facing)).build();
		} else {
			throw new CommandParsingException(ErrorStatus.PLACE_COMMAND_PARSING_ERROR);
		}
	}	

	public Properties getProp() {
		return prop;
	}
	
}
