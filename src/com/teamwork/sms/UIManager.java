package com.teamwork.sms;

import java.awt.BorderLayout;

import javax.swing.*;

import mydatabase.Database;

/**
 * @author MonsterAndy
 * @see Class UIManager implements the window frame
 */
@SuppressWarnings("serial")
public class UIManager extends JFrame {

	final static String SQLUSER = "sa";
	final static String SQLPASSWD = "00544123";
	JPanel title = null;
	JTabbedPane funcPane = null;
	QueryPanel queryPanel = null;
	InsertPanel insertPanel = null;
	DeletePanel deletePanel = null;
	UpdatePanel updatePanel = null;
	Database msSQLs = null; 

	public UIManager() {
		super("Student Manager");
		msSQLs = new Database(SQLUSER, SQLPASSWD);
		funcPane = new JTabbedPane(JTabbedPane.TOP);
		title = new JPanel();
		title.add(new JLabel("Student Manage System"));
		queryPanel = new QueryPanel();
		insertPanel = new InsertPanel();
		deletePanel = new DeletePanel();
		updatePanel = new UpdatePanel();
		funcPane.add("Query", queryPanel);
		funcPane.add("Insert", insertPanel);
		funcPane.add("Delete", deletePanel);
		funcPane.add("Update", updatePanel);
		setLayout(new BorderLayout());
		add(title, BorderLayout.NORTH);
		add(funcPane, BorderLayout.CENTER);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		setBounds(0, 0, 500, 400);
		setLocation(470, 200);
		validate();
	}

}
