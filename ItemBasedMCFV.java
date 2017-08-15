package MCFV;

import common.SimpleTool;

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
public class ItemBasedMCFV {
	/**
	 * Running steps
	 */
	private static int runSteps;
	
	/**
	 * Data Model
	 */
	public DataModel dataModel;
	
	/**
	 * leave-out-item index 
	 */
	private int leaveOutItemIndex;

	/**
	 * leave-out value
	 */
	private double leaveOutValue;
	
	/**
	 * metric
	 */
	public double mae;
	public double rsme;
	
	/**
	 ************************* 
	 * Leave one out.
	 * 
	 * @author Henry
	 ************************* 
	 */
	public void leaveOneOut(int paraItemIndex, int paraValue) {
		// leaveOutUserIndex = paraUserIndex;
		leaveOutItemIndex = paraItemIndex;
		runSteps ++;
		leaveOutValue = paraValue;
		runSteps ++;
		dataModel.iChMatrix[paraItemIndex][paraValue - 1]--;
		runSteps ++;
		dataModel.iTrTotRatings[paraItemIndex] -= leaveOutValue;
		runSteps ++;
		dataModel.iTrDgr[paraItemIndex]--;
		runSteps ++;
		if(dataModel.iTrDgr[paraItemIndex] > 1e-6){
			dataModel.iTrAveRatings[paraItemIndex] = dataModel.iTrTotRatings[paraItemIndex] 
						/ dataModel.iTrDgr[paraItemIndex];
		}else{
			dataModel.iTrAveRatings[paraItemIndex] = 0;
		}//Of if
		runSteps ++;
	}// Of leaveOneOut
	
	/**
	 ************************* 
	 * Leave one out restore.
	 * 
	 * @author Henry
	 ************************* 
	 */
	public void leaveOneOutRestore() {
		dataModel.iChMatrix[leaveOutItemIndex][(int) (leaveOutValue) - 1]++;
		runSteps ++;
		dataModel.iTrDgr[leaveOutItemIndex]++;
		runSteps ++;
		dataModel.iTrTotRatings[leaveOutItemIndex] += leaveOutValue;
		runSteps ++;
		dataModel.iTrAveRatings[leaveOutItemIndex] = dataModel.iTrTotRatings[leaveOutItemIndex] 
				/ dataModel.iTrDgr[leaveOutItemIndex];
		runSteps ++;
	}// Of leaveOneOutRestore
	
	/**
	 * Find the items rated by the user.
	 * @param paraUserIndex
	 * @param paraItemIndex
	 * @return
	 */
	int[] findRatedItems(int paraUserIndex, int paraItemIndex) {
		// Step 1. Find the items bought by the user
		int[] tempItems = dataModel.uTrRateInds[paraUserIndex];
		if (tempItems == null || tempItems.length == 0) {
			return null;
		} // Of if

		return tempItems;
	}// Of findRatedItems

