package com.teamwork.sms.query;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

import javax.swing.*;

import mydatabase.Teacher;

@SuppressWarnings("serial")
public class CardTeacher extends JPanel implements ActionListener {

	JLabel teacherIDLabel = null;
	JTextField teacherIDText = null;
	JLabel teacherNameLabel = null;
	JTextField teacherNameText = null;
	JButton queryButton = null;
	JTextPane resultPane = null;
	JScrollPane resultScrollPane = null;
	Teacher teacher = null;
	Teacher tempTeacher = null;
	Stack<Teacher> teaStack = null;
	
	public CardTeacher() {
		teacher = new Teacher();
		SpringLayout layout = new SpringLayout();
		setLayout(layout);
		teacherIDLabel = new JLabel("ID:");
		teacherNameLabel = new JLabel("Name:");
		teacherIDText = new JTextField(12);
		teacherNameText = new JTextField(12);
		queryButton = new JButton("Query");
		queryButton.addActionListener(this);
		resultPane = new JTextPane();
		resultPane.setEditable(false);
		resultScrollPane = new JScrollPane(resultPane);
		resultScrollPane.setPreferredSize(new Dimension(380, 140));
		add(teacherIDLabel);
		add(teacherIDText);
		add(teacherNameLabel);
		add(teacherNameText);
		add(queryButton);
		add(resultScrollPane);
		layout.putConstraint(SpringLayout.WEST, teacherIDLabel, 150, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, teacherIDLabel, 10, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, teacherIDText, 10, SpringLayout.EAST, teacherIDLabel);
		layout.putConstraint(SpringLayout.NORTH, teacherIDText, 6, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, teacherNameLabel, 128, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, teacherNameLabel, 20, SpringLayout.SOUTH, teacherIDLabel);
		layout.putConstraint(SpringLayout.WEST, teacherNameText, 10, SpringLayout.EAST, teacherNameLabel);
		layout.putConstraint(SpringLayout.NORTH, teacherNameText, 10, SpringLayout.SOUTH, teacherIDText);

		layout.putConstraint(SpringLayout.WEST, queryButton, 193, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, queryButton, 10, SpringLayout.SOUTH, teacherNameLabel);
		layout.putConstraint(SpringLayout.WEST, resultScrollPane, 50, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, resultScrollPane, 10, SpringLayout.SOUTH, queryButton);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == queryButton) {
			resultPane.setText(null);
			String id = "";
			String name = "";
			String str;
			id = teacherIDText.getText().trim();
			name = teacherNameText.getText().trim();
			teaStack = teacher.search(id, name);
			if (teaStack.empty()) {
				QueryTools.insertMsg(resultPane, "No results found!", true, Color.RED, 14);
				return;
			}
			while (!teaStack.empty()) {
				tempTeacher = teaStack.pop();
				str = tempTeacher.id + " -- " + tempTeacher.name + "\n";
				QueryTools.insertMsg(resultPane, str, true, Color.BLACK, 14);
				str = tempTeacher.sex.trim() + " - " + tempTeacher.academy_name + "\n";
				QueryTools.insertMsg(resultPane, str, false, Color.BLACK, 13);
			}
			
		}
	}

}
