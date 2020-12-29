package martin.baca.defenders;

import java.awt.Color;

import sk.upjs.jpaz2.ImageShape;
import sk.upjs.jpaz2.Pane;
import sk.upjs.jpaz2.Turtle;
import sk.upjs.jpaz2.theater.Stage;

/**
 * Create box around buttons on the game scene.
 */
public class RightHandSideMenu extends Pane {
	/**
	 * The stage.
	 */
	private Stage stage;

	
	

	/**
	 * Constructs a create tower button.
	 * 
	 * @param game the game.
	 */
	public RightHandSideMenu(Stage stage) {
		super(74, 218);
		this.stage = stage;

		setBorderWidth(0);
		setTransparentBackground(false);
		setBackgroundColor(Color.lightGray);


		// !TEMPORARY
	}
}
