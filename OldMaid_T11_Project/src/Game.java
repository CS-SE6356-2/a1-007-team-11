import java.util.*;
public class Game 
{
	protected int numPlayers;
	private List<Card> discardPile = new ArrayList<>();
	private Deck deck = new Deck();
	private List<Player> playerList = new ArrayList<>();
	protected boolean gameIsOver = false;

	
	public void gameOver()
	{
		gameIsOver = true;
	}
}
