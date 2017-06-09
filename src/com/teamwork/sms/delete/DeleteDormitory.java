package com.teamwork.sms.delete;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import mydatabase.Dormitory;

@SuppressWarnings("serial")
public class DeleteDormitory extends JPanel implements ActionListener {

	JLabel dormNameLabel = null;
	JTextField dormNameText = null;
	JButton deleteButton = null;
	Dormitory dormitory = null;
	
	public DeleteDormitory() {
		dormitory = new Dormitory();
		SpringLayout layout = new SpringLayout();
		setLayout(layout);
		dormNameLabel = new JLabel("Name:");
		dormNameText = new JTextField(12);
		deleteButton = new JButton("Delete");
		deleteButton.addActionListener(this);
		add(dormNameLabel);
		add(dormNameText);
		add(deleteButton);
		layout.putConstraint(SpringLayout.WEST, dormNameLabel, 144, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, dormNameLabel, 20, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, dormNameText, 10, SpringLayout.EAST, dormNameLabel);
		layout.putConstraint(SpringLayout.NORTH, dormNameText, 16, SpringLayout.NORTH, this);

		layout.putConstraint(SpringLayout.WEST, deleteButton, 193, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, deleteButton, 30, SpringLayout.SOUTH, dormNameLabel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == deleteButton) {
			String name;
			if ((name = dormNameText.getText().trim()) == null || dormNameText.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(null, "Empty Dormitory Name!");
				return;
			}
			if (dormitory.delete(name)) {
				JOptionPane.showMessageDialog(null, "Delete Success!");
				dormNameText.setText(null);
			} else {
				JOptionPane.showMessageDialog(null, "Unknow Error!");
			}
		}
	}

}
