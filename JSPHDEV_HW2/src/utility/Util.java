package utility;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import model.Student;

import exception.StuRecordsException;


public class Util {
	public static Student[] readFile(String filename, Student[] testStu){
		try{
			FileReader file = new FileReader(filename);
			BufferedReader buff = new BufferedReader(file);
			boolean eof = false;
			
			//remove the header (first line) of the data file
			buff.readLine();
			int stuCount = 0;
			while(!eof){
				String line = buff.readLine();

				if (line == null){
					eof = true;
					try{
						if (stuCount < 40){
						throw new StuRecordsException("Less than 40 records are read. " + 
								"Statistics are based on student records read.");
						} 
					} catch(StuRecordsException exception) {
							System.out.println(exception.getMsg());
					} finally{
					}
				}
				else{
					//throw a custom exception when there are more than 40 records in the data file
					try{
						if (stuCount >= 40){
							eof = true;
							throw new StuRecordsException("More than 40 records! " +
									"Only the first 40 records are read and calculated.");
						}
							Student stu = new Student();
							int[] stuScores = new int[5];
							String[] stuLine = line.split("\\t");

							for (int i=1; i<stuLine.length; i++){
								stuScores[i-1] = Integer.parseInt(stuLine[i]);
							}
							
							stu.setSID(Integer.parseInt(stuLine[0]));
							stu.setScore(stuScores);
							testStu[stuCount] = stu;
							stuCount++;
						
					} catch(StuRecordsException exception) {
						System.out.println(exception.getMsg());
					} finally{
					}
				}
			}
			buff.close();
		} catch (IOException e){
			System.out.println("Error -- " + e.toString());
		}
		
		return testStu;
	}
}
