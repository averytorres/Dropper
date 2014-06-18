import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;


public class Fire {
	private int fireX;
	private int fireY;
	
	public int getFireX() {
		return fireX;
	}
	public int getFireY() {
		return fireY;
	}
	
	private boolean fired;

	public Fire(int inX,int inY){
		fireX=inX;
		fireY=inY;
		setFired(true);
	}
	public Fire(){
		setFired(false);
	}
	public void draw(Graphics g){
		g.setColor(Color.CYAN);	
		g.fillRect(fireX, fireY, 3, 11);
		if(0>fireY){
			this.setFired(false);
		}
		fireY=fireY-1;
	}
	public boolean isFired() {
		return fired;
	}
	public void setFired(boolean fired) {
		this.fired = fired;
	}
}
