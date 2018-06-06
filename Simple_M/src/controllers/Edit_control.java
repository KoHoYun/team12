package controllers;

import models.*;
import views.*;

import javax.swing.JButton;
import javax.swing.JTextPane;

public class Edit_control {
	
	 /* edit button�쓣 �늻瑜대㈃ textpane�씠 �닔�젙媛��뒫�빐吏�怨�, load button�� �늻瑜� �닔 �뾾�뼱�꽌 text file�쓣 遺덈윭�삱 �닔 �뾾�떎 */
	
	public Edit_control(JTextPane a, boolean editable) {
		a.setEditable(editable);
	}

}
