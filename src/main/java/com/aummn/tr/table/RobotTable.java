package com.aummn.tr.table;

import java.util.concurrent.atomic.AtomicBoolean;

import com.aummn.tr.model.Position;

/**
 * This class simulating the table surface where robot is moving on.
 * 
 * @author James Jin
 * 
 */
public class RobotTable implements Table {
	private int minX = 0;
	private int minY = 0;
	private int maxX = 0;
	private int maxY = 0;

	private AtomicBoolean robotPlaced = new AtomicBoolean(false);
	private Position robotPosition = null;
	
	
	private RobotTable(int minX, int minY, int maxX, int maxY) {
		this.minX = minX;
		this.minY = minY;
		this.maxX = maxX;
		this.maxY = maxY;
	}

	public static TableBuilder builder() {
		return new TableBuilder();
	}

	public static class TableBuilder {
		private int minX = 0;
		private int minY = 0;
		private int maxX = 0;
		private int maxY = 0;

		private TableBuilder() {
		}

		public TableBuilder minX(int x) {
			this.minX = x;
			return this;
		}

		public TableBuilder minY(int y) {
			this.minY = y;
			return this;
		}

		public TableBuilder maxX(int x) {
			this.maxX = x;
			return this;
		}

		public TableBuilder maxY(int y) {
			this.maxY = y;
			return this;
		}

		public RobotTable build() {
			return new RobotTable(minX, minY, maxX, maxY);
		}
	}
	
	/**
	 * check whether the Robot is on the table. 
	 * 
	 * x the x-coordinate of current Robot position
	 * y the y-coordinate of current Robot position
	 * 
	 * return true if Robot is on the table; false otherwise.
	 */
	public boolean isOnTheTable(int x, int y) {
		return (x < 0 || y < 0 || x > this.getMaxX() || y > this.getMaxY())
				? false
				: true;
	}
	
	/**
	 * Places the Robot on the table and marks status. 
	 * 
	 * robotPosition current Robot position
	 */
	public void placeRobot(Position robotPosition) {
		this.robotPosition = robotPosition;
		this.robotPlaced.set(true);
	}
	
	public boolean isRobotPlaced() {
		return robotPlaced.get();
	}

	public int getMinX() {
		return minX;
	}

	public void setMinX(int minX) {
		this.minX = minX;
	}

	public int getMinY() {
		return minY;
	}

	public void setMinY(int minY) {
		this.minY = minY;
	}

	public int getMaxX() {
		return maxX;
	}

	public void setMaxX(int maxX) {
		this.maxX = maxX;
	}

	public int getMaxY() {
		return maxY;
	}

	public void setMaxY(int maxY) {
		this.maxY = maxY;
	}

	public AtomicBoolean getRobotPlaced() {
		return robotPlaced;
	}

	public void setRobotPlaced(AtomicBoolean robotPlaced) {
		this.robotPlaced = robotPlaced;
	}

	public Position getRobotPosition() {
		return robotPosition;
	}

	public void setRobotPosition(Position robotPosition) {
		this.robotPosition = robotPosition;
	}
	
}