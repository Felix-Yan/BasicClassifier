package imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Collections;
import java.util.HashMap;

/**
 * This classifier classifies the test data set of iris into different labels according to the training data set.
 * @author yanlong
 *
 */
public class NearestNeighbourClassifier {
	List<Iris> trainingSet = new ArrayList<Iris>();
	List<Iris> testSet = new ArrayList<Iris>();
	double[] ranges = new double[4];

	/**
	 * A constructor for NearestNeighbourClassifier. It reads in the training set, 
	 * test set and the data range.
	 * @param k - number of nearest neighbours
	 */
	public NearestNeighbourClassifier(String training, String test, int k){
		IrisReader iris = new IrisReader();
		double[][] r = iris.extractRanges(training);
		trainingSet = iris.readTrainingset(training);
		testSet = iris.readTestset(test);
		calculateRange(r);
		classify(k);
	}

	/**
	 * This calculates the attributes range.
	 * @param r
	 */
	public void calculateRange(double[][] r){
		ranges[0]= r[0][1]-r[0][0];
		ranges[1]= r[1][1]-r[1][0];
		ranges[2]= r[2][1]-r[2][0];
		ranges[3]= r[3][1]-r[3][0];
	}

	/**
	 * This calculates the euclidean distance between two Iris objects by using their attribute vectors.
	 * @param i
	 * @param j
	 * @return
	 */
	public double calculateDistance(Iris i, Iris j){
		double s1 = Math.pow((i.sepalLength - j.sepalLength)/ranges[0],2);
		double s2 = Math.pow((i.sepalWidth - j.sepalWidth)/ranges[1],2);
		double s3 = Math.pow((i.petalLength - j.petalLength)/ranges[2],2);
		double s4 = Math.pow((i.petalWidth - j.petalWidth)/ranges[3],2);
		return Math.sqrt(s1+s2+s3+s4);
	}

	/**
	 * This classifies all the instances in the test set according to the training set.
	 * @param k
	 */
	public void classify(int k){
		int error = 0;//count the number of wrong classifications
		int count = 0;//count of instances
		for(Iris i: testSet){
			Iris[] nearestNeighbours = new Iris[k];
			List <Double> distances = new ArrayList<Double>();
			for(Iris j: trainingSet){//find all the distances
				double distance = calculateDistance(i,j);
				distances.add(distance);
			}
			for(int index = 0; index<k; index++){//find the k nearest neighbour
				double min = Collections.min(distances);
				int minIndex = distances.indexOf(min);
				Iris neighbour = trainingSet.get(minIndex);//the nearest neighbour
				nearestNeighbours[index] = neighbour;
				//set the smallest value to maximum double to remove it without influencing the order
				distances.set(minIndex, Double.MAX_VALUE);
			}
			String classification = findMajority(nearestNeighbours);
			System.out.println("Instance "+(++count)+" is Labeled as: "+classification);
			if(!i.getLabel().equals(classification)){
				System.out.println("======error======="+error);
				System.out.println("Should be: "+i.getLabel());
				error++;
			}
			i.setLabel(classification);
		}
		double accuracy = 1 - error*1.0/testSet.size();
		System.out.printf("The accuracy is %.2f %n",accuracy);
	}

	/**
	 * This finds out the majority label in the nearest neighbours.
	 * If no majority is found, select the label based on the alphabetical order.
	 * @param neighbours
	 * @return
	 */
	public String findMajority(Iris[] neighbours){
		List<String> labels = new ArrayList<String>();
		for(int i=0; i<neighbours.length; i++){
			labels.add(neighbours[i].getLabel());
		}
		Collections.sort(labels);
		String previous = null;
		String majority = null;
		int num = 0;
		int max = 0;
		for (String str:labels) {
			if (str.equals(previous)) {
				num++;
			} else {
				num = 1;
				previous = str;
			}
			if (num>max) {
				max = num;
				majority = previous;
			}
		}
		/*if(majority == null){
			majority = previous;
		}*/
		return majority;
	}

	public double calculateAccuracy(){
		return 0;
	}

	/**
	 * The main method to find out the nearest neighbours
	 * @param args
	 */
	public static void main(String[] args){
		new NearestNeighbourClassifier(args[0], args[1], Integer.parseInt(args[2]));
	}
}
