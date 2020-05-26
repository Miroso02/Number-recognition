package neuroStuff;

class Neuron
{
	float[] weights;
	int nOfWeights;
	
	Neuron(int nOfWeights)
	{	
		this.nOfWeights = nOfWeights;
		weights = new float[nOfWeights];
		for (int i = 0; i < nOfWeights; i++)
		{
			weights[i] = (float) (Math.random() - .5); 
		}
	}
}
