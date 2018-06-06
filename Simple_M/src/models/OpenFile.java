package models;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JTextPane;

public class OpenFile {

	public OpenFile(File f, JTextPane a) throws IOException{
		FileReader filereader = new FileReader(f);
		BufferedReader reader = new BufferedReader(filereader);
		String line = null;
		// JTextpane makes clean
		if (a != null) {
			a.setText("");
		}
		a.setText(reader.readLine());
		// read one line at a time
		while ((line = reader.readLine()) != null) {
			a.setText(a.getText() + "\n" + line);
		}
		reader.close();
	}
	
}
