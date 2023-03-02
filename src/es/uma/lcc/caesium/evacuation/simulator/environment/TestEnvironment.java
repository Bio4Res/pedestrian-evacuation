package es.uma.lcc.caesium.evacuation.simulator.environment;

import java.io.FileNotFoundException;
import java.io.FileReader;

import com.github.cliftonlabs.json_simple.JsonException;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsoner;

/**
 * Class to test the classes modeling the environment
 * @author ccottap
 *
 */
public class TestEnvironment {

	/**
	 * Main method
	 * @param args command-line arguments (name of JSON file)
	 * @throws FileNotFoundException if JSON file does  not exist
	 * @throws JsonException if there is a problem reading the JSON file
	 */
	public static void main(String[] args) throws FileNotFoundException, JsonException {
		String filename = (args.length == 0) ? "environment-example.json" : args[0];
		FileReader reader = new FileReader(filename);
		JsonObject jo = (JsonObject) Jsoner.deserialize(reader);
		Environment env = EnvironmentFactory.buildFromJSON(jo);
		System.out.println(env);
	}

}
