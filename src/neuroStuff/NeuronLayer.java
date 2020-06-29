package neuroStuff;

import java.io.Serializable;

class NeuronLayer implements Serializable
{
	private static final long serialVersionUID = 2L;
	
	Neuron[] neurons;
	NeuronLayer prevLayer;
	float[] values;
	final int nOfNeurons;

	NeuronLayer(int nOfNeurons, NeuronLayer prevLayer)
	{
		if (nOfNeurons < 1)
			throw new IndexOutOfBoundsException("Too few neurons in Neuron Layer");

		this.values = new float[nOfNeurons];
		this.prevLayer = prevLayer;
		this.nOfNeurons = nOfNeurons;

		neurons = new Neuron[nOfNeurons];
		if (prevLayer != null)
		{
			for (int i = 0; i < nOfNeurons; i++)
				neurons[i] = new Neuron(prevLayer.nOfNeurons);
		}
	}

	void countValues()
	{
		for (int i = 0; i < nOfNeurons; i++)
			values[i] = neurons[i].countValue(prevLayer.values);
	}

	void changeWeights(float[] mistakes)
	{
		if (prevLayer == null) return;

		float sumOfMistakes = 0;
		for (int i = 0; i < nOfNeurons; i++)
		{
			Neuron neuron = neurons[i];
			float curNeuronMistake = neuron.mistakeKoef
															* (mistakes.length > 1 ? mistakes[i] : mistakes[0]);
			sumOfMistakes += curNeuronMistake;

			for (int j = 0; j < neuron.nOfWeights; j++)
				neuron.weights[j] += curNeuronMistake * prevLayer.values[j];
			neuron.bias += curNeuronMistake;
		}
		float[] mistake = { sumOfMistakes };
		prevLayer.changeWeights(mistake);
	}
}
