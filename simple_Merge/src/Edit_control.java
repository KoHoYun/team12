import javax.swing.JButton;
import javax.swing.JTextPane;

public class Edit_control {

	/*
	 * edit button�� ������ textpane�� ��������������, load button�� ���� �� ��� text file�� �ҷ��� �� ����
	 */
	public Edit_control(JTextPane a, JButton aButton, boolean editable, boolean push) {
		a.setEditable(editable);
		aButton.setEnabled(push);
	}
	
}
