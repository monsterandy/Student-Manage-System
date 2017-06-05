package com.teamwork.sms.query;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

import javax.swing.*;

import mydatabase.StudentCourse;

@SuppressWarnings("serial")
public class CardScore extends JPanel implements ActionListener {

	JLabel stuIDLabel = null;
	JTextField stuIDText = null;
	JLabel courseNameLabel = null;
	JTextField courseNameText = null;
	JButton queryButton = null;
	JTextPane resultPane = null;
	JScrollPane resultScrollPane = null;
	StudentCourse studentCourse = null;
	StudentCourse tempCourse = null;
	Stack<StudentCourse> couStack = null;

	public CardScore() {
		studentCourse = new StudentCourse();
		SpringLayout layout = new SpringLayout();
		setLayout(layout);
		stuIDLabel = new JLabel("ID:");
		courseNameLabel = new JLabel("Course Name:");
		stuIDText = new JTextField(12);
		courseNameText = new JTextField(12);
		queryButton = new JButton("Query");
		queryButton.addActionListener(this);
		resultPane = new JTextPane();
		resultPane.setEditable(false);
		resultScrollPane = new JScrollPane(resultPane);
		resultScrollPane.setPreferredSize(new Dimension(380, 140));
		add(stuIDLabel);
		add(stuIDText);
		add(courseNameLabel);
		add(courseNameText);
		add(queryButton);
		add(resultScrollPane);

		layout.putConstraint(SpringLayout.WEST, stuIDLabel, 170, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, stuIDLabel, 10, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, stuIDText, 10, SpringLayout.EAST, stuIDLabel);
		layout.putConstraint(SpringLayout.NORTH, stuIDText, 6, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, courseNameLabel, 100, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, courseNameLabel, 20, SpringLayout.SOUTH, stuIDLabel);
		layout.putConstraint(SpringLayout.WEST, courseNameText, 10, SpringLayout.EAST, courseNameLabel);
		layout.putConstraint(SpringLayout.NORTH, courseNameText, 10, SpringLayout.SOUTH, stuIDText);

		layout.putConstraint(SpringLayout.WEST, queryButton, 193, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, queryButton, 10, SpringLayout.SOUTH, courseNameLabel);
		layout.putConstraint(SpringLayout.WEST, resultScrollPane, 50, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, resultScrollPane, 10, SpringLayout.SOUTH, queryButton);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == queryButton) {
			resultPane.setText(null);
			if (stuIDText.getText().trim().equals("")) {
	    		JOptionPane.showMessageDialog(null, "Please Input Student ID！");
	    		return;
			}
			String id = "";
			String name = "";
			String str;
			id = stuIDText.getText().trim();
			name = courseNameText.getText().trim();
			couStack = studentCourse.search(id, name);
			if (couStack.empty()) {
				QueryTools.insertMsg(resultPane, "No results found!", true, Color.RED, 14);
				return;
			}
			while (!couStack.empty()) {
				tempCourse = couStack.pop();
				str = tempCourse.student_id + " -- " + tempCourse.course_name + " -- ";
				QueryTools.insertMsg(resultPane, str, true, Color.BLACK, 14);
				str = tempCourse.score + "\n";
				QueryTools.insertMsg(resultPane, str, true, Color.RED, 15);
			}
		}
	}

}
