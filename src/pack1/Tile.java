package pack1;

import processing.core.PApplet;

public class Tile
{
	Vector position;
	boolean active;
	
	private PApplet sketch;
	private static final int TILE_SIZE = Program.TILE_SIZE;
	
	public Tile(int xPos, int yPos)
	{
		this.sketch = Program.sketch;
		position = new Vector(xPos * TILE_SIZE, yPos * TILE_SIZE);
		active = false;
	}
	
	public void display()
	{
		sketch.fill(active ? 0 : 255);
		Program.rect(position, TILE_SIZE);
	}
	
	public void activate() 
	{
		if (sketch.mousePressed)
		{
			if (mouseOn())
			{
				this.active = true;
			}
		}
	}
	
	private boolean mouseOn() 
	{
		return (int) Math.abs(sketch.mouseX - TILE_SIZE / 2 - position.x) < TILE_SIZE / 2 
			&& (int) Math.abs(sketch.mouseY - TILE_SIZE / 2 - position.y) < TILE_SIZE / 2;
	}
}
