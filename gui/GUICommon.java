package MCFV.gui;

import java.awt.*;
import javax.swing.*;

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
public class GUICommon extends Object {
	public static Frame mainFrame = null;

	public static JTabbedPane mainPane = null;

	/**
	 * Defaut font.
	 */
	public static final Font MY_FONT = new Font("Times New Romans", Font.PLAIN,
			12);

	/**
	 * Default color
	 */
	public static final Color MY_COLOR = Color.lightGray;

	/**
	 *************************** 
	 * Set the main frame. This can be done only once at the initialzing stage.
	 * 
	 * @param paramFrame
	 *            the main frame of the GUI.
	 *************************** 
	 */
	public static void setFrame(Frame paramFrame) throws Exception {
		if (mainFrame == null) {
			mainFrame = paramFrame;
		} else {
			throw new Exception("The main frame can be set only ONCE!");
		}// Of if
	}// Of setFrame

	/**
	 *************************** 
	 * Set the main pane. This can be done only once at the initialzing stage.
	 * 
	 * @param paramPane
	 *            the main pane of the GUI.
	 *************************** 
	 */
	public static void setPane(JTabbedPane paramPane) throws Exception {
		if (mainPane == null) {
			mainPane = paramPane;
		} else {
			throw new Exception("The main pane can be set only ONCE!");
		}// Of if
	}// Of setPAne

}// Of class GUICommon
