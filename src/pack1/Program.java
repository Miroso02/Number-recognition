package pack1;
import java.io.IOException;

import neuroStuff.NeuralNetwork;
import processing.core.PApplet;

public class Program extends PApplet
{
	//------------------------------------------- Static variables -------------------------------------------------------------------

	static PApplet sketch;

	static final int W_IN_TILES = 20;
	static final int H_IN_TILES = 32;
	static final int TILE_SIZE = 30;
	static final int N_OF_TILES = W_IN_TILES * H_IN_TILES;
	static final String INPUT_NUMBERS = "1234567890";

	static void rect(Vector position, float size)
	{
		sketch.rect(position.x, position.y, size, size);
	}

	//--------------------------------------------- Processing part -----------------------------------------------------------------

	NeuralNetwork nn;
	TileController tileController;

	public void settings()
	{
		size(W_IN_TILES * TILE_SIZE, H_IN_TILES * TILE_SIZE);
	}
	public void setup()
	{
		noStroke();
		tileController = new TileController(N_OF_TILES);
		nn = new NeuralNetwork(N_OF_TILES, 12, 12, 10);
		try
		{
			nn.saveTo("savedNNs/NN1");
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	public void draw()
	{
		tileController.update();

		if (keyPressed && tileController.isAnyActive())
		{
			if (INPUT_NUMBERS.indexOf(key) != -1)
			{
				int answer = Integer.parseInt("" + key);
				println(answer);

				float[] answers = new float[10];
				answers[answer] = 1;
				float[] inputs = tileController.getData();
				println(nn.train(inputs, answers));
				tileController.clear();
			}
		}
	}

	//----------------------------------------------- main() ----------------------------------------------------------------------------

	public static void main(String[] args)
	{
		sketch = new Program();
		String[] pargs = {""};
		PApplet.runSketch(pargs, sketch);
	}
}
