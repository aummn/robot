package com.aummn.tr;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import com.aummn.tr.data.input.CLIDataInputImpl;
import com.aummn.tr.data.input.DataInput;
import com.aummn.tr.data.input.FileDataInputImpl;
import com.aummn.tr.service.Game;
import com.aummn.tr.service.ToyRobotGame;
import com.aummn.tr.util.FileUtil;

/**
 * This class is the entry point for launching toy robot game.
 *
 * @author James Jin
 * 
 */
public class App {
	public static final String APPLICATION_PROPERTY_FILE = "application.properties";
	public static final String INPUT_FILE_FOLDER = "./src/test/resources";
	public static List<String> FILE_LISTS = Arrays.asList("test_file_move_cmd.txt");

	public static void main(String[] args) {
		FileUtil fileUtil = new FileUtil();
		Properties prop = fileUtil.loadConfig(APPLICATION_PROPERTY_FILE);
//    	DataInput dataInput = new CLIDataInputImpl();     // command line input
		DataInput dataInput = new FileDataInputImpl(INPUT_FILE_FOLDER, FILE_LISTS);  // command file input
		Game game = new ToyRobotGame(prop, dataInput);
		game.start();
	}

}
