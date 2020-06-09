package pack1;

public class TileController
{
	Tile tiles[];
	final int tilesNumber;
	final int W_IN_TILES;
	final int H_IN_TILES;

	public TileController(int tilesNumber)
	{
		this.W_IN_TILES = Program.W_IN_TILES;
		this.H_IN_TILES = Program.H_IN_TILES;
		this.tilesNumber = tilesNumber;

		tiles = new Tile[tilesNumber];
		for (int i = 0; i < tilesNumber; i++)
		{
			int xPos = i % W_IN_TILES;
			int yPos = (int) (i / W_IN_TILES);
			tiles[i] = new Tile(xPos, yPos);
		}

		for (int i = 0; i < W_IN_TILES; i++)
		{
			for (int j = 0; j < H_IN_TILES; j++)
			{
				Tile tile = getTile(i, j);
				tile.addAdj(getTile(i - 1, j));
				tile.addAdj(getTile(i + 1, j));
				tile.addAdj(getTile(i, j - 1));
				tile.addAdj(getTile(i, j + 1));
			}
		}
	}

	public void update()
	{
		for (Tile tile : tiles)
		{
			tile.activate();
			tile.display();
		}
	}

	public void clear()
	{
		for (Tile tile : tiles)
		{
			tile.activation = 0;
		}
	}

	public float[] getData()
	{
		float data[] = new float[tilesNumber];
		for (int i = 0; i < tilesNumber; i++)
			data[i] = tiles[i].activation;
		return data;
	}

	public boolean isAnyActive()
	{
		for (Tile t : tiles)
			if (t.activation > 0)
				return true;
		return false;
	}

	public Tile getTile(int xPos, int yPos)
	{
		if (xPos < 0 || xPos >= W_IN_TILES || yPos < 0 || yPos >= H_IN_TILES)
			return null;
		return tiles[xPos + yPos * W_IN_TILES];
	}
}
