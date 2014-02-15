package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;

import exception.AutoOptionException;

public class Automobile implements Serializable {
	/**
	 * Add generated serial version id
	 */
	private static final long serialVersionUID = 5774851700803777533L;
	private String make;
	private String model;
	private float baseprice;
	private LinkedHashMap<String, OptionSet> opsetList;
	
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

	/**
	 * Define inner class - OptionSet
	 * Each automobile object contains a number of option sets,
	 * which stored in linked hash map.
	 */
	private class OptionSet implements Serializable{
		/**
		 * Add generated serial version id
		 */
		private static final long serialVersionUID = -8217196236787222819L;
		private ArrayList<Option> optList;
		private Option optionChoice = null;
		
		protected OptionSet(){
			super();
			this.optList = new ArrayList<Option>();
		}
		

		protected synchronized ArrayList<Option> getOpt() {
			return optList;
		}
		
		protected synchronized Option getOptionChoice(){
			return optionChoice;
		}
		
		protected synchronized void setOptionChoice(String OptionName){
			for (int i=0; i<optList.size(); i++){
				if (optList.get(i).getName().equals(OptionName)){
					this.optionChoice = optList.get(i);
				}
			}
		}
		
		protected synchronized String print(){
			StringBuffer sB = new StringBuffer();
			for (int optIndex=0; optIndex<optList.size(); optIndex++){
				sB.append("\n\t");
				sB.append(optList.get(optIndex).print());
			}
			return sB.toString();
		}
	}

	/**
	 * Define inner class - Option
	 * Each option set object contains a number of options,
	 * which stored in array list.
	 */
	private class Option implements Serializable {
		/**
		 * Add generated serial version id
		 */
		private static final long serialVersionUID = 2040570308448757416L;
		private String name;
		float price;
		
		public synchronized String getName() {
			return name;
		}
		protected synchronized void setName(String name) {
			this.name = name;
		}
		protected synchronized float getPrice() {
			return price;
		}
		protected synchronized void setPrice(float price) {
			this.price = price;
		}
		
		protected synchronized String print(){
			StringBuffer sB = new StringBuffer(name);
			sB.append(" - Price: $");
			sB.append(price);
			return sB.toString();
		}
	}

	/**
	 * Define Getters, which used to return values of automobile parameters, option sets and options
	 */
	public synchronized String getMake() {
		return make;
	}
	
	public synchronized String getModel() {
		return model;
	}
	
	public synchronized float getBaseprice() {
		return baseprice;
	}
	
	public synchronized LinkedHashMap<String, OptionSet> getOpsetList() {
		return opsetList;
	}
	
	public synchronized OptionSet getOpset(String opsetName) {
		return opsetList.get(opsetName);
	}
	
	public synchronized String getOptionChoice(String opsetName){
		return opsetList.get(opsetName).getOptionChoice().getName();
	}
	
	public synchronized float getOptionChoicePrice(String opsetName){
		return opsetList.get(opsetName).getOptionChoice().getPrice();
	}
	
	public synchronized float getTotalPrice(){
		float totalPrice = (float) 0.0;
		Iterator<String> iter = opsetList.keySet().iterator();
		while(iter.hasNext()) {
			String key = iter.next();
			totalPrice += opsetList.get(key).getOptionChoice().getPrice();
		}
		return totalPrice;
	}
	
	/**
	 * Define Finders, which used to return specific automobile option set or option
	 */
	public synchronized OptionSet findOpsetWithName(String opsetName){
		return opsetList.get(opsetName);
	}
	
	public synchronized Option findOptWithName(String optName){
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

	/**
	 * Define Setters, which used to set values of automobile parameters, option sets and options
	 */
	public synchronized void setMake(String make) {
		this.make = make;
	}
	
	public synchronized void setModel(String model) {
		this.model = model;
	}

	public synchronized void setBaseprice(float baseprice) {
		this.baseprice = baseprice;
	}

	public synchronized void setOpsetValues(String opsetName) {
		OptionSet opset = new OptionSet();
		opsetList.put(opsetName, opset);
	}
	
	public synchronized void setOptionValues(String opsetName, String name, float price){
		Option opt = new Option();
		opt.setName(name);
		opt.setPrice(price);
		this.opsetList.get(opsetName).getOpt().add(opt);
	}
	
	public synchronized void setOptionChoice(String opsetName, String optionName){
		opsetList.get(opsetName).setOptionChoice(optionName);
	}
	
	/**
	 * Define update functions, which used to update values of specific automobile option sets and options.
	 */
	public void updateOpsetName(String opsetName, String newName){
		try{
			if (!opsetList.containsKey(opsetName))
				throw new AutoOptionException("The option set name entered is not found. Please check!" + 
								"\nNo update is made.");
			else {
				opsetList.put(newName, opsetList.get(opsetName));
				opsetList.remove(opsetName);
				System.out.println("Updated!");
			}
		} catch(AutoOptionException e){
			System.out.println(e.getMsg());
		}
	}
	
	public synchronized void updateOpt(String opsetName, String optName, float price){
		try{
			if(!opsetList.containsKey(opsetName))
				throw new AutoOptionException("The option set name entered is not found. Please check!" + 
						"\nNo update is made.");
			else{
				OptionSet OptList = opsetList.get(opsetName);
				boolean optNameExists = false;
				for (int j=0; j<OptList.getOpt().size(); j++){
					if (OptList.getOpt().get(j).getName().equals(optName)){
						OptList.getOpt().get(j).setPrice(price);
						optNameExists = true;
						break;
					}
				}
				if (optNameExists == false)
					throw new AutoOptionException("The option name entered is not found. Please check!" + 
							"\nNo update is made.");
			}
		} catch(AutoOptionException e){
			System.out.println(e.getMsg());
		}
	}
	
	/**
	 * Define delete date functions, which used to delete values of specific automobile option sets and options.
	 */
	public synchronized void deleteOpset(String opsetmodel){
		opsetList.remove(opsetmodel);
	}
	
	public synchronized void deleteOpt(String opsetName, String optName){
		OptionSet OptList = opsetList.get(opsetName);
		for (int j=0; j<OptList.getOpt().size(); j++){
			if (OptList.getOpt().get(j).getName().equals(optName)){
				OptList.getOpt().remove(j);
				break;
			}
		}
	}
	
	// print automobile model, base price and options
	public synchronized String print(){
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
			sB.append(key);	
			sB.append(optList.print());	
		}
		return sB.toString();
	}
}
