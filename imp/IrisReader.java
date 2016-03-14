package imp;

import java.io.BufferedReader;
import java.io.IOException;
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
	private final String root = "../ass1-data/part1/";//This is where the data sets should be.
	/**
	 * Constructor for IrisReader with a given filename.
	 * @param filename
	 */
	public IrisReader(String filename){
		switch(filename){
		case "iris-test.txt":
			break;
		case "iris-training.txt":
			break;
		case "iris-data.txt":
			break;
		default:
			System.out.println("No such data file exists.");
		}

	}

	public List<Iris> readTrainingset(String filename){
		String directory = root+filename;
		Path path = Paths.get(directory);
		try(BufferedReader reader = Files.newBufferedReader(path)){
			String line = null;
			while((line = reader.readLine()) != null){
				String[] words = line.split(" ");
				if(words.length > 2){
					if(words[1].equals("Front")){
						break;
					}
				}
			}
			Set<ArrayList<Double>> front = new HashSet<ArrayList<Double>>();
			while((line = reader.readLine()) != null){
				String[] words = line.split(" ");
				if(words[0].equals("Fitness:")){
					ArrayList<Double> qos = new ArrayList<Double>();
					double t = Double.parseDouble(words[1].substring(1));
					double c = Double.parseDouble(words[2]);
					double a = 1.0 - Double.parseDouble(words[3]);
					double r = 1.0 - Double.parseDouble(words[4].substring(0, words[4].length()-1));
					qos.add(t);
					qos.add(c);
					qos.add(a);
					qos.add(r);
					front.add(qos);
					//frontWriter.println(words[1].substring(1)+" "+words[2]+" "+words[3]+" "+words[4].substring(0, words[4].length()-1));
				}
			}
		} catch (IOException x){
			System.err.format("IOException: %s%n", x);
		}
		return null;
	}

	public List<Iris> readTestset(String filename){
		return null;
	}

	public double[][] readDataset(String filename){
		return null;
	}
}
