import java.util.*;

class Hand {
	public Integer handSize;
	protected List<Card> myHand;
	
	public void reorderHand() {
		Collections.shuffle(myHand);
	}
	
	public List<Card> discardPairs() {
		List<Card> discard = new ArrayList<Card>();
		Card firstCard, secondCard;
		
		for(int i = 0; i < myHand.size(); ++i) {
			firstCard = myHand.get(i);
			for(int j = i + 1; j < myHand.size(); ++j) {
				secondCard = myHand.get(j);
				if(firstCard.getValue() == secondCard.getValue()) {
					myHand.remove(i);
					myHand.remove(j);
					discard.add(firstCard);
					discard.add(secondCard);
				}
			}
		}
		
		return discard;
	}
	
	public void addCard(Card newCard) {
		myHand.add(newCard);
		this.discardPairs();
	}
	
	public void removeCard(Player targetPlayer, int targetIndex) {
		Card targetCard = targetPlayer.hand.myHand.get(targetIndex);
		targetPlayer.takeCard(targetIndex);
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
