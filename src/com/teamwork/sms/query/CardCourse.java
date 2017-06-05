package com.teamwork.sms.query;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

import javax.swing.*;

import mydatabase.Course;

@SuppressWarnings("serial")
public class CardCourse extends JPanel implements ActionListener {

	JLabel courseNameLabel = null;
	JTextField courseNameText = null;
	JButton queryButton = null;
	JTextPane resultPane = null;
	JScrollPane resultScrollPane = null;
	Course course = null;
	Course tempCourse = null;
	Stack<Course> couStack = null;

	public CardCourse() {
		course = new Course();
		SpringLayout layout = new SpringLayout();
		setLayout(layout);
		courseNameLabel = new JLabel("Course:");
		courseNameText = new JTextField(12);
		queryButton = new JButton("Query");
		queryButton.addActionListener(this);
		resultPane = new JTextPane();
		resultPane.setEditable(false);
		resultScrollPane = new JScrollPane(resultPane);
		resultScrollPane.setPreferredSize(new Dimension(380, 140));
		add(courseNameLabel);
		add(courseNameText);
		add(queryButton);
		add(resultScrollPane);

		layout.putConstraint(SpringLayout.WEST, courseNameLabel, 140, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, courseNameLabel, 10, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, courseNameText, 10, SpringLayout.EAST, courseNameLabel);
		layout.putConstraint(SpringLayout.NORTH, courseNameText, 6, SpringLayout.NORTH, this);

		layout.putConstraint(SpringLayout.WEST, queryButton, 193, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, queryButton, 30, SpringLayout.SOUTH, courseNameLabel);
		layout.putConstraint(SpringLayout.WEST, resultScrollPane, 50, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, resultScrollPane, 26, SpringLayout.SOUTH, queryButton);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == queryButton) {
			resultPane.setText(null);
			String name = "";
			String str;
			name = courseNameText.getText().trim();
			couStack = course.search(name);
			if (couStack.empty()) {
				QueryTools.insertMsg(resultPane, "No results found!", true, Color.RED, 14);
				return;
			}
			while (!couStack.empty()) {
				tempCourse = couStack.pop();
				str = tempCourse.name + "\n";
				QueryTools.insertMsg(resultPane, str, true, Color.BLACK, 14);
				str = "time: " + (tempCourse.time) + " - credit: " + (tempCourse.score) + " - "
						+ tempCourse.teacher_name + "\n";
				QueryTools.insertMsg(resultPane, str, false, Color.BLACK, 13);
			}
		}
	}

}
