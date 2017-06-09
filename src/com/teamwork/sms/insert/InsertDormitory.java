package com.teamwork.sms.insert;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import mydatabase.Dormitory;

@SuppressWarnings("serial")
public class InsertDormitory extends JPanel implements ActionListener {

	JLabel dormNameLabel = null;
	JTextField dormNameText = null;
	JButton insertButton = null;
	Dormitory dormitory = null;

	public InsertDormitory() {
		dormitory = new Dormitory();
		SpringLayout layout = new SpringLayout();
		setLayout(layout);
		dormNameLabel = new JLabel("Name:");
		dormNameText = new JTextField(12);
		insertButton = new JButton("Insert");
		insertButton.addActionListener(this);
		add(dormNameLabel);
		add(dormNameText);
		add(insertButton);
		layout.putConstraint(SpringLayout.WEST, dormNameLabel, 144, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, dormNameLabel, 20, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, dormNameText, 10, SpringLayout.EAST, dormNameLabel);
		layout.putConstraint(SpringLayout.NORTH, dormNameText, 16, SpringLayout.NORTH, this);

		layout.putConstraint(SpringLayout.WEST, insertButton, 193, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, insertButton, 30, SpringLayout.SOUTH, dormNameLabel);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == insertButton) {
			String name;
			if ((name = dormNameText.getText().trim()) == null || dormNameText.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(null, "Empty Dormitory Name!");
				return;
			}
			if (dormitory.insert(name)) {
				JOptionPane.showMessageDialog(null, "Insert Success!");
				dormNameText.setText(null);
			} else {
				JOptionPane.showMessageDialog(null, "Unknow Error!");
			}
		}
	}

}
