package com.teamwork.sms.query;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

import javax.swing.*;

import mydatabase.Academy;

@SuppressWarnings("serial")
public class CardAcademy extends JPanel implements ActionListener {

	JLabel academyNameLabel = null;
	JTextField academyNameText = null;
	JLabel academyLeaderLabel = null;
	JTextField academyLeaderText = null;
	JButton queryButton = null;
	JTextPane resultPane = null;
	JScrollPane resultScrollPane = null;
	Academy academy = null;
	Academy tempAcademy = null;
	Stack<Academy> acaStack = null;

	public CardAcademy() {
		academy = new Academy();
		SpringLayout layout = new SpringLayout();
		setLayout(layout);
		academyNameLabel = new JLabel("Academy Name:");
		academyLeaderLabel = new JLabel("Leader:");
		academyNameText = new JTextField(12);
		academyLeaderText = new JTextField(12);
		queryButton = new JButton("Query");
		queryButton.addActionListener(this);
		resultPane = new JTextPane();
		resultPane.setEditable(false);
		resultScrollPane = new JScrollPane(resultPane);
		resultScrollPane.setPreferredSize(new Dimension(380, 140));
		add(academyNameLabel);
		add(academyNameText);
		add(academyLeaderLabel);
		add(academyLeaderText);
		add(queryButton);
		add(resultScrollPane);
		layout.putConstraint(SpringLayout.WEST, academyNameLabel, 82, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, academyNameLabel, 10, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, academyNameText, 10, SpringLayout.EAST, academyNameLabel);
		layout.putConstraint(SpringLayout.NORTH, academyNameText, 6, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, academyLeaderLabel, 138, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, academyLeaderLabel, 20, SpringLayout.SOUTH, academyNameLabel);
		layout.putConstraint(SpringLayout.WEST, academyLeaderText, 10, SpringLayout.EAST, academyLeaderLabel);
		layout.putConstraint(SpringLayout.NORTH, academyLeaderText, 10, SpringLayout.SOUTH, academyNameText);

		layout.putConstraint(SpringLayout.WEST, queryButton, 193, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, queryButton, 10, SpringLayout.SOUTH, academyLeaderLabel);
		layout.putConstraint(SpringLayout.WEST, resultScrollPane, 50, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, resultScrollPane, 10, SpringLayout.SOUTH, queryButton);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == queryButton) {
			resultPane.setText(null);
			String name = "";
			String leader = "";
			String str;
			name = academyNameText.getText().trim();
			leader = academyLeaderText.getText().trim();
			acaStack = academy.search(name, leader);
			if (acaStack.empty()) {
				QueryTools.insertMsg(resultPane, "No results found!", true, Color.RED, 14);
			}
			while(!acaStack.empty()) {
				tempAcademy = acaStack.pop();
				str = tempAcademy.name + " -- " + tempAcademy.leader + "\n";
				QueryTools.insertMsg(resultPane, str, true, Color.BLACK, 14);
			}
		}
	}

}
