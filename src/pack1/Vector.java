package pack1;

public class Vector
{
	public float x;
	public float y;

	public Vector(float x, float y)
	{
		this.x = x;
		this.y = y;
	}

	public Vector add(Vector v)
	{
		x += v.x;
		y += v.y;
		return this;
	}
	public Vector mult(float scalar)
	{
		x *= scalar;
		y *= scalar;
		return this;
	}

	public Vector copy()
	{
		return new Vector(this.x, this.y);
	}

	public void set(float x, float y)
	{
		this.x = x;
		this.y = y;
	}

	public static Vector copy(Vector v)
	{
		return new Vector(v.x, v.y);
	}
}
