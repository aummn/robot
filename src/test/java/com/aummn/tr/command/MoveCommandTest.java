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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;

public class MoveCommandTest {

	@Rule 
	public MockitoRule rule = MockitoJUnit.rule();
	
	@Mock
	CommandParser checker;
	
	@Mock
	Table table;
	
	@InjectMocks
	MoveCommand moveCommand;
	
	Position position;
	
	@Before
	public void setUp() throws Exception {
		position = Position.builder().x(0).y(0).facing(Facing.INVALID).build();;
	}

	@Test
	public void givenFacingNorth_andX0Y0_whenMove_thenReturnNextPosition() {
		position.setX(0);
		position.setY(0);
		position.setFacing(Facing.NORTH);
		
		when(table.getRobotPosition()).thenReturn(position);
		when(table.isOnTheTable(anyInt(), anyInt())).thenReturn(true);
		moveCommand.execute();
		
		assertThat(position.getX()).isEqualTo(0);
		assertThat(position.getY()).isEqualTo(1);
		assertThat(position.getFacing()).isEqualTo(Facing.NORTH);
	}
	
	@Test
	public void givenFacingNorth_andX1Y1_whenMove_thenReturnNextPosition() {

		position.setX(1);
		position.setY(1);
		position.setFacing(Facing.NORTH);
		
		when(table.getRobotPosition()).thenReturn(position);
		when(table.isOnTheTable(anyInt(), anyInt())).thenReturn(true);
		moveCommand.execute();
		
		assertThat(position.getX()).isEqualTo(1);
		assertThat(position.getY()).isEqualTo(2);
		assertThat(position.getFacing()).isEqualTo(Facing.NORTH);
	}
	
	@Test(expected=OffTableException.class)
	public void givenFacingNorth_andX2Y5_whenMove_thenReturnNextPosition() {

		position.setX(2);
		position.setY(5);
		position.setFacing(Facing.NORTH);
		
		when(table.getRobotPosition()).thenReturn(position);
		when(table.isOnTheTable(eq(2), eq(6))).thenReturn(false);
		moveCommand.execute();
	}
	
	@Test(expected=OffTableException.class)
	public void givenFacingNorth_andX3Y5_whenMove_thenReturnNextPosition() {

		position.setX(3);
		position.setY(5);
		position.setFacing(Facing.NORTH);
		
		when(table.getRobotPosition()).thenReturn(position);
		when(table.isOnTheTable(eq(3), eq(6))).thenReturn(false);
		moveCommand.execute();
	}
	
	@Test
	public void givenFacingSouth_andX4Y5_whenMove_thenReturnNextPosition() {

		position.setX(4);
		position.setY(5);
		position.setFacing(Facing.SOUTH);
		
		when(table.getRobotPosition()).thenReturn(position);
		when(table.isOnTheTable(anyInt(), anyInt())).thenReturn(true);
		moveCommand.execute();
		
		assertThat(position.getX()).isEqualTo(4);
		assertThat(position.getY()).isEqualTo(4);
		assertThat(position.getFacing()).isEqualTo(Facing.SOUTH);
	}
	
	@Test
	public void givenFacingSouth_andX4Y4_whenMove_thenReturnNextPosition() {

		position.setX(4);
		position.setY(4);
		position.setFacing(Facing.SOUTH);
		
		when(table.getRobotPosition()).thenReturn(position);
		when(table.isOnTheTable(anyInt(), anyInt())).thenReturn(true);
		moveCommand.execute();
		
		assertThat(position.getX()).isEqualTo(4);
		assertThat(position.getY()).isEqualTo(3);
		assertThat(position.getFacing()).isEqualTo(Facing.SOUTH);
	}
	
	@Test
	public void givenFacingSouth_andX4Y1_whenMove_thenReturnNextPosition() {

		position.setX(4);
		position.setY(1);
		position.setFacing(Facing.SOUTH);
		
		when(table.getRobotPosition()).thenReturn(position);
		when(table.isOnTheTable(anyInt(), anyInt())).thenReturn(true);
		moveCommand.execute();
		
		assertThat(position.getX()).isEqualTo(4);
		assertThat(position.getY()).isEqualTo(0);
		assertThat(position.getFacing()).isEqualTo(Facing.SOUTH);
	}
	
