package testing;

import static org.junit.Assert.*;

import org.junit.Test;

public class HandTest {

	@org.junit.Test
	public void reorderedHandIsDifferentTest() {
		boolean handsAreSame;
		int i = 0;
		Hand exHand = new Hand();
		Hand shuffledHand = new Hand();
		Card exCard = new Card("J", 0);
		exHand.addCard(exCard);
		shuffledHand.addCard(exCard);
		
		exCard = new Card("D", 2);
		exHand.addCard(exCard);
		shuffledHand.addCard(exCard);
		
		exCard = new Card("C", 3);
		exHand.addCard(exCard);
		shuffledHand.addCard(exCard);
		
		exCard = new Card("H", 4);
		exHand.addCard(exCard);
		shuffledHand.addCard(exCard);
		
		exCard = new Card("S", 5);
		exHand.addCard(exCard);
		shuffledHand.addCard(exCard);
		
		shuffledHand.reorderHand();
		
		do {
			handsAreSame = true;
			if( exHand.myHand.get(i).getSuit() != shuffledHand.myHand.get(i).getSuit() &&
				exHand.myHand.get(i).getValue() != shuffledHand.myHand.get(i).getValue() ) {
				handsAreSame = false;
			}
			i++;
		} while(handsAreSame);
		
		assertFalse("Reordering hand should result in the hand being different", handsAreSame);
	}
	
	@org.junit.Test
	public void discardPairsDecrementTest() {
		int oldSize, newSize;
		Game testGame = new Game(1);
		Card testCard = new Card("D", 2);
		Hand testHand = testGame.playerList.get(0).hand;
		testHand.addCard(testCard);
		
		testCard = new Card("C", 2);
		testHand.addCard(testCard);
		
		testCard = new Card("J", 0);
		testHand.addCard(testCard);
		oldSize = testHand.handSize;
		
		testHand.discardPairs();
		newSize = testHand.handSize;
		
		assertTrue("Discarding pairs from the hand should reduce the hand size", newSize < oldSize);
	}

	@org.junit.Test
	public void addCardIncrementTest() {
		int oldSize, newSize;
		Hand testHand = new Hand();
		Card testCard = new Card("J", 0);
		
		oldSize = testHand.handSize;
		testHand.addCard(testCard);
		newSize = testHand.handSize;
		
		assertEquals("Adding a card should increment hand size", newSize, oldSize + 1);
	}
	
	@org.junit.Test
	public void removeCardDecrementTest() {
		int oldSize, newSize;
		Player testPlayer = new Player();
		Hand testHand = testPlayer.hand;
		Card testCard = new Card("J", 0);
		
		testHand.addCard(testCard);
		oldSize = testHand.handSize;
		testHand.removeCard(testPlayer, 0);
		newSize = testHand.handSize;
		
		assertEquals("Removing a card should decrement hand size", newSize + 1, oldSize);
	}
}
