package com.teamwork.sms.insert;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

@SuppressWarnings("serial")
public class InsertStudent extends JPanel implements ActionListener {

	JLabel stuIDLabel = null;
	JTextField stuIDText = null;
	JLabel stuNameLabel = null;
	JTextField stuNameText = null;
	JLabel stuYearLabel = null;
	JTextField stuYearText = null;
	JLabel stuSexLabel = null;
	JRadioButton stuSexMale = null;
	JRadioButton stuSexFemale = null;
	ButtonGroup stuSexGroup = null;
	JPanel stuSexPanel = null;
	JLabel stuBirthdayLabel = null;
	JTextField stuBirthYearText = null;
	JTextField stuBirthMonthText = null;
	JTextField stuBirthDayText = null;
	JLabel stuNationLabel = null;
	JTextField stuNationText = null;
	JLabel stuClassLabel = null;
	JTextField stuClassText = null;
	JLabel stuDormLabel = null;
	JTextField stuDormText = null;
	JButton insertButton = null;

	public InsertStudent() {
		SpringLayout layout = new SpringLayout();
		setLayout(layout);
		stuIDLabel = new JLabel("ID:");
		stuNameLabel = new JLabel("Name:");
		stuYearLabel = new JLabel("Year:");
		stuSexLabel = new JLabel("Sex:");
		stuBirthdayLabel = new JLabel("Birthday:");
		stuNationLabel = new JLabel("Nation:");
		stuClassLabel = new JLabel("Class:");
		stuDormLabel = new JLabel("Dormitory:");
		stuIDText = new JTextField(8);
		stuNameText = new JTextField(8);
		stuYearText = new JTextField(5);
		stuBirthYearText = new JTextField(3);
		stuBirthMonthText = new JTextField(2);
		stuBirthDayText = new JTextField(2);
		stuNationText = new JTextField(5);
		stuClassText = new JTextField(3);
		stuDormText = new JTextField(3);
		insertButton = new JButton("Insert");

		stuSexMale = new JRadioButton("Male", true);
		stuSexFemale = new JRadioButton("Female");
		stuSexGroup = new ButtonGroup();
		stuSexGroup.add(stuSexMale);
		stuSexGroup.add(stuSexFemale);
		stuSexPanel = new JPanel();
		stuSexPanel.add(stuSexMale);
		stuSexPanel.add(stuSexFemale);
		add(stuIDLabel);
		add(stuIDText);
		add(stuNameLabel);
		add(stuNameText);
		add(stuYearLabel);
		add(stuYearText);
		add(stuSexLabel);
		add(stuSexPanel);
		add(stuBirthdayLabel);
		add(stuBirthYearText);
		add(stuBirthMonthText);
		add(stuBirthDayText);
		add(stuNationLabel);
		add(stuNationText);
		add(stuClassLabel);
		add(stuClassText);
		add(stuDormLabel);
		add(stuDormText);
		add(insertButton);
		layout.putConstraint(SpringLayout.WEST, stuIDLabel, 54, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, stuIDLabel, 10, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, stuIDText, 20, SpringLayout.EAST, stuIDLabel);
		layout.putConstraint(SpringLayout.NORTH, stuIDText, 6, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, stuNameLabel, 40, SpringLayout.EAST, stuIDText);
		layout.putConstraint(SpringLayout.NORTH, stuNameLabel, 10, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, stuNameText, 20, SpringLayout.EAST, stuNameLabel);
		layout.putConstraint(SpringLayout.NORTH, stuNameText, 6, SpringLayout.NORTH, this);

		layout.putConstraint(SpringLayout.WEST, stuYearLabel, 41, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, stuYearLabel, 20, SpringLayout.SOUTH, stuIDLabel);
		layout.putConstraint(SpringLayout.WEST, stuYearText, 20, SpringLayout.EAST, stuYearLabel);
		layout.putConstraint(SpringLayout.NORTH, stuYearText, 10, SpringLayout.SOUTH, stuIDText);
		layout.putConstraint(SpringLayout.WEST, stuSexLabel, 90, SpringLayout.EAST, stuYearText);
		layout.putConstraint(SpringLayout.NORTH, stuSexLabel, 20, SpringLayout.SOUTH, stuNameLabel);
		layout.putConstraint(SpringLayout.WEST, stuSexPanel, 13, SpringLayout.EAST, stuSexLabel);
		layout.putConstraint(SpringLayout.NORTH, stuSexPanel, 5, SpringLayout.SOUTH, stuNameText);

		layout.putConstraint(SpringLayout.WEST, stuBirthdayLabel, 17, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, stuBirthdayLabel, 20, SpringLayout.SOUTH, stuYearLabel);
		layout.putConstraint(SpringLayout.WEST, stuBirthYearText, 20, SpringLayout.EAST, stuBirthdayLabel);
		layout.putConstraint(SpringLayout.NORTH, stuBirthYearText, 10, SpringLayout.SOUTH, stuYearText);
		layout.putConstraint(SpringLayout.WEST, stuBirthMonthText, 3, SpringLayout.EAST, stuBirthYearText);
		layout.putConstraint(SpringLayout.NORTH, stuBirthMonthText, 16, SpringLayout.SOUTH, stuSexLabel);
		layout.putConstraint(SpringLayout.WEST, stuBirthDayText, 3, SpringLayout.EAST, stuBirthMonthText);
		layout.putConstraint(SpringLayout.NORTH, stuBirthDayText, 8, SpringLayout.SOUTH, stuSexPanel);
		layout.putConstraint(SpringLayout.WEST, stuNationLabel, 20, SpringLayout.EAST, stuBirthDayText);
		layout.putConstraint(SpringLayout.NORTH, stuNationLabel, 20, SpringLayout.SOUTH, stuSexLabel);
		layout.putConstraint(SpringLayout.WEST, stuNationText, 20, SpringLayout.EAST, stuNationLabel);
		layout.putConstraint(SpringLayout.NORTH, stuNationText, 8, SpringLayout.SOUTH, stuSexPanel);

		layout.putConstraint(SpringLayout.WEST, stuClassLabel, 34, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, stuClassLabel, 20, SpringLayout.SOUTH, stuBirthdayLabel);
		layout.putConstraint(SpringLayout.WEST, stuClassText, 20, SpringLayout.EAST, stuClassLabel);
		layout.putConstraint(SpringLayout.NORTH, stuClassText, 10, SpringLayout.SOUTH, stuBirthYearText);
		layout.putConstraint(SpringLayout.WEST, stuDormLabel, 72, SpringLayout.EAST, stuClassText);
		layout.putConstraint(SpringLayout.NORTH, stuDormLabel, 20, SpringLayout.SOUTH, stuNationLabel);
		layout.putConstraint(SpringLayout.WEST, stuDormText, 20, SpringLayout.EAST, stuDormLabel);
		layout.putConstraint(SpringLayout.NORTH, stuDormText, 10, SpringLayout.SOUTH, stuNationText);

		layout.putConstraint(SpringLayout.WEST, insertButton, 190, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, insertButton, 40, SpringLayout.SOUTH, stuDormLabel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
