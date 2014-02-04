package utility;
import java.io.*; 

import model.Automobile;

public class ReadFile {
	public Automobile buildAutoObject(String fileName, Automobile auto){
		try{
			FileReader file = new FileReader(fileName);
			BufferedReader buff = new BufferedReader(file);
			boolean eof = false;
			Automobile automobile = auto;
			
			int opsetIndex = 0;
			while (eof != true){
				String line = buff.readLine();
				
				if (line == null){
					eof = true;
				} else {
					String[] opset = line.split(":");
					String opsetName = opset[0];
					String[] opt = opset[1].split(";");
					int optionSize = opt.length;
					
					automobile.setOpsetValues(opsetIndex, opsetName, optionSize); 
					for (int optIndex=0; optIndex<optionSize; optIndex++){
						String[] opsetSplit = opt[optIndex].split(",");
						String optName = opsetSplit[0];
						float optPrice = Float.parseFloat(opsetSplit[1]);
						automobile.setOptionValues(opsetIndex, optIndex, optName, optPrice);
					}
					opsetIndex++;
				}
			}
			buff.close();
		} catch (IOException e){
			System.out.println("Error -- " + e.toString());
		}
		return auto;
	}
}
