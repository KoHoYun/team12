package controllers;

import models.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileWriter;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextPane;

import models.*;
import views.*;

public class Save_control {

	public Save_control(JButton button, JTextPane text, ActionEvent e)
	{
		new SaveFile(button, text, e);
	}
	
}
