
public class Main {

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		Game game = new Game();
		Graphics graphics = new Graphics();
		replay(game);
	}
	
	public void play()
	{
		
	}
	
	public void create()
	{
		
	}
	
	public void join()
	{
		
	}
	
	public void setup()
	{
		
	}
	
	static protected void replay(Game game)
	{
		if (game.gameIsOver)
			game = new Game();
	}

}
