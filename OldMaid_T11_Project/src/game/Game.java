package game;
import java.util.*;

//import static src.Main.game;


public class Game
{
    public int numPlayers;
    public List<Card> discardPile;
    public Deck deck;
    public List<Player> playerList;
    public Player currentPlayer,prevPlayer;
    public boolean gameIsOver = false;

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
	public boolean gameOverCheck()
	{
	    if(discardPile.size()==52){
            return true;
        }
		return false;
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
			this.currentPlayer=playerList.get(cPlayerIndex+1);
			this.prevPlayer=playerList.get(cPlayerIndex);
        }catch (NullPointerException|IndexOutOfBoundsException e){
			this.currentPlayer= playerList.get(0);
			this.prevPlayer=playerList.get(cPlayerIndex);
		}
	}

	public String[] toPlayerNamesArr(){
		String[] namesArr= new String[playerList.size()];
		for (int i=0;i<playerList.size();i++) {
			namesArr[i]= playerList.get(i).getName();
			if(playerList.get(i).getName()==""){
				i=playerList.size();
				break;
			}
		}
		return namesArr;
	}

}
