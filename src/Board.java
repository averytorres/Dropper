
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JPanel;



@SuppressWarnings("serial")
public class Board extends JPanel implements KeyListener{

	final JFrame frame = new JFrame("Dropper");
	boolean up=false,down=false,left=false,right=false,fire=false,gameover=false,started=false;
	int playerX,playerY,fireX,fireY;
	int graveyard=0;
	int score=0;
	int lives=5;
	Player p; 
	Fire[] f;
	Enemy[] e;
	int highScore=0;
	
	public Board(){

		setBackground(Color.black);
		setFocusable(true);
		setPreferredSize(new Dimension(400, 400));

		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent){
				System.exit(0);
			}        
		});  
		this.addKeyListener(this);
		frame.add(this);
		frame.setPreferredSize(new Dimension(500, 500));
		frame.setSize(new Dimension(500, 500));
		frame.setVisible(true);

		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			public void run() {
				repaint();
			}
		}, 0, (long) 13);

		p= new Player();
		playerX=p.getPlayerX();
		playerY=p.getPlayerY();

		f= new Fire[3];
		for(int i=0;i<f.length;i++){
			f[i]= new Fire();
		}

		e= new Enemy[10];
		for(int i=0;i<e.length;i++){
			e[i] = new Enemy();
		}
		

	    
	}



	protected void paintComponent(Graphics g) {
		super.paintComponent(g); 

		if(!started){
			welcomeScreen(g);
		}
		else if(!gameover){
		//Draw Player To Screen
		drawPlayer(g);
		g.drawString("Score: "+score, 400, 10);
		g.drawString("Lives: "+lives, 30, 10);
		//Draw Enemies To Screen
		for(int i=0;i<e.length;i++){
			if(e[i].isAlive()){
				e[i].draw(g);

				//check if player collides with enemy
				boolean collide = checkCollision(e[i].getEnemyX()+10,e[i].getEnemyY()+25);

				//player collides with enemy
				if(collide){
					e[i].reset();
					lives--;
					
					//player loses game
					if(lives<1)
						gameover=true;
				}

				//Draw Bullets To Screen && check for hit
				drawShotWithCheck(g,i);

			}
			else{

				resurrect(g,i); 
				
				//Draw Bullets To Screen
				drawShotWithCheck(g,i);
			}
		}
		}
		else{
			//draws the game over screen
			gameover(g);
		}
	}

	private void resurrect(Graphics g, int i) {
		if(graveyard>6){
			Random r = new Random();
			if(r.nextInt(500)>250){
				e[i].setAlive(true);
				e[i].reset();
				graveyard--;
			}
			
			}
		
	}

	private void welcomeScreen(Graphics g) {
		g.setFont(new Font("TimesRoman", Font.BOLD, 50));
		g.setColor(Color.WHITE);
		g.drawString("Dropper", (this.getWidth()/4)+40, this.getHeight()/2);
		g.setFont(new Font("TimesRoman", Font.BOLD, 20));
		g.setColor(Color.DARK_GRAY);
		g.drawString("by Avery Torres", (this.getWidth()/4)+60, (this.getHeight()/2)+40);
		
	}

	private void gameover(Graphics g) {
		
		if(score>highScore){
			updateHS();
		}
		g.setFont(new Font("TimesRoman", Font.BOLD, 50));
		g.setColor(Color.RED);
		g.drawString("GAME OVER", (this.getWidth()/8)+40, this.getHeight()/2);
		g.setColor(Color.WHITE);
		g.setFont(new Font("TimesRoman", Font.BOLD, 30));
		g.drawString("Your Score:"+score, (this.getWidth()/4)+40, (this.getHeight()/2)+50);
		g.drawString("High Score:"+highScore, (this.getWidth()/4)+40, (this.getHeight()/2)+80);	
	}

	private void updateHS() {
			highScore=score;
	}

	private void drawShotWithCheck(Graphics g, int i) {
		for(int j=0;j<f.length;j++){

			if(f[j].isFired()){
				f[j].draw(g);

				boolean shot = checkShot(e[i].getEnemyX()+10,e[i].getEnemyY()+25,j);
				if(shot){
					e[i].reset();
					e[i].setAlive(false);
					graveyard++;
					score++;
				}
			}
		}

	}

