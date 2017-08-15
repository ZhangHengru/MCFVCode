package MCFV;

import java.awt.Frame;

import MCFV.gui.ApplicationShutdown;
import MCFV.gui.GUICommon;
import MCFV.gui.MBMenuBar;

//import coser.gui.guicommon.GUICommon;
//import coser.gui.menu.CoserMenuBar;

/**
 * The main entrance of the software.
 * 
 * @author Henry 2017.8.15.
 *
 */
public class Main {
	/**
	 * The entrance method.
	 */
	public static void main(String args[]) {
		// A simple frame to contain dialogs.
		Frame mainFrame = new Frame();
		mainFrame
				.setTitle("MCFV. http://www.fansmale.com, zhanghrswpu@163.com");
		try {
			GUICommon.setFrame(mainFrame);
		} catch (Exception ee) {
			System.out.println(ee.toString());
			System.exit(0);
		}// Of try
		mainFrame.setMenuBar(MBMenuBar.mbMenuBar);
		// Basic settings of the frame
		mainFrame.setSize(800, 550);
		mainFrame.setLocation(50, 50);
		mainFrame.addWindowListener(ApplicationShutdown.applicationShutdown);
		mainFrame.setBackground(GUICommon.MY_COLOR);
		mainFrame.setVisible(true);
	}// Of main
}