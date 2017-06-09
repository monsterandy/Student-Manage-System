package com.teamwork.sms.delete;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import mydatabase.Class;

@SuppressWarnings("serial")
public class DeleteClass extends JPanel implements ActionListener {

	JLabel classNameLabel = null;
	JTextField classNameText = null;
	JButton deleteButton = null;
	Class class1 = null;

	public DeleteClass() {
		class1 = new Class();
		SpringLayout layout = new SpringLayout();
		setLayout(layout);
		classNameLabel = new JLabel("Class:");
		classNameText = new JTextField(12);
		deleteButton = new JButton("Delete");
		deleteButton.addActionListener(this);
		add(classNameLabel);
		add(classNameText);
		add(deleteButton);
		layout.putConstraint(SpringLayout.WEST, classNameLabel, 144, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, classNameLabel, 20, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, classNameText, 10, SpringLayout.EAST, classNameLabel);
		layout.putConstraint(SpringLayout.NORTH, classNameText, 16, SpringLayout.NORTH, this);

		layout.putConstraint(SpringLayout.WEST, deleteButton, 193, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, deleteButton, 30, SpringLayout.SOUTH, classNameLabel);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == deleteButton) {
			String name;
			if ((name = classNameText.getText().trim()) == null || classNameText.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(null, "Empty Class Name!");
				return;
			}
			if (class1.delete(name)) {
				JOptionPane.showMessageDialog(null, "Delete Success!");
				classNameText.setText(null);
			} else {
				JOptionPane.showMessageDialog(null, "Unknow Error!");
			}
		}
	}

}
