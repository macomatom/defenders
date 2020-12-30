package martin.baca.defenders;

public class TowerSpot {

	private double x;
	private double y;

	private int scopeX = 30;
	private int scopeY = 20;

	public TowerSpot(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public boolean checkTowerSpot(double x, double y) {
		return (this.x - scopeX <= x && x <= this.x + scopeX && this.y - scopeY <= y && y <= this.y + scopeY) ? true
				: false;
	}
}
