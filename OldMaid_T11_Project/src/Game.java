package src;

import java.util.*;

import static src.Main.game;

public class Game
{
	protected int numPlayers;
	protected List<Card> discardPile;
	protected Deck deck;
	protected List<Player> playerList;
	protected boolean gameIsOver = false;

	public Game(int numPlayers) {
		this.numPlayers = numPlayers;
		playerList=new ArrayList<>();
		deck=new Deck();
		discardPile=new ArrayList<>();
		populateGame(this.numPlayers,playerList);
	}

	public Game(){
		playerList=new ArrayList<>();
		deck=new Deck();
		discardPile=new ArrayList<>();
	}

	public void populateGame(int numPlayers, List<Player> playerL){
		for(int i=0;i<numPlayers;i++){
			playerL.add(new Player());
		}
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
