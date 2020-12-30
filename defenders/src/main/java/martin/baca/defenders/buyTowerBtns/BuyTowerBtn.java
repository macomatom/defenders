package martin.baca.defenders.buyTowerBtns;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

import sk.upjs.jpaz2.ImageShape;
import sk.upjs.jpaz2.Pane;
import sk.upjs.jpaz2.Turtle;
import sk.upjs.jpaz2.theater.Stage;

/**
 * A create tower button displayed on the game scene.
 */
public class BuyTowerBtn extends Pane {
	/**
	 * The stage.
	 */
	private Stage stage;

	public boolean isClicked = false;
	public boolean createTower = false;

	/**
	 * Constructs a create tower button.
	 * 
	 * @param game the game.
	 */
	public BuyTowerBtn(Stage stage) {
		super(70, 70);
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
