package game;
import java.util.*;

//import static src.Main.java.convertToIntValues;
import static game.Main.game;

public class Hand {
	public Integer handSize;
	public List<Card> myHand;
	
	public void reorderHand() {
		Collections.shuffle(myHand);
	}
	
	public void discardPairs() {
		Card firstCard, secondCard;
		boolean pairFound=false;
		for(int i = 0; i < this.myHand.size();i++) {
			firstCard = this.myHand.get(i);
			for(int j = i + 1; j < this.myHand.size()&&!pairFound; j++) {
				secondCard = this.myHand.get(j);
				if(firstCard.getValue() == secondCard.getValue()) {
					this.myHand.remove(i);
					this.myHand.remove(j-1);
					game.discardPile.add(firstCard);
					game.discardPile.add(secondCard);
					System.out.println("Discarding pair of "+firstCard.getValue()+" "+secondCard.getValue());
					pairFound=true;
				}
			}
			if(pairFound){
				i--;
				pairFound=false;
			}
		}

	}
	
	public void addCard(Card newCard) {
		this.myHand.add(newCard);
	}
	
	public void removeCard(Player targetPlayer, int targetIndex) {
		Card targetCard = targetPlayer.hand.myHand.get(targetIndex);
		targetPlayer.hand.myHand.remove(targetIndex);
		this.addCard(targetCard);
	}
	
	public Hand() {
		myHand = new ArrayList<Card>();
		handSize = 0;
	}
	public Hand(List<Card> l) {
		myHand = l;
		handSize = l.size();
	}
}
