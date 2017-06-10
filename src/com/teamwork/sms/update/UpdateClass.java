package com.teamwork.sms.update;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import mydatabase.Class;

@SuppressWarnings("serial")
public class UpdateClass extends JPanel implements ActionListener {

	JLabel prevNameLabel = null;
	JTextField prevNameText = null;
	JLabel classNameLabel = null;
	JTextField classNameText = null;
	JButton updateButton = null;
	Class class1 = null;

	
	public UpdateClass() {
		class1 = new Class();
		SpringLayout layout = new SpringLayout();
		setLayout(layout);
		prevNameLabel = new JLabel("* Previous Major Name:");
		prevNameText = new JTextField(8);
		classNameLabel = new JLabel("Class:");
		classNameText = new JTextField(12);
		updateButton = new JButton("Update");
		updateButton.addActionListener(this);
		add(prevNameLabel);
		add(prevNameText);
		add(classNameLabel);
		add(classNameText);
		add(updateButton);

		layout.putConstraint(SpringLayout.WEST, prevNameLabel, 90, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, prevNameLabel, 10, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, prevNameText, 10, SpringLayout.EAST, prevNameLabel);
		layout.putConstraint(SpringLayout.NORTH, prevNameText, 6, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, classNameLabel, 149, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, classNameLabel, 20, SpringLayout.SOUTH, prevNameLabel);
		layout.putConstraint(SpringLayout.WEST, classNameText, 10, SpringLayout.EAST, classNameLabel);
		layout.putConstraint(SpringLayout.NORTH, classNameText, 10, SpringLayout.SOUTH, prevNameText);

		layout.putConstraint(SpringLayout.WEST, updateButton, 193, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, updateButton, 30, SpringLayout.SOUTH, classNameLabel);

		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == updateButton) {
			String name;
			String toname;
			if ((name = prevNameText.getText().trim()) == null || prevNameText.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(null, "Empty Previous Name!");
				return;
			}
			if (classNameText.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(null, "Nothing Updated!");
				return;
			}
			toname = classNameText.getText().trim();
			if (class1.update(name, toname)) {
				JOptionPane.showMessageDialog(null, "Update Success!");
				classNameText.setText(null);
			} else {
				JOptionPane.showMessageDialog(null, "Unknow Error!");
			}
		}
	}

}
