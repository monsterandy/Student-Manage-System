package com.teamwork.sms;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;

import com.teamwork.sms.insert.*;

/**
 * @author MonsterAndy
 * @see Class InsertPanel implements the insert part
 */
@SuppressWarnings("serial")
public class InsertPanel extends JPanel implements ItemListener {

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
	InsertStudent insertStudent = null;
	InsertAcademy insertAcademy = null;
	InsertMajor insertMajor = null;
	InsertClass insertClass = null;
	InsertTeacher insertTeacher = null;
	InsertCourse insertCourse = null;
	InsertDormitory insertDormitory = null;
	InsertScore insertScore = null;

	public InsertPanel() {
		// Create the JComboBox panel
		comboBoxPane = new JPanel();
		cBox = new JComboBox<String>(comboBoxItems);
		cBox.setEditable(false);
		cBox.addItemListener(this);
		comboBoxPane.add(cBox);

		// Create the cards panel
		cardsPanel = new JPanel();
		insertStudent = new InsertStudent();
		insertAcademy = new InsertAcademy();
		insertMajor = new InsertMajor();
		insertClass = new InsertClass();
		insertTeacher = new InsertTeacher();
		insertCourse = new InsertCourse();
		insertDormitory = new InsertDormitory();
		insertScore = new InsertScore();
		cardsPanel = new JPanel(new CardLayout());
		cardsPanel.add(insertStudent, STUPANEL);
		cardsPanel.add(insertAcademy, ACADEMYPANEL);
		cardsPanel.add(insertMajor, MAJORPANEL);
		cardsPanel.add(insertClass, CLASSPANEL);
		cardsPanel.add(insertTeacher, TEACHERPANEL);
		cardsPanel.add(insertCourse, COURSEPANEL);
		cardsPanel.add(insertDormitory, DOMPANEL);
		cardsPanel.add(insertScore, SCOREPANEL);
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
