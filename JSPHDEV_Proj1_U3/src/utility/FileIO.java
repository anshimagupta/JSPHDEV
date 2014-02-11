package utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import adapter.BuildAuto;

import model.Automobile;

public class FileIO {
	public void serializeAuto(BuildAuto buildAuto, String fileName) throws FileNotFoundException, IOException{
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName));
		out.writeObject(buildAuto);
		out.close();
	}
	
	public BuildAuto deserializeAuto(String fileName) throws FileNotFoundException, IOException, ClassNotFoundException{
		ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName));
		BuildAuto buildAuto = (BuildAuto) in.readObject();
		return buildAuto;
	}
}
