package martin.baca.defenders;

import sk.upjs.jpaz2.theater.*;

/**
 * The demo stage.
 */
public class DemoStage extends Stage {
	
	/**
	 * Constructs the stage.
	 */
	public DemoStage() {
		super("Name of the stage", 1000, 581);
	}

	@Override
	protected void initialize() {
		// initialize stage and add scenes
		addScene(IntroScene.NAME, new IntroScene(this));
		addScene(MainScene.NAME, new MainScene(this));
	}
}