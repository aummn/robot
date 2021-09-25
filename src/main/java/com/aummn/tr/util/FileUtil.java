package com.aummn.tr.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * This class is for reading data file and config file. 
 *
 *
 * @author James Jin
 * 
 */
public class FileUtil {

	/**
	 * Reads data from a file in a folder
	 * 
	 * @param folder the data file folder
	 * 
	 * @param fileName the name of the data file
	 * 
	 * @return a list of data in string format
	 * 
	 * @throws IOException when file can't be found or read 
	 * 
	 */
	public List<String> readInput(String folder, String fileName) {
		List<String> list = new ArrayList<>();
		try {
			list = Files.lines(Paths.get(folder, fileName))
					.filter(line -> Optional.ofNullable(line).isPresent())
					.map(String::trim)
					.filter(line -> (!(line.isEmpty())))
					.filter(line -> (!(line.startsWith("#"))))
					.map(String::toUpperCase)
					.collect(Collectors.toList());
			      
		} catch (IOException e) {
			System.out.println("input file reading error: " + fileName);
			e.printStackTrace();
		}
		return list;
	}
	
}
