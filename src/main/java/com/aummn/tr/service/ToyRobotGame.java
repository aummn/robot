package com.aummn.tr.service;

import java.util.Properties;

import com.aummn.tr.command.AbstractCommand;
import com.aummn.tr.command.CommandFactory;
import com.aummn.tr.data.input.DataInput;
import com.aummn.tr.exception.RobotExceptionHandler;
import com.aummn.tr.model.Position;
import com.aummn.tr.parser.CommandParser;
import com.aummn.tr.parser.RobotCommandParser;
import com.aummn.tr.table.RobotTable;
import com.aummn.tr.table.Table;

/**
 * This class is for reading input commands , executing commands and display
 * outputs.
 *
 * @author James Jin
 * 
 */
public class ToyRobotGame implements Game {
	private static final String TABLE_UNIT_X = "table.unit.x";
	private static final String TABLE_UNIT_Y = "table.unit.y";

	private Table table;
	private CommandParser checker;
	private CommandFactory factory;
	private DataInput dataInput;
	private Position position;

	public ToyRobotGame(Properties prop, DataInput dataInput) {
		setUp(prop, dataInput);
	}

	/**
	 * Starts handling input commands from command line interface.
	 * 
	 */
	public Position start() {
		System.out.println("Valid Robot commands are: 'PLACE X, Y, NORTH', 'MOVE', 'LEFT', 'RIGHT', 'REPORT'\n");
		while (keepRunning()) {
			processCommands();
		}
		return this.getPosition();
	}

	private void processCommands() {
		this.getDataInput().getInput().stream().forEach(input -> {
			try {
				AbstractCommand c = factory.createCommand(input);
				this.setPosition(c.execute());
			} catch (Exception ex) {
				RobotExceptionHandler.INSTANCE.handleExceptions(ex);
			}
		});
	}

	private boolean keepRunning() {
		return this.getDataInput().hasNextInput();
	}

	private void setUp(Properties prop, DataInput dataInput) {
		int xUnit = Integer.parseInt(prop.getProperty(TABLE_UNIT_X));
		int yUnit = Integer.parseInt(prop.getProperty(TABLE_UNIT_Y));
		this.table = RobotTable.builder().minX(0).minY(0).maxX(xUnit).maxY(yUnit).build();
		this.checker = new RobotCommandParser(prop);
		this.factory = new CommandFactory(checker, table);
		this.dataInput = dataInput;
	}

	public DataInput getDataInput() {
		return dataInput;
	}

	public void setDataInput(DataInput dataInput) {
		this.dataInput = dataInput;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

}
