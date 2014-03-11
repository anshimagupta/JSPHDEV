package server;

import model.Automobile;

public interface AutoServer {
	public void buildCarModelOptions(String modelName, Automobile auto);
	public String printAllModelNames();
	public Automobile returnSelectedAuto(String modelName);
}
