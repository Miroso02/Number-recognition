package pack1;

public class TileController
{
	Tile[] tiles;
	final int tilesNumber;
	
	public TileController(int tilesNumber)
	{
		this.tilesNumber = tilesNumber;
		tiles = new Tile[tilesNumber];
		for (int i = 0; i < tilesNumber; i++)
		{
			tiles[i] = new Tile(i % Program.W_IN_TILES, (int) (i / Program.W_IN_TILES));
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
			tile.active = false;
		}
	}
	
	public float[] getData()
	{
		float[] data = new float[tilesNumber];
		for (int i = 0; i < tilesNumber; i++)
		{
			data[i] = tiles[i].active ? 1 : 0; 
		}
		return data;
	}
	
	public boolean isAnyActive()
	{
		for (Tile t : tiles)
			if (t.active)
				return true;
		return false;
	}
}
