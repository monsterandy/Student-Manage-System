package com.teamwork.sms.insert;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import mydatabase.Major;

@SuppressWarnings("serial")
public class InsertMajor extends JPanel implements ActionListener {
	
	JLabel academyNameLabel = null;
	JTextField academyNameText = null;
	JLabel majorNameLabel = null;
	JTextField majorNameText = null;
	JButton insertButton = null;
	Major major = null;
	
	public InsertMajor() {
		major = new Major();
		SpringLayout layout = new SpringLayout();
		setLayout(layout);
		academyNameLabel = new JLabel("Academy Name:");
		majorNameLabel = new JLabel("Major:");
		academyNameText = new JTextField(12);
		majorNameText = new JTextField(12);
		insertButton = new JButton("Insert");
		insertButton.addActionListener(this);
		add(academyNameLabel);
		add(academyNameText);
		add(majorNameLabel);
		add(majorNameText);
		add(insertButton);
		layout.putConstraint(SpringLayout.WEST, academyNameLabel, 82, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, academyNameLabel, 10, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, academyNameText, 10, SpringLayout.EAST, academyNameLabel);
		layout.putConstraint(SpringLayout.NORTH, academyNameText, 6, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, majorNameLabel, 144, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, majorNameLabel, 20, SpringLayout.SOUTH, academyNameLabel);
		layout.putConstraint(SpringLayout.WEST, majorNameText, 10, SpringLayout.EAST, majorNameLabel);
		layout.putConstraint(SpringLayout.NORTH, majorNameText, 10, SpringLayout.SOUTH, academyNameText);

		layout.putConstraint(SpringLayout.WEST, insertButton, 193, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, insertButton, 30, SpringLayout.SOUTH, majorNameLabel);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == insertButton) {
			String academy_name;
			String name;
			if ((academy_name = academyNameText.getText().trim()) == null || academyNameText.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(null, "Empty Academy Name!");
				return;
			}
			if ((name = majorNameText.getText().trim()) == null || majorNameText.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(null, "Empty Major Name!");
				return;
			}
			if (major.insert(name, academy_name)) {
				JOptionPane.showMessageDialog(null, "Insert Success!");
				academyNameText.setText(null);
				majorNameText.setText(null);
			} else {
				JOptionPane.showMessageDialog(null, "Unknow Error!");
			}
		}
	}

}
