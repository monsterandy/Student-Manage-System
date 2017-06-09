package com.teamwork.sms.delete;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import mydatabase.StudentCourse;

@SuppressWarnings("serial")
public class DeleteScore extends JPanel implements ActionListener {

	JLabel stuIDLabel = null;
	JTextField stuIDText = null;
	JLabel courseNameLabel = null;
	JTextField courseNameText = null;
	JButton deleteButton = null;
	StudentCourse studentCourse = null;

	public DeleteScore() {
		studentCourse = new StudentCourse();
		SpringLayout layout = new SpringLayout();
		setLayout(layout);
		stuIDLabel = new JLabel("Student ID:");
		courseNameLabel = new JLabel("Course Name:");
		stuIDText = new JTextField(12);
		courseNameText = new JTextField(12);
		deleteButton = new JButton("Delete");
		deleteButton.addActionListener(this);
		add(stuIDLabel);
		add(stuIDText);
		add(courseNameLabel);
		add(courseNameText);
		add(deleteButton);

		layout.putConstraint(SpringLayout.WEST, stuIDLabel, 120, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, stuIDLabel, 10, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, stuIDText, 10, SpringLayout.EAST, stuIDLabel);
		layout.putConstraint(SpringLayout.NORTH, stuIDText, 6, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, courseNameLabel, 102, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, courseNameLabel, 20, SpringLayout.SOUTH, stuIDLabel);
		layout.putConstraint(SpringLayout.WEST, courseNameText, 10, SpringLayout.EAST, courseNameLabel);
		layout.putConstraint(SpringLayout.NORTH, courseNameText, 10, SpringLayout.SOUTH, stuIDText);

		layout.putConstraint(SpringLayout.WEST, deleteButton, 193, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, deleteButton, 30, SpringLayout.SOUTH, courseNameLabel);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == deleteButton) {
			String student_id;
			String course_name;
			if (stuIDText.getText().trim().equals("") && courseNameText.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(null, "Empty Student ID and Course Name!");
				return;
			}
			student_id = stuIDText.getText().trim();
			course_name = courseNameText.getText().trim();
			if (studentCourse.delete(student_id, course_name)) {
				JOptionPane.showMessageDialog(null, "Delete Success!");
				stuIDText.setText(null);
				courseNameText.setText(null);
			} else {
				JOptionPane.showMessageDialog(null, "Unknow Error!");
			}
		}
	}

}
