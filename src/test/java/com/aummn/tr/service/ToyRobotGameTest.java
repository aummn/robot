package com.aummn.tr.service;

import org.junit.Before;
import org.junit.Test;

import com.aummn.tr.data.input.DataInput;
import com.aummn.tr.data.input.FileDataInputImpl;
import com.aummn.tr.model.Facing;
import com.aummn.tr.model.Position;
import com.aummn.tr.util.FileUtil;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class ToyRobotGameTest {

	String INPUT_FILE_FOLDER = "./src/test/resources";
	String APPLICATION_PROPERTY_FILE = "application.properties";

	Properties prop;
	DataInput dataInput;
	ToyRobotGame game;
	

	@Before
	public void setUp() throws Exception {
		FileUtil fileUtil = new FileUtil();
		prop = fileUtil.loadConfig(APPLICATION_PROPERTY_FILE);
	}

	
	@Test
	public void givenTestFileMoveCmd_whenStartGame_thenReturnRobotPosition() {
		List<String> FILE_LISTS = Arrays.asList("test_file_move_cmd.txt");
		DataInput dataInput = new FileDataInputImpl(INPUT_FILE_FOLDER, FILE_LISTS);
		game = new ToyRobotGame(prop, dataInput);
		Position p = game.start();
		assertThat(p.getX() == 0).isTrue();
		assertThat(p.getY() == 5).isTrue();
		assertThat(p.getFacing() == Facing.NORTH).isTrue();
	}

	@Test
	public void givenTestFileLeftCmd_whenStartGame_thenReturnRobotPosition() {
		List<String> FILE_LISTS = Arrays.asList("test_file_left_cmd.txt");
		DataInput dataInput = new FileDataInputImpl(INPUT_FILE_FOLDER, FILE_LISTS);
		game = new ToyRobotGame(prop, dataInput);
		Position p = game.start();
		assertThat(p.getX() == 0).isTrue();
		assertThat(p.getY() == 1).isTrue();
		assertThat(p.getFacing() == Facing.EAST).isTrue();
	}
	
	@Test
	public void givenTestFileRightCmd_whenStartGame_thenReturnRobotPosition() {
		List<String> FILE_LISTS = Arrays.asList("test_file_right_cmd.txt");
		DataInput dataInput = new FileDataInputImpl(INPUT_FILE_FOLDER, FILE_LISTS);
		game = new ToyRobotGame(prop, dataInput);
		Position p = game.start();
		assertThat(p.getX() == 3).isTrue();
		assertThat(p.getY() == 0).isTrue();
		assertThat(p.getFacing() == Facing.WEST).isTrue();
	}
	
	@Test
	public void givenTestFileRandomWalk_1_whenStartGame_thenReturnRobotPosition() {
		List<String> FILE_LISTS = Arrays.asList("test_file_random_walk_1.txt");
		DataInput dataInput = new FileDataInputImpl(INPUT_FILE_FOLDER, FILE_LISTS);
		game = new ToyRobotGame(prop, dataInput);
		Position p = game.start();
		assertThat(p.getX() == 1).isTrue();
		assertThat(p.getY() == 0).isTrue();
		assertThat(p.getFacing() == Facing.EAST).isTrue();
	}	
	
	@Test
	public void givenTestFileInvalidCmdMix_1_whenStartGame_thenReturnRobotPosition() {
		List<String> FILE_LISTS = Arrays.asList("test_file_invalid_cmd_mix_1.txt");
		DataInput dataInput = new FileDataInputImpl(INPUT_FILE_FOLDER, FILE_LISTS);
		game = new ToyRobotGame(prop, dataInput);
		Position p = game.start();
		assertThat(p.getX() == 3).isTrue();
		assertThat(p.getY() == 0).isTrue();
		assertThat(p.getFacing() == Facing.EAST).isTrue();
	}	
}