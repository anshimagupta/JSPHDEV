package adapter;
import java.io.Serializable;
import java.util.LinkedHashMap;

import utility.ReadFile;
import model.*;

public abstract class ProxyAutoMobile implements Serializable {
	private static LinkedHashMap<String, Automobile> auto_shop = new LinkedHashMap<String, Automobile>();
	
	public void buildAuto(String modelName, String fileName){
		ReadFile reader = new ReadFile();
		auto_shop.put(modelName, reader.buildAutoObject(fileName));
	}
	
	public void printAuto(String modelName){
		System.out.println(auto_shop.get(modelName).print());
	}
	
	public void updateOptionSetName(String modelName, String opsetName, String newName){
		auto_shop.get(modelName).updateOpset(opsetName, newName);
	}
	
	public void updateOptionPrice(String modelName, String opsetName, String optName, float newPrice){
		auto_shop.get(modelName).updateOpt(opsetName, optName, newPrice);
	}
	
	public void setOptionChoice(String modelName, String opsetName, String optName){
		auto_shop.get(modelName).setOptionChoice(opsetName, optName);
	}
	
	public String getOptionChoice(String modelName, String opsetName){
		return auto_shop.get(modelName).getOptionChoice(opsetName);
	}
	
	public float getOptionChoicePrice(String modelName, String opsetName){
		return auto_shop.get(modelName).getOptionChoicePrice(opsetName);
	}
	
	public void eidtOption(String modelName, String opsetName, String optName, float optPrice){
		auto_shop.get(modelName).updateOpt(opsetName, optName, optPrice);
	}
}
