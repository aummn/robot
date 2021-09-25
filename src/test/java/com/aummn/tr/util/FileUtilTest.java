package com.aummn.tr.util;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.aummn.tr.App;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.anyString;

@RunWith(PowerMockRunner.class)
@PrepareForTest({FileUtil.class, App.class})
public class FileUtilTest {
	
	String folder;
	String fileName;
	
	FileUtil fileUtil;
	
	@Before
	public void setUp() throws Exception {
		folder = "./input/data";
		fileName = "file1.txt";
		fileUtil = new FileUtil();
	}
	
    @Test
	public void givenADataFile_whenReadInput_thenReturnInputs() throws IOException {
    	
    	List<String> inputs = Arrays.asList("PLACE 0, 0, east", "", "  ", "#", " # ", " MOVE ", "RIGHT", " LEft", "Report");
    	PowerMockito.mockStatic(Files.class);
    	PowerMockito.mockStatic(Paths.class);
    	Path path = mock(Path.class);
    	
    	when(Paths.get(anyString(), anyString())).thenReturn(path);
    	when(Files.lines(eq(path))).thenReturn(inputs.stream());
    	
        List<String> result = fileUtil.readInput(folder, fileName);
        assertThat(result).hasSize(5).contains("PLACE 0, 0, EAST").contains("MOVE").contains("LEFT").contains("REPORT");
    }
    
    @Test
	public void givenAConfigFile_whenLoadConfig_thenReturnProperty() throws Exception {
   	
    	Properties p = mock(Properties.class);
    	PowerMockito.whenNew(Properties.class).withNoArguments().thenReturn(p);
    	
    	PowerMockito.mockStatic(App.class);
    	ClassLoader cl = mock(ClassLoader.class);
    	when(App.class.getClassLoader()).thenReturn(cl);
    	InputStream is = mock(InputStream.class);
    	when(cl.getResourceAsStream(anyString())).thenReturn(is);

    	doNothing().when(p).load(eq(is));

    	fileUtil.loadConfig(fileName);
    	verify(p, times(1)).load(is);
    	PowerMockito.verifyStatic(App.class, times(1));
    	App.class.getClassLoader();

    }
    
}