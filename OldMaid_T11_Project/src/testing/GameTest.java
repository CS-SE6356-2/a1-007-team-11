package testing;

import static org.junit.Assert.*;

import org.junit.Test;

public class GameTest {

	@org.junit.Test
	public void populateGameIncrementTest() {
		Game testGame = new Game();
		
		testGame.populateGame(1, testGame.playerList);
		assertFalse("Populating game should increase the number of players", testGame.playerList.isEmpty());
	}

	@org.junit.Test
	public void gameOverCheckTrueTest() {
		Game testGame = new Game();
		Deck testDeck = new Deck();
		
		//Set discard pile equal to a default 53-card deck
		testGame.discardPile = testDeck.cardList;
		//Remove one card from discard pile so it contains 52 cards
		testGame.discardPile.remove(0);
		assertTrue("If the discard pile contains 52 cards, the game should be over", testGame.gameOverCheck());
	}
	
	@org.junit.Test
	public void gameOverCheckFalseTest() {
		Game testGame = new Game();
		
		assertFalse("If the discard pile contains less than 52 cards, the game should not be over", testGame.gameOverCheck());
	}
	
	@org.junit.Test
	public void passTurnIncrementTest() {
		Game testGame = new Game(2);
		Player player1 = testGame.currentPlayer;
		testGame.passTurn();
		Player player2 = testGame.currentPlayer;
		
		assertNotSame("Passing a player's turn should change the current player to a different Player Object", player1, player2);
	}
}
