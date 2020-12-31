package martin.baca.defenders.buttons;

import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import sk.upjs.jpaz2.ImageShape;
import sk.upjs.jpaz2.Pane;
import sk.upjs.jpaz2.Turtle;

public class InfoBtn extends Pane {
	
	private Turtle icon;
	
	private boolean isClicked;

	public InfoBtn() {
		super(40,40);

		setTransparentBackground(true);
		setBorderWidth(0);

		icon = new Turtle();
		icon.setShape(new ImageShape("menu_buttons", "info.png"));
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

	public boolean isClicked() {
		return isClicked;
	}

	public void setClicked(boolean isClicked) {
		this.isClicked = isClicked;
	}
	
	
}
