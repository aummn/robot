package com.aummn.tr.command;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.aummn.tr.exception.EmptyCommandException;
import com.aummn.tr.exception.ErrorStatus;
import com.aummn.tr.exception.RobotNotBePlacedException;
import com.aummn.tr.exception.UnsupportedCommandException;
import com.aummn.tr.parser.CommandParser;
import com.aummn.tr.parser.RobotCommandParser;
import com.aummn.tr.table.Table;

/**
 * This class is a factory to produce command processors.
 *
 * @author James Jin
 * 
 */
public class CommandFactory {

	private static final String SPACE_REGEX = " ";
	private Map<String, AbstractCommand> toyCommandsMap = new HashMap<>();
	private CommandParser checker;
	private Table table;

	public CommandFactory(CommandParser checker, Table table) {
		this.checker = checker;
		this.table = table;
		setupCommands();
	}

	/**
	 * Get the command based on the input string.
	 * 
	 * @param input the input command string
	 * @return a command processor
	 * 
	 */
	public AbstractCommand createCommand(String in) {
		boolean isValuePresent = Optional.ofNullable(in).isPresent();
		if (isValuePresent) {
			System.out.println("Input command: " + in);
			String cmdName = in.trim().split(SPACE_REGEX)[0];
			AbstractCommand cmd = this.getToyCommandsMap().get(CommandName.getCommandName(cmdName).getName());

			// return unsupported command exception if the input command is not found or
			// invalid
			if (!(Optional.ofNullable(cmd).isPresent()) || !(cmd.isValidInput(in))) {
				throw new UnsupportedCommandException(ErrorStatus.UNSUPPORTED_COMMAND);
			}

			// set the input for Place command parsing
			if (cmd instanceof PlaceCommand) {
				cmd.setInput(in);
			}

			boolean isRobotPlaced = this.getTable().isRobotPlaced();
			// ignore commands other than PLACE when Robot is not be placed
			if (!isRobotPlaced && !(cmd instanceof PlaceCommand)) {
				throw new RobotNotBePlacedException(ErrorStatus.PLACE_COMMAND_NOT_BE_EXECUTED);
			}
			return cmd;
		} else { // input nothing
			throw new EmptyCommandException(ErrorStatus.EMPTY_COMMAND);
		}
	}

	public CommandFactory addCommand(AbstractCommand command) {
		toyCommandsMap.put(command.getName(), command);
		return this;
	}

	public void removeCommand(AbstractCommand command) {
		toyCommandsMap.remove(command.getName());
	}

	public CommandParser getChecker() {
		return checker;
	}

	public void setChecker(RobotCommandParser checker) {
		this.checker = checker;
	}

	public Table getTable() {
		return table;
	}

	public void setTable(Table table) {
		this.table = table;
	}

	public Map<String, AbstractCommand> getToyCommandsMap() {
		return toyCommandsMap;
	}

	public void setToyCommandsMap(Map<String, AbstractCommand> toyCommandsMap) {
		this.toyCommandsMap = toyCommandsMap;
	}

	private void setupCommands() {
		this.addCommand(new PlaceCommand(this.getChecker(), this.getTable()))
				.addCommand(new MoveCommand(this.getChecker(), this.getTable()))
				.addCommand(new LeftCommand(this.getChecker(), this.getTable()))
				.addCommand(new RightCommand(this.getChecker(), this.getTable()))
				.addCommand(new ReportCommand(this.getChecker(), this.getTable()));
	}

}
