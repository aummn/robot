package com.aummn.tr.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.stream.Collectors;

import com.aummn.tr.App;

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
	
	/**
	 * Loads config file data
	 * 
	 * @param configFile a config file
	 * 
	 * @return a Properties object containing configs
	 * 
	 * @throws IOException when the file can't be found or read 
	 * 
	 */
	public Properties loadConfig(String configFile) {
		Properties prop = new Properties();
		try (InputStream input = App.class.getClassLoader().getResourceAsStream(configFile)) {
			// load application properties
			prop.load(input);
		} catch (IOException ex) {
			System.out.println("Sorry, unable to read config: " + configFile);
			ex.printStackTrace();
		}
		return prop;
	}	
}
