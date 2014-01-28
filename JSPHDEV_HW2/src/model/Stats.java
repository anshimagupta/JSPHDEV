package model;

public abstract class Stats {
	int[] lowscores = new int[5];
	int[] highscores = new int[5];
	float[] avgscores = new float[5];
	
	public abstract void findLow(Student[] stu);
	public abstract void findHigh(Student[] stu);
	public abstract void calculateAvg(Student[] stu);
	
	public void printStats(){
		System.out.print("High Score");
		for (int i=0; i<highscores.length; i++){
			System.out.printf("\t%d", highscores[i]);
		}
		System.out.print("\n");
		
		System.out.print("Low Score");
		for (int i=0; i<lowscores.length; i++){
			System.out.printf("\t%d", lowscores[i]);
		}
		System.out.print("\n");
		
		System.out.print("Average\t");
		for (int i=0; i<avgscores.length; i++){
			System.out.printf("\t%.2f", avgscores[i]);
		}
		System.out.print("\n");
	}
}
