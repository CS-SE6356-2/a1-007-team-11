import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//Author: Chris Jung cyj170130
public class Deck {
	List<Card> cardList = new ArrayList<Card>();
	
	//default constructor
	Deck(){
		Card temp = new Card();
		String suit = "";
		//loop each suit type
		for(int i = 1; i <= 4; i++) {
			switch(i) {
				case 1:
					suit = "Diamonds";
				case 2:
					suit = "Clubs";
				case 3:
					suit = "Hearts";
				case 4:
					suit = "Spades";
				}
			//set cards for each suit
			for(int j = 1; j < 14; j++) {
				temp.setSuit(suit);
				temp.setValue(j);
				cardList.add(temp);
			}
		}
		//add Joker
		temp.setSuit("Joker");
		temp.setValue(0);
		cardList.add(temp);
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
