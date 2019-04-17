package src;

import java.util.*;
public class Game 
{
	protected int numPlayers;
	protected List<Card> discardPile;
	protected Deck deck;
	protected List<Player> playerList;
	protected boolean gameIsOver = false;

	public Game(int numPlayers) {
		this.numPlayers = numPlayers;
		playerList=new ArrayList<>(numPlayers);
		deck=new Deck();
		discardPile=new ArrayList<>();
	}

	public Game(){
		playerList=new ArrayList<>();
		deck=new Deck();
		discardPile=new ArrayList<>();
	}

	public void gameOver()
	{
		gameIsOver = true;
	}

	public List<Player> getPlayerList(){
		return playerList;
	}

	public List<Card> getDiscardPile(){
		return discardPile;
	}
}
