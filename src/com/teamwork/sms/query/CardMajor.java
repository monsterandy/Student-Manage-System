package com.teamwork.sms.query;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

import javax.swing.*;

import mydatabase.Major;

@SuppressWarnings("serial")
public class CardMajor extends JPanel implements ActionListener {

	JLabel majorNameLabel = null;
	JTextField majorNameText = null;
	JButton queryButton = null;
	JTextPane resultPane = null;
	JScrollPane resultScrollPane = null;
	Major major = null;
	Major tempMajor = null;
	Stack<Major> majStack = null;

	public CardMajor() {
		major = new Major();
		SpringLayout layout = new SpringLayout();
		setLayout(layout);
		majorNameLabel = new JLabel("Major:");
		majorNameText = new JTextField(12);
		queryButton = new JButton("Query");
		queryButton.addActionListener(this);
		resultPane = new JTextPane();
		resultPane.setEditable(false);
		resultScrollPane = new JScrollPane(resultPane);
		resultScrollPane.setPreferredSize(new Dimension(380, 140));
		add(majorNameLabel);
		add(majorNameText);
		add(queryButton);
		add(resultScrollPane);

		layout.putConstraint(SpringLayout.WEST, majorNameLabel, 150, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, majorNameLabel, 10, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, majorNameText, 10, SpringLayout.EAST, majorNameLabel);
		layout.putConstraint(SpringLayout.NORTH, majorNameText, 6, SpringLayout.NORTH, this);

		layout.putConstraint(SpringLayout.WEST, queryButton, 193, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, queryButton, 30, SpringLayout.SOUTH, majorNameLabel);
		layout.putConstraint(SpringLayout.WEST, resultScrollPane, 50, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, resultScrollPane, 26, SpringLayout.SOUTH, queryButton);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == queryButton) {
			resultPane.setText(null);
			String name = "";
			String str;
			name = majorNameText.getText().trim();
			majStack = major.search(name);
			if (majStack.empty()) {
				QueryTools.insertMsg(resultPane, "No results found!", true, Color.RED, 14);
				return;
			}
			while (!majStack.empty()) {
				tempMajor = majStack.pop();
				str = "Find major name: " + tempMajor.name + "\n";
				QueryTools.insertMsg(resultPane, str, true, Color.BLACK, 14);
			}
		}
	}

}
