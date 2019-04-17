package src;

class Player {
	protected Hand hand;
	
	void chooseCard(Player targetPlayer) {
		// This function requires the graphics class
		int targetIndex = 0;
		hand.removeCard(targetPlayer, targetIndex);
	}
	
	void takeCard(int numCard) {
		this.hand.myHand.remove(numCard);
	}
	
	public Player() {
		hand = new Hand();
	}
	
	public Player(Hand newHand) {
		hand = newHand;
	}
}
