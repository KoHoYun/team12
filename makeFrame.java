import java.awt.*;
import java.util.*;
import java.io.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.filechooser.*;

//jframe으로 판 만들기
public class makeFrame {
	JFrame jframe = new JFrame();
	JPanel leftP = new JPanel();
	JPanel centerP = new JPanel();
	JPanel rightP = new JPanel();
	JPanel button = new JPanel();
	JPanel button2 = new JPanel();

	private JFileChooser filechoose = new JFileChooser();

	private JButton load = new JButton("load");
	private JButton edit = new JButton("edit");
	private JButton save = new JButton("save");
	private JButton load2 = new JButton("load");
	private JButton edit2 = new JButton("edit");
	private JButton save2 = new JButton("save");
	private JButton cmpare = new JButton("compare");
	private JButton mergeRi = new JButton("merge >>");
	private JButton mergeLe = new JButton("merge <<");

	JTextArea jta = new JTextArea();
	JTextArea jta2 = new JTextArea();
	JScrollPane scroll = new JScrollPane(jta);
	JScrollPane scroll2 = new JScrollPane(jta2);

	public makeFrame() {
		button.setLayout(new GridLayout(1, 3));
		button.add(load);
		button.add(edit);
		button.add(save);
		load.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filechoose.addChoosableFileFilter(new FileNameExtensionFilter("Text file", "txt"));
				filechoose.setAcceptAllFileFilterUsed(false);
				int returnVal = filechoose.showOpenDialog(null);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					try {
						File f = filechoose.getSelectedFile();
						fileopen(f, jta);
					} catch (Exception event) {
						return;
					}
				}
			}
		});
		edit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setButton(false, true);
			}
		});
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setButton(true, false);
			}
		});

		button2.setLayout(new GridLayout(1, 3));
		button2.add(load2);
		button2.add(edit2);
		button2.add(save2);
		load2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filechoose.addChoosableFileFilter(new FileNameExtensionFilter("Text file", "txt"));
				filechoose.setAcceptAllFileFilterUsed(false);
				int returnVal = filechoose.showOpenDialog(null);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					try {
						File f = filechoose.getSelectedFile();
						fileopen(f, jta2);
					} catch (IOException event) {
						return;
					}
				}
			}
		});
		edit2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setButton2(false, true);
			}
		});
		save2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setButton2(true, false);
			}
		});

		leftP.setLayout(new GridLayout(2, 1));
		leftP.add(button);
		leftP.setPreferredSize(new Dimension(280, 580));
		leftP.add(scroll);
		jta.setEditable(false);
		/**********************************************************************/
		jta.addMouseListener(new MouseAdapter() {
		         @Override
		         public void mouseClicked(MouseEvent e) {
		         
		          try {
					int line = jta.getLineOfOffset(jta.getCaretPosition());
				} catch (BadLocationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		          
		          mergeLe.addActionListener(new ActionListener() {
		  			public void actionPerformed(ActionEvent e) {		
		  				//mergeText.mergeToLeft(line)--> 클릭된 줄 라인 번호 넣어주기 
		  			}
		  		});
		          
		          mergeRi.addActionListener(new ActionListener() {
		  			public void actionPerformed(ActionEvent e) {		
		  				//mergeText.mergeToRight(line)--> 클릭된 줄 라인 번호 넣어주기 
		  			}
		  		});
		         }
		      });
		
		
		/******************************************************************************/
		/**********************************************************************/
		jta2.addMouseListener(new MouseAdapter() {
		         @Override
		         public void mouseClicked(MouseEvent e) {
		         
		          try {
					int line = jta.getLineOfOffset(jta.getCaretPosition());
				} catch (BadLocationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		          
		          mergeLe.addActionListener(new ActionListener() {
		  			public void actionPerformed(ActionEvent e) {		
		  				//mergeText.mergeToLeft(line)--> 클릭된 줄 라인 번호 넣어주기 
		  			}
		  		});
		          
		          mergeRi.addActionListener(new ActionListener() {
		  			public void actionPerformed(ActionEvent e) {		
		  				//mergeText.mergeToRight(line)--> 클릭된 줄 라인 번호 넣어주기 
		  			}
		  		});
		         }
		      });
		
		
		/******************************************************************************/
		centerP.setLayout(new GridLayout(2, 1));
		centerP.add(button2);
		centerP.setPreferredSize(new Dimension(280, 580));
		centerP.add(scroll2);
		jta2.setEditable(false);
		
		rightP.setLayout(new GridLayout(3, 1));
		rightP.add(cmpare);
		rightP.add(mergeLe);
		rightP.add(mergeRi);
		rightP.setPreferredSize(new Dimension(100, 550));
		cmpare.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {		
				try
				{
					new Compare(jta, jta2);
					//mergeText에 결과 textarea 두개 넣어주기 어레이리스트도? 
				}
				catch(IOException event)
				{
					return;
				}
				
			}
		});
		
		jframe.add(leftP, "West");
		jframe.add(rightP, "Center");
		jframe.add(centerP, "East");

		jframe.setSize(700, 600);
		jframe.setVisible(true);

		jframe.setDefaultCloseOperation(jframe.EXIT_ON_CLOSE);

	}

	void setButton(boolean a, boolean b) {
		load.setEnabled(a);
		jta.setEditable(b);
	}

	void setButton2(boolean a, boolean b) {
		load2.setEnabled(a);
		jta2.setEditable(b);
	}

	void fileopen(File f, JTextArea a) throws FileNotFoundException, IOException {
		FileReader filereader = new FileReader(f);
		BufferedReader reader = new BufferedReader(filereader);

		String line = null;
		// textarea에 이미 다른 파일이 열려 있는 경우
		if (a != null) {
			a.setText("");
		}
		// line으로 받아서 text 다 읽을 때까지 textarea에 새롭게 추가시킴
		while ((line = reader.readLine()) != null) {
			a.append(line);
			a.append("\n");
		}
		reader.close();
	}
}
