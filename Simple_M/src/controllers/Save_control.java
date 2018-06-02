package controllers;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileWriter;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextPane;

import models.*;
import views.*;

public class Save_control {

	public Save_control(JButton button, JTextPane text, ActionEvent e) {
		if (e.getSource() == button) {
			JFileChooser sf = new JFileChooser();
			if (sf.showSaveDialog(button) == sf.APPROVE_OPTION) {
				try {
					String str = text.getText().trim();
					if (str.length() < 1)
						return;
					File f = sf.getSelectedFile();
					FileWriter fw = new FileWriter(f);
					fw.write(str);
					fw.close();

				} catch (Exception ex) {
				}
			}

		}
	}
	
}
