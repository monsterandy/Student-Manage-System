package com.teamwork.sms.update;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import mydatabase.Major;

@SuppressWarnings("serial")
public class UpdateMajor extends JPanel implements ActionListener {

	JLabel prevNameLabel = null;
	JTextField prevNameText = null;
	JLabel majorNameLabel = null;
	JTextField majorNameText = null;
	JButton updateButton = null;
	Major major = null;

	public UpdateMajor() {
		major = new Major();
		SpringLayout layout = new SpringLayout();
		setLayout(layout);
		prevNameLabel = new JLabel("* Previous Major Name:");
		prevNameText = new JTextField(8);
		majorNameLabel = new JLabel("Major:");
		majorNameText = new JTextField(12);
		updateButton = new JButton("Update");
		updateButton.addActionListener(this);
		add(prevNameLabel);
		add(prevNameText);
		add(majorNameLabel);
		add(majorNameText);
		add(updateButton);

		layout.putConstraint(SpringLayout.WEST, prevNameLabel, 90, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, prevNameLabel, 10, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, prevNameText, 10, SpringLayout.EAST, prevNameLabel);
		layout.putConstraint(SpringLayout.NORTH, prevNameText, 6, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, majorNameLabel, 149, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, majorNameLabel, 20, SpringLayout.SOUTH, prevNameLabel);
		layout.putConstraint(SpringLayout.WEST, majorNameText, 10, SpringLayout.EAST, majorNameLabel);
		layout.putConstraint(SpringLayout.NORTH, majorNameText, 10, SpringLayout.SOUTH, prevNameText);

		layout.putConstraint(SpringLayout.WEST, updateButton, 193, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, updateButton, 30, SpringLayout.SOUTH, majorNameLabel);

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
			if (majorNameText.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(null, "Nothing Updated!");
				return;
			}
			toname = majorNameText.getText().trim();
			if (major.update(name, toname)) {
				JOptionPane.showMessageDialog(null, "Update Success!");
				majorNameText.setText(null);
			} else {
				JOptionPane.showMessageDialog(null, "Unknow Error!");
			}
		}
	}

}
