package com.teamwork.sms.insert;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import mydatabase.Academy;

@SuppressWarnings("serial")
public class InsertAcademy extends JPanel implements ActionListener {

	JLabel academyNameLabel = null;
	JTextField academyNameText = null;
	JLabel academyLeaderLabel = null;
	JTextField academyLeaderText = null;
	JButton insertButton = null;
	Academy academy = null;

	public InsertAcademy() {
		academy = new Academy();
		SpringLayout layout = new SpringLayout();
		setLayout(layout);
		academyNameLabel = new JLabel("Academy Name:");
		academyLeaderLabel = new JLabel("Leader:");
		academyNameText = new JTextField(12);
		academyLeaderText = new JTextField(12);
		insertButton = new JButton("Insert");
		insertButton.addActionListener(this);
		add(academyNameLabel);
		add(academyNameText);
		add(academyLeaderLabel);
		add(academyLeaderText);
		add(insertButton);
		layout.putConstraint(SpringLayout.WEST, academyNameLabel, 82, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, academyNameLabel, 10, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, academyNameText, 10, SpringLayout.EAST, academyNameLabel);
		layout.putConstraint(SpringLayout.NORTH, academyNameText, 6, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, academyLeaderLabel, 138, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, academyLeaderLabel, 20, SpringLayout.SOUTH, academyNameLabel);
		layout.putConstraint(SpringLayout.WEST, academyLeaderText, 10, SpringLayout.EAST, academyLeaderLabel);
		layout.putConstraint(SpringLayout.NORTH, academyLeaderText, 10, SpringLayout.SOUTH, academyNameText);

		layout.putConstraint(SpringLayout.WEST, insertButton, 193, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, insertButton, 30, SpringLayout.SOUTH, academyLeaderLabel);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == insertButton) {
			String name;
			String leader;
			if ((name = academyNameText.getText().trim()) == null || academyNameText.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(null, "Empty Academy Name!");
				return;
			}
			if ((leader = academyLeaderText.getText().trim()) == null || academyLeaderText.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(null, "Empty Leader Name!");
				return;
			}
			if (academy.insert(name, leader)) {
				JOptionPane.showMessageDialog(null, "Insert Success!");
				academyNameText.setText(null);
				academyLeaderText.setText(null);
			} else {
				JOptionPane.showMessageDialog(null, "Unknow Error!");
			}
		}
	}

}
