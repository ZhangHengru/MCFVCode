package MCFV.gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dialog;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import MCFV.ItemBasedMCFV;
import MCFV.DataModel;

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
public class MCFVRecDialog extends Dialog implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1824087094625089819L;

	public static MCFVRecDialog mcfvRecDialog = new MCFVRecDialog();
	
	/**
	 * Select the train file.
	 */
	private FilenameField trainFileField;
	
	/**
	 * Select the test file.
	 */
	//private FilenameField testFileField;
	
	/**
	 * Select the relation file.
	 */
	//private FilenameField relationFileField;

	/**
	 * The user number value.
	 */
	private TextField userNumField;
	
	/**
	 * The item number value.
	 */
	private TextField itemNumField;
	
	/**
	 * k neighbors.
	 */
	private TextField kNeighbors;
	

	/**
	 *************************** 
	 * The only constructor.
	 *************************** 
	 */
	private MCFVRecDialog() {
		// This dialog is not module
		super(GUICommon.mainFrame, "MCFV recommenation", true);

		trainFileField = new FilenameField(30);
		trainFileField.setText("movielens943u1682m.txt");
		Button trainButton = new Button(" Browse ");
		trainButton.addActionListener(trainFileField);
		
		Panel sourceFilePanel = new Panel();
		sourceFilePanel.setLayout(new GridLayout(1, 1));
		sourceFilePanel.add(new Label("The training set:"));
		sourceFilePanel.add(trainFileField);
		sourceFilePanel.add(trainButton);
		
		userNumField = new TextField("943");
		itemNumField = new TextField("1682");
		kNeighbors = new TextField("20");
				
		Panel centerPanel = new Panel();
		centerPanel.setLayout(new GridLayout(3, 1));
		centerPanel.add(new Label("  NumOfUsers: "));
		centerPanel.add(userNumField);
		centerPanel.add(new Label("  NumOfItems: "));
		centerPanel.add(itemNumField);
		centerPanel.add(new Label("  NumOfNeighbors: "));
		centerPanel.add(kNeighbors);

		Button okButton = new Button(" OK ");
		okButton.addActionListener(this);
		DialogCloser dialogCloser = new DialogCloser(this);
		Button cancelButton = new Button(" Cancel ");
		cancelButton.addActionListener(dialogCloser);
		Button helpButton = new Button(" Help ");
		helpButton.setSize(20, 10);
		// helpButton.addActionListener(new HelpDialog("Test-cost distribution",
		// "coser/gui/dialog/tcnds/LoadTcNdshelp.txt"));
		Panel okPanel = new Panel();
		okPanel.add(okButton);
		okPanel.add(cancelButton);
		okPanel.add(helpButton);

		setLayout(new BorderLayout());
		add(BorderLayout.NORTH, sourceFilePanel);
		add(BorderLayout.CENTER, centerPanel);
		add(BorderLayout.SOUTH, okPanel);

		setBackground(GUICommon.MY_COLOR);
		setLocation(200, 200);
		setSize(420, 250);
		addWindowListener(dialogCloser);
		setVisible(false);
	}// Of constructor

	/**
	 *************************** 
	 * Read the arff file.
	 *************************** 
	 */
	public void actionPerformed(ActionEvent ae) {
		setVisible(false);
		ProgressDialog.progressDialog
				.setMessageAndShow("Please wait a few seconds. Computing ... \r\n");
		String resultString = "";
		try {
			int userNumber = Integer.parseInt(userNumField.getText());
			int itemNumber = Integer.parseInt(itemNumField.getText());
			int neigborNumber = Integer.parseInt(kNeighbors.getText());
			
			String tempTrain = trainFileField.getText();
			
			ItemBasedMCFV tempMCFV = new ItemBasedMCFV();
			tempMCFV.dataModel = new DataModel(userNumber, itemNumber);
			tempMCFV.dataModel.setItemTrainSet(tempTrain);
			tempMCFV.dataModel.setUserTrainSet(tempTrain);
			tempMCFV.computeMAEAndRSME(neigborNumber);
			System.out.println("MAE: " + tempMCFV.mae);
			System.out.println("RSME: " + tempMCFV.rsme);
			resultString += "The MAE is: " + tempMCFV.mae 
					+ " and the RSME is: " + tempMCFV.rsme +"\r\n";
		} catch (Exception ee) {
			resultString += ee.getMessage();
		}// Of try

		ProgressDialog.progressDialog.setMessageAndShow(resultString);
	}// Of actionPerformed
}// Of KMeansDialog
