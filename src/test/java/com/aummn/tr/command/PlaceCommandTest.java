package com.aummn.tr.command;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import com.aummn.tr.exception.OffTableException;
import com.aummn.tr.model.Facing;
import com.aummn.tr.model.Position;
import com.aummn.tr.parser.CommandParser;
import com.aummn.tr.table.Table;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.eq;

public class PlaceCommandTest {

	Position position;
	String placeCommandPattern = "^(\\s*)(PLACE)(\\s+\\d+\\s*),(\\s*\\d+\\s*),(\\s*(NORTH|SOUTH|EAST|WEST)\\s*)$";

	@Rule 
	public MockitoRule rule = MockitoJUnit.rule();
	
	@Mock
	CommandParser checker;
	
	@Mock
	Table table;
	
	@InjectMocks
	PlaceCommand placeCommand;
	

	@Before
	public void setUp() throws Exception {
		position = Position.builder().x(0).y(0).facing(Facing.INVALID).build();;
	}

	@Test(expected=OffTableException.class)
	public void givenFacingNorth_andX6Y5_whenExecute_thenReturnPlaceCommand() {
		String cs = "PLACE 6,5,NORTH";
		position.setX(6);
		position.setY(5);
		position.setFacing(Facing.NORTH);
		
		when(checker.parsePlaceCommand(eq(cs))).thenReturn(position);
		when(table.isOnTheTable(eq(6), eq(5))).thenReturn(false);
		placeCommand.setInput(cs);
		placeCommand.execute();
	}
	
	@Test
	public void givenFacingNorth_andX0Y0_whenExecute_thenReturnPlaceCommand() {
		String cs = "PLACE 0,0,NORTH";
		position.setFacing(Facing.NORTH);
		
		when(checker.parsePlaceCommand(eq(cs))).thenReturn(position);
		when(table.isOnTheTable(eq(0), eq(0))).thenReturn(true);
		doNothing().when(table).placeRobot(eq(position));
		placeCommand.setInput(cs);
		placeCommand.execute();
		verify(table, times(1)).placeRobot(eq(position));
	}
	
}