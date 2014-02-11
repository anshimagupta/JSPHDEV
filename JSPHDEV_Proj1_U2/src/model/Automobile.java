package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;

public class Automobile implements Serializable {
	private String make;
	private String model;
	private float baseprice;
	private LinkedHashMap<String, OptionSet> opsetList;
	private Option choice;
	
	// constructors
	public Automobile(){
		super();
		this.model = null;
		this.baseprice = 0;
		this.opsetList = new LinkedHashMap<String, OptionSet>();
	}

	public Automobile (String n, float price){
		this.model = n;
		this.baseprice = price;
		this.opsetList = new LinkedHashMap<String, OptionSet>();
	}

	// inner class of OptionSet
	private class OptionSet implements Serializable{
		private String name;
		private ArrayList<Option> optList;
		private Option optionChoice = null;
		
		protected OptionSet(){
			super();
			this.name = null;
			this.optList = new ArrayList<Option>();
		}
		
		protected OptionSet(String n){
			super();
			this.name = n;
			this.optList = new ArrayList<Option>();
		}

		protected String getName() {
			return name;
		}

		protected void setName(String name) {
			this.name = name;
		}

		protected ArrayList<Option> getOpt() {
			return optList;
		}

		protected void setOpt(ArrayList<Option> opt) { 
			this.optList = opt;
		}
		
		protected Option getOptionChoice(){
			return optionChoice;
		}
		
		protected void setOptionChoice(String OptionName){
			for (int i=0; i<optList.size(); i++){
				if (optList.get(i).getName().equals(OptionName)){
					this.optionChoice = optList.get(i);
				}
			}
		}
		
		protected String print(){
			StringBuffer sB = new StringBuffer(name);
			for (int optIndex=0; optIndex<optList.size(); optIndex++){
				sB.append("\n\t");
				sB.append(optList.get(optIndex).print());
			}
			return sB.toString();
		}
	}

	// inner class of Option
	private class Option implements Serializable {
		private String name;
		float price;
		
		public String getName() {
			return name;
		}
		protected void setName(String name) {
			this.name = name;
		}
		protected float getPrice() {
			return price;
		}
		protected void setPrice(float price) {
			this.price = price;
		}
		
		protected String print(){
			StringBuffer sB = new StringBuffer(name);
			sB.append(" - Price: $");
			sB.append(price);
			return sB.toString();
		}
	}

	// Getters
	public String getMake() {
		return make;
	}
	
	public String getModel() {
		return model;
	}
	
	public float getBaseprice() {
		return baseprice;
	}
	
	public OptionSet getOpset(String opsetName) {
		return this.opsetList.get(opsetName);
	}
	
	public String getOptionChoice(String opsetName){
		return opsetList.get(opsetName).getOptionChoice().getName();
	}
	
	public float getOptionChoicePrice(String opsetName){
		return opsetList.get(opsetName).getOptionChoice().getPrice();
	}
	
	public float getTotalPrice(){
		float totalPrice = (float) 0.0;
		Iterator<String> iter = opsetList.keySet().iterator();
		while(iter.hasNext()) {
			String key = iter.next();
			totalPrice += opsetList.get(key).getOptionChoice().getPrice();
		}
		return totalPrice;
	}
	
	// Find
	public OptionSet findOpsetWithName(String opsetName){
		return opsetList.get(opsetName);
	}
	
	public Option findOptWithName(String optName){
		Iterator<String> iter = opsetList.keySet().iterator();
		while(iter.hasNext()) {
			String key = iter.next();
			OptionSet optList = opsetList.get(key);
			for (int j=0; j<optList.getOpt().size(); j++){
				if (optList.getOpt().get(j).getName().equals(optName)){
					return optList.getOpt().get(j);
				}
			}
		}
		return null;
	}

	// setter
	public void setMake(String make) {
		this.make = make;
	}
	
	public void setModel(String model) {
		this.model = model;
	}

	public void setBaseprice(float baseprice) {
		this.baseprice = baseprice;
	}

	public void setOpsetValues(String opsetName) {
		OptionSet opset = new OptionSet(opsetName);
		opsetList.put(opsetName, opset);
	}
	
	public void setOptionValues(String opsetName, String name, float price){
		Option opt = new Option();
		opt.setName(name);
		opt.setPrice(price);
		this.opsetList.get(opsetName).getOpt().add(opt);
	}
	
	public void setOptionChoice(String opsetName, String optionName){
		opsetList.get(opsetName).setOptionChoice(optionName);
	}
	
	// update
	public void updateOpset(String opsetName, String newName){
		opsetList.get(opsetName).setName(newName);
	}
	
	public void updateOpt(String opsetName, String optName, float price){
		OptionSet OptList = opsetList.get(opsetName);
		for (int j=0; j<OptList.getOpt().size(); j++){
			if (OptList.getOpt().get(j).getName().equals(optName)){
				OptList.getOpt().get(j).setPrice(price);
				break;
			}
		}
	}
	
	public void deleteOpset(String opsetmodel){
		opsetList.remove(opsetmodel);
	}
	
	public void deleteOpt(String opsetName, String optName){
		OptionSet OptList = opsetList.get(opsetName);
		for (int j=0; j<OptList.getOpt().size(); j++){
			if (OptList.getOpt().get(j).getName().equals(optName)){
				OptList.getOpt().remove(j);
				break;
			}
		}
	}
	
	// print automobile model, base price and options
	public String print(){
		StringBuffer sB = new StringBuffer();
		sB.append("Make: ");
		sB.append(make);
		sB.append("\nModel: ");
		sB.append(model);
		sB.append("\nBase Price: $");
		sB.append(baseprice);
		
		
		Iterator<String> iter = opsetList.keySet().iterator();
		while(iter.hasNext()) {
			String key = iter.next();
			OptionSet optList = opsetList.get(key);
			sB.append("\n\n");
			sB.append(optList.print());	
		}
		return sB.toString();
	}
}
