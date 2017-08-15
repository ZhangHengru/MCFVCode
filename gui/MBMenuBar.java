package MCFV.gui;

import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
public class MBMenuBar extends MenuBar {
	/**
	 * For serialization.
	 */
	private static final long serialVersionUID = -8436059105842104698L;

	public static MBMenuBar mbMenuBar = new MBMenuBar();

	/********************* Uniform menu *********************/
	/**
	 * MCFV menu
	 */
	public Menu mcfvMenu;

	/**
	 * File item
	 */
	//public MenuItem fileMenuItem;

	/**
	 * Data model menu item
	 */
	//public MenuItem dataModelMenuItem;

	/**
	 * MCFV recommend menu item
	 */
	public MenuItem mcfvRecMenuItem;
	

	public MBMenuBar() {
		super();
		
		// file
		//fileMenuItem = new MenuItem("File..");
		//fileMenuItem.addActionListener(new fileShower());
		//fileMenuItem.setEnabled(true);

		// uniform
		mcfvRecMenuItem = new MenuItem("MCFV recommend");
		mcfvRecMenuItem.addActionListener(new mcfvRecShower());
		mcfvRecMenuItem.setEnabled(true);

		mcfvMenu = new Menu("SMALE recommender system");
		mcfvMenu.add(mcfvRecMenuItem);
		mcfvMenu.setEnabled(true);

		add(mcfvMenu);

	}// Of the constructor

	/**
	 *************************** 
	 * Show the data-model dialog.
	 *************************** 
	 */
	private class mcfvRecShower implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			MCFVRecDialog.mcfvRecDialog.setVisible(true);
		}// Of actionPerformed
	}// Of friendRecShower
}
