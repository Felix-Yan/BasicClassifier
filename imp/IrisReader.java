package imp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This reads the data set of iris to assist NearestNeighbourClassifier.
 * @author yanlong
 *
 */
public class IrisReader {
	//This is where the data sets should be. Put it under the src folder.
	private final String root = "src/ass1-data/part1/";

	/**
	 * This reads iris-training.txt and returns a list of Iris objects with labels.
	 * @param filename
	 * @return
	 */
	public List<Iris> readTrainingset(String filename){
		List<Iris> irisList = new ArrayList<Iris>();
		String directory = root+filename;
		//BufferedReader r = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(directory)));
		Path path = Paths.get(directory);
		try(BufferedReader reader = Files.newBufferedReader(path)){
			String line = null;
			while((line = reader.readLine()) != null){
				if(line.equals("")) break;//Finish after the last line
				String[] words = line.split("  ");
				double sepalLength = Double.parseDouble(words[0]);
				double sepalWidth = Double.parseDouble(words[1]);
				double petalLength = Double.parseDouble(words[2]);
				double petalWidth = Double.parseDouble(words[3]);
				Iris i = new Iris(sepalLength,sepalWidth,petalLength,petalWidth,words[4]);
				irisList.add(i);
			}

		} catch (IOException x){
			System.err.format("IOException: %s%n", x);
		}
		return irisList;
	}

	/**
	 * This reads iris-test.txt and returns a list of Iris objects without labels.
	 * @param filename
	 * @return
	 */
	public List<Iris> readTestset(String filename){
		List<Iris> irisList = new ArrayList<Iris>();
		String directory = root+filename;
		//BufferedReader r = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(directory)));
		Path path = Paths.get(directory);
		try(BufferedReader reader = Files.newBufferedReader(path)){
			String line = null;
			while((line = reader.readLine()) != null){
				if(line.equals("")) break;//Finish after the last line
				String[] words = line.split("  ");
				double sepalLength = Double.parseDouble(words[0]);
				double sepalWidth = Double.parseDouble(words[1]);
				double petalLength = Double.parseDouble(words[2]);
				double petalWidth = Double.parseDouble(words[3]);
				Iris i = new Iris(sepalLength,sepalWidth,petalLength,petalWidth, words[4]);
				irisList.add(i);
			}

		} catch (IOException x){
			System.err.format("IOException: %s%n", x);
		}
		return irisList;
	}

	/**
	 * This reads iris.data and extracts the attribute ranges of iris
	 * @param filename
	 * @return
	 */
	public double[][] readDataset(String filename){
		double[][] ranges = new double[4][2];
		String directory = root+filename;
		Path path = Paths.get(directory);
		boolean isFirstLine = true;
		try(BufferedReader reader = Files.newBufferedReader(path)){
			String line = null;
			while((line = reader.readLine()) != null){
				if(line.equals("")) break;//Finish after the last line
				String[] words = line.split(",");
				double sepalLength = Double.parseDouble(words[0]);
				double sepalWidth = Double.parseDouble(words[1]);
				double petalLength = Double.parseDouble(words[2]);
				double petalWidth = Double.parseDouble(words[3]);
				if(isFirstLine){//This initializes the ranges array by filling in the first line of data
					ranges[0][0]=sepalLength;
					ranges[0][1]=sepalLength;
					ranges[1][0]=sepalWidth;
					ranges[1][1]=sepalWidth;
					ranges[2][0]=petalLength;
					ranges[2][1]=petalLength;
					ranges[3][0]=petalWidth;
					ranges[3][1]=petalWidth;
					isFirstLine = false;
				}else{//update the lowerLimit and uppperLimit by comparison
					if(sepalLength<ranges[0][0]){
						ranges[0][0]=sepalLength;
					}else if(sepalLength>ranges[0][1]){
						ranges[0][1]=sepalLength;
					}
					if(sepalWidth<ranges[1][0]){
						ranges[1][0]=sepalWidth;
					}else if(sepalWidth>ranges[1][1]){
						ranges[1][1]=sepalWidth;
					}
					if(petalLength<ranges[2][0]){
						ranges[2][0]=petalLength;
					}else if(petalLength>ranges[2][1]){
						ranges[2][1]=petalLength;
					}
					if(petalWidth<ranges[3][0]){
						ranges[3][0]=petalWidth;
					}else if(petalWidth>ranges[3][1]){
						ranges[3][1]=petalWidth;
					}
				}
			}

		} catch (IOException x){
			System.err.format("IOException: %s%n", x);
		}
		return ranges;
	}
}
