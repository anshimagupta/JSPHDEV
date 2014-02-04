package model;

import java.io.Serializable;

public class Automobile implements Serializable {
	private String name;
	private float baseprice;
	private OptionSet opset[];
	
	// constructors
	public Automobile(){
		super();
	}

	public Automobile (String n, float price, int size){
		this.name = n;
		this.baseprice = price;
		this.opset = new OptionSet[size];
		
		// instantiate object to avoid NullPointerException
		for (int i=0; i<this.opset.length; i++){
			this.opset[i] = new OptionSet();
		}
	}

	// inner class of OptionSet
	private class OptionSet implements Serializable{
		private String name;
		private Option opt[];
		
		protected OptionSet(){
			super();
		}
		
		protected OptionSet(String n, int size){
			this.name = n;
			this.opt = new Option[size];
			
			// instantiate object to avoid NullPointerException
			for (int i=0; i<this.opt.length; i++){
				this.opt[i] = new Option();
			}
		}

		protected String getName() {
			return name;
		}

		protected void setName(String name) {
			this.name = name;
		}

		protected Option[] getOpt() {
			return opt;
		}

		protected void setOpt(int index, Option opt) {
			this.opt[index] = opt;
		}
		
		protected String print(){
			StringBuffer sB = new StringBuffer(name);
			for (int optIndex=0; optIndex<opt.length; optIndex++){
				sB.append("\n\t");
				sB.append(opt[optIndex].print());
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
	public String getName() {
		return name;
	}
	
	public float getBaseprice() {
		return baseprice;
	}
	
	public OptionSet getOpset(int index) {
		return opset[index];
	}
	
	// Find
	public OptionSet findOpsetWithName(String opsetName){
		int index = -1;
		for (int i=0; i< opset.length; i++){
			if (opset[i].getName() == opsetName){
				index = i;
				break;
			}
		}
		return opset[index];
	}
	
	public Option findOptWithName(String optName){
		int opsetIndex = -1;
		int optIndex = -1;
		
		for (int i=0; i< opset.length; i++){
			for (int j=0; j<opset[i].getOpt().length; j++){
				if (opset[i].getOpt()[j].getName() == optName){
					opsetIndex = i;
					optIndex = j;
					break;
				}
			}

		}
		return opset[opsetIndex].getOpt()[optIndex];
	}

	// setter
	public void setName(String name) {
		this.name = name;
	}

	public void setBaseprice(float baseprice) {
		this.baseprice = baseprice;
	}

	public void setOpsetValues(int index, String opsetName, int optSize) {
		OptionSet opset = new OptionSet(opsetName, optSize);
		opset.setName(opsetName);
		this.opset[index] = opset;
	}
	
	public void setOptionValues(int opsetIndex, int optIndex, String name, float price){
		Option opt = new Option();
		opt.setName(name);
		opt.setPrice(price);
		this.opset[opsetIndex].setOpt(optIndex, opt);
	}
	
	// update
	public void updateOpset(String opsetName, OptionSet opset){
		for (int i=0; i<this.opset.length; i++){
			if (this.opset[i].getName() == opsetName){
				this.opset[i] = opset;
				break;
			}
		}
	}
	
	public void updateOpt(String opsetName, String optName, float price){
		for (int i=0; i<this.opset.length; i++){
			if (this.opset[i].getName() == opsetName){
				for (int j=0; j<this.opset[i].getOpt().length; j++){
					if (this.opset[i].getOpt()[j].getName() == optName){
						this.opset[i].getOpt()[j].setPrice(price);
						break;
					}
				}
			}
		}
	}
	
	public void deleteOpset(String opsetName){
		for (int i=0; i<this.opset.length; i++){
			if (this.opset[i].getName() == opsetName){
				// int optionLength = this.opset[i].getOpt().length;
				this.opset[i] = null;
				break;
			}
		}
	}
	
	public void deleteOpt(String opsetName, String optName){
		for (int i=0; i<this.opset.length; i++){
			if (this.opset[i].getName() == opsetName){
				for (int j=0; j<this.opset[i].getOpt().length; j++){
					if (this.opset[i].getOpt()[j].getName() == optName){
						this.opset[i].getOpt()[j] = null;
						break;
					}
				}
			}
		}
	}
	
	// print automobile name, base price and options
	public String print(){
		StringBuffer sB = new StringBuffer("Model Name: ");
		sB.append(name);
		sB.append("\nBase Price: $");
		sB.append(baseprice);
		
		for (int opsetIndex=0; opsetIndex<opset.length; opsetIndex++){
			sB.append("\n\n");
			sB.append(opset[opsetIndex].print());
		}
		return sB.toString();
	}
}
