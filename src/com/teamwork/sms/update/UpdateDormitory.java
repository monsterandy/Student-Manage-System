package com.teamwork.sms.update;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import mydatabase.Dormitory;

@SuppressWarnings("serial")
public class UpdateDormitory extends JPanel implements ActionListener {

	JLabel prevNameLabel = null;
	JTextField prevNameText = null;
	JLabel dormNameLabel = null;
	JTextField dormNameText = null;
	JButton updateButton = null;
	Dormitory dormitory = null;
	
	public UpdateDormitory() {
		dormitory = new Dormitory();
		SpringLayout layout = new SpringLayout();
		setLayout(layout);
		prevNameLabel = new JLabel("* Previous Major Name:");
		prevNameText = new JTextField(8);
		dormNameLabel = new JLabel("Name:");
		dormNameText = new JTextField(12);
		updateButton = new JButton("Update");
		updateButton.addActionListener(this);
		add(prevNameLabel);
		add(prevNameText);
		add(dormNameLabel);
		add(dormNameText);
		add(updateButton);
		layout.putConstraint(SpringLayout.WEST, prevNameLabel, 91, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, prevNameLabel, 10, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, prevNameText, 10, SpringLayout.EAST, prevNameLabel);
		layout.putConstraint(SpringLayout.NORTH, prevNameText, 6, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, dormNameLabel, 149, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, dormNameLabel, 20, SpringLayout.SOUTH, prevNameLabel);
		layout.putConstraint(SpringLayout.WEST, dormNameText, 10, SpringLayout.EAST, dormNameLabel);
		layout.putConstraint(SpringLayout.NORTH, dormNameText, 10, SpringLayout.SOUTH, prevNameText);

		layout.putConstraint(SpringLayout.WEST, updateButton, 193, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, updateButton, 30, SpringLayout.SOUTH, dormNameLabel);
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
			if (dormNameText.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(null, "Nothing Updated!");
				return;
			}
			toname = dormNameText.getText().trim();
			if (dormitory.update(name, toname)) {
				JOptionPane.showMessageDialog(null, "Update Success!");
				dormNameText.setText(null);
			} else {
				JOptionPane.showMessageDialog(null, "Unknow Error!");
			}
		}
	}

}
