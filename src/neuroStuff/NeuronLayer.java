package neuroStuff;

class NeuronLayer
{
	Neuron[] neurons;
	NeuronLayer prevLayer;
	float[] values;
	int nOfNeurons;
	
	NeuronLayer(int nOfNeurons, NeuronLayer prevLayer)
	{
		if (nOfNeurons < 1)
			throw new IndexOutOfBoundsException("Too few neurons in Neuron Layer");
		
		this.values = new float[nOfNeurons];
		this.nOfNeurons = nOfNeurons;
		
		neurons = new Neuron[nOfNeurons];
		if (prevLayer != null)
		{
			for (int i = 0; i < nOfNeurons; i++) 
			{
				neurons[i] = new Neuron(prevLayer.nOfNeurons);
			}
		}
	}
}
