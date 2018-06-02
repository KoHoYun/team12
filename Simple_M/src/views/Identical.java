package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Identical {
	private JFrame jframe = new JFrame();
	private JButton OK_button = new JButton("OK");
	private JLabel label = new JLabel("Two files are identical");
	public Identical(boolean bool)
	{
		jframe.add(label,"Center");
		jframe.add(OK_button,"South");
		
		OK_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jframe.dispose();
			}
		});
		
		jframe.setSize(200, 200);
		jframe.setVisible(bool);
		jframe.setDefaultCloseOperation(jframe.EXIT_ON_CLOSE);
	}
}
