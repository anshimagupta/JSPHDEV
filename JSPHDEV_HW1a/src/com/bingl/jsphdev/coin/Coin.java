package com.bingl.jsphdev.coin;

public class Coin {
	private String sideUp;
	
	Coin(){
		toss();
	}
	
	public String getSideUp() {
		return sideUp;
	}

	public void setSideUp(String sideUp) {
		this.sideUp = sideUp;
	}
	
	public void toss(){
		double x = Math.random();
		if(x>0.5){
			this.setSideUp("heads");
		} else{
			this.setSideUp("tails");
		}
	}
}
