package com.teamwork.sms.delete;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import mydatabase.Academy;

@SuppressWarnings("serial")
public class DeleteAcademy extends JPanel implements ActionListener {

	JLabel academyNameLabel = null;
	JTextField academyNameText = null;
	JLabel academyLeaderLabel = null;
	JTextField academyLeaderText = null;
	JButton deleteButton = null;
	Academy academy = null;
	
	public DeleteAcademy() {
		academy = new Academy();
		SpringLayout layout = new SpringLayout();
		setLayout(layout);
		academyNameLabel = new JLabel("Academy Name:");
		academyLeaderLabel = new JLabel("Leader:");
		academyNameText = new JTextField(12);
		academyLeaderText = new JTextField(12);
		deleteButton = new JButton("Delete");
		deleteButton.addActionListener(this);
		add(academyNameLabel);
		add(academyNameText);
		add(academyLeaderLabel);
		add(academyLeaderText);
		add(deleteButton);
		layout.putConstraint(SpringLayout.WEST, academyNameLabel, 82, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, academyNameLabel, 10, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, academyNameText, 10, SpringLayout.EAST, academyNameLabel);
		layout.putConstraint(SpringLayout.NORTH, academyNameText, 6, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, academyLeaderLabel, 138, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, academyLeaderLabel, 20, SpringLayout.SOUTH, academyNameLabel);
		layout.putConstraint(SpringLayout.WEST, academyLeaderText, 10, SpringLayout.EAST, academyLeaderLabel);
		layout.putConstraint(SpringLayout.NORTH, academyLeaderText, 10, SpringLayout.SOUTH, academyNameText);

		layout.putConstraint(SpringLayout.WEST, deleteButton, 193, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, deleteButton, 30, SpringLayout.SOUTH, academyLeaderLabel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == deleteButton) {
			String name;
			String leader;
			if (academyNameText.getText().trim().equals("") && academyLeaderText.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(null, "Empty Academy Name and Leader Name!");
				return;
			}
			name = academyNameText.getText().trim();
			leader = academyLeaderText.getText().trim();
			if (academy.delete(name, leader)) {
				JOptionPane.showMessageDialog(null, "Delete Success!");
				academyNameText.setText(null);
				academyLeaderText.setText(null);
			} else {
				JOptionPane.showMessageDialog(null, "Unknow Error!");
			}
		}
	}

}
