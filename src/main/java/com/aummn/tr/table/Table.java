package com.aummn.tr.table;

import com.aummn.tr.model.Position;

/**
 * This interface defining the methods for robot status checking. 
 *
 * @author James Jin
 * 
 */
public interface Table {
	
	/**
	 * check whether the Robot is on the table. 
	 * 
	 * x the x-coordinate of current Robot position
	 * y the y-coordinate of current Robot position
	 * 
	 * @return true if Robot is on the table; false otherwise.
	 */
	boolean isOnTheTable(int x, int y);
	
	/**
	 * Places the Robot on the table and marks status. 
	 * 
	 * robotPosition current Robot position
	 */
	void placeRobot(Position robotPosition);
	
	/**
	 * Gets the Robot's position. 
	 * 
	 * @return current Robot's position
	 */
	Position getRobotPosition();
	
	/**
	 * Is the Robot on the table 
	 * 
	 * @return true if the robot is on the table, false otherwise.
	 *  
	 */	
	 boolean isRobotPlaced();
	
}
