package com.teamwork.sms;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;

import com.teamwork.sms.delete.*;

/**
 * @author MonsterAndy
 * @see Class DeletePanel implements the delete part
 */
@SuppressWarnings("serial")
public class DeletePanel extends JPanel implements ItemListener {

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
	DeleteStudent deleteStudent = null;
	DeleteAcademy deleteAcademy = null;
	DeleteMajor deleteMajor = null;
	DeleteClass deleteClass = null;
	DeleteTeacher deleteTeacher = null;
	DeleteCourse deleteCourse = null;
	DeleteDormitory deleteDormitory = null;
	DeleteScore deleteScore = null;

	public DeletePanel() {
		// Create the JComboBox panel
		comboBoxPane = new JPanel();
		cBox = new JComboBox<String>(comboBoxItems);
		cBox.setEditable(false);
		cBox.addItemListener(this);
		comboBoxPane.add(cBox);

		// Create the cards panel
		cardsPanel = new JPanel();

		deleteStudent = new DeleteStudent();
		deleteAcademy = new DeleteAcademy();
		deleteMajor = new DeleteMajor();
		deleteClass = new DeleteClass();
		deleteTeacher = new DeleteTeacher();
		deleteCourse = new DeleteCourse();
		deleteDormitory = new DeleteDormitory();
		deleteScore = new DeleteScore();
		cardsPanel = new JPanel(new CardLayout());
		cardsPanel.add(deleteStudent, STUPANEL);
		cardsPanel.add(deleteAcademy, ACADEMYPANEL);
		cardsPanel.add(deleteMajor, MAJORPANEL);
		cardsPanel.add(deleteClass, CLASSPANEL);
		cardsPanel.add(deleteTeacher, TEACHERPANEL);
		cardsPanel.add(deleteCourse, COURSEPANEL);
		cardsPanel.add(deleteDormitory, DOMPANEL);
		cardsPanel.add(deleteScore, SCOREPANEL);
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
