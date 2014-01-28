package com.bingl.jsphdev.coin;

public class Simulation {
	// toss the coin for 20 times
	private int totalToss = 20;
	
	public void run(){
		int totalHeads = 0;
		int totalTails = 0;
		int count = 0;
		Coin testCoin = new Coin();
		
		System.out.printf("The side that is intially facing up is %s", testCoin.getSideUp());
		System.out.printf("\nToss the coin for %d times:", totalToss);
		System.out.println();
		
		while(count < totalToss){
			count++;
			testCoin.toss();
			if(testCoin.getSideUp()=="heads"){
				System.out.printf("\nToss No.%d - face up: %s", count, testCoin.getSideUp());
				totalHeads++;
			} else if (testCoin.getSideUp()=="tails"){
				System.out.printf("\nToss No.%d - face up: %s", count, testCoin.getSideUp());
				totalTails++;
			}
		}
		
		System.out.println();
		System.out.printf("\nTotal number of heads is %d", totalHeads);
		System.out.printf("\nTotal number of tails is %d", totalTails);
	}
}
