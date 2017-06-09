package com.teamwork.sms.insert;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import mydatabase.StudentCourse;

@SuppressWarnings("serial")
public class InsertScore extends JPanel implements ActionListener {

	JLabel stuIDLabel = null;
	JTextField stuIDText = null;
	JLabel courseNameLabel = null;
	JTextField courseNameText = null;
	JLabel scoreLabel = null;
	JTextField scoreText = null;
	JButton insertButton = null;
	StudentCourse studentCourse = null;

	public InsertScore() {
		studentCourse = new StudentCourse();
		SpringLayout layout = new SpringLayout();
		setLayout(layout);
		stuIDLabel = new JLabel("Student ID:");
		courseNameLabel = new JLabel("Course Name:");
		scoreLabel = new JLabel("Score:");
		stuIDText = new JTextField(12);
		courseNameText = new JTextField(12);
		scoreText = new JTextField(12);
		insertButton = new JButton("Delete");
		insertButton.addActionListener(this);
		add(stuIDLabel);
		add(stuIDText);
		add(courseNameLabel);
		add(courseNameText);
		add(scoreLabel);
		add(scoreText);
		add(insertButton);

		layout.putConstraint(SpringLayout.WEST, stuIDLabel, 120, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, stuIDLabel, 10, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, stuIDText, 10, SpringLayout.EAST, stuIDLabel);
		layout.putConstraint(SpringLayout.NORTH, stuIDText, 6, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, courseNameLabel, 102, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, courseNameLabel, 20, SpringLayout.SOUTH, stuIDLabel);
		layout.putConstraint(SpringLayout.WEST, courseNameText, 10, SpringLayout.EAST, courseNameLabel);
		layout.putConstraint(SpringLayout.NORTH, courseNameText, 10, SpringLayout.SOUTH, stuIDText);

		layout.putConstraint(SpringLayout.WEST, scoreLabel, 152, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, scoreLabel, 20, SpringLayout.SOUTH, courseNameLabel);
		layout.putConstraint(SpringLayout.WEST, scoreText, 10, SpringLayout.EAST, scoreLabel);
		layout.putConstraint(SpringLayout.NORTH, scoreText, 10, SpringLayout.SOUTH, courseNameText);

		layout.putConstraint(SpringLayout.WEST, insertButton, 193, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, insertButton, 30, SpringLayout.SOUTH, scoreLabel);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == insertButton) {
			String student_id;
			String course_name;
			double score;
			if ((student_id = stuIDText.getText().trim()) == null || stuIDText.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(null, "Empty Student ID!");
				return;
			}
			if ((course_name = courseNameText.getText().trim()) == null || courseNameText.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(null, "Empty Course Name!");
				return;
			}
			if (scoreText.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(null, "Empty Course Score!");
				return;
			} else {
				score = Double.parseDouble(scoreText.getText().trim());
			}
			if (studentCourse.insert(student_id, course_name, score)) {
				JOptionPane.showMessageDialog(null, "Insert Success!");
				stuIDText.setText(null);
				courseNameText.setText(null);
				scoreText.setText(null);
			} else {
				JOptionPane.showMessageDialog(null, "Unknow Error!");
			}
		}
	}

}
