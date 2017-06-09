package com.teamwork.sms.update;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import mydatabase.Student;

@SuppressWarnings("serial")
public class UpdateStudent extends JPanel implements ActionListener {

	JLabel prevStuNameLabel = null;
	JTextField prevStuNameText = null;

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
	JButton updateButton = null;
	Student student = null;

	public UpdateStudent() {
		student = new Student();
		SpringLayout layout = new SpringLayout();
		setLayout(layout);
		prevStuNameLabel = new JLabel("* Previous Name:");
		stuIDLabel = new JLabel("ID:");
		stuNameLabel = new JLabel("Name:");
		stuYearLabel = new JLabel("Year:");
		stuSexLabel = new JLabel("Sex:");
		stuBirthdayLabel = new JLabel("Birthday:");
		stuNationLabel = new JLabel("Nation:");
		prevStuNameText = new JTextField(8);
		stuIDText = new JTextField(8);
		stuNameText = new JTextField(8);
		stuYearText = new JTextField(5);
		stuBirthYearText = new JTextField(3);
		stuBirthMonthText = new JTextField(2);
		stuBirthDayText = new JTextField(2);
		stuNationText = new JTextField(5);
		updateButton = new JButton("Update");
		updateButton.addActionListener(this);

		stuSexMale = new JRadioButton("Male");
		stuSexFemale = new JRadioButton("Female");
		stuSexGroup = new ButtonGroup();
		stuSexGroup.add(stuSexMale);
		stuSexGroup.add(stuSexFemale);
		stuSexPanel = new JPanel();
		stuSexPanel.add(stuSexMale);
		stuSexPanel.add(stuSexFemale);
		add(prevStuNameLabel);
		add(prevStuNameText);
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
		add(updateButton);

		layout.putConstraint(SpringLayout.WEST, prevStuNameLabel, 120, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, prevStuNameLabel, 10, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, prevStuNameText, 20, SpringLayout.EAST, prevStuNameLabel);
		layout.putConstraint(SpringLayout.NORTH, prevStuNameText, 6, SpringLayout.NORTH, this);

		layout.putConstraint(SpringLayout.WEST, stuIDLabel, 54, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, stuIDLabel, 50, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, stuIDText, 20, SpringLayout.EAST, stuIDLabel);
		layout.putConstraint(SpringLayout.NORTH, stuIDText, 46, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, stuNameLabel, 40, SpringLayout.EAST, stuIDText);
		layout.putConstraint(SpringLayout.NORTH, stuNameLabel, 50, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, stuNameText, 20, SpringLayout.EAST, stuNameLabel);
		layout.putConstraint(SpringLayout.NORTH, stuNameText, 46, SpringLayout.NORTH, this);

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

		layout.putConstraint(SpringLayout.WEST, updateButton, 192, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, updateButton, 40, SpringLayout.SOUTH, stuNationLabel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == updateButton) {
			String name;
			String id;
			String toname;
			int year = 0;
			String sex;
			String birthday;
			String nation;
			if ((name = prevStuNameText.getText().trim()) == null || prevStuNameText.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(null, "Empty Previous Name!");
				return;
			}
			if (stuIDText.getText().trim().equals("") && stuNameText.getText().trim().equals("")
					&& stuYearText.getText().trim().equals("") && !stuSexMale.isSelected() && !stuSexFemale.isSelected()
					&& stuBirthYearText.getText().trim().equals("") && stuBirthMonthText.getText().trim().equals("")
					&& stuBirthDayText.getText().trim().equals("") && stuNationText.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(null, "Nothing Updated!");
				return;
			}
			id = stuIDText.getText().trim();
			toname = stuNameText.getText().trim();
			if (stuYearText.getText().trim().equals("")) {
				year = 0;
			} else {
				year = Integer.parseInt(stuYearText.getText().trim());
			}
			if (stuSexMale.isSelected()) {
				sex = "男";
			} else if (stuSexFemale.isSelected()) {
				sex = "女";
			} else {
				sex = "";
			}
			if (!stuBirthYearText.getText().trim().equals("") && !stuBirthMonthText.getText().trim().equals("")
					&& !stuBirthDayText.getText().trim().equals("")) {
				birthday = stuBirthYearText.getText().trim() + "-" + stuBirthMonthText.getText().trim() + "-"
						+ stuBirthDayText.getText().trim();
			} else {
				birthday = "";
			}
			nation = stuNationText.getText().trim();
			if (student.update(name, id, toname, year, sex, birthday, nation)) {
				JOptionPane.showMessageDialog(null, "Update Success!");
				stuIDText.setText(null);
				stuNameText.setText(null);
				stuYearText.setText(null);
				stuBirthYearText.setText(null);
				stuBirthMonthText.setText(null);
				stuBirthDayText.setText(null);
				stuNationText.setText(null);
			} else {
				JOptionPane.showMessageDialog(null, "Unknow Error!");
			}
		}
	}

}
