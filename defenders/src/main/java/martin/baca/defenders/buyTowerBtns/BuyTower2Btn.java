package martin.baca.defenders.buyTowerBtns;

import java.awt.Color;
import java.awt.event.MouseEvent;

import sk.upjs.jpaz2.ImageShape;
import sk.upjs.jpaz2.Pane;
import sk.upjs.jpaz2.Turtle;
import sk.upjs.jpaz2.theater.Stage;

public class BuyTower2Btn extends BuyTowerBtn {
	/**
	 * The stage.
	 */
	private Stage stage;
	
	private int PRICE = 200;

	public boolean isClicked = false;
	public boolean createTower = false;

	/**
	 * Constructs a create tower button.
	 * 
	 * @param game the game.
	 */
	public BuyTower2Btn(Stage stage) {
		super(stage);
		this.stage = stage;

		setBorderWidth(1);
		setBorderColor(Color.black);
//		setTransparentBackground(true);
		setBackgroundColor(Color.black);

		// paint button image
		Turtle painter = new Turtle();
		painter.setShape(new ImageShape("towers", "tower2-70x70.png"));
		add(painter);
		painter.center();
		painter.stamp();
		remove(painter);
		
		Pane pane = new Pane(50, 20);
		add(pane);
		pane.add(painter);
		pane.setBorderWidth(0);
		pane.setBackgroundColor(new Color(0,0,0, 90));
		pane.setPosition(0, 50);
		painter.setShape(new ImageShape("coin", "coin15x15.png"));
		painter.setPosition(10, 10);
		painter.stamp();
		painter.setPenColor(new Color(250,194,40));
		painter.turn(90);
		painter.setPosition(19, 15);
		painter.print(String.valueOf(PRICE));

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

}