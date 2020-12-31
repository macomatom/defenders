package martin.baca.defenders.buttons;

import java.awt.event.MouseEvent;

import sk.upjs.jpaz2.ImageShape;
import sk.upjs.jpaz2.Pane;
import sk.upjs.jpaz2.Turtle;

public class PauseBtn extends Pane {
	
	private Turtle icon;
	
	private boolean isPaused;
	
	private boolean isClicked;

	public PauseBtn() {
		super(40,40);
		
		isPaused = false;
		isClicked = false;

		setTransparentBackground(true);
		setBorderWidth(0);

		icon = new Turtle();
		icon.setShape(new ImageShape("menu_buttons", "pause.png"));
		add(icon);
		icon.center();
	}

	@Override
	protected boolean onCanClick(int x, int y) {
		return true;
	}

	@Override
	protected void onMouseClicked(int x, int y, MouseEvent detail) {
		isClicked = true;
	}

	public boolean isPaused() {
		return isPaused;
	}

	public void setPaused(boolean isClicked) {
		this.isPaused = isClicked;
	}

	public boolean isClicked() {
		return isClicked;
	}

	public void setClicked(boolean isClicked) {
		this.isClicked = isClicked;
	}
}
