package martin.baca.defenders.towers;

import sk.upjs.jpaz2.ImageShape;

public class Tower1 extends Tower {

	private final int towerNumber = 1;

	private final int dx = 5;
	private final int dy = -40;

	public Tower1() {
		setShape(new ImageShape("towers", "tower1.png"));
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
