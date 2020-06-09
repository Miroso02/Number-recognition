package pack1;

import java.util.ArrayList;
import processing.core.PApplet;

public class Tile
{
	Vector position;
	float activation;

	private ArrayList<Tile> adjacent = new ArrayList<Tile>();
	private PApplet sketch;
	private static final int TILE_SIZE = Program.TILE_SIZE;

	public Tile(int xPos, int yPos)
	{
		this.sketch = Program.sketch;
		position = new Vector(xPos * TILE_SIZE, yPos * TILE_SIZE);
		activation = 0;
	}

	public void display()
	{
		sketch.fill(255 * (1 - activation));
		Program.rect(position, TILE_SIZE);
	}

	public void activate()
	{
		if (sketch.mousePressed)
		{
			if (mouseOn())
			{
				activation += .6;
				if (activation > 1) activation = 1;
				for (Tile tile : adjacent)
				{
					tile.activation += .15;
					if (tile.activation > 1)
						tile.activation = 1;
				}
			}
		}
	}

	private boolean mouseOn()
	{
		return (int) Math.abs(sketch.mouseX - TILE_SIZE / 2 - position.x) < TILE_SIZE / 2
			&& (int) Math.abs(sketch.mouseY - TILE_SIZE / 2 - position.y) < TILE_SIZE / 2;
	}

	public boolean addAdj(Tile adj)
	{
		if (adj != null)
		{
			adjacent.add(adj);
			return true;
		}
		return false;
	}
}
