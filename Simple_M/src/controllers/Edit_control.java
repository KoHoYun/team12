package controllers;

import models.*;
import views.*;

import javax.swing.JButton;
import javax.swing.JTextPane;

public class Edit_control {
	
	 /*text area can editable*/
	
	public Edit_control(JTextPane a, boolean editable) {
		a.setEditable(editable);
	}

}
