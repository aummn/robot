package com.aummn.tr.parser;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.Properties;

@RunWith(JUnitParamsRunner.class)
public class RobotCommandParserJUnitParamsTest {

	public static final String PATTERN_PLACE_COMMAND = "pattern.place.command";
	public static final String PATTERN_MOVE_COMMAND = "pattern.move.command";
	public static final String PATTERN_LEFT_COMMAND = "pattern.left.command";
	public static final String PATTERN_RIGHT_COMMAND = "pattern.right.command";
	public static final String PATTERN_REPORT_COMMAND = "pattern.report.command";
	
	public static final String PATTERN_PLACE_COMMAND_STRING = "^(\\s*)(PLACE)(\\s+\\d+\\s*),(\\s*\\d+\\s*),(\\s*(NORTH|SOUTH|EAST|WEST)\\s*)$";
	public static final String PATTERN_MOVE_COMMAND_STRING = "^(\\s*)(MOVE)(\\s*)$";
	public static final String PATTERN_LEFT_COMMAND_STRING = "^(\\s*)(LEFT)(\\s*)$";
	public static final String PATTERN_RIGHT_COMMAND_STRING = "^(\\s*)(RIGHT)(\\s*)$";
	public static final String PATTERN_REPORT_COMMAND_STRING = "^(\\s*)(REPORT)(\\s*)$";
	
	public static final String ERROR_MSG_PLACE_COMMAND = "error.msg.place.command";
	public static final String ERROR_MSG_MOVE_COMMAND = "error.msg.move.command";
	public static final String ERROR_MSG_LEFT_COMMAND = "error.msg.left.command";
	public static final String ERROR_MSG_RIGHT_COMMAND = "error.msg.right.command";
	public static final String ERROR_MSG_REPORT_COMMAND = "error.msg.report.command";
	
	public static final String ERROR_MSG_PLACE_COMMAND_STRING = "not valid PLACE command";
	public static final String ERROR_MSG_MOVE_COMMAND_STRING = "not valid MOVE command";
	public static final String ERROR_MSG_LEFT_COMMAND_STRING = "not valid LEFT command";
	public static final String ERROR_MSG_RIGHT_COMMAND_STRING = "not valid RIGHT command";
	public static final String ERROR_MSG_REPORT_COMMAND_STRING = "not valid REPORT command";
	
	@Rule 
	public MockitoRule rule = MockitoJUnit.rule();
	
	@Mock
	Properties prop;
	
	@InjectMocks
	RobotCommandParser checker;
	
    @Before
    public void createRecognizerBeforeEveryExecution() {   	
		when(prop.getProperty(PATTERN_PLACE_COMMAND)).thenReturn(PATTERN_PLACE_COMMAND_STRING);
		when(prop.getProperty(PATTERN_MOVE_COMMAND)).thenReturn(PATTERN_MOVE_COMMAND_STRING);
		when(prop.getProperty(PATTERN_LEFT_COMMAND)).thenReturn(PATTERN_LEFT_COMMAND_STRING);
		when(prop.getProperty(PATTERN_RIGHT_COMMAND)).thenReturn(PATTERN_RIGHT_COMMAND_STRING);
		when(prop.getProperty(PATTERN_REPORT_COMMAND)).thenReturn(PATTERN_REPORT_COMMAND_STRING);
		
		when(prop.getProperty(ERROR_MSG_PLACE_COMMAND)).thenReturn(ERROR_MSG_PLACE_COMMAND_STRING);
		when(prop.getProperty(ERROR_MSG_MOVE_COMMAND)).thenReturn(ERROR_MSG_MOVE_COMMAND_STRING);
		when(prop.getProperty(ERROR_MSG_LEFT_COMMAND)).thenReturn(ERROR_MSG_LEFT_COMMAND_STRING);
		when(prop.getProperty(ERROR_MSG_RIGHT_COMMAND)).thenReturn(ERROR_MSG_RIGHT_COMMAND_STRING);
		when(prop.getProperty(ERROR_MSG_REPORT_COMMAND)).thenReturn(ERROR_MSG_REPORT_COMMAND_STRING);
		
    }
    
