package com.teamwork.sms.query;

import java.awt.Color;

import javax.swing.JTextPane;
import javax.swing.text.*;

public class QueryTools {

	public static void insertMsg(JTextPane resultPane, String str, boolean isBold, Color color, int fontSize) {
		StyledDocument doc = resultPane.getStyledDocument();
		SimpleAttributeSet attr = new SimpleAttributeSet();
		StyleConstants.setBold(attr, isBold);
		StyleConstants.setForeground(attr, color);
		StyleConstants.setFontSize(attr, fontSize);
		try {
			doc.insertString(doc.getLength(), str, attr);
		} catch (BadLocationException ble) {
			System.out.println("BadLocationException:   " + ble);
		}
	}

}
