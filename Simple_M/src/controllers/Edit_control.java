package controllers;

import models.*;
import views.*;

import javax.swing.JButton;
import javax.swing.JTextPane;

public class Edit_control {
	
	 /* edit button을 누르면 textpane이 수정가능해지고, load button은 누를 수 없어서 text file을 불러올 수 없다 */
	
	public Edit_control(JTextPane a, JButton aButton, boolean editable, boolean push) {
		a.setEditable(editable);
		aButton.setEnabled(push);
	}

}
