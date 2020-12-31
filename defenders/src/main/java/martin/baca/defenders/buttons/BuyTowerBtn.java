package martin.baca.defenders.buttons;

import java.awt.Color;
import java.awt.event.MouseEvent;

import sk.upjs.jpaz2.ImageShape;
import sk.upjs.jpaz2.Pane;
import sk.upjs.jpaz2.Turtle;
import sk.upjs.jpaz2.theater.Stage;

public class BuyTowerBtn extends Pane {

	private boolean isClicked = false;
	private boolean createTower = false;

	private Stage stage;
	/**
	 * Constructs a create tower button.
	 * 
	 * @param game the game.
	 */
	public BuyTowerBtn(Stage stage, ImageShape towerThumbnail, int price) {
		super(70,70);
		
		this.stage = stage;
		
		setBorderWidth(1);
		setBorderColor(Color.black);
//		setTransparentBackground(true);
		setBackgroundColor(Color.black);

		// paint button image
		Turtle painter = new Turtle();
		painter.setShape(towerThumbnail);
		add(painter);
		painter.center();
		painter.stamp();
		remove(painter);
		
		Pane pane = new Pane(50, 20);
		add(pane);
		pane.add(painter);
		pane.setBorderWidth(0);
		pane.setBackgroundColor(new Color(0,0,0, 200));
		pane.setPosition(0, 50);
		painter.setShape(new ImageShape("coin", "coin15x15.png"));
		painter.setPosition(10, 10);
		painter.stamp();
		painter.setPenColor(new Color(250,194,40));
		painter.turn(90);
		painter.setPosition(21, 14);
		painter.print(String.valueOf(price));

		pane.remove(painter);
	}

	@Override
	protected boolean onCanClick(int x, int y) {
		return true;
	}

	@Override
	protected void onMouseClicked(int x, int y, MouseEvent detail) {
		createTower = true;
	}

	public boolean isClicked() {
		return isClicked;
	}

	public void setClicked(boolean isClicked) {
		this.isClicked = isClicked;
	}

	public boolean isCreateTower() {
		return createTower;
	}

	public void setCreateTower(boolean createTower) {
		this.createTower = createTower;
	}
	
	
}
