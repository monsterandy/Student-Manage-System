package com.teamwork.sms.insert;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import mydatabase.Class;

@SuppressWarnings("serial")
public class InsertClass extends JPanel implements ActionListener {

	JLabel majorNameLabel = null;
	JTextField majorNameText = null;
	JLabel classNameLabel = null;
	JTextField classNameText = null;
	JButton insertButton = null;
	Class class1 = null;

	public InsertClass() {
		class1 = new Class();
		SpringLayout layout = new SpringLayout();
		setLayout(layout);
		majorNameLabel = new JLabel("Major Name:");
		classNameLabel = new JLabel("Class:");
		majorNameText = new JTextField(12);
		classNameText = new JTextField(12);
		insertButton = new JButton("Insert");
		insertButton.addActionListener(this);
		add(majorNameLabel);
		add(majorNameText);
		add(classNameLabel);
		add(classNameText);
		add(insertButton);
		layout.putConstraint(SpringLayout.WEST, majorNameLabel, 82, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, majorNameLabel, 10, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, majorNameText, 10, SpringLayout.EAST, majorNameLabel);
		layout.putConstraint(SpringLayout.NORTH, majorNameText, 6, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, classNameLabel, 123, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, classNameLabel, 20, SpringLayout.SOUTH, majorNameLabel);
		layout.putConstraint(SpringLayout.WEST, classNameText, 10, SpringLayout.EAST, classNameLabel);
		layout.putConstraint(SpringLayout.NORTH, classNameText, 10, SpringLayout.SOUTH, majorNameText);

		layout.putConstraint(SpringLayout.WEST, insertButton, 193, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, insertButton, 30, SpringLayout.SOUTH, classNameLabel);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == insertButton) {
			String major_name;
			String name;
			if ((major_name = majorNameText.getText().trim()) == null || majorNameText.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(null, "Empty Major Name!");
				return;
			}
			if ((name = classNameText.getText().trim()) == null || classNameText.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(null, "Empty Class Name!");
				return;
			}
			if (class1.insert(name, major_name)) {
				JOptionPane.showMessageDialog(null, "Insert Success!");
				classNameText.setText(null);
				majorNameText.setText(null);
			} else {
				JOptionPane.showMessageDialog(null, "Unknow Error!");
			}
		}
	}

}
