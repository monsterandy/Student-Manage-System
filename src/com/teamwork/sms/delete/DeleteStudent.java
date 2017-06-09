package com.teamwork.sms.delete;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import mydatabase.Student;

@SuppressWarnings("serial")
public class DeleteStudent extends JPanel implements ActionListener {

	JLabel stuNameLabel = null;
	JTextField stuNameText = null;
	JButton deleteButton = null;
	Student student = null;

	public DeleteStudent() {
		student = new Student();
		SpringLayout layout = new SpringLayout();
		setLayout(layout);
		stuNameLabel = new JLabel("Name:");
		stuNameText = new JTextField(12);
		deleteButton = new JButton("Delete");
		deleteButton.addActionListener(this);
		add(stuNameLabel);
		add(stuNameText);
		add(deleteButton);
		layout.putConstraint(SpringLayout.WEST, stuNameLabel, 144, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, stuNameLabel, 20, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, stuNameText, 10, SpringLayout.EAST, stuNameLabel);
		layout.putConstraint(SpringLayout.NORTH, stuNameText, 16, SpringLayout.NORTH, this);

		layout.putConstraint(SpringLayout.WEST, deleteButton, 193, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, deleteButton, 30, SpringLayout.SOUTH, stuNameLabel);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == deleteButton) {
			String name;
			if ((name = stuNameText.getText().trim()) == null || stuNameText.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(null, "Empty Student Name!");
				return;
			}
			if (student.delete(name)) {
				JOptionPane.showMessageDialog(null, "Delete Success!");
				stuNameText.setText(null);
			} else {
				JOptionPane.showMessageDialog(null, "Unknow Error!");
			}
		}
	}

}
