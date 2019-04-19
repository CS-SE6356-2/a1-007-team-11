package src;

import java.util.*;

import static src.Main.game;

public class Game
{
	protected int numPlayers;
	protected List<Card> discardPile;
	protected Deck deck;
	protected List<Player> playerList;
	protected Player currentPlayer,prevPlayer;
	protected boolean gameIsOver = false;

	public Game(int numPlayers) {
		this.numPlayers = numPlayers;
		playerList=new ArrayList<>();
		deck=new Deck();
		discardPile=new ArrayList<>();
		populateGame(this.numPlayers,playerList);
		currentPlayer=playerList.get(0);
		prevPlayer=null;
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

	public void passTurn(){
		int cPlayerIndex= playerList.indexOf(currentPlayer);
		try{
		    System.out.println("In try pre :"+currentPlayer);
			this.currentPlayer=playerList.get(cPlayerIndex+1);
			this.prevPlayer=playerList.get(cPlayerIndex);
            System.out.println("In try post :"+currentPlayer);
            System.out.println("In try post :"+prevPlayer);


        }catch (NullPointerException e){
			this.currentPlayer= playerList.get(0);
			this.prevPlayer=playerList.get(cPlayerIndex);
		}
	}

}
