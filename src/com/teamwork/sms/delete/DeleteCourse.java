package com.teamwork.sms.delete;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import mydatabase.Course;

@SuppressWarnings("serial")
public class DeleteCourse extends JPanel implements ActionListener {

	JLabel courseNameLabel = null;
	JTextField courseNameText = null;
	JButton deleteButton = null;
	Course course = null;

	public DeleteCourse() {
		course = new Course();
		SpringLayout layout = new SpringLayout();
		setLayout(layout);
		courseNameLabel = new JLabel("Name:");
		courseNameText = new JTextField(12);
		deleteButton = new JButton("Delete");
		deleteButton.addActionListener(this);
		add(courseNameLabel);
		add(courseNameText);
		add(deleteButton);
		layout.putConstraint(SpringLayout.WEST, courseNameLabel, 144, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, courseNameLabel, 20, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, courseNameText, 10, SpringLayout.EAST, courseNameLabel);
		layout.putConstraint(SpringLayout.NORTH, courseNameText, 16, SpringLayout.NORTH, this);

		layout.putConstraint(SpringLayout.WEST, deleteButton, 193, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, deleteButton, 30, SpringLayout.SOUTH, courseNameLabel);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == deleteButton) {
			String name;
			if ((name = courseNameText.getText().trim()) == null || courseNameText.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(null, "Empty Course Name!");
				return;
			}
			if (course.delete(name)) {
				JOptionPane.showMessageDialog(null, "Delete Success!");
				courseNameText.setText(null);
			} else {
				JOptionPane.showMessageDialog(null, "Unknow Error!");
			}
		}
	}

}
