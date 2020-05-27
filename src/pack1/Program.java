package pack1;
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
	
	static void rect(Vector position, float size) 
	{
		sketch.rect(position.x, position.y, size, size);
	}
	
	//--------------------------------------------- Processing part -----------------------------------------------------------------
	
	Tile[] tiles = new Tile[N_OF_TILES];
	NeuralNetwork nn = new NeuralNetwork(N_OF_TILES, 12, 12, 10);
	
	public void settings() 
	{
		size(W_IN_TILES * TILE_SIZE, H_IN_TILES * TILE_SIZE);
	}
	public void setup() 
	{
		noStroke();
		for (int i = 0; i < N_OF_TILES; i++)
		{
			tiles[i]= new Tile(i % W_IN_TILES, (int) (i / W_IN_TILES)); 
		}
		
		float[] data = new float[N_OF_TILES];
		for (int i = 0; i < N_OF_TILES; i++)
			data[i] = .5f;
		println(nn.countValues(data));
	}
	public void draw()
	{
		for (Tile tile : tiles)
		{
			tile.display();
			tile.activate();
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
