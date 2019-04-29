package testing;

import static org.junit.Assert.*;

import game.Card;
import game.Deck;
import org.junit.Test;

public class DeckTest {

	@org.junit.Test
	public void dealDecrementTest() {
		int oldSize, newSize;
		Deck testDeck = new Deck();
		oldSize = testDeck.cardList.size();
		Card dummyCard = testDeck.deal();
		newSize = testDeck.cardList.size();
		
		assertEquals("Dealing one card from the deck should decrement deck size", 
					  oldSize, newSize + 1);
	}
	
	@org.junit.Test
	public void dealReturnsCorrectCardTest() {
		Deck testDeck = new Deck();
		Card dummyCard = testDeck.deal();
		
		assertTrue("Dealing the first card from an unshuffled deck should return the Ace of Diamonds", 
					(dummyCard.getSuit() == "D" && dummyCard.getValue() == 1) );
	}

	public void shuffledDeckIsSameSizeTest() {
		Deck unshuffled, shuffled;
		unshuffled = new Deck();
		shuffled = new Deck();
		shuffled.shuffle();
		
		assertEquals("Shuffling the deck should not change its size", unshuffled.cardList.size(), shuffled.cardList.size());
	}
	
	@org.junit.Test
	public void shuffledDeckIsDifferentOrderTest() {
		boolean decksAreSame;
		int i = 0;
		Deck unshuffled, shuffled;
		unshuffled = new Deck();
		shuffled = new Deck();
		shuffled.shuffle();
		
		do {
			decksAreSame = true;
			int unshuffledValue, shuffledValue;
			unshuffledValue = unshuffled.cardList.get(i).getValue();
			shuffledValue = shuffled.cardList.get(i).getValue();
			String unshuffledSuit = unshuffled.cardList.get(i).getSuit();
			String shuffledSuit = shuffled.cardList.get(i).getSuit();
			if( unshuffledValue != shuffledValue && !unshuffledSuit.equals(shuffledSuit) ) {
				decksAreSame = false;
			}
			i++;
		} while(decksAreSame);
		
		assertFalse("Shuffling the deck should change the deck", decksAreSame);
	}
}
