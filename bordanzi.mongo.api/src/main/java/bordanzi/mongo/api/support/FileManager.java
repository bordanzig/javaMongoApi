package bordanzi.mongo.api.support;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileManager {
	
	private ArrayList<String> fileMemory;

	public String getNextLine() {
		return fileMemory.remove(0);
	}

	public boolean hasLines() {
		return !fileMemory.isEmpty();
	}

	public void initFile(String file) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line = null;
		fileMemory = new ArrayList<String>();
		
		line = reader.readLine();
		
		while((line = reader.readLine())!=null){	
			fileMemory.add(line);
		}
		reader.close();
		
	}

}
