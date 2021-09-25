package com.aummn.tr.command;

import com.aummn.tr.exception.ErrorStatus;
import com.aummn.tr.exception.OffTableException;
import com.aummn.tr.model.Position;
import com.aummn.tr.parser.CommandParser;
import com.aummn.tr.table.Table;

/**
 * This class placing the robot on the table.
 *
 * @author James Jin
 * 
 */
public class PlaceCommand extends AbstractCommand {

	private CommandName commandName = CommandName.PLACE;
	private String input;
	private CommandParser checker;
	private Table table;

	public PlaceCommand(CommandParser checker, Table table) {
		this.checker = checker;
		this.table = table;
	}

	/**
	 * Place the robot on the specified location on the table
	 * 
	 */
	@Override
	public void execute() {
		Position p = this.getChecker().parsePlaceCommand(this.getInput());
		if(table.isOnTheTable(p.getX(), p.getY())) {
			table.placeRobot(p);
		} else {
			throw new OffTableException(ErrorStatus.OFF_TABLE);
		}
	}
	
	/**
	 * Is the input a valid Place command
	 * 
	 * @return true if the input is a valid Place command, false otherwise.
	 * 
	 */
	public boolean isValidInput(String input) {
		return this.getChecker().isPlaceCommand(input);
	}

	public Table getTable() {
		return table;
	}

	public void setTable(Table table) {
		this.table = table;
	}

	public CommandParser getChecker() {
		return checker;
	}

	public void setChecker(CommandParser checker) {
		this.checker = checker;
	}

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
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
	
}
