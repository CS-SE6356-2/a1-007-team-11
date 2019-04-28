package testing;

import static org.junit.Assert.*;

import org.junit.Test;

public class PlayerTest {

	@org.junit.Test
	public void chooseCardDecrementTest() {
		int oldSize, newSize;
		Player testPlayer1 = new Player();
		Player testPlayer2 = new Player();
		Hand testHand = testPlayer1.hand;
		Card testCard = new Card("J", 0);
		testHand.addCard(testCard);
		
		testPlayer2.chooseCard(testPlayer1);
		assertEquals("chooseCard should decrement hand size of target player and increment hand size of calling player",
					  testPlayer1.hand.handSize + 1, testPlayer2.hand.handSize);
	}
	
	@org.junit.Test
	public void chooseCardTargetHandTest() {
		int oldSize, newSize;
		Player testPlayer1 = new Player();
		Player testPlayer2 = new Player();
		Hand testHand = testPlayer1.hand;
		Card testCard = new Card("J", 0);
		testHand.addCard(testCard);
		
		testPlayer2.chooseCard(testPlayer1);
		assertTrue("chooseCard should remove the first card in target player's hand",
					testPlayer1.hand.myHand.isEmpty());
	}

	@org.junit.Test
	public void chooseCardCallerHandTest() {
		int oldSize, newSize;
		Player testPlayer1 = new Player();
		Player testPlayer2 = new Player();
		Hand testHand = testPlayer1.hand;
		Card testCard = new Card("J", 0);
		testHand.addCard(testCard);
		
		testPlayer2.chooseCard(testPlayer1);
		assertFalse("chooseCard should add one card to the caller's hand",
					testPlayer2.hand.myHand.isEmpty());
	}
}
