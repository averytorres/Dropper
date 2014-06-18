
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JPanel;



public class Board extends JPanel implements KeyListener{

	final JFrame frame = new JFrame("Centipede");
	boolean up=false,down=false,left=false,right=false,fire=false;
	int playerX=100;
	int playerY=400;
	
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
        }, 0, (long) 3);
        
	}
	
	protected void paintComponent(Graphics g) {
	    super.paintComponent(g);  
	    drawPlayer(g); 
	  }
	
	private void drawPlayer(Graphics g) {
		 
		if(up){
			playerY=playerY-4;
			
		}
		else if(down){
			playerY=playerY+4;
		}
		if(left){
			playerX=playerX-7;
			
		}
		else if(right){
			playerX=playerX+7;
		}
		Dimension d = this.getSize();
		if(d.height-15<playerY){
		
			playerY=d.height-15;
		}
		if(10>playerY){
		
			playerY=10;
		}
		if(d.width-15<playerX){
		
			playerX=d.width-15;
		}
		if(10>playerX){
			
			playerX=10;
		}
		
		g.setColor(Color.RED);
		g.fillRect(playerX-1, playerY+8, 8, 5);
		
	    g.setColor(Color.WHITE); 
	    //body
	    g.fillRect(playerX-1, playerY, 8, 9);
	    g.fillRect(playerX+2, playerY-4, 2, 16);
	    g.fillRect(playerX-7, playerY+4, 20, 5);
	    g.fillRect(playerX-7, playerY+1, 3, 11);
	    g.fillRect(playerX+10, playerY+1, 3, 11);

	    //HITBOX LOCATION
//	    g.setColor(Color.GREEN);
//	    g.fillRect(playerX-7, playerY, 20, 10);
				
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
		if(e.getKeyCode()==KeyEvent.VK_SPACE){
			fire=true;
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
		
	}

	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args){
		Board b = new Board();
	}

}