    @Test
    @Parameters({
            "PLACE 0\\,0\\, SOUTH,    true",
            "  plACe 0\\,0\\, sOUtH,  true",
            "PLACE 1\\,2 north,       false",
            "PLACE,                   false",
            "PLACE 0\\,0\\, ,         false",
            "PLACE ,0\\,              false",
            "PLACE  \\, SOUTH,            false",
            "PLACECMD 0\\,0\\, norTH ,false",
            " PLACE 1 \\, 3 \\, eaST,  true",
            "Move 0\\,0\\, SOUTH,     false",
            ",                        false",
            "   ,                     false",
            " 0\\,0\\, SOUTH,         false",
            " - ,                     false",
            " abc ,                   false",
            " 12 ,                    false",
            " place a\\, 3\\, west ,  false",
            " place 3\\, 4\\, west ,  true",
            " PLaCE 0\\,0\\, NOrth ,  true",
            " p;ace 0\\,0\\, NOrth ,  false",
            " PLaCE 0\\,0\\, NOrth1 , false",
            " PLaCE 0\\,0\\, NOrth\\,1 , false",
            " \\*  ,                  false",
            " Place 5\\,  5,          false",
            " Place 5\\,  5\\,  WesT, true",
            "MOVE,                    false",
            " Left,                   false",
            " Right ,                 false",
            "REPORT,                  false"
    })
	public void shouldReturnPlaceCommandIsValid(String input, boolean isValid) {
    	boolean result = checker.isPlaceCommand(input);
		assertThat(result).isEqualTo(isValid);
    }
    
    
    @Test
    @Parameters({
            "MOVE,     true",
            " move ,   true",
            " MoVe,    true",
            "  MoveCMD,false",
            " 12,      false",
            " \\*,     false",
            " move 1,  false",
            " mov ,    false",
            " - ,      false",
            " abc,     false",
            "movE\\,,   false",
            ",         false",
            " LEFT ,   false",
            "place 0\\, 0\\, south, false",
            " Right,   false",
            " REPORT,  false"
    })
	public void shouldReturnMoveCommandIsValid(String input, boolean isValid) {
    	boolean result = checker.isMoveCommand(input);
		assertThat(result).isEqualTo(isValid);
    }
    
    @Test
    @Parameters({
            "LEFT,     true",
            " left ,   true",
            " LefT,    true",
            "  LeftCMD,false",
            " 12a,      false",
            " \\*,     false",
            " left 1,  false",
            " lef ,    false",
            " - ,      false",
            " abc,     false",
            "leFT\\,,   false",
            ",         false",
            " MOVE ,   false",
            "place 0\\, 0\\, south, false",
            " Right,   false",
            " REPORT,  false"
    })
	public void shouldReturnLeftCommandIsValid(String input, boolean isValid) {
    	boolean result = checker.isLeftCommand(input);
		assertThat(result).isEqualTo(isValid);
    }
    
    @Test
    @Parameters({
            "RIGHT,     true",
            " right ,   true",
            " RighT,    true",
            "  RightCMD,false",
            " 12a,      false",
            " \\*,     false",
            " Right 1,  false",
            " rig ,    false",
            " - ,      false",
            " abc,     false",
            "RigH\\,,   false",
            ",         false",
            " LEFT ,   false",
            "place 0\\, 0\\, south, false",
            " MOVE,   false",
            " REPORT,  false"
    })
	public void shouldReturnRightCommandIsValid(String input, boolean isValid) {
    	boolean result = checker.isRightCommand(input);
		assertThat(result).isEqualTo(isValid);
    }
    
    @Test
    @Parameters({
            "REPORT,     true",
            " report ,   true",
            " RePOrt,    true",
            "  ReportCMD,false",
            " 12a,      false",
            " \\*,     false",
            " Report 1,  false",
            " Rep ,    false",
            " - ,      false",
            " abc,     false",
            "RiPO\\,,   false",
            ",         false",
            " LEFT ,   false",
            "place 0\\, 0\\, south, false",
            " MOVE,   false",
            " RIGHT,  false"
    })
	public void shouldReturnReportCommandIsValid(String input, boolean isValid) {
    	boolean result = checker.isReportCommand(input);
		assertThat(result).isEqualTo(isValid);
    }
    
}