package clinet;

import java.util.Iterator;
import java.util.Scanner;

import model.Automobile;

public class SelectCarOption{
	public String askConfiguration(){
		System.out.println("Want to configure your car?");
		System.out.println("1. Yes");
		System.out.println("2. No");
		
		Scanner config_sc = new Scanner(System.in);
		String configOption = config_sc.nextLine();
		return configOption;
	}
	
	public void configAuto(Automobile auto){
		StringBuffer sB = new StringBuffer("Please review your final selections. Thanks.\n");
		sB.append("Car Make: " + auto.getMake());
		sB.append("\n");
		sB.append("Car Model: " + auto.getModel());
		sB.append("\n");
		sB.append("Base Price: " + auto.getBaseprice());
		sB.append("\n");
		
		Iterator<String> iter = auto.getOpsetList().keySet().iterator();
		System.out.println("Please review the option information for " + auto.getModel());
		System.out.println(auto.print());
		while (iter.hasNext()){
			String opsetName = iter.next();
			System.out.println("\nPlease enter your option choice for " + opsetName);
			Scanner sc = new Scanner(System.in);
			String selectedOption = sc.nextLine();
			auto.setOptionChoice(opsetName, selectedOption);
			sB.append("\n");
			sB.append(opsetName);
			sB.append("\n\t");
			sB.append(auto.getOptionChoice(opsetName) + ": $" + auto.getOptionChoicePrice(opsetName));
			sB.append("\n");
		}
		System.out.println(sB.toString());
	}

}
