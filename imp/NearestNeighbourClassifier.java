package imp;

import java.util.ArrayList;
import java.util.List;

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
	 * A constructor for NearestNeighbourClassifier. It reads in the training set, test set and the data range.
	 */
	public NearestNeighbourClassifier(){
		IrisReader iris = new IrisReader();
		double[][] r = iris.readDataset("iris.data");
		trainingSet = iris.readTrainingset("iris-training.txt");
		testSet = iris.readTestset("iris-test.txt");
		calculateRange(r);
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

	public void classify(){
		for(Iris i: testSet){
			Iris nearestNeighbour = null;
			double minDistance = Double.MAX_VALUE;
			for(Iris j: trainingSet){
				double distance = calculateDistance(i,j);
				if(distance < minDistance){
					minDistance = distance;
					nearestNeighbour = j;
				}
			}
			i.setLabel(nearestNeighbour.getLabel());
		}
	}
	/**
	 * The main method to find out the nearest neighbours
	 * @param args
	 */
	public static void main(String[] args){


	}
}
