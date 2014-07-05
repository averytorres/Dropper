import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;


public class Welcome {

	int width,height;
	long wait;
	private Image cherry;
	private Image title;
	private Image ghosts;
	Board board;

public Welcome(int width, int height, long wait, Board board) {
		
		this.width=width;
		this.height=height;
		this.wait=wait;
		this.board=board;
		
		
	}
public void welcomeScreen(Graphics g) {

	//load images
	load();
	
	//draw display screen
	drawBorder(g);
	g.drawImage(ghosts, 0, 0, board);
	g.drawImage(title, width/10, height/3, board);
	g.setFont(new Font("TimesRoman", Font.BOLD, 15));
	g.setColor(Color.WHITE);
	g.drawString("Highscore:", (width/3)+10, height/12);
	if(System.nanoTime()-wait>4000000000l){
		g.drawString("Press SPACE to start", (width/4)+40, (height/2)+60);
	}
	
}
private void load() {
	
	ImageIcon iic = new ImageIcon(this.getClass().getResource("/img/cherry.png"));
	cherry = iic.getImage();
	ImageIcon iit = new ImageIcon(this.getClass().getResource("/img/title.png"));
	title = iit.getImage();
	ImageIcon iigt = new ImageIcon(this.getClass().getResource("/img/ghosts_title.png"));
	ghosts = iigt.getImage();
	
}

private void drawBorder(final Graphics g){
	
	Random r = new Random();
	
	//top bar
	int num=0;
	if(r.nextInt(20)<2){
		num=15;
	}
	for(int i=num;i<width;i=i+30){
		g.drawImage(cherry, i, 0, board);
	}	
	//bottom bar
	
	for(int i=num;i<width;i=i+30){
		g.drawImage(cherry, i, height-15, board);
	}
	//left bar
	for(int i=num+25;i<height-27;i=i+30){
		g.drawImage(cherry, 0, i, board);
	}
	//right bar
	for(int i=num+25;i<height-27;i=i+30){
		g.drawImage(cherry, width-15, i, board);
	}
	
}

}
