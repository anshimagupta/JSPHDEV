package model;

public class Student {
	private int SID;
	private int score[] = new int[5];
	
	public int getSID(){
		return this.SID;
	}
	
	public void setSID(int SID){
		this.SID = SID;
	}

	public int[] getScore() {
		return score;
	}

	public void setScore(int[] score) {
		this.score = score;
	}
	
	public void printRecords(){
		System.out.print(this.SID);
		for (int i=0; i<this.score.length; i++){
			System.out.printf("\t%d", this.score[i]);
		}
		System.out.print("\n");
	}
}	
