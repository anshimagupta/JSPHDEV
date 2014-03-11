package clinet;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

public class CarModelOptionsIO {
	public Properties readPropertyFile(String filename){
		String fileName = filename;
		Properties props = new Properties();
		if(fileName.equalsIgnoreCase("")){
			System.out.println("Please enter name of file containing auto information:");
			Scanner input_sc = new Scanner(System.in);
			fileName = input_sc.nextLine();
		} 

		FileInputStream in = null;
		try {
			in = new FileInputStream(fileName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			props.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return props;
	}
}
