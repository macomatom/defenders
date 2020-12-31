package martin.baca.defenders;

import sk.upjs.jpaz2.theater.*;

public class DemoStage extends Stage {
	
	private boolean buyTowerButtonIsClicked;
	
	public DemoStage() {
		super("Name of the stage", 1000, 581);
		buyTowerButtonIsClicked = false;
	}

	@Override
	protected void initialize() {
		// initialize stage and add scenes
		addScene(IntroScene.NAME, new IntroScene(this));
		addScene(MainScene.NAME, new MainScene(this));
	}
	
	public static void main(String[] args) {
		// create demo stage and start a show
		DemoStage show = new DemoStage();
		show.run(MainScene.NAME);
		
//		for (int i=-30; i< 0; i++) {
//			int j = 200 + (i-799);
//			System.out.println(i + ",160");
//		}
	}	
}