	@Test(expected=OffTableException.class)
	public void givenFacingSouth_andX4Y0_whenMove_thenReturnNextPosition() {

		position.setX(4);
		position.setY(0);
		position.setFacing(Facing.SOUTH);
		
		when(table.getRobotPosition()).thenReturn(position);
		when(table.isOnTheTable(eq(4), eq(-1))).thenReturn(false);
		moveCommand.execute();
	}
	
	@Test
	public void givenFacingWest_andX2Y0_whenMove_thenReturnNextPosition() {

		position.setX(2);
		position.setY(0);
		position.setFacing(Facing.WEST);
		
		when(table.getRobotPosition()).thenReturn(position);
		when(table.isOnTheTable(anyInt(), anyInt())).thenReturn(true);
		moveCommand.execute();
		
		assertThat(position.getX()).isEqualTo(1);
		assertThat(position.getY()).isEqualTo(0);
		assertThat(position.getFacing()).isEqualTo(Facing.WEST);
	}
	
	@Test
	public void givenFacingWest_andX1Y0_whenMove_thenReturnNextPosition() {

		position.setX(1);
		position.setY(0);
		position.setFacing(Facing.WEST);
		
		when(table.getRobotPosition()).thenReturn(position);
		when(table.isOnTheTable(anyInt(), anyInt())).thenReturn(true);
		moveCommand.execute();
		
		assertThat(position.getX()).isEqualTo(0);
		assertThat(position.getY()).isEqualTo(0);
		assertThat(position.getFacing()).isEqualTo(Facing.WEST);
	}
	
	@Test(expected=OffTableException.class)
	public void givenFacingWest_andX0Y0_whenMove_thenReturnNextPosition() {

		position.setX(0);
		position.setY(0);
		position.setFacing(Facing.WEST);
		
		when(table.getRobotPosition()).thenReturn(position);
		when(table.isOnTheTable(eq(-1), eq(0))).thenReturn(false);
		moveCommand.execute();
	}
	
	@Test
	public void givenFacingEast_andX3Y3_whenMove_thenReturnNextPosition() {

		position.setX(3);
		position.setY(3);
		position.setFacing(Facing.EAST);
		
		when(table.getRobotPosition()).thenReturn(position);
		when(table.isOnTheTable(anyInt(), anyInt())).thenReturn(true);
		moveCommand.execute();
		
		assertThat(position.getX()).isEqualTo(4);
		assertThat(position.getY()).isEqualTo(3);
		assertThat(position.getFacing()).isEqualTo(Facing.EAST);
	}
	
	@Test(expected=OffTableException.class)
	public void givenFacingEast_andX5Y3_whenMove_thenReturnNextPosition() {
		position.setX(5);
		position.setY(3);
		position.setFacing(Facing.EAST);
		
		when(table.getRobotPosition()).thenReturn(position);
		when(table.isOnTheTable(eq(6), eq(3))).thenReturn(false);
		moveCommand.execute();
	}
	
	@Test
	public void givenFacingNorth_andX4Y4_whenMove_thenReturnNextPosition() {

		position.setX(4);
		position.setY(3);
		position.setFacing(Facing.NORTH);
		
		when(table.getRobotPosition()).thenReturn(position);
		when(table.isOnTheTable(anyInt(), anyInt())).thenReturn(true);
		moveCommand.execute();
		
		assertThat(position.getX()).isEqualTo(4);
		assertThat(position.getY()).isEqualTo(4);
		assertThat(position.getFacing()).isEqualTo(Facing.NORTH);
	}
	
