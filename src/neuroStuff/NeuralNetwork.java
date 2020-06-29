package neuroStuff;

import java.io.IOException;
import java.io.Serializable;

public class NeuralNetwork implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private NeuronLayer[] layers;
	private NeuronLayer inputLayer;
	private NeuronLayer outputLayer;
	private int nOfLayers;
	private static NNLoader loader = new NNLoader();

	//------------------------------------- Constructor ------------------------------------------------------------------------

	public NeuralNetwork(int ...neuronsInLayer)
	{
		nOfLayers = neuronsInLayer.length;
		if (nOfLayers < 2)
			throw new IndexOutOfBoundsException("Too few layers in Neural Network");

		layers = new NeuronLayer[nOfLayers];
		inputLayer = new NeuronLayer(neuronsInLayer[0], null);
		layers[0] = inputLayer;
		for (int i = 1; i < nOfLayers; i++)
		{
			layers[i] = new NeuronLayer(neuronsInLayer[i], layers[i - 1]);
		}
		outputLayer = layers[nOfLayers - 1];
	}

	//-------------------------------- Main methods ----------------------------------------------------------------------------

	public float[] countValues(float[] inputs)
	{
		if (inputs.length != inputLayer.nOfNeurons)
			throw new IndexOutOfBoundsException("Invalid amount of inputs: " + inputs.length + "; Expected: " + inputLayer.nOfNeurons);

		inputLayer.values = inputs;
		for (int i = 1; i < nOfLayers; i++)
			layers[i].countValues();
		return outputLayer.values;
	}

	public float[] train(float[] inputs, float[] answers)
	{
		float[] results = countValues(inputs);
		float[] mistakes = new float[results.length];
		for (int i = 0; i < results.length; i++)
			mistakes[i] = answers[i] - results[i];

		outputLayer.changeWeights(mistakes);
		return results;
	}

	//------------------------------------ Activation functions ---------------------------------------------------------------------------

	static float activFunc(float input)
	{
		return (float) (1 / (Math.exp(-input) + 1));
	}
	static float dActivFunc(float input)
	{
		return (float) (Math.exp(-input) / Math.pow(Math.exp(-input) + 1, 2));
	}

	//------------------------- Other methods ---------------------------------------------------------------

	public String stringify()
	{
		String result = "";

		for (int i = 1; i < nOfLayers; i++)
		{
      for (Neuron n : layers[i].neurons)
			{
        for (float w : n.weights)
				{
          result += w + ",";
        }
        result += n.bias + ";";
      }
      result += "\n";
    }
		return result;
	}

	//------------------------- Saving / Loading methods -------------------------

	public void saveTo(String fileName)
	{
		try
		{
			loader.saveTo(fileName, this);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public static NeuralNetwork loadFrom(String fileName)
	{
		try
		{
			return loader.loadFrom(fileName);
		}
		catch (IOException e)
		{
			e.printStackTrace();
			return null;
		}
	}
}
