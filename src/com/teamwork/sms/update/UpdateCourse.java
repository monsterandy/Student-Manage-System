package com.teamwork.sms.update;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import mydatabase.Course;

@SuppressWarnings("serial")
public class UpdateCourse extends JPanel implements ActionListener {

	JLabel prevNameLabel = null;
	JTextField prevNameText = null;
	JLabel courseNameLabel = null;
	JTextField courseNameText = null;
	JLabel courseTimeLabel = null;
	JTextField courseTimeText = null;
	JLabel courseScoreLabel = null;
	JTextField courseScoreText = null;
	JButton updateButton = null;
	Course course = null;

	public UpdateCourse() {
		course = new Course();
		SpringLayout layout = new SpringLayout();
		setLayout(layout);
		prevNameLabel = new JLabel("* Previous Major Name:");
		prevNameText = new JTextField(8);
		courseNameLabel = new JLabel("Name:");
		courseTimeLabel = new JLabel("Time:");
		courseScoreLabel = new JLabel("Credit:");
		courseNameText = new JTextField(12);
		courseTimeText = new JTextField(12);
		courseScoreText = new JTextField(12);
		updateButton = new JButton("Update");
		updateButton.addActionListener(this);
		add(prevNameLabel);
		add(prevNameText);
		add(courseNameLabel);
		add(courseNameText);
		add(courseTimeLabel);
		add(courseTimeText);
		add(courseScoreLabel);
		add(courseScoreText);
		add(updateButton);
		layout.putConstraint(SpringLayout.WEST, prevNameLabel, 91, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, prevNameLabel, 10, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, prevNameText, 10, SpringLayout.EAST, prevNameLabel);
		layout.putConstraint(SpringLayout.NORTH, prevNameText, 6, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, courseNameLabel, 149, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, courseNameLabel, 20, SpringLayout.SOUTH, prevNameLabel);
		layout.putConstraint(SpringLayout.WEST, courseNameText, 10, SpringLayout.EAST, courseNameLabel);
		layout.putConstraint(SpringLayout.NORTH, courseNameText, 10, SpringLayout.SOUTH, prevNameText);

		layout.putConstraint(SpringLayout.WEST, courseTimeLabel, 155, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, courseTimeLabel, 20, SpringLayout.SOUTH, courseNameLabel);
		layout.putConstraint(SpringLayout.WEST, courseTimeText, 10, SpringLayout.EAST, courseTimeLabel);
		layout.putConstraint(SpringLayout.NORTH, courseTimeText, 10, SpringLayout.SOUTH, courseNameText);

		layout.putConstraint(SpringLayout.WEST, courseScoreLabel, 148, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, courseScoreLabel, 20, SpringLayout.SOUTH, courseTimeLabel);
		layout.putConstraint(SpringLayout.WEST, courseScoreText, 10, SpringLayout.EAST, courseScoreLabel);
		layout.putConstraint(SpringLayout.NORTH, courseScoreText, 10, SpringLayout.SOUTH, courseTimeText);

		layout.putConstraint(SpringLayout.WEST, updateButton, 193, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, updateButton, 30, SpringLayout.SOUTH, courseScoreLabel);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == updateButton) {
			String name;
			String toname;
			int time;
			double score;
			if ((name = prevNameText.getText().trim()) == null || prevNameText.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(null, "Empty Previous Name!");
				return;
			}
			if (courseNameText.getText().trim().equals("") && courseTimeText.getText().trim().equals("")
					&& courseScoreText.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(null, "Nothing Updated!");
				return;
			}
			toname = courseNameText.getText().trim();
			if (courseTimeText.getText().trim().equals("")) {
				time = 0;
			} else {
				time = Integer.parseInt(courseTimeText.getText().trim());
			}
			if (courseScoreText.getText().trim().equals("")) {
				score = 0;
			} else {
				score = Double.parseDouble(courseScoreText.getText().trim());
			}
			if (course.update(name, toname, time, score)) {
				JOptionPane.showMessageDialog(null, "Update Success!");
				courseNameText.setText(null);
				courseTimeText.setText(null);
				courseScoreText.setText(null);
			} else {
				JOptionPane.showMessageDialog(null, "Unknow Error!");
			}
		}
	}

}
