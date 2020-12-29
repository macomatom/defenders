package martin.baca.defenders;

import java.awt.Color;
import java.awt.event.MouseEvent;

import sk.upjs.jpaz2.ImageShape;
import sk.upjs.jpaz2.Pane;
import sk.upjs.jpaz2.Turtle;
import sk.upjs.jpaz2.theater.Stage;

/**
 * A create tower button displayed on the game scene.
 */
public class CreateTowerButton extends Pane {
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
	public CreateTowerButton(Stage stage) {
		super(70, 70);
		this.stage = stage;

		setBorderWidth(1);
		setBorderColor(Color.black);
//		setTransparentBackground(true);
		setBackgroundColor(Color.black);

		// paint button image
		Turtle painter = new Turtle();
		painter.setShape(new ImageShape("tower", "tower-70x70.png"));
		add(painter);
		painter.center();
		painter.stamp();
		remove(painter);
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
