package com.aummn.tr.table;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import com.aummn.tr.model.Facing;
import com.aummn.tr.model.Position;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(JUnitParamsRunner.class)
public class RobotTableJUnitParamsTest {

	private int MAX_X = 5;
	private int MAX_Y = 5;
	
	@Rule 
	public MockitoRule rule = MockitoJUnit.rule();
	
	
	RobotTable table;
	
    @Before
    public void createRecognizerBeforeEveryExecution() {
    	table = RobotTable.builder().minX(0).minY(0).maxX(MAX_X).maxY(MAX_Y).build();
    }
    
    @Test
    @Parameters({
            "0, 0,    true",
            "-1, 0,   false",
            "0, -1,   false",
            "6, 2,    false",
            "3, 6,    false",
            "5, 5,    true"
    })
	public void shouldReturnIsOnTheTableIsValid(int x, int y, boolean isValid) {
    	boolean result = table.isOnTheTable(x, y);
		assertThat(result).isEqualTo(isValid);
    }
    
	@Test
	public void givenAValidPosition_whenPlaceRobot_thenPlaceRobotOnTheTableAndSetStatus() {
		Position position = Position.builder().x(1).y(2).facing(Facing.NORTH).build();;
	
        table.placeRobot(position);
        
        Position p = table.getRobotPosition();
		assertThat(p.getX()).isEqualTo(1);
		assertThat(p.getY()).isEqualTo(2);
		assertThat(p.getFacing()).isEqualTo(Facing.NORTH);
		assertThat(table.isRobotPlaced()).isTrue();
	}

    
}