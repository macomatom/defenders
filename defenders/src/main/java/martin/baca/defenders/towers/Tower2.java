package martin.baca.defenders.towers;

import sk.upjs.jpaz2.ImageShape;

public class Tower2 extends Tower{
	
	private final int towerNumber = 2;
	
	private final int dx = 0;
	private final int dy = -36;
	
	public Tower2() {
		setShape(new ImageShape("towers", "tower2.png"));
	}
	
	public int getTowerNumber() {
		return towerNumber;
	}
	
	public int getDx() {
		return dx;
	}

	public int getDy() {
		return dy;
	}
}
