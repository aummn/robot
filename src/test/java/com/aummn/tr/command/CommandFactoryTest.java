package com.aummn.tr.command;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import com.aummn.tr.exception.EmptyCommandException;
import com.aummn.tr.exception.RobotNotBePlacedException;
import com.aummn.tr.exception.UnsupportedCommandException;
import com.aummn.tr.model.Facing;
import com.aummn.tr.model.Position;
import com.aummn.tr.parser.CommandParser;
import com.aummn.tr.table.Table;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.anyString;

public class CommandFactoryTest {

	Position position;

	@Rule 
	public MockitoRule rule = MockitoJUnit.rule();
	
	@Mock
	CommandParser checker;
	
	@Mock
	Table table;
	
	@InjectMocks
	CommandFactory commandFactory;
	

	@Before
	public void setUp() throws Exception {
		position = Position.builder().x(0).y(0).facing(Facing.INVALID).build();;
	}
	
	@Test(expected=EmptyCommandException.class)
	public void givenNullInput_whenCreateCommand_thenReturnEmptyCommandException() {
		commandFactory.createCommand(null);
	}

	@Test(expected=UnsupportedCommandException.class)
	public void givenEmptyInput_whenCreateCommand_thenReturnUnsupportedCommandException() {
		commandFactory.createCommand("");
	}
	
	@Test(expected=UnsupportedCommandException.class)
	public void givenInvalidCMD_whenCreateCommand_thenReturnUnsupportedCommandException() {
		commandFactory.createCommand("PLACE_BLOCKER");
	}
	
	
	@Test(expected=UnsupportedCommandException.class)
	public void givenInvalidPlaceCMD_whenCreateCommand_thenReturnUnsupportedCommandException() {
		when(checker.isPlaceCommand(anyString())).thenReturn(false);
		commandFactory.createCommand("PLACE  , 0,0, north");
	}
	
	@Test(expected=RobotNotBePlacedException.class)
	public void givenMoveCMD_andRobotNotBePlaced_whenCreateCommand_thenReturnRobotNotBePlacedException() {
		when(table.isRobotPlaced()).thenReturn(false);
		when(checker.isMoveCommand(anyString())).thenReturn(true);
		commandFactory.createCommand("Move");
	}
	
	@Test(expected=RobotNotBePlacedException.class)
	public void givenLeftCMD_andRobotNotBePlaced_whenCreateCommand_thenReturnRobotNotBePlacedException() {
		when(table.isRobotPlaced()).thenReturn(false);
		when(checker.isLeftCommand(anyString())).thenReturn(true);
		commandFactory.createCommand("LEFT");
	}
	
	@Test(expected=RobotNotBePlacedException.class)
	public void givenReportCMD_andRobotNotBePlaced_whenCreateCommand_thenReturnRobotNotBePlacedException() {
		when(table.isRobotPlaced()).thenReturn(false);
		when(checker.isReportCommand(anyString())).thenReturn(true);
		commandFactory.createCommand("REPORT");
	}
	
	@Test(expected=RobotNotBePlacedException.class)
	public void givenRightCMD_andRobotNotBePlaced_whenCreateCommand_thenReturnRobotNotBePlacedException() {
		when(table.isRobotPlaced()).thenReturn(false);
		when(checker.isRightCommand(anyString())).thenReturn(true);
		commandFactory.createCommand("Right ");
	}
	
	
	@Test
	public void givenPlaceCMD_andRobotNotBePlaced_whenCreateCommand_thenReturnRobotNotBePlacedException() {
		when(table.isRobotPlaced()).thenReturn(false);
		when(checker.isPlaceCommand(anyString())).thenReturn(true);
		AbstractCommand cmd = commandFactory.createCommand("PLACE 0,0, NORTH");
		assertThat(cmd instanceof PlaceCommand).isTrue();
	}
	
	

}