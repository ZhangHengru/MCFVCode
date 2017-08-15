package MCFV.gui;

import java.awt.event.*;

//import coser.common.*;
//import coser.gui.dialog.common.*;

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
public class ApplicationShutdown implements WindowListener, ActionListener {
	public static ApplicationShutdown applicationShutdown = new ApplicationShutdown();

	/**
	 *************************** 
	 * Empty constructor
	 *************************** 
	 */
	private ApplicationShutdown() {
	}// Of ApplicationShutdown.

	/**
	 *************************** 
	 * Shutdown the system
	 *************************** 
	 */
	public void windowClosing(WindowEvent comeInWindowEvent) {
		GUICommon.mainFrame.dispose();
		System.exit(0);
	}// Of windowClosing.

	public void windowActivated(WindowEvent comeInWindowEvent) {
	}// Of windowActivated.

	public void windowClosed(WindowEvent comeInWindowEvent) {
	}// Of windowClosed.

	public void windowDeactivated(WindowEvent comeInWindowEvent) {
	}// Of windowDeactivated.

	public void windowDeiconified(WindowEvent comeInWindowEvent) {
	}// Of windowDeiconified.

	public void windowIconified(WindowEvent comeInWindowEvent) {
	}// Of windowIconified.

	public void windowOpened(WindowEvent comeInWindowEvent) {
	}// Of windowOpened.

	/**
    *************************
    *************************
    */
	public void actionPerformed(ActionEvent ee) {
		GUICommon.mainFrame.dispose();
		System.exit(0);
	}// Of actionPerformed.

}// Of class ApplicationShutdown
