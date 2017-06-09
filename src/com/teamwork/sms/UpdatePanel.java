package com.teamwork.sms;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;

@SuppressWarnings("serial")
public class UpdatePanel extends JPanel implements ItemListener {

	final static String STUPANEL = "Student";
	final static String ACADEMYPANEL = "Academy";
	final static String MAJORPANEL = "Major";
	final static String CLASSPANEL = "Class";
	final static String TEACHERPANEL = "Teacher";
	final static String COURSEPANEL = "Course";
	final static String DOMPANEL = "Dormitory";
	final static String SCOREPANEL = "Score";
	String comboBoxItems[] = { STUPANEL, ACADEMYPANEL, MAJORPANEL, CLASSPANEL, TEACHERPANEL, COURSEPANEL, DOMPANEL,
			SCOREPANEL };
	JPanel comboBoxPane = null;
	JComboBox<String> cBox = null;
	JPanel cardsPanel = null;
	JPanel updateStudent = null;
	JPanel updateAcademy = null;
	JPanel updateMajor = null;
	JPanel updateClass = null;
	JPanel updateTeacher = null;
	JPanel updateCourse = null;
	JPanel updateDormitory = null;
	JPanel updateScore = null;

	public UpdatePanel() {
		// Create the JComboBox panel
		comboBoxPane = new JPanel();
		cBox = new JComboBox<String>(comboBoxItems);
		cBox.setEditable(false);
		cBox.addItemListener(this);
		comboBoxPane.add(cBox);

		// Create the cards panel
		cardsPanel = new JPanel();
		updateStudent = new JPanel();
		updateAcademy = new JPanel();
		updateMajor = new JPanel();
		updateClass = new JPanel();
		updateTeacher = new JPanel();
		updateCourse = new JPanel();
		updateDormitory = new JPanel();
		updateScore = new JPanel();
		cardsPanel = new JPanel(new CardLayout());
		cardsPanel.add(updateStudent, STUPANEL);
		cardsPanel.add(updateAcademy, ACADEMYPANEL);
		cardsPanel.add(updateMajor, MAJORPANEL);
		cardsPanel.add(updateClass, CLASSPANEL);
		cardsPanel.add(updateTeacher, TEACHERPANEL);
		cardsPanel.add(updateCourse, COURSEPANEL);
		cardsPanel.add(updateDormitory, DOMPANEL);
		cardsPanel.add(updateScore, SCOREPANEL);
		setLayout(new BorderLayout());
		add(comboBoxPane, BorderLayout.NORTH);

		add(cardsPanel, BorderLayout.CENTER);
	}

	@Override
	public void itemStateChanged(ItemEvent evt) {
		CardLayout cl = (CardLayout) (cardsPanel.getLayout());
		cl.show(cardsPanel, (String) evt.getItem());
	}

}
