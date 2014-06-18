import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;


public class Enemy {
	
	int enemyX;
	int enemyY;
	int speed;
	boolean alive;
	Random r;
	
	public Enemy(){
		r= new Random();

		enemyX=r.nextInt(470);
		enemyY=(-1)*r.nextInt(20);
		speed=r.nextInt(3);
		alive=true;
	}
	
	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public int getEnemyX() {
		return enemyX;
	}
	public void setEnemyX(int enemyX) {
		this.enemyX = enemyX;
	}
	public int getEnemyY() {
		return enemyY;
	}
	public void setEnemyY(int enemyY) {
		this.enemyY = enemyY;
	}
	
	public void draw(Graphics g){
		
		if(isAlive()){
	    g.setColor(Color.WHITE); 
	    //body
	    g.fillRoundRect(enemyX, enemyY, 20, 20, 50, 50);
	    g.fillRoundRect(enemyX+5, enemyY+10, 10, 15, 50, 50);
	    
	    g.setColor(Color.BLACK);
	    g.fillRoundRect(enemyX+1, enemyY+5, 9, 9, 50, 50);
	    g.fillRoundRect(enemyX+10, enemyY+5, 9, 9, 50, 50);
	 
	    g.drawLine(enemyX+8, enemyY+25,enemyX+8, enemyY+20);
	    g.drawLine(enemyX+10, enemyY+25,enemyX+10, enemyY+20);
	    g.drawLine(enemyX+12, enemyY+25,enemyX+12, enemyY+20);
	    
	    g.drawLine(enemyX+9, enemyY+15, enemyX+9, enemyY+17);
	    g.drawLine(enemyX+11, enemyY+15, enemyX+11, enemyY+17);
	    
	    g.setColor(Color.RED);
	    g.drawLine(enemyX+10, enemyY+25,enemyX+10, enemyY+25);
	    
	    setEnemyY(getEnemyY()+(speed+2));
	    
	    if(getEnemyY()>470){
	    	reset();
	    	
	    }
		}
	}

	public void reset() {
		// TODO Auto-generated method stub
		enemyX=r.nextInt(470);
    	setEnemyY(0);
    	speed=r.nextInt(4);
	}

}
