package com.teamwork.sms;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;

import com.teamwork.sms.query.*;

@SuppressWarnings("serial")
public class QueryPanel extends JPanel implements ItemListener {

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
	CardStudent cardStudent = null;
	CardAcademy cardAcademy = null;
	CardMajor cardMajor = null;
	CardClass cardClass = null;
	CardTeacher cardTeacher = null;
	CardCourse cardCourse = null;
	CardDormitory cardDormitory = null;
	CardScore cardScore = null;

	public QueryPanel() {
		// Create the JComboBox panel
		comboBoxPane = new JPanel();
		cBox = new JComboBox<String>(comboBoxItems);
		cBox.setEditable(false);
		cBox.addItemListener(this);
		comboBoxPane.add(cBox);

		// Create the cards panel
		cardsPanel = new JPanel();
		cardStudent = new CardStudent();
		cardAcademy = new CardAcademy();
		cardMajor = new CardMajor();
		cardClass = new CardClass();
		cardTeacher = new CardTeacher();
		cardCourse = new CardCourse();
		cardDormitory = new CardDormitory();
		cardScore = new CardScore();

		cardsPanel = new JPanel(new CardLayout());
		cardsPanel.add(cardStudent, STUPANEL);
		cardsPanel.add(cardAcademy, ACADEMYPANEL);
		cardsPanel.add(cardMajor, MAJORPANEL);
		cardsPanel.add(cardClass, CLASSPANEL);
		cardsPanel.add(cardTeacher, TEACHERPANEL);
		cardsPanel.add(cardCourse, COURSEPANEL);
		cardsPanel.add(cardDormitory, DOMPANEL);
		cardsPanel.add(cardScore, SCOREPANEL);
		setLayout(new BorderLayout());
		add(comboBoxPane, BorderLayout.NORTH);

		add(cardsPanel, BorderLayout.CENTER);

	}

	@Override
	public void itemStateChanged(ItemEvent evt) {
		CardLayout cl = (CardLayout)(cardsPanel.getLayout());
        cl.show(cardsPanel, (String)evt.getItem());
	}

}
