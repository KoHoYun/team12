
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
		// textarea�� �̹� �ٸ� ������ ���� �ִ� ���
		if (a != null) {
			a.setText("");
		}
		a.setText(reader.readLine());
		// line���� �޾Ƽ� text �� ���� ������ textarea�� ���Ӱ� �߰���Ŵ
		while ((line = reader.readLine()) != null) {
			a.setText(a.getText() + "\n" + line);
		}
		reader.close();
	}
}
