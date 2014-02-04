package utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import model.Automobile;

public class FileIO {
	public void serializeAuto(Automobile automobile, String fileName) throws FileNotFoundException, IOException{
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName));
		out.writeObject(automobile);
		out.close();
	}
	
	public Automobile deserializeAuto(String fileName) throws FileNotFoundException, IOException, ClassNotFoundException{
		ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName));
		Automobile automobile = (Automobile) in.readObject();
		return automobile;
	}
}
