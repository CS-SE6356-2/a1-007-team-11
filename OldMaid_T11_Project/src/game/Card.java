package game;

//Author: Chris Jung cyj170130

public class Card {
	private String suit;
	private int value;
	
	//default constructor
	Card(){
		suit = null;
		value = -1;
	}
	
	Card(String s, int v){
		suit = s;
		value = v;
	}
	
	//get methods
	public String getSuit() {
		return suit;
	}
	
	public int getValue() {
		return value;
	}
	
	//set methods
	public void setSuit(String s) {
		suit = s;
	}
	
	public void setValue(int v) {
		value = v;
	}
}
