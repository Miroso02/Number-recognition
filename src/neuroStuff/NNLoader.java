package neuroStuff;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

class NNLoader
{
  NNLoader() {}

  void saveTo(String fileName, NeuralNetwork nn) throws IOException
	{
		BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
		String dataToSave = nn.stringify();
		writer.write(dataToSave);
		writer.close();
	}

	NeuralNetwork loadFrom(String fileName) throws IOException
	{
		BufferedReader reader = new BufferedReader(new FileReader(fileName));
		ArrayList<String> loadedData = new ArrayList<>();
		String line;
		while ((line = reader.readLine()) != null)
			loadedData.add(line);
		reader.close();
		return createNNfromStrings(loadedData);
	}

	private NeuralNetwork createNNfromStrings(ArrayList<String> layers)
	{
		ArrayList<ArrayList<ArrayList<Float>>> nnData = new ArrayList<>();

		int[] structure = new int[layers.size() + 1];
		for (int i = 0; i < layers.size(); i++)
		{
			String layerData = layers.get(i);
			String[] neurons = layerData.split(";");
			for (int j = 0; j < neurons.length; j++) {
				String neuronData = neurons[i];
				String[] weights = neuronData.split(",");
				if (structure[i] == 0)
					structure[i] = weights.length;
			}
		}

		NeuralNetwork loadedNN = new NeuralNetwork();

    return loadedNN;
	}
}
