package com.aummn.tr.command;

import com.aummn.tr.model.Position;
import com.aummn.tr.parser.CommandParser;
import com.aummn.tr.table.Table;


/**
 * This class reporting the current robot position. 
 *
 * @author James Jin
 * 
 */
public class ReportCommand extends AbstractCommand {
	private CommandName commandName = CommandName.REPORT;
	private Table table;
	private CommandParser checker;
	
	public ReportCommand(CommandParser checker, Table table) {
		this.table = table;
		this.checker = checker;
	}

	/**
	 * Output the current robot position.
	 * 
	 */
	public void execute() {
		Position p = this.getTable().getRobotPosition();
		StringBuilder sb = new StringBuilder();
		sb.append("Robot position: x = [" + p.getX() + "] ");
		sb.append("y = [" + p.getY() + "] ");
		sb.append("facing = [" + p.getFacing().toString() + "]\n");
		System.out.println(sb.toString());
	}

	/**
	 * Is the input a valid Report command
	 * 
	 * @return true if the input is a valid Report command, false otherwise.
	 * 
	 */
	public boolean isValidInput(String input) {
		return this.getChecker().isReportCommand(input);
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
