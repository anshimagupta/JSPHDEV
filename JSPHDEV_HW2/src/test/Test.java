package test;

import model.*;
import utility.Util;

public class Test {

	public static void main(String[] args) {
		// test with 39 students
		System.out.println("=============================");
		System.out.println("Test with 39 line of records:");
		model.Student[] testStu39 = new Student[40];
		testStu39 = Util.readFile("HW2_Sample_data_39stu.csv", testStu39);
		StudentStatistics stuStats1 = new StudentStatistics();
		stuStats1.findLow(testStu39);
		stuStats1.findHigh(testStu39);
		stuStats1.calculateAvg(testStu39);
		
		// print individual student scores
		System.out.println("\nStud\tQu1\tQu2\tQu3\tQu4\tQu5");
		for (int i=0; i<testStu39.length; i++){
			if (testStu39[i]!=null){
				testStu39[i].printRecords();
			}
		}
		System.out.println();
		
		// print high, low, and average scores
		stuStats1.printStats();		
		
		System.out.println("\n");
		
		// test with 40 students
		System.out.println("=============================");
		System.out.println("Test with 40 line of records:");
		model.Student[] testStu40 = new Student[40];
		testStu40 = Util.readFile("HW2_Sample_data_40stu.csv", testStu40);
		StudentStatistics stuStats2 = new StudentStatistics();
		stuStats2.findLow(testStu40);
		stuStats2.findHigh(testStu40);
		stuStats2.calculateAvg(testStu40);
		
		// print individual student scores
		System.out.println("\nStud\tQu1\tQu2\tQu3\tQu4\tQu5");
		for (int i=0; i<testStu40.length; i++){
			testStu40[i].printRecords();
		}
		System.out.println();
		
		// print high, low, and average scores
		stuStats2.printStats();
		
		System.out.println("\n");
		
		// test with 41 students
		System.out.println("=============================");
		System.out.println("Test with 41 line of records:");
		model.Student[] testStu41 = new Student[40];
		testStu41 = Util.readFile("HW2_Sample_data_41stu.csv", testStu41);
		StudentStatistics stuStats3 = new StudentStatistics();
		stuStats3.findLow(testStu41);
		stuStats3.findHigh(testStu41);
		stuStats3.calculateAvg(testStu41);
		
		// print individual student scores
		System.out.println("\nStud\tQu1\tQu2\tQu3\tQu4\tQu5");
		for (int i=0; i<testStu41.length; i++){
			testStu41[i].printRecords();
		}
		System.out.println();
		
		// print high, low, and average scores
		stuStats3.printStats();
	}
}
