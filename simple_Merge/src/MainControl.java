import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JTextPane;

public class MainControl {

	private View theView;
	private openFile theFile;

	/*
	 * View랑 연결된 부분
	 */
	public MainControl(View theView) {
		this.theView = theView;

		this.theView.loadBtn1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				new Load_control(getFileChoose(), getTextLeft());
			}
		});
		this.theView.loadBtn2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				new Load_control(getFileChoose(), getTextRight());
			}
		});

		this.theView.editBtn1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				new Edit_control(getTextLeft(), theView.loadBtn1, true, false);
			}
		});

		this.theView.editBtn2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				new Edit_control(getTextRight(), theView.loadBtn2, true, false);
			}
		});

		this.theView.saveBtn1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// 파일 save 할 수 있게
				new Save_control(theView.saveBtn1, getTextLeft(),e);
			}
		});
		
		this.theView.saveBtn2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// 파일 save 할 수 있게
				new Save_control(theView.saveBtn2, getTextRight(),e);
			}
		});
		
		 this.theView.compareBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						new Compare_control(getTextLeft(), getTextRight());
						// mergeText에 결과 textarea 두개 넣어주기 어레이리스트도?
					} catch (IOException event) {
						return;
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
			});
	}

	public JFileChooser getFileChoose() {
		return theView.filechoose;
	}

	public JTextPane getTextLeft() {
		return theView.leftcode;
	}

	public JTextPane getTextRight() {
		return theView.rightcode;
	}

}
