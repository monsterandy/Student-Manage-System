package com.teamwork.sms.query;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

import javax.swing.*;

import mydatabase.Dormitory;

@SuppressWarnings("serial")
public class CardDormitory extends JPanel implements ActionListener {

	JLabel dormitoryNameLabel = null;
	JTextField dormitoryNameText = null;
	JButton queryButton = null;
	JTextPane resultPane = null;
	JScrollPane resultScrollPane = null;
	Dormitory dormitory = null;
	Dormitory tempDormitory = null;
	Stack<Dormitory> domStack = null;
	
	public CardDormitory() {
		dormitory = new Dormitory();
		SpringLayout layout = new SpringLayout();
		setLayout(layout);
		dormitoryNameLabel = new JLabel("Dormitory:");
		dormitoryNameText = new JTextField(12);
		queryButton = new JButton("Query");
		queryButton.addActionListener(this);
		resultPane = new JTextPane();
		resultPane.setEditable(false);
		resultScrollPane = new JScrollPane(resultPane);
		resultScrollPane.setPreferredSize(new Dimension(380, 140));
		add(dormitoryNameLabel);
		add(dormitoryNameText);
		add(queryButton);
		add(resultScrollPane);

		layout.putConstraint(SpringLayout.WEST, dormitoryNameLabel, 130, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, dormitoryNameLabel, 10, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, dormitoryNameText, 10, SpringLayout.EAST, dormitoryNameLabel);
		layout.putConstraint(SpringLayout.NORTH, dormitoryNameText, 6, SpringLayout.NORTH, this);

		layout.putConstraint(SpringLayout.WEST, queryButton, 193, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, queryButton, 30, SpringLayout.SOUTH, dormitoryNameLabel);
		layout.putConstraint(SpringLayout.WEST, resultScrollPane, 50, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, resultScrollPane, 26, SpringLayout.SOUTH, queryButton);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == queryButton) {
			resultPane.setText(null);
			String name = "";
			String str;
			name = dormitoryNameText.getText().trim();
			domStack = dormitory.search(name);
			if (domStack.empty()) {
				QueryTools.insertMsg(resultPane, "No results found!", true, Color.RED, 14);
				return;
			}
			while (!domStack.empty()) {
				tempDormitory = domStack.pop();
				str = "Find dormitory name: " + tempDormitory.name + "\n";
				QueryTools.insertMsg(resultPane, str, true, Color.BLACK, 14);
			}
		}
	}
}
