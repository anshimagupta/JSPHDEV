package clinet;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

public class CarModelOptionsIO {
	public Properties readPropertyFile(){
		System.out.println("Please enter name of file containing auto information:");
		Scanner input_sc = new Scanner(System.in);
		String fileName = input_sc.nextLine();

		FileInputStream in = null;
		try {
			in = new FileInputStream(fileName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Properties props = new Properties();
		try {
			props.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return props;
	}
}
