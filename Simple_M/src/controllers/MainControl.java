package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JTextPane;

import models.*;
import views.*;

public class MainControl {
	
	private View theView;
	private OpenFile theFile;
	
	public MainControl(View theView) {
		this.theView = theView;

		this.theView.loadBtn1.addActionListener((ActionListener) new ActionListener() {
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
				new Save_control(theView.saveBtn1, getTextLeft(),e);
			}
		});
		
		this.theView.saveBtn2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				new Save_control(theView.saveBtn2, getTextRight(),e);
			}
		});
		
		 this.theView.compareBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						new Compare_control(getTextLeft(), getTextRight());
						// mergeText
					} catch (IOException event) {
						return;
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					theView.compareBtn.setEnabled(false);
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
