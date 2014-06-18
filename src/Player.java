import java.awt.Color;
import java.awt.Graphics;


public class Player {
	
	int playerX;
	int playerY;
	
	protected int getPlayerX() {
		return playerX;
	}

	public void setPlayerX(int playerX) {
		this.playerX = playerX;
	}

	protected int getPlayerY() {
		return playerY;
	}

	public void setPlayerY(int playerY) {
		this.playerY = playerY;
	}

	
	
	public Player(){
		playerX=245;
		playerY=400;
	}

	public void draw(Graphics g){
		g.setColor(Color.RED);
		g.fillRect(playerX-1, playerY+8, 8, 5);
		
	    g.setColor(Color.WHITE); 
	    //body
	    g.fillRect(playerX-1, playerY, 8, 9);
	    g.fillRect(playerX+2, playerY-4, 2, 16);
	    g.fillRect(playerX-7, playerY+4, 20, 5);
	    g.fillRect(playerX-7, playerY+1, 3, 11);
	    g.fillRect(playerX+10, playerY+1, 3, 11);
	    
	    g.setColor(Color.RED); 
	    g.drawLine(playerX+2, playerY-4,playerX+2, playerY-4);
	}
}
