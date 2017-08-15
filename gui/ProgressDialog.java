/*
 * @(#)ProgressDialog.java
 *
 */

package MCFV.gui;

import java.awt.*;

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
public class ProgressDialog extends Dialog {

	/**
	 * Serail UID.
	 */
	private static final long serialVersionUID = -2243735738817185629L;

	/**
	 * The ONLY ProgressDialog.
	 */
	public static ProgressDialog progressDialog = new ProgressDialog();

	/**
	 * The label containing the message to display.
	 */
	private TextArea messageTextArea;

	/**
	 *************************** 
	 * Display an error dialog and respective error message. Like other dialogs,
	 * this constructor is private, such that users can use only one dialog,
	 * i.e., ProgressDialog.progressDialog to display message. This is helpful
	 * for saving space (only one dialog) since we may need "many" dialogs.
	 *************************** 
	 */
	private ProgressDialog() {
		// This dialog is not module.
		super(GUICommon.mainFrame, "Processing", false);

		// Prepare for the dialog.
		messageTextArea = new TextArea();

		// Add TextArea and Button
		setLayout(new BorderLayout());
		add(BorderLayout.CENTER, messageTextArea);

		setLocation(200, 200);
		setSize(500, 200);
		addWindowListener(new DialogCloser());
		setVisible(false);
	}// Of constructor

	/**
	 *************************** 
	 * Set message.
	 * 
	 * @param paramMessage
	 *            the new message
	 *************************** 
	 */
	public void setMessageAndShow(String paramMessage) {
		messageTextArea.setText(paramMessage);

		setVisible(true);
	}// Of setMessageAndShow

	/**
	 *************************** 
	 * Append message.
	 * 
	 * @param paramMessage
	 *            the new message
	 *************************** 
	 */
	public void appendMessage(String paramMessage) {
		messageTextArea.append(paramMessage);
	}// Of appendMessage

	/**
	 *************************** 
	 * To display more progress has been made. May be replaced by other
	 * approaches with better visualization.
	 *************************** 
	 */
	public void moreProgress() {
		messageTextArea.append("...");
	}// Of appendMessage

}// Of class ProgressDialog
