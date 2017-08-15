package MCFV.gui;

import java.io.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Project: The "Efficient collaborative filtering recommendations with multi-channel feature vectors" project.
 * <p>
 * Summary: Close the dialog. The message sender could be either a button or X
 * at the right top of respective dialog.
 * <p>
 * Author: <b>Henry</b> zhanghrswpu@163.com <br>
 * Copyright: The source code and all documents are open and free. PLEASE keep
 * this header while revising the program. <br>
 * Organization: <a href=http://www.fansmale.com/>Lab of Machine Learning</a>,
 * SouthWest Petroleum University, Sichuan 610500, China.<br>
 * Progress: OK. Copied from Hydrosimu.<br>
 * Written time: August 15, 2017. <br>
 * Last modify time: August 15, 2017.
 */
public class FilenameField extends TextField implements ActionListener,
		FocusListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4796206438434365366L;

	/**
	 *************************** 
	 * No special initialization..
	 *************************** 
	 */
	public FilenameField() {
		super();
		setText("");
		addFocusListener(this);
	}// Of constructor

	/**
	 *************************** 
	 * No special initialization.
	 * 
	 * @param paraWidth
	 *            The width of the .
	 *************************** 
	 */
	public FilenameField(int paraWidth) {
		super(paraWidth);
		setText("");
		addFocusListener(this);
	}// Of constructor

	/**
	 *************************** 
	 * No special initialization.
	 * 
	 * @param paraWidth
	 *            The width of the .
	 * @param paraText
	 *            The given initial text
	 *************************** 
	 */
	public FilenameField(int paraWidth, String paraText) {
		super(paraWidth);
		setText(paraText);
		addFocusListener(this);
	}// Of constructor

	/**
	 *************************** 
	 * No special initialization.
	 * 
	 * @param paraWidth
	 *            The width of the .
	 * @param paraText
	 *            The given initial text
	 *************************** 
	 */
	public FilenameField(String paraText, int paraWidth) {
		super(paraWidth);
		setText(paraText);
		addFocusListener(this);
	}// Of constructor

	/**
	 ********************************** 
	 * Implement ActionListenter.
	 * 
	 * @param paraEvent
	 *            The event is unimportant.
	 ********************************** 
	 */
	public void actionPerformed(ActionEvent paraEvent) {
		FileDialog fileDialog = new FileDialog(GUICommon.mainFrame,
				"Select a file");
		// fileDialog.setSize(300, 300);
		fileDialog.setVisible(true);
		if (fileDialog.getDirectory() == null) {
			setText("");
			return;
		}
		String directoryName = fileDialog.getDirectory();
		// Use relative path if in the same area, Fan loves this one.

		setText(directoryName + fileDialog.getFile());
	}// Of actionPerformed

	/**
	 ********************************** 
	 * Implement FocusListenter.
	 * 
	 * @param paraEvent
	 *            The event is unimportant.
	 ********************************** 
	 */
	public void focusGained(FocusEvent paraEvent) {
	}// Of focusGained

	/**
	 ********************************** 
	 * Implement FocusListenter.
	 * 
	 * @param paraEvent
	 *            The event is unimportant.
	 ********************************** 
	 */
	public void focusLost(FocusEvent paraEvent) {
	}// Of focusLost

}// Of class FileSelecter
