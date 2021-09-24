package com.aummn.tr.model;

/**
 * This class holding the robot's location info including position and facing.
 * 
 * @author James Jin
 * 
 */
public class Position {

	private int x = 0;
	private int y = 0;
	private Facing facing = Facing.NORTH;

	private Position(int x, int y, Facing facing) {
		this.x = x;
		this.y = y;
		this.facing = facing;
	}

	// employ builder pattern for Position building
	public static PositionBuilder builder() {
		return new PositionBuilder();
	}

	public static class PositionBuilder {
		private int x = 0;
		private int y = 0;
		private Facing facing = Facing.NORTH;

		private PositionBuilder() {
		}

		public PositionBuilder x(int x) {
			this.x = x;
			return this;
		}

		public PositionBuilder y(int y) {
			this.y = y;
			return this;
		}

		public PositionBuilder facing(Facing facing) {
			this.facing = facing;
			return this;
		}

		public Position build() {
			return new Position(x, y, facing);
		}
	}
	
	@Override
	public String toString() {
		return "Position(x = " + x + ", y = " + y + ", facing = " + facing.toString() + ")";
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Facing getFacing() {
		return facing;
	}

	public void setFacing(Facing facing) {
		this.facing = facing;
	}

}