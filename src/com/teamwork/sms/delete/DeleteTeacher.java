package com.teamwork.sms.delete;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import mydatabase.Teacher;

@SuppressWarnings("serial")
public class DeleteTeacher extends JPanel implements ActionListener {

	JLabel teacherNameLabel = null;
	JTextField teacherNameText = null;
	JButton deleteButton = null;
	Teacher teacher = null;

	public DeleteTeacher() {
		teacher = new Teacher();
		SpringLayout layout = new SpringLayout();
		setLayout(layout);
		teacherNameLabel = new JLabel("Name:");
		teacherNameText = new JTextField(12);
		deleteButton = new JButton("Delete");
		deleteButton.addActionListener(this);
		add(teacherNameLabel);
		add(teacherNameText);
		add(deleteButton);
		layout.putConstraint(SpringLayout.WEST, teacherNameLabel, 144, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, teacherNameLabel, 20, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, teacherNameText, 10, SpringLayout.EAST, teacherNameLabel);
		layout.putConstraint(SpringLayout.NORTH, teacherNameText, 16, SpringLayout.NORTH, this);

		layout.putConstraint(SpringLayout.WEST, deleteButton, 193, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, deleteButton, 30, SpringLayout.SOUTH, teacherNameLabel);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == deleteButton) {
			String name;
			if ((name = teacherNameText.getText().trim()) == null || teacherNameText.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(null, "Empty Teacher Name!");
				return;
			}
			if (teacher.delete(name)) {
				JOptionPane.showMessageDialog(null, "Delete Success!");
				teacherNameText.setText(null);
			} else {
				JOptionPane.showMessageDialog(null, "Unknow Error!");
			}
		}
	}

}
