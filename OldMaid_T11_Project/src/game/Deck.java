package game;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//Author: Chris Jung cyj170130
public class Deck {
	public List<Card> cardList;
	
	//default constructor
	public Deck(){
		cardList= new ArrayList<>();
		String suit = "";
		//loop each suit type
		for(int i = 1; i <= 4; i++) {
			switch(i) {
				case 1:
					//Diamond Case
					suit = "D";
					break;
				case 2:
					//Club Case
					suit = "C";
					break;
				case 3:
					//Heart Case
					suit = "H";
					break;
				case 4:
					//Spade Case
					suit = "S";
					break;
				}
			//set cards for each suit
			for(int j = 1; j < 14; j++) {
				Card temp = new Card();
				temp.setSuit(suit);
				temp.setValue(j);
				cardList.add(temp);
			}
		}
		//add Joker
		Card joker = new Card();
		joker.setSuit("J");
		joker.setValue(0);
		cardList.add(joker);
	}
	
	//deals first card out, removing it from the deck
	public Card deal() {
		Card temp = cardList.get(0);
		cardList.remove(0);
		return temp;
	}
	
	//shuffles this instance of the deck
	public void shuffle() {
		Collections.shuffle(cardList);
	}
}
