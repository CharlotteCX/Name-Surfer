/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import acm.program.*;
import java.awt.event.*;
import javax.swing.*;
import acm.graphics.*;

public class NameSurfer extends Program implements NameSurferConstants {

	/* Method: init() */
	/**
	 * This method has the responsibility for reading in the data base
	 * and initializing the interactors at the top of the window.
	 */
	public void init() {
	    // You fill this in, along with any helper methods //
		
		nameField = new JTextField(MAX_NAME);
		nameField.addActionListener(this);
		graphButton = new JButton("Graph");
		clearButton = new JButton("Clear");
		add(new JLabel("Name"), NORTH);
		add(nameField, NORTH);
		add(graphButton, NORTH);
		add(clearButton, NORTH);
		addActionListeners();
		
		 graph = new NameSurferGraph();
         add(graph);
	}

	/* Method: actionPerformed(e) */
	/**
	 * This class is responsible for detecting when the buttons are
	 * clicked, so you will have to define a method to respond to
	 * button actions.
	 */
	public void actionPerformed(ActionEvent e) {
		// You fill this in //
		Object source=e.getSource();
		if(source==nameField||source==graphButton){
			println("Graph: "+nameField.getText());
			println("Graph: "+nameDB.findEntry(nameField.getText()).toString());
		NameSurferEntry ranking=nameDB.findEntry(nameField.getText());
		
		if(ranking!=null){
			graph.addEntry(ranking);
			graph.update();
		}
	
		}
		else if(source==clearButton){
			
			graph.clear();
		}
	}
	
	//Private constants
	private static final int MAX_NAME = 25;
	
	//Private instance variables
	private JTextField nameField;
	private JButton graphButton;
	private JButton clearButton;
	
	private NameSurferDataBase nameDB =new NameSurferDataBase("names-data.txt");
	private NameSurferGraph graph;
}
