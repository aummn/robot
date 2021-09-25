package com.aummn.tr.command;

import com.aummn.tr.model.Facing;
import com.aummn.tr.model.Position;
import com.aummn.tr.parser.CommandParser;
import com.aummn.tr.table.Table;

/**
 * This class turning the robot right on the table.
 *
 * @author James Jin
 * 
 */
public class RightCommand extends AbstractCommand {
	private CommandName commandName = CommandName.RIGHT;
	private Table table;
	private CommandParser checker;
	
	public RightCommand(CommandParser checker, Table table) {
		this.table = table;
		this.checker = checker;
	}

	/**
	 * Turn the robot right based on the current facing.
	 * 
	 */
	public Position execute() {
		Position p = this.getTable().getRobotPosition();
		Facing facing = p.getFacing();
		Facing nextFacing = Facing.INVALID;

		switch (facing) {
		case NORTH:
			nextFacing = Facing.EAST;
			break;
		case EAST:
			nextFacing = Facing.SOUTH;
			break;
		case SOUTH:
			nextFacing = Facing.WEST;
			break;
		case WEST:
			nextFacing = Facing.NORTH;
			break;
		default:
			break;
		}
		p.setFacing(nextFacing);
		return p;
	}

	/**
	 * Is the input a valid Right command
	 * 
	 * @return true if the input is a valid Right command, false otherwise.
	 * 
	 */
	public boolean isValidInput(String input) {
		return this.getChecker().isRightCommand(input);
	}
	
	public Table getTable() {
		return table;
	}

	public void setTable(Table table) {
		this.table = table;
	}


	public CommandName getCommandName() {
		return commandName;
	}

	public void setCommandName(CommandName commandName) {
		this.commandName = commandName;
	}

	public String getName() {
		return this.getCommandName().getName();
	}

	public CommandParser getChecker() {
		return checker;
	}

	public void setChecker(CommandParser checker) {
		this.checker = checker;
	}
	
}
