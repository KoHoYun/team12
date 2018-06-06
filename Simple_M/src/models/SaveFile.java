package models;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileWriter;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextPane;

public class SaveFile {
	public SaveFile(JButton button, JTextPane text, ActionEvent e) {
		if (e.getSource() == button) {
			JFileChooser sf = new JFileChooser();
			if (sf.showSaveDialog(button) == sf.APPROVE_OPTION) {
				try {
					String str = text.getText();
					if (str.length() < 1)
						return;
					File f = sf.getSelectedFile();
					FileWriter fw = new FileWriter(f+".txt");
					fw.write(str);
					fw.close();
					System.out.println("file이 잘 저장되었습니다");

				} catch (Exception ex) {
					System.out.println("파일 저장에 오류가 있습니다");	
				}
			}

		}
	}
}
