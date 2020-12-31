package martin.baca.defenders.buttons;

import java.awt.Color;
import java.awt.event.MouseEvent;

import sk.upjs.jpaz2.*;
import sk.upjs.jpaz2.theater.*;

public class BinBtn extends Pane {

	private Turtle icon;

	public boolean isActive;
	
	private boolean isClicked;

	public BinBtn(Stage stage) {
		super(50, 50);

		isActive = false;
		isClicked = false;
		
		setBorderWidth(0);
		setTransparentBackground(true);

		icon = new Turtle();
		icon.setShape(new ImageShape("bin", "bin-inactive45x45.png"));
		add(icon);
		icon.center();
	}
	
	public void updateView(boolean isActive) {
		if (isActive) {
			icon.setShape(new ImageShape("bin", "bin-active45x45.png"));
		} else {
			icon.setShape(new ImageShape("bin", "bin-inactive45x45.png"));
		}
	}

	@Override
	protected void onMouseClicked(int x, int y, MouseEvent detail) {
		isClicked = true;
	}
	
	@Override
	protected boolean onCanClick(int x, int y) {
		return icon.containsInShape(x, y);
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public boolean isClicked() {
		return isClicked;
	}

	public void setClicked(boolean isClicked) {
		this.isClicked = isClicked;
	}
}
