package com.teamwork.sms.query;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

import javax.swing.*;

import mydatabase.StudentDetail;

@SuppressWarnings("serial")
public class CardStudent extends JPanel implements ActionListener {

	JLabel stuIDLabel = null;
	JTextField stuIDText = null;
	JLabel stuNameLabel = null;
	JTextField stuNameText = null;
	JButton queryButton = null;
	JTextPane resultPane = null;
	JScrollPane resultScrollPane = null;
	StudentDetail studentDetail = null;
	StudentDetail tempDetail = null;
	Stack<StudentDetail> stuStack = null;

	public CardStudent() {
		studentDetail = new StudentDetail();
		SpringLayout layout = new SpringLayout();
		setLayout(layout);
		stuIDLabel = new JLabel("ID:");
		stuNameLabel = new JLabel("Name:");
		stuIDText = new JTextField(12);
		stuNameText = new JTextField(12);
		queryButton = new JButton("Query");
		queryButton.addActionListener(this);
		resultPane = new JTextPane();
		resultPane.setEditable(false);
		resultScrollPane = new JScrollPane(resultPane);
		resultScrollPane.setPreferredSize(new Dimension(380, 140));
		add(stuIDLabel);
		add(stuIDText);
		add(stuNameLabel);
		add(stuNameText);
		add(queryButton);
		add(resultScrollPane);

		layout.putConstraint(SpringLayout.WEST, stuIDLabel, 150, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, stuIDLabel, 10, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, stuIDText, 10, SpringLayout.EAST, stuIDLabel);
		layout.putConstraint(SpringLayout.NORTH, stuIDText, 6, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, stuNameLabel, 128, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, stuNameLabel, 20, SpringLayout.SOUTH, stuIDLabel);
		layout.putConstraint(SpringLayout.WEST, stuNameText, 10, SpringLayout.EAST, stuNameLabel);
		layout.putConstraint(SpringLayout.NORTH, stuNameText, 10, SpringLayout.SOUTH, stuIDText);

		layout.putConstraint(SpringLayout.WEST, queryButton, 193, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, queryButton, 10, SpringLayout.SOUTH, stuNameLabel);
		layout.putConstraint(SpringLayout.WEST, resultScrollPane, 50, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, resultScrollPane, 10, SpringLayout.SOUTH, queryButton);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == queryButton) {
			resultPane.setText(null);
			String id = "";
			String name = "";
			String str;
			id = stuIDText.getText().trim();
			name = stuNameText.getText().trim();
			stuStack = studentDetail.search(id, name);
			if (stuStack.empty()) {
				QueryTools.insertMsg(resultPane, "No results found!", true, Color.RED, 14);
				return;
			}
			while (!stuStack.empty()) {
				tempDetail = stuStack.pop();
				str = tempDetail.id + " -- " + tempDetail.name + "\n";
				QueryTools.insertMsg(resultPane, str, true, Color.BLACK, 14);
				str = tempDetail.sex + " - " + tempDetail.birthday + " - " + tempDetail.nation + "\n"
						+ tempDetail.academy_name + " - " + tempDetail.major_name + " - " + tempDetail.class_name
						+ "班 - " + tempDetail.dormitory_name + "宿舍\n";
				QueryTools.insertMsg(resultPane, str, false, Color.BLACK, 13);
			}
			
		}
	}

}
