package neuroStuff;

public class NeuralNetwork
{
	NeuronLayer[] layers;
	private NeuronLayer inputLayer;
	private NeuronLayer outputLayer;
	private int nOfLayers;
	
	public NeuralNetwork(int ...neuronsInLayer)
	{
		nOfLayers = neuronsInLayer.length;
		if (nOfLayers < 2)
			throw new IndexOutOfBoundsException("Too few layers in Neural Network");
		inputLayer = new NeuronLayer(neuronsInLayer[0], null);
	}
}
