package com.teamwork.sms.delete;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import mydatabase.Major;

@SuppressWarnings("serial")
public class DeleteMajor extends JPanel implements ActionListener {

	JLabel majorNameLabel = null;
	JTextField majorNameText = null;
	JButton deleteButton = null;
	Major major = null;

	public DeleteMajor() {
		major = new Major();
		SpringLayout layout = new SpringLayout();
		setLayout(layout);
		majorNameLabel = new JLabel("Major:");
		majorNameText = new JTextField(12);
		deleteButton = new JButton("Delete");
		deleteButton.addActionListener(this);
		add(majorNameLabel);
		add(majorNameText);
		add(deleteButton);
		layout.putConstraint(SpringLayout.WEST, majorNameLabel, 144, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, majorNameLabel, 20, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, majorNameText, 10, SpringLayout.EAST, majorNameLabel);
		layout.putConstraint(SpringLayout.NORTH, majorNameText, 16, SpringLayout.NORTH, this);

		layout.putConstraint(SpringLayout.WEST, deleteButton, 193, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, deleteButton, 30, SpringLayout.SOUTH, majorNameLabel);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == deleteButton) {
			String name;
			if ((name = majorNameText.getText().trim()) == null || majorNameText.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(null, "Empty Major Name!");
				return;
			}
			if (major.delete(name)) {
				JOptionPane.showMessageDialog(null, "Delete Success!");
				majorNameText.setText(null);
			} else {
				JOptionPane.showMessageDialog(null, "Unknow Error!");
			}
		}
	}

}
