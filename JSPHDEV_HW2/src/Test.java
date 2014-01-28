import model.*;
import utility.Util;

public class Test {

	public static void main(String[] args) {
		model.Student[] testStu = new Student[40];
		testStu = Util.readFile("/home/bing/workspace/JSPHDEV_HW2/HW2_Sample_data.csv", testStu);
		StudentStatistics stuStats = new StudentStatistics();
		stuStats.findLow(testStu);
		stuStats.findHigh(testStu);
		stuStats.calculateAvg(testStu);
		
		// print individual student scores
		System.out.println("\nStud\tQu1\tQu2\tQu3\tQu4\tQu5");
		for (int i=0; i<testStu.length; i++){
			testStu[i].printRecords();
		}
		System.out.println();
		
		// print high, low, and average scores
		stuStats.printStats();
	}
}
