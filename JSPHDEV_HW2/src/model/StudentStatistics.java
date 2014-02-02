package model;

public class StudentStatistics extends Stats {
	@Override
	public void findLow(Student[] stu){
		for (int i = 0; i < numQuiz; i++){
			int min = stu[0].getScore()[i];
			for (int j = 0; j < stu.length; j++){
				if (stu[j]!=null && min > stu[j].getScore()[i])
					min = stu[j].getScore()[i];
			}
			lowscores[i] = min;
		}
	}
	
	@Override
	public void findHigh(Student[] stu){
		for (int i = 0; i < numQuiz; i++){
			int max = stu[0].getScore()[i];
			for (int j = 0; j < stu.length; j++){
				if (stu[j]!=null && max < stu[j].getScore()[i])
					max = stu[j].getScore()[i];
			}
			highscores[i] = max;
		}
	}
	
	@Override
	public void calculateAvg(Student[] stu){
		for (int i = 0; i < numQuiz; i++){
			int sum = 0;
			float avg = 0;
			int stuCount = 0;
			
			for (int j = 0; j < stu.length; j++){
				if (stu[j]!=null){
					sum += stu[j].getScore()[i];
					stuCount++;
				}
				avg = (float)sum/stuCount;
			}
			avgscores[i] = avg;
		}
	}
}
