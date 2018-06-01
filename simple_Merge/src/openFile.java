
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JTextPane;

public class openFile {
	
	public openFile(File f, JTextPane a) throws IOException{
		FileReader filereader = new FileReader(f);
		BufferedReader reader = new BufferedReader(filereader);
		String line = null;
		// textarea에 이미 다른 파일이 열려 있는 경우
		if (a != null) {
			a.setText("");
		}
		a.setText(reader.readLine());
		// line으로 받아서 text 다 읽을 때까지 textarea에 새롭게 추가시킴
		while ((line = reader.readLine()) != null) {
			a.setText(a.getText() + "\n" + line);
		}
		reader.close();
	}
}
