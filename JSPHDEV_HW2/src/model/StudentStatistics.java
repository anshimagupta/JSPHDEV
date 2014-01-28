package model;

public class StudentStatistics extends Stats {
	@Override
	public void findLow(Student[] stu){
		for (int i = 0; i < lowscores.length; i++){
			int min = stu[0].getScore()[i];
			for (int j = 0; j < stu.length; j++){
				if (min > stu[j].getScore()[i])
					min = stu[j].getScore()[i];
			}
			lowscores[i] = min;
		}
	}
	
	@Override
	public void findHigh(Student[] stu){
		for (int i = 0; i < highscores.length; i++){
			int max = stu[0].getScore()[i];
			for (int j = 0; j < stu.length; j++){
				if (max < stu[j].getScore()[i])
					max = stu[j].getScore()[i];
			}
			highscores[i] = max;
		}
	}
	
	@Override
	public void calculateAvg(Student[] stu){
		for (int i = 0; i < avgscores.length; i++){
			int sum = 0;
			float avg = 0;
			
			for (int j = 0; j < stu.length; j++){
				sum += stu[j].getScore()[i];
				avg = (float)sum/stu.length;
			}
			avgscores[i] = avg;
		}
	}
}
