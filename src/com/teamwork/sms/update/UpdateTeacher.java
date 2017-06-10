package com.teamwork.sms.update;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import mydatabase.Teacher;

@SuppressWarnings("serial")
public class UpdateTeacher extends JPanel implements ActionListener {

	JLabel prevNameLabel = null;
	JTextField prevNameText = null;
	JLabel teacherIDLabel = null;
	JTextField teacherIDText = null;
	JLabel teacherNameLabel = null;
	JTextField teacherNameText = null;
	JLabel teacherSexLabel = null;
	JRadioButton teacherSexMale = null;
	JRadioButton teacherSexFemale = null;
	ButtonGroup teacherSexGroup = null;
	JPanel teacherSexPanel = null;
	JButton updateButton = null;
	Teacher teacher = null;

	public UpdateTeacher() {
		teacher = new Teacher();
		SpringLayout layout = new SpringLayout();
		setLayout(layout);
		prevNameLabel = new JLabel("* Previous Teacher Name:");
		prevNameText = new JTextField(8);
		teacherIDLabel = new JLabel("ID:");
		teacherNameLabel = new JLabel("Name:");
		teacherSexLabel = new JLabel("Sex:");
		teacherIDText = new JTextField(12);
		teacherNameText = new JTextField(12);
		teacherSexMale = new JRadioButton("Male");
		teacherSexFemale = new JRadioButton("Female");
		teacherSexGroup = new ButtonGroup();
		teacherSexGroup.add(teacherSexMale);
		teacherSexGroup.add(teacherSexFemale);
		teacherSexPanel = new JPanel();
		teacherSexPanel.add(teacherSexMale);
		teacherSexPanel.add(teacherSexFemale);
		updateButton = new JButton("Update");
		updateButton.addActionListener(this);
		add(prevNameLabel);
		add(prevNameText);
		add(teacherIDLabel);
		add(teacherIDText);
		add(teacherNameLabel);
		add(teacherNameText);
		add(teacherSexLabel);
		add(teacherSexPanel);
		add(updateButton);

		layout.putConstraint(SpringLayout.WEST, prevNameLabel, 90, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, prevNameLabel, 10, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, prevNameText, 10, SpringLayout.EAST, prevNameLabel);
		layout.putConstraint(SpringLayout.NORTH, prevNameText, 6, SpringLayout.NORTH, this);

		layout.putConstraint(SpringLayout.WEST, teacherIDLabel, 166, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, teacherIDLabel, 50, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, teacherIDText, 10, SpringLayout.EAST, teacherIDLabel);
		layout.putConstraint(SpringLayout.NORTH, teacherIDText, 46, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, teacherNameLabel, 144, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, teacherNameLabel, 20, SpringLayout.SOUTH, teacherIDLabel);
		layout.putConstraint(SpringLayout.WEST, teacherNameText, 10, SpringLayout.EAST, teacherNameLabel);
		layout.putConstraint(SpringLayout.NORTH, teacherNameText, 10, SpringLayout.SOUTH, teacherIDText);

		layout.putConstraint(SpringLayout.WEST, teacherSexLabel, 158, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, teacherSexLabel, 20, SpringLayout.SOUTH, teacherNameLabel);
		layout.putConstraint(SpringLayout.WEST, teacherSexPanel, 10, SpringLayout.EAST, teacherSexLabel);
		layout.putConstraint(SpringLayout.NORTH, teacherSexPanel, 6, SpringLayout.SOUTH, teacherNameText);

		layout.putConstraint(SpringLayout.WEST, updateButton, 193, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, updateButton, 30, SpringLayout.SOUTH, teacherSexLabel);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == updateButton) {
			String name;
			String id;
			String toname;
			String sex;
			if ((name = prevNameText.getText().trim()) == null || prevNameText.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(null, "Empty Previous Name!");
				return;
			}
			if (teacherIDText.getText().trim().equals("") && teacherNameText.getText().trim().equals("")
					&& !teacherSexMale.isSelected() && !teacherSexFemale.isSelected()) {
				JOptionPane.showMessageDialog(null, "Nothing Updated!");
				return;
			}
			id = teacherIDText.getText().trim();
			toname = teacherNameText.getText().trim();
			if (teacherSexMale.isSelected()) {
				sex = "男";
			} else if (teacherSexFemale.isSelected()) {
				sex = "女";
			} else {
				sex = "";
			}
			if (teacher.update(name, id, toname, sex)) {
				JOptionPane.showMessageDialog(null, "Update Success!");
				teacherIDText.setText(null);
				teacherNameText.setText(null);
			} else {
				JOptionPane.showMessageDialog(null, "Unknow Error!");
			}
		}
	}

}
