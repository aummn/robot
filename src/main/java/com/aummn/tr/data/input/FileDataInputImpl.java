package com.aummn.tr.data.input;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.aummn.tr.util.FileUtil;

/**
 * This class is for reading inputs from files. 
 *
 *
 * @author James Jin
 * 
 */
public class FileDataInputImpl implements DataInput {
	private String folder;
	private List<String> fileNames;
	private Iterator<String> iterator;
	private FileUtil fileUtil;
	
	public FileDataInputImpl(String folder, List<String> fileNames) {
		this.folder = folder;
		this.fileNames = fileNames;
		this.iterator = fileNames.listIterator();
		this.fileUtil = new FileUtil();
	}
	
	@Override
	public List<String> getInput() {
		List<String> list = new ArrayList<>();
		if (iterator.hasNext()) {
			list = fileUtil.readInput(this.getFolder(), iterator.next());
		}
		return list;
	}
	
	@Override
	public boolean hasNextInput() {
		return iterator.hasNext();
	}

	public String getFolder() {
		return folder;
	}

	public void setFolder(String folder) {
		this.folder = folder;
	}

	public FileUtil getFileUtil() {
		return fileUtil;
	}

	public void setFileUtil(FileUtil fileUtil) {
		this.fileUtil = fileUtil;
	}

}
