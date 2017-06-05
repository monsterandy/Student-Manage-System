package com.teamwork.sms.query;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

import javax.swing.*;

import mydatabase.Class;

@SuppressWarnings("serial")
public class CardClass extends JPanel implements ActionListener {
	
	JLabel classNameLabel = null;
	JTextField classNameText = null;
	JButton queryButton = null;
	JTextPane resultPane = null;
	JScrollPane resultScrollPane = null;
	Class class1 = null;
	Class tempClass = null;
	Stack<Class> claStack = null;
	
	public CardClass() {
		class1 = new Class();
		SpringLayout layout = new SpringLayout();
		setLayout(layout);
		classNameLabel = new JLabel("Class:");
		classNameText = new JTextField(12);
		queryButton = new JButton("Query");
		queryButton.addActionListener(this);
		resultPane = new JTextPane();
		resultPane.setEditable(false);
		resultScrollPane = new JScrollPane(resultPane);
		resultScrollPane.setPreferredSize(new Dimension(380, 140));
		add(classNameLabel);
		add(classNameText);
		add(queryButton);
		add(resultScrollPane);

		layout.putConstraint(SpringLayout.WEST, classNameLabel, 150, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, classNameLabel, 10, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, classNameText, 10, SpringLayout.EAST, classNameLabel);
		layout.putConstraint(SpringLayout.NORTH, classNameText, 6, SpringLayout.NORTH, this);

		layout.putConstraint(SpringLayout.WEST, queryButton, 193, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, queryButton, 30, SpringLayout.SOUTH, classNameLabel);
		layout.putConstraint(SpringLayout.WEST, resultScrollPane, 50, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, resultScrollPane, 26, SpringLayout.SOUTH, queryButton);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == queryButton) {
			resultPane.setText(null);
			String name = "";
			String str;
			name = classNameText.getText().trim();
			claStack = class1.search(name);
			if (claStack.empty()) {
				QueryTools.insertMsg(resultPane, "No results found!", true, Color.RED, 14);
				return;
			}
			while (!claStack.empty()) {
				tempClass = claStack.pop();
				str = "Find class name: " + tempClass.name + "班\n";
				QueryTools.insertMsg(resultPane, str, true, Color.BLACK, 14);
			}
		}
	}

}
