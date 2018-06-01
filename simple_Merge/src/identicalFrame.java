import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class identicalFrame {
	private JFrame jframe = new JFrame();
	private JButton OK_button = new JButton("OK");
	private JLabel label = new JLabel("Two files are identical");
	public identicalFrame()
	{
		jframe.add(label,"Center");
		jframe.add(OK_button,"South");
		
		OK_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jframe.dispose();
			}
		});
		
		jframe.setSize(200, 200);
		jframe.setVisible(true);
		jframe.setDefaultCloseOperation(jframe.EXIT_ON_CLOSE);
	}
}
