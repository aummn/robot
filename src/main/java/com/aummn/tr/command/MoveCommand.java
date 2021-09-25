package com.aummn.tr.command;

import com.aummn.tr.exception.ErrorStatus;
import com.aummn.tr.exception.OffTableException;
import com.aummn.tr.model.Facing;
import com.aummn.tr.model.Position;
import com.aummn.tr.parser.CommandParser;
import com.aummn.tr.table.Table;

/**
 * This class moving the robot on the table.
 *
 * @author James Jin
 * 
 */
public class MoveCommand extends AbstractCommand {
	private CommandName commandName = CommandName.MOVE;
	private CommandParser checker;
	private Table table;

	public MoveCommand(CommandParser checker, Table table) {
		this.checker = checker;
		this.table = table;
	}

	/**
	 * Move the robot based on the facing and ensure it's on table.
	 * 
	 */
	public void execute() {
		Position p = this.getTable().getRobotPosition();
		int x = p.getX();
		int y = p.getY();
		Facing facing = p.getFacing();

		switch (facing) {

		case NORTH:
			y += 1;
			break;
		case SOUTH:
			y -= 1;
			break;
		case EAST:
			x += 1;
			break;
		case WEST:
			x -= 1;
			break;
		default:
			break;
		}

		// update the Robot position
		if (this.getTable().isOnTheTable(x, y)) {
			p.setX(x);
			p.setY(y);
		} else {
			throw new OffTableException(ErrorStatus.OFF_TABLE);
		}
	}
	
	/**
	 * Is the input a valid Move command
	 * 
	 * @return true if the input is a valid Move command, false otherwise.
	 * 
	 */
	public boolean isValidInput(String input) {
		return this.getChecker().isMoveCommand(input);
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
