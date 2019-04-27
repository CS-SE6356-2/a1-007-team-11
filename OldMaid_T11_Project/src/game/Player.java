package game;

//import static Main.game;
import static game.Main.game;

public class Player {
	public Hand hand;
	public String name;

	public void chooseCard(Player targetPlayer) {
		// This function requires the graphics class
		int targetIndex = 0;
		hand.removeCard(targetPlayer, targetIndex);
	}

	public void takeCard(int numCard) {
		game.currentPlayer.hand.removeCard(game.prevPlayer,numCard);
	}
	
	public Player() {
		hand = new Hand();
		name= null;
	}
	
	public Player(Hand newHand, String name) {
		hand = newHand;
		this.name=name;
	}

	public void setName(String n){
		this.name=n;
	}

	public String getName(){
		return this.name;
	}

}
