package neuroStuff;

class Neuron
{
	float[] weights;
	float bias;

	float mistakeKoef;
	int nOfWeights;

	Neuron(int nOfWeights)
	{
		this.nOfWeights = nOfWeights;
		mistakeKoef = 0;
		weights = new float[nOfWeights];
		for (int i = 0; i < nOfWeights; i++)
			weights[i] = (float) (Math.random() - .5);
	}

	float countValue(float[] inputs)
	{
		float result = 0;
		for (int i = 0; i < nOfWeights; i++)
			result += weights[i] * inputs[i];
		result += bias;
		mistakeKoef = NeuralNetwork.dActivFunc(result);
		return NeuralNetwork.activFunc(result);
	}
}
