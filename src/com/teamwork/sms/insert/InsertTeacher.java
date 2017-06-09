package com.teamwork.sms.insert;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import mydatabase.Teacher;

@SuppressWarnings("serial")
public class InsertTeacher extends JPanel implements ActionListener {

	JLabel teacherIDLabel = null;
	JTextField teacherIDText = null;
	JLabel teacherNameLabel = null;
	JTextField teacherNameText = null;
	JLabel teacherSexLabel = null;
	JRadioButton teacherSexMale = null;
	JRadioButton teacherSexFemale = null;
	ButtonGroup teacherSexGroup = null;
	JPanel teacherSexPanel = null;
	JLabel academyNameLabel = null;
	JTextField academyNameText = null;
	JButton insertButton = null;
	Teacher teacher = null;

	public InsertTeacher() {
		teacher = new Teacher();
		SpringLayout layout = new SpringLayout();
		setLayout(layout);
		teacherIDLabel = new JLabel("ID:");
		teacherNameLabel = new JLabel("Name:");
		teacherSexLabel = new JLabel("Sex:");
		academyNameLabel = new JLabel("Academy Name:");
		teacherIDText = new JTextField(12);
		teacherNameText = new JTextField(12);
		academyNameText = new JTextField(12);
		teacherSexMale = new JRadioButton("Male", true);
		teacherSexFemale = new JRadioButton("Female");
		teacherSexGroup = new ButtonGroup();
		teacherSexGroup.add(teacherSexMale);
		teacherSexGroup.add(teacherSexFemale);
		teacherSexPanel = new JPanel();
		teacherSexPanel.add(teacherSexMale);
		teacherSexPanel.add(teacherSexFemale);
		insertButton = new JButton("Insert");
		insertButton.addActionListener(this);
		add(teacherIDLabel);
		add(teacherIDText);
		add(teacherNameLabel);
		add(teacherNameText);
		add(teacherSexLabel);
		add(teacherSexPanel);
		add(academyNameLabel);
		add(academyNameText);
		add(insertButton);

		layout.putConstraint(SpringLayout.WEST, teacherIDLabel, 166, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, teacherIDLabel, 10, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, teacherIDText, 10, SpringLayout.EAST, teacherIDLabel);
		layout.putConstraint(SpringLayout.NORTH, teacherIDText, 6, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, teacherNameLabel, 144, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, teacherNameLabel, 20, SpringLayout.SOUTH, teacherIDLabel);
		layout.putConstraint(SpringLayout.WEST, teacherNameText, 10, SpringLayout.EAST, teacherNameLabel);
		layout.putConstraint(SpringLayout.NORTH, teacherNameText, 10, SpringLayout.SOUTH, teacherIDText);

		layout.putConstraint(SpringLayout.WEST, teacherSexLabel, 158, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, teacherSexLabel, 20, SpringLayout.SOUTH, teacherNameLabel);
		layout.putConstraint(SpringLayout.WEST, teacherSexPanel, 10, SpringLayout.EAST, teacherSexLabel);
		layout.putConstraint(SpringLayout.NORTH, teacherSexPanel, 6, SpringLayout.SOUTH, teacherNameText);
		layout.putConstraint(SpringLayout.WEST, academyNameLabel, 83, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, academyNameLabel, 20, SpringLayout.SOUTH, teacherSexLabel);
		layout.putConstraint(SpringLayout.WEST, academyNameText, 10, SpringLayout.EAST, academyNameLabel);
		layout.putConstraint(SpringLayout.NORTH, academyNameText, 6, SpringLayout.SOUTH, teacherSexPanel);

		layout.putConstraint(SpringLayout.WEST, insertButton, 193, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, insertButton, 30, SpringLayout.SOUTH, academyNameLabel);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == insertButton) {
			String id;
			String name;
			String sex;
			String academy_name;
			if ((id = teacherIDText.getText().trim()) == null || teacherIDText.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(null, "Empty Teacher ID!");
				return;
			}
			if ((name = teacherNameText.getText().trim()) == null || teacherNameText.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(null, "Empty Teacher Name!");
				return;
			}
			if (teacherSexMale.isSelected()) {
				sex = "男";
			} else {
				sex = "女";
			}
			if ((academy_name = academyNameText.getText().trim()) == null
					|| academyNameText.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(null, "Empty Academy Name!");
				return;
			}
			if (teacher.insert(id, name, sex, academy_name)) {
				JOptionPane.showMessageDialog(null, "Insert Success!");
				teacherIDText.setText(null);
				teacherNameText.setText(null);
				academyNameText.setText(null);
			} else {
				JOptionPane.showMessageDialog(null, "Unknow Error!");
			}

		}
	}

}
