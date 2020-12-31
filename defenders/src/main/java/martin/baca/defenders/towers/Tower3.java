package martin.baca.defenders.towers;

import sk.upjs.jpaz2.ImageShape;

public class Tower3 extends Tower {
	
	private final int towerNumber = 3;
	
	private final int dx = -1;
	private final int dy = -38;
	
	public Tower3() {
		setShape(new ImageShape("towers", "tower3.png"));
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