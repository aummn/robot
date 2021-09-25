package com.aummn.tr.data.input;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import com.aummn.tr.util.FileUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.eq;


public class FileDataInputImplTest {

	@Rule 
	public MockitoRule rule = MockitoJUnit.rule();
	
	@Mock
	FileUtil fileUtil;
	
	String folder;
	List<String> fileNames;
	
	FileDataInputImpl dataInput;
	
	@Before
	public void setUp() throws Exception {
		folder = "./input/data";
		fileNames = new ArrayList<>();
	}
	
    @Test
	public void givenNoDataFile_whenGetInput_thenReturnEmpty() {
		dataInput = new FileDataInputImpl(folder, fileNames);
		List<String> result = dataInput.getInput();
		assertThat(result).isEmpty();
    }
    
    @Test
	public void givenDataFile_whenGetInput_thenReturnInputData() {
    	fileNames = Arrays.asList("file1.txt", "file2.txt","file3.txt");
		dataInput = new FileDataInputImpl(folder, fileNames);
		dataInput.setFileUtil(fileUtil);
		
    	List<String> inputsInFile1 = Arrays.asList("PLACE 0,0, NORTH", "MOVE", "LEFT", "REPORT");
    	List<String> inputsInFile2 = Arrays.asList("MOVE", "LEFT", "RIGHT", "MOVE", "REPORT");
    	List<String> inputsInFile3 = Arrays.asList("PLACE 0,0, SOUTH");
    	
    	when(fileUtil.readInput(eq(folder), eq("file1.txt"))).thenReturn(inputsInFile1);
    	when(fileUtil.readInput(eq(folder), eq("file2.txt"))).thenReturn(inputsInFile2);
    	when(fileUtil.readInput(eq(folder), eq("file3.txt"))).thenReturn(inputsInFile3);
    	
    	// load file1.txt
    	List<String> result = dataInput.getInput();
		assertThat(result).hasSize(4).contains("PLACE 0,0, NORTH").contains("MOVE");
		
		// load file2.txt
    	result = dataInput.getInput();
		assertThat(result).hasSize(5).contains("LEFT").contains("RIGHT");
		
		// load file3.txt
    	result = dataInput.getInput();
		assertThat(result).hasSize(1).contains("PLACE 0,0, SOUTH");
		
    	result = dataInput.getInput();
		assertThat(result).isEmpty();
		
    }
    
    @Test
	public void givenNoDataFile_whenHasNextInput_thenReturnFalse() {
		dataInput = new FileDataInputImpl(folder, fileNames);
		boolean result = dataInput.hasNextInput();
		assertThat(result).isFalse();
    }
    
    @Test
	public void givenADataFile_whenHasNextInput_thenReturnTrue() {
    	fileNames = Arrays.asList("file1.txt");
		dataInput = new FileDataInputImpl(folder, fileNames);
		boolean result = dataInput.hasNextInput();
		assertThat(result).isTrue();
    }
    
}