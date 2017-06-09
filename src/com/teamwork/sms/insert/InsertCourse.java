package com.teamwork.sms.insert;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import mydatabase.Course;

@SuppressWarnings("serial")
public class InsertCourse extends JPanel implements ActionListener {

	JLabel courseNameLabel = null;
	JTextField courseNameText = null;
	JLabel courseTimeLabel = null;
	JTextField courseTimeText = null;
	JLabel courseScoreLabel = null;
	JTextField courseScoreText = null;
	JLabel teacherNameLabel = null;
	JTextField teacherNameText = null;
	JButton insertButton = null;
	Course course = null;

	public InsertCourse() {
		course = new Course();
		SpringLayout layout = new SpringLayout();
		setLayout(layout);
		courseNameLabel = new JLabel("Name:");
		courseTimeLabel = new JLabel("Time:");
		courseScoreLabel = new JLabel("Credit:");
		teacherNameLabel = new JLabel("Teacher Name:");
		courseNameText = new JTextField(12);
		courseTimeText = new JTextField(12);
		courseScoreText	 = new JTextField(12);
		teacherNameText	= new JTextField(12);
		insertButton = new JButton("Insert");
		insertButton.addActionListener(this);
		add(courseNameLabel);
		add(courseNameText);
		add(courseTimeLabel);
		add(courseTimeText);
		add(courseScoreLabel);
		add(courseScoreText);
		add(teacherNameLabel);
		add(teacherNameText);
		add(insertButton);
		
		layout.putConstraint(SpringLayout.WEST, courseNameLabel, 150, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, courseNameLabel, 10, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, courseNameText, 10, SpringLayout.EAST, courseNameLabel);
		layout.putConstraint(SpringLayout.NORTH, courseNameText, 6, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, courseTimeLabel, 155, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, courseTimeLabel, 20, SpringLayout.SOUTH, courseNameLabel);
		layout.putConstraint(SpringLayout.WEST, courseTimeText, 10, SpringLayout.EAST, courseTimeLabel);
		layout.putConstraint(SpringLayout.NORTH, courseTimeText, 10, SpringLayout.SOUTH, courseNameText);

		layout.putConstraint(SpringLayout.WEST, courseScoreLabel, 148, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, courseScoreLabel, 20, SpringLayout.SOUTH, courseTimeLabel);
		layout.putConstraint(SpringLayout.WEST, courseScoreText, 10, SpringLayout.EAST, courseScoreLabel);
		layout.putConstraint(SpringLayout.NORTH, courseScoreText, 10, SpringLayout.SOUTH, courseTimeText);
		layout.putConstraint(SpringLayout.WEST, teacherNameLabel, 97, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, teacherNameLabel, 20, SpringLayout.SOUTH, courseScoreLabel);
		layout.putConstraint(SpringLayout.WEST, teacherNameText, 10, SpringLayout.EAST, teacherNameLabel);
		layout.putConstraint(SpringLayout.NORTH, teacherNameText, 10, SpringLayout.SOUTH, courseScoreText);

		layout.putConstraint(SpringLayout.WEST, insertButton, 193, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, insertButton, 30, SpringLayout.SOUTH, teacherNameLabel);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == insertButton) {
			String name;
			int time;
			double score;
			String teacher_name;
			if ((name = courseNameText.getText().trim()) == null || courseNameText.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(null, "Empty Course Name!");
				return;
			}
			if (courseTimeText.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(null, "Empty Course Time!");
				return;
			} else {
				time = Integer.parseInt(courseTimeText.getText().trim());
			}
			if (courseScoreText.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(null, "Empty Course Credit!");
				return;
			} else {
				score = Double.parseDouble(courseScoreText.getText().trim());
			}
			if ((teacher_name = teacherNameText.getText().trim()) == null || teacherNameText.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(null, "Empty Teacher Name!");
				return;
			}
			
			if (course.insert(name, time, score, teacher_name)) {
				JOptionPane.showMessageDialog(null, "Insert Success!");
				courseNameText.setText(null);
				courseTimeText.setText(null);
				courseScoreText.setText(null);
				teacherNameText.setText(null);
			} else {
				JOptionPane.showMessageDialog(null, "Unknow Error!");
			}

		}
	}

}
