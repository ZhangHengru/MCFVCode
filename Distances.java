package MCFV;

public class Distances {
	/**
	 ************************* 
	 * Compute the cosine value of two vectors. The method is the same as
	 * cosine(double[], double[]), however we cannot invoke that method for
	 * efficiency.
	 * 
	 * @param paraFirstVector
	 *            the first vector
	 * @param paraSecondVector
	 *            the second vector
	 * @throws exception
	 *             if there are negative values the algorithm ID
	 * @author Henry 2017/08/15
	 ************************* 
	 */
	public static double cosine(int[] paraFirstVector, int[] paraSecondVector)
			throws Exception {
		// Check length
		if (paraFirstVector.length != paraSecondVector.length) {
			throw new Exception(
					"Error occurred in cosine(). The arrays should have the same length.");
		}// Of if

		// Check elements, might be unuseful
		for (int i = 0; i < paraFirstVector.length; i++) {
			if ((paraFirstVector[i] < 0) || (paraSecondVector[i] < 0)) {
				throw new Exception(
						"Error occurred in.cosine(). Elements should be non-negative.");
			}// Of if
		}// Of for i
		
		int tempMax1 = paraFirstVector[0];
		int tempMax2 = paraSecondVector[0];
		// find the maximum element	
		for (int i = 1; i < paraFirstVector.length; i++) {
			if(paraFirstVector[i] > tempMax1){
				tempMax1 = paraFirstVector[i];
			}//Of if
			if(paraSecondVector[i] > tempMax2){
				tempMax2 = paraSecondVector[i];
			}//Of if
		}// Of for i
		
		if(tempMax1 == 0 || tempMax2 == 0){
			return 0.0;
		}//Of if
				
		double tempNumerator = 0;
		double tempFirstModule = 0;
		double tempSecondModule = 0;
		for (int i = 0; i < paraFirstVector.length; i++) {
	//		if(paraFirstVector[i] < 1e-6 || paraSecondVector[i] < 1e-6){
	//			continue;
	//		}//Of if
			tempNumerator += (paraFirstVector[i] * 1.0 / tempMax1)
					* (paraSecondVector[i] * 1.0 / tempMax2);
			tempFirstModule += (paraFirstVector[i] * 1.0 / tempMax1) * 
					(paraFirstVector[i] * 1.0 /tempMax1);
			tempSecondModule += (paraSecondVector[i] * 1.0 / tempMax2) * 
					(paraSecondVector[i] * 1.0 / tempMax2);
		}// Of for i
		double tempDenominator = Math.sqrt(tempFirstModule)
				* Math.sqrt(tempSecondModule);

		if (tempDenominator == 0) {
			return 0;
		}// Of if
		return tempNumerator / tempDenominator;
	}// Of cosine
	
	/**
	 ************************* 
	 * Compute the cosine value of two vectors. The method is the same as
	 * cosine(double[], double[]), however we cannot invoke that method for
	 * efficiency.
	 * 
	 * @param paraFirstVector
	 *            the first vector
	 * @param paraSecondVector
	 *            the second vector
	 * @throws exception
	 *             if there are negative values the algorithm ID
	 * @author Henry 2017/08/15
	 ************************* 
	 */
	public static double cosine(int[] paraFirstIndices, int[] paraFirstRatings,
			int[] paraSecondIndices, int[] paraSecondRatings)
			throws Exception {				
		double tempNumerator = 0;
		double tempFirstModule = 0;
		double tempSecondModule = 0;
		int i = 0, j = 0;
		
		while(i < paraFirstIndices.length && j < paraSecondIndices.length){
			if(paraFirstIndices[i] < paraSecondIndices[j]){
				i ++;
			}else if(paraFirstIndices[i] > paraSecondIndices[j]){
				j ++;
			}else{
				tempNumerator += paraFirstRatings[i] * paraSecondRatings[j];
				i ++;
				j ++;
			}//Of if
		}//Of while
		for (i = 0; i < paraFirstIndices.length; i++) {
			tempFirstModule += (paraFirstRatings[i] * 1.0) * 
					(paraFirstRatings[i] * 1.0);
		}// Of for i
		for (i = 0; i < paraSecondIndices.length; i++) {
			tempSecondModule += (paraSecondRatings[i] * 1.0) * 
					(paraSecondRatings[i] * 1.0);
		}// Of for i
		double tempDenominator = Math.sqrt(tempFirstModule)
				* Math.sqrt(tempSecondModule);

		if (tempDenominator == 0) {
			return 0;
		}// Of if
		return tempNumerator / tempDenominator;
	}// Of cosine
	
	/**
	 ************************* 
	 * Compute the cosine value of two vectors. The method is the same as
	 * cosine(double[], double[]), however we cannot invoke that method for
	 * efficiency.
	 * 
	 * @param paraFirstVector
	 *            the first vector
	 * @param paraSecondVector
	 *            the second vector
	 * @throws exception
	 *             if there are negative values the algorithm ID
	 * @author Henry 2017/08/09
	 ************************* 
	 */
	public static double edSim(int[] paraFirstIndices, int[] paraFirstRatings,
			int[] paraSecondIndices, int[] paraSecondRatings){
		int i = 0, j = 0;
		
		double tempNumerator = 0;
		int tempInterSectNum = 0;
		while(i < paraFirstIndices.length && j < paraSecondIndices.length){
			if(paraFirstIndices[i] < paraSecondIndices[j]){
				i ++;
			}else if(paraFirstIndices[i] > paraSecondIndices[j]){
				j ++;
			}else{
				tempNumerator += (paraFirstRatings[i] - paraSecondRatings[j]) ^2;
				tempInterSectNum ++;
				i ++;
				j ++;
			}//Of if
		}//Of while
		
		double tempEdSim = 0;
		if(tempInterSectNum > 1e-6){
			tempEdSim = 1 / (1 + Math.sqrt(tempNumerator) / tempInterSectNum);
		}//Of if
		
		return tempEdSim;
	}//Of edSim
}// Of Distances
