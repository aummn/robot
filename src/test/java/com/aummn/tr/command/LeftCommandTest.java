package com.aummn.tr.command;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import com.aummn.tr.model.Facing;
import com.aummn.tr.model.Position;
import com.aummn.tr.parser.CommandParser;
import com.aummn.tr.table.Table;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class LeftCommandTest {

	@Rule 
	public MockitoRule rule = MockitoJUnit.rule();
	
	@Mock
	CommandParser checker;
	
	@Mock
	Table table;
	
	@InjectMocks
	private LeftCommand leftCommand;

	private Position position;
	
	
	@Before
	public void setUp() throws Exception {
		position = Position.builder().x(0).y(0).facing(Facing.INVALID).build();;
	}

	@Test
	public void givenFacingNorth_andTurnLeft_whenExecute_thenReturnWest() {
		position.setFacing(Facing.NORTH);
		when(table.getRobotPosition()).thenReturn(position);
		leftCommand.execute();
		assertThat(position.getFacing()).isEqualTo(Facing.WEST);
	}
	
	@Test
	public void givenFacingWest_andTurnLeft_whenExecute_thenReturnSouth() {
		position.setFacing(Facing.WEST);
		when(table.getRobotPosition()).thenReturn(position);
		leftCommand.execute();
		assertThat(position.getFacing()).isEqualTo(Facing.SOUTH);
	}
	
	@Test
	public void givenFacingSouth_andTurnLeft_whenExecute_thenReturnEast() {
		position.setFacing(Facing.SOUTH);
		when(table.getRobotPosition()).thenReturn(position);
		leftCommand.execute();
		assertThat(position.getFacing()).isEqualTo(Facing.EAST);
	}	
	
	@Test
	public void givenFacingEast_andTurnLeft_whenExecute_thenReturnNorth() {
		position.setFacing(Facing.EAST);
		when(table.getRobotPosition()).thenReturn(position);
		leftCommand.execute();
		assertThat(position.getFacing()).isEqualTo(Facing.NORTH);
	}		
	
	@Test
	public void givenNoFacingDirection_andTurnLeft_whenExecute_thenReturnNoFacingDirection() {
		position.setFacing(Facing.INVALID);
		when(table.getRobotPosition()).thenReturn(position);
		leftCommand.execute();
		assertThat(position.getFacing()).isEqualTo(Facing.INVALID);
	}

}