//	private void drawShot(Graphics g) {
//		for(int j=0;j<f.length;j++){
//
//			if(f[j].isFired()){
//				f[j].draw(g);
//
//			}
//		}
//
//	}

	private boolean checkShot(int enX, int enY, int pos) {
		if(((f[pos].getFireX()<=enX+10)&&(f[pos].getFireX()>=enX-10)) && ((f[pos].getFireY()<enY+3)&&(f[pos].getFireY()>enY-35))){
			return true;
		}
		return false;
	}

	private boolean checkCollision(int enX, int enY) {
		if(((p.getPlayerX()<=enX+18)&&(p.getPlayerX()>=enX-20)) && ((p.getPlayerY()<enY+3)&&(p.getPlayerY()>enY-35))){

			return true;
		}
		return false;

	}

	private void drawPlayer(Graphics g) {

		if(up){
			p.setPlayerY(p.getPlayerY()-4);

		}
		else if(down){
			p.setPlayerY(p.getPlayerY()+4);
		}
		if(left){
			p.setPlayerX(p.getPlayerX()-7);
		}
		else if(right){
			p.setPlayerX(p.getPlayerX()+7);
		}

		Dimension d = this.getSize();
		if(d.height-15<p.getPlayerY()){
			p.setPlayerY(d.height-15);
		}
		if(10>p.getPlayerY()){
			p.setPlayerY(10);
		}
		if(d.width-15<p.getPlayerX()){
			p.setPlayerX(d.width-15);
		}
		if(10>p.getPlayerX()){
			p.setPlayerX(10);
		}

		playerX=p.getPlayerX();
		playerY=p.getPlayerY();
		p.draw(g);

	}

	public void dispatch() {
		// TODO Auto-generated method stub

	}

	public void keyPressed(KeyEvent e) {

		if(e.getKeyCode()==KeyEvent.VK_UP){
			up=true;	
		}
		if(e.getKeyCode()==KeyEvent.VK_DOWN){
			down=true;
		}
		if(e.getKeyCode()==KeyEvent.VK_LEFT){
			left=true;
		}
		if(e.getKeyCode()==KeyEvent.VK_RIGHT){
			right=true;
		}


	}

	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_UP){	
			up=false;

		}
		if(e.getKeyCode()==KeyEvent.VK_DOWN){
			down=false;
		}
		if(e.getKeyCode()==KeyEvent.VK_LEFT){
			left=false;
		}
		if(e.getKeyCode()==KeyEvent.VK_RIGHT){
			right=false;
		}
		if(e.getKeyCode()==KeyEvent.VK_SPACE){

			if(!f[0].isFired()){
				f[0] = new Fire(playerX+2,playerY);
			}
			else if(!f[1].isFired()){
				f[1] = new Fire(playerX+2,playerY);
			}
			else if(!f[2].isFired()){
				f[2] = new Fire(playerX+2,playerY);
			}
		}
		if(gameover && e.getKeyCode()==KeyEvent.VK_ENTER){
			resetGame();
		}
		if(!started && e.getKeyCode()==KeyEvent.VK_SPACE){
			started=true;
		}
	}

	private void resetGame() {
		lives=5;
		gameover=false;
		for(int i=0;i<this.e.length;i++){
			this.e[i].reset();
			this.e[i].setAlive(true);
		}
		f[0].setFired(false);
		f[1].setFired(false);
		f[2].setFired(false);
		started=false;
		score=0;
	}

	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) throws IOException{
		@SuppressWarnings("unused")
		Board b = new Board();
	}

}
