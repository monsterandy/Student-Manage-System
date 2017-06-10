package com.teamwork.sms.update;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import mydatabase.Academy;

@SuppressWarnings("serial")
public class UpdateAcademy extends JPanel implements ActionListener {

	JLabel prevNameLabel = null;
	JTextField prevNameText = null;

	JLabel academyNameLabel = null;
	JTextField academyNameText = null;
	JLabel academyLeaderLabel = null;
	JTextField academyLeaderText = null;
	JButton updateButton = null;
	Academy academy = null;

	public UpdateAcademy() {
		academy = new Academy();
		SpringLayout layout = new SpringLayout();
		setLayout(layout);
		prevNameLabel = new JLabel("* Previous Academy Name:");
		prevNameText = new JTextField(8);

		academyNameLabel = new JLabel("Academy Name:");
		academyLeaderLabel = new JLabel("Leader:");
		academyNameText = new JTextField(12);
		academyLeaderText = new JTextField(12);
		updateButton = new JButton("Update");
		updateButton.addActionListener(this);
		add(prevNameLabel);
		add(prevNameText);
		add(academyNameLabel);
		add(academyNameText);
		add(academyLeaderLabel);
		add(academyLeaderText);
		add(updateButton);
		layout.putConstraint(SpringLayout.WEST, prevNameLabel, 80, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, prevNameLabel, 10, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, prevNameText, 20, SpringLayout.EAST, prevNameLabel);
		layout.putConstraint(SpringLayout.NORTH, prevNameText, 6, SpringLayout.NORTH, this);

		layout.putConstraint(SpringLayout.WEST, academyNameLabel, 82, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, academyNameLabel, 50, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, academyNameText, 10, SpringLayout.EAST, academyNameLabel);
		layout.putConstraint(SpringLayout.NORTH, academyNameText, 46, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, academyLeaderLabel, 138, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, academyLeaderLabel, 20, SpringLayout.SOUTH, academyNameLabel);
		layout.putConstraint(SpringLayout.WEST, academyLeaderText, 10, SpringLayout.EAST, academyLeaderLabel);
		layout.putConstraint(SpringLayout.NORTH, academyLeaderText, 10, SpringLayout.SOUTH, academyNameText);

		layout.putConstraint(SpringLayout.WEST, updateButton, 193, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, updateButton, 30, SpringLayout.SOUTH, academyLeaderLabel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == updateButton) {
			String name;
			String toname;
			String leader;
			if ((name = prevNameText.getText().trim()) == null || prevNameText.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(null, "Empty Previous Name!");
				return;
			}
			if (academyNameText.getText().trim().equals("") && academyLeaderText.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(null, "Nothing Updated!");
				return;
			}
			toname = academyNameText.getText().trim();
			leader = academyLeaderText.getText().trim();
			if (academy.update(name, toname, leader)) {
				JOptionPane.showMessageDialog(null, "Update Success!");
				academyNameText.setText(null);
				academyLeaderText.setText(null);
			} else {
				JOptionPane.showMessageDialog(null, "Unknow Error!");
			}
		}
	}
	
}