	@Test
	public void givenFacingWest_andX4Y4_whenMove_thenReturnNextPosition() {

		position.setX(4);
		position.setY(4);
		position.setFacing(Facing.WEST);
		
		when(table.getRobotPosition()).thenReturn(position);
		when(table.isOnTheTable(anyInt(), anyInt())).thenReturn(true);
		moveCommand.execute();
		
		assertThat(position.getX()).isEqualTo(3);
		assertThat(position.getY()).isEqualTo(4);
		assertThat(position.getFacing()).isEqualTo(Facing.WEST);
	}
	
	@Test(expected=OffTableException.class)
	public void givenFacingSouth_andX0Y0_whenMove_thenReturnNextPosition() {

		position.setX(0);
		position.setY(0);
		position.setFacing(Facing.SOUTH);
		
		when(table.getRobotPosition()).thenReturn(position);
		when(table.isOnTheTable(eq(0), eq(-1))).thenReturn(false);
		moveCommand.execute();
	}
	
	@Test(expected=OffTableException.class)
	public void givenFacingWest_andX0Y4_whenMove_thenReturnNextPosition() {

		position.setX(0);
		position.setY(4);
		position.setFacing(Facing.WEST);
		
		when(table.getRobotPosition()).thenReturn(position);
		when(table.isOnTheTable(eq(-1), eq(4))).thenReturn(false);
		moveCommand.execute();
	}
	
	@Test
	public void givenFacingWest_andX3Y3_whenMove_thenReturnNextPosition() {

		when(table.getRobotPosition()).thenReturn(position);
		position.setX(3);
		position.setY(3);
		position.setFacing(Facing.WEST);
		when(table.getRobotPosition()).thenReturn(position);
		when(table.isOnTheTable(anyInt(), anyInt())).thenReturn(true);
		moveCommand.execute();
		
		assertThat(position.getX()).isEqualTo(2);
		assertThat(position.getY()).isEqualTo(3);
		assertThat(position.getFacing()).isEqualTo(Facing.WEST);
	}
	
	@Test(expected=OffTableException.class)
	public void givenFacingEast_andX5Y0_whenMove_thenReturnNextPosition() {

		position.setX(5);
		position.setY(0);
		position.setFacing(Facing.EAST);
		
		when(table.getRobotPosition()).thenReturn(position);
		when(table.isOnTheTable(eq(6), eq(0))).thenReturn(false);
		moveCommand.execute();
	}	
	
	
	@Test(expected=OffTableException.class)
	public void givenFacingNorth_andX5Y5_whenMove_thenReturnNextPosition() {

		position.setX(5);
		position.setY(5);
		position.setFacing(Facing.NORTH);
		
		when(table.getRobotPosition()).thenReturn(position);
		when(table.isOnTheTable(eq(5), eq(6))).thenReturn(false);
		moveCommand.execute();
	}
	
	@Test(expected=OffTableException.class)
	public void givenFacingEast_andX5Y5_whenMove_thenReturnNextPosition() {

		position.setX(5);
		position.setY(5);
		position.setFacing(Facing.EAST);
		
		when(table.getRobotPosition()).thenReturn(position);
		when(table.isOnTheTable(eq(6), eq(5))).thenReturn(false);
		moveCommand.execute();
	}
	
	@Test(expected=OffTableException.class)
	public void givenFacingSouth_andX5Y0_whenMove_thenReturnNextPosition() {

		position.setX(5);
		position.setY(0);
		position.setFacing(Facing.SOUTH);
		
		when(table.getRobotPosition()).thenReturn(position);
		when(table.isOnTheTable(eq(5), eq(-1))).thenReturn(false);
		moveCommand.execute();
	}	
	
	@Test
	public void givenFacingWest_andX5Y0_whenMove_thenReturnNextPosition() {

		position.setX(5);
		position.setY(0);
		position.setFacing(Facing.WEST);
		
		when(table.getRobotPosition()).thenReturn(position);
		when(table.isOnTheTable(eq(4), eq(0))).thenReturn(true);
		moveCommand.execute();
		
		assertThat(position.getX()).isEqualTo(4);
		assertThat(position.getY()).isEqualTo(0);
		assertThat(position.getFacing()).isEqualTo(Facing.WEST);
	}		
	
}