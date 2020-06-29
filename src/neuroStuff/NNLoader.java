package neuroStuff;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

class NNLoader
{
  NNLoader() {}

  void saveTo(String fileName, NeuralNetwork nn) throws IOException
	{
		FileOutputStream fos = new FileOutputStream(fileName);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(nn);
		fos.close();
		oos.close();
	}

	NeuralNetwork loadFrom(String fileName) throws IOException
	{
		try 
		{
			FileInputStream fis = new FileInputStream(fileName);
			ObjectInputStream ois = new ObjectInputStream(fis);
			NeuralNetwork nn = (NeuralNetwork) ois.readObject();
			fis.close();
			ois.close();
			return nn;
		}
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
}
