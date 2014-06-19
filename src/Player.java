import java.awt.Color;
import java.awt.Dimension;
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

	public void update(boolean up, boolean down, boolean left, boolean right, Dimension d) {
		//check for movment 
		if(up){
			this.setPlayerY(this.getPlayerY()-4);

		}
		else if(down){
			this.setPlayerY(this.getPlayerY()+4);
		}
		if(left){
			this.setPlayerX(this.getPlayerX()-7);
		}
		else if(right){
			this.setPlayerX(this.getPlayerX()+7);
		}

		//create bounds the player cannot pass through
		
		if(d.height-15<this.getPlayerY()){
			this.setPlayerY(d.height-15);
		}
		if(10>this.getPlayerY()){
			this.setPlayerY(10);
		}
		if(d.width-15<this.getPlayerX()){
			this.setPlayerX(d.width-15);
		}
		if(10>this.getPlayerX()){
			this.setPlayerX(10);
		}

		//draw location of player
		playerX=this.getPlayerX();
		playerY=this.getPlayerY();
		
	}
}