	/**
	 * 
	 * @param paraUserIndex
	 * @param paraItemIndex
	 * @return
	 */
	double predict(int paraUserIndex, int paraItemIndex, int paraK) throws Exception {
		//Step 1. Find the position of my items that bought by the user.
		int[] tempMyItems = findRatedItems(paraUserIndex, paraItemIndex);
		
		if(tempMyItems == null){
			return 3.0;
		}//Of if
			
		//Step 2. kNN recommendation
		int[] tempNeigborIndies = new int[paraK + 2];
		double[] tempFriDistances = new double[paraK + 2];
		for(int i = 0; i < tempNeigborIndies.length; i ++){
			tempNeigborIndies[i] = -1;
			if(i == 0){
				tempFriDistances[i] = 10; //Flag
			}else{
				tempFriDistances[i] = -10;
			}//of if
		}//of for i
		//Step 2.1 Find k-nearest neighbors
		for(int i = 0; i < tempMyItems.length; i ++){
			//Step 2.1.1 Obtain one possible neighbor.
			int tempOneNeighbor = tempMyItems[i];
			
			//exclude myself
			if(paraItemIndex == tempOneNeighbor){
				continue;
			}//Of if

			double tempDistance = Distances.cosine(dataModel.iChMatrix[paraItemIndex],
					dataModel.iChMatrix[tempOneNeighbor]);
			
//			System.out.println(paraUserIndex + ":" + tempOneNeighbor 
//					+ "; distance = " + tempDistance);
			//Step 2.1.3 Insert sort (Sort from largest to smallest)
			for(int j = paraK; j >= 0; j --){
	//			System.out.println("Distance: " + tempDistance + " : " + tempFriDistances[j]);
				if(tempDistance > tempFriDistances[j]){
					tempFriDistances[j] = tempFriDistances[j - 1];
					tempNeigborIndies[j] = tempNeigborIndies[j - 1];
				}else{
	//				System.out.println("position: " + j + "; neighbor = " + tempMyFriends[i]);
					tempFriDistances[j + 1] = tempDistance;
					tempNeigborIndies[j + 1] = i; //the indices of users who rate the item.
					break;
				}//Of if
			}//Of for j
		}//of for i
		
		// Step 3. Compute the distribution of the predicting item
		double tempPrediction = 0;
		int tempNeighbors = 0;
		for (int i = 1; i <= paraK; i++) {
			if(tempNeigborIndies[i] != -1){
				int tempRating = dataModel.uTrRatings[paraUserIndex][tempNeigborIndies[i]];
				tempPrediction += tempRating;
				tempNeighbors ++;
			}//Of if
		} // Of for i
		
		if(tempNeighbors > 1e-6){
			tempPrediction /= tempNeighbors;
		}else{
			tempPrediction = 3.0;
		}//Of if

		return tempPrediction;
	}// Of predict

	/**
	 * 
	 * Compute MAE
	 * @return
	 */
	public void computeMAEAndRSME(int paraK) throws Exception{
		mae = 0;
		int tempTotalCount = 0;
		//Step 1. Predict the distribution
		for(int i = 0; i < dataModel.uTrRatings.length; i ++){
			for(int j = 0; j < dataModel.uTrRatings[i].length; j ++){
				//Step 1. leave one out
				leaveOneOut(dataModel.uTrRateInds[i][j], dataModel.uTrRatings[i][j]);
				//Step 2. prediction
				double tempPrediction = predict(i, 
						dataModel.uTrRateInds[i][j] , paraK);
				double tempDeviation = (tempPrediction - leaveOutValue);
				mae += Math.abs(tempDeviation);
				rsme += Math.pow(tempDeviation, 2);
				
				//Step 3. leave out restore
				leaveOneOutRestore();
				
				tempTotalCount ++;
			}//of for 
		}//of for i
		
		if(tempTotalCount > 1e-6){
			mae /= tempTotalCount;
			rsme = Math.sqrt(rsme / tempTotalCount);
		}//of 
	}//Of computeMAEAndRSME
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			ItemBasedMCFV tempMCFV = new ItemBasedMCFV();
			tempMCFV.dataModel = new DataModel(943, 1682);
			String tempTrain = "D:/workspace/datasets/movielens/movielens943u1682m.txt";
					//"D:/workspace/datasets/movielens/movielens706u8570m.txt";//706, 129651
			tempMCFV.dataModel.setItemTrainSet(tempTrain);
			tempMCFV.dataModel.setUserTrainSet(tempTrain);
			for (int tempK = 25; tempK < 31; tempK += 5) {
				tempMCFV.runSteps = 0;
				long tempStart = System.currentTimeMillis();
				tempMCFV.computeMAEAndRSME(tempK);  //tempK
				long tempEnd = System.currentTimeMillis();
				System.out.println("K = " + tempK + ", consume " + (tempEnd - tempStart) + " ms" 
						+  ", MAE = " + tempMCFV.mae +  ", RSME = " + tempMCFV.rsme
						+ ", Steps = " + tempMCFV.runSteps);
			}// Of for tempRadius
		} catch (Exception ee) {
			ee.printStackTrace();
		}// Of try
	}//Of main
}//Of Class ItemBasedMCFV
