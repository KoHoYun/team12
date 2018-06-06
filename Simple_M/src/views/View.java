package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

public class View extends JFrame {

	private ImageIcon loadicon = new ImageIcon("icon\\load.png");
	private ImageIcon editicon = new ImageIcon("icon\\edit.png");
	private ImageIcon saveicon = new ImageIcon("icon\\save.png");
	private ImageIcon compareicon = new ImageIcon("icon\\compare.png");
	private ImageIcon rightic = new ImageIcon("icon\\right-arrow.png");
	private ImageIcon leftic = new ImageIcon("icon\\left-arrow.png");

	public JFileChooser filechoose = new JFileChooser();
	public JButton loadBtn1 = new JButton("LOAD", loadicon);
	public JButton editBtn1 = new JButton("EDIT", editicon);
	public JButton saveBtn1 = new JButton("SAVE", saveicon);
	public JButton loadBtn2 = new JButton("LOAD", loadicon);
	public JButton editBtn2 = new JButton("EDIT", editicon);
	public JButton saveBtn2 = new JButton("SAVE", saveicon);
	public JButton compareBtn = new JButton("COMPARE", compareicon);
	public JButton mergeBtn1 = new JButton("MERGE", leftic);
	public JButton mergeBtn2 = new JButton("MERGE", rightic);
	JLabel jlb = new JLabel(" ");

	JPanel buttons = new JPanel();
	JPanel logo = new JPanel();
	JPanel CMbtns = new JPanel();
	JPanel blank1 = new JPanel();
	JPanel blank2 = new JPanel();
	JTextArea menupane = new JTextArea();
	JScrollPane jp = new JScrollPane(menupane);
	private JTextPane leftcode = new JTextPane();
	private JTextPane rightcode = new JTextPane();
	JScrollPane scroll1 = new JScrollPane(leftcode);
	JScrollPane scroll2 = new JScrollPane(rightcode);

	protected ImageIcon createImageIcon(String path, String description) {
		java.net.URL imgURL = getClass().getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL, description);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}

	public View() {

		super();
		
		Dimension dim = new Dimension(1200, 800);

		JFrame view = new JFrame("gui");
		view.setBounds(120, 120, 1200, 800);
		view.setPreferredSize(dim);

		JPanel main = new JPanel();

		JMenuBar bar = new JMenuBar();
		JMenu help = new JMenu("Help");
		JMenuItem mi = new JMenuItem("도움말");
		bar.add(help);
		help.add(mi);
		view.add(jp, "Center");
		mi.setMnemonic('F');
		view.setJMenuBar(bar);

		leftcode.setEditable(false);
		rightcode.setEditable(false);

		main.setLayout(new BorderLayout());
		buttons.setLayout(new GridLayout(1, 7));

		buttons.add(loadBtn1);
		buttons.add(editBtn1);
		buttons.add(saveBtn1);
		buttons.add(logo);
		buttons.add(loadBtn2);
		buttons.add(editBtn2);
		buttons.add(saveBtn2);

		mergeBtn1.setBorderPainted(false);
		mergeBtn2.setBorderPainted(false);
		mergeBtn1.setContentAreaFilled(false);
		mergeBtn2.setContentAreaFilled(false);

		CMbtns.setLayout(new GridLayout(5, 1));
		CMbtns.add(blank1);
		CMbtns.add(compareBtn);
		CMbtns.add(blank2);
		CMbtns.add(mergeBtn1);
		CMbtns.add(mergeBtn2);

		main.add(buttons, "North");

		JPanel Frame = new JPanel();
		Frame.setLayout(new BoxLayout(Frame, BoxLayout.X_AXIS));
		leftcode.setPreferredSize(new Dimension(500, 700));
		rightcode.setPreferredSize(new Dimension(500, 700));

		setTextLeft(leftcode);
		setTextRight(rightcode);
		
		main.add(Frame, "Center");

		Frame.add(scroll1);
		Frame.add(CMbtns);
		Frame.add(scroll2);

		view.add(main);
		view.setVisible(true);
	}
	
	private void setTextLeft(JTextPane leftcode)
	{
		this.leftcode = leftcode;
	}
	
	public JTextPane getTextLeft()
	{
		return this.leftcode;
	}
	
	private void setTextRight(JTextPane leftcode)
	{
		this.rightcode = rightcode;
	}
	
	public JTextPane getTextRight()
	{
		return this.rightcode;
	}
}
