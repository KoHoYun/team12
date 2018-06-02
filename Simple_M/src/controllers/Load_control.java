package controllers;

import models.*;
import views.*;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JTextPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Load_control {

	public Load_control(JFileChooser filechoose, JTextPane code) {
		filechoose.addChoosableFileFilter(new FileNameExtensionFilter("Text file", "txt"));
		filechoose.setAcceptAllFileFilterUsed(false);
		int returnVal = filechoose.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			try {
				File f = filechoose.getSelectedFile();
				new OpenFile(f, code);
			} catch (Exception event) {
				return;
			}

		}

	}	
}
