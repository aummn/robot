package com.aummn.tr.data.input;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * This class is for reading inputs from command line. 
 *
 * @author James Jin
 * 
 */
public class CLIDataInputImpl implements DataInput {
	
	public CLIDataInputImpl() {}
	
	public List<String> getInput() {
		// read command inputs from the command line
		System.out.println("Please input next command:");
		String input = new Scanner(System.in).nextLine();
        return Arrays.asList(input);
	}
	
	public boolean hasNextInput() {
		// get next command from CLI
		return true;
	}

}
