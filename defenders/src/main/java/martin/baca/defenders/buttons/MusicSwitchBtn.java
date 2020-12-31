package martin.baca.defenders.buttons;

import java.awt.event.MouseEvent;

import sk.upjs.jpaz2.ImageShape;
import sk.upjs.jpaz2.Pane;
import sk.upjs.jpaz2.Turtle;

public class MusicSwitchBtn extends Pane {

	public boolean isActive;
	public boolean isClicked;
	
	public MusicSwitchBtn(boolean isActive) {
		super(50, 50);

		isActive = true;
		isClicked = false;

		setTransparentBackground(true);
		setBorderWidth(0);

		Turtle painter = new Turtle();
		if (isActive) {
			painter.setShape(new ImageShape("menu_buttons", "music-switch-on35x30.png"));
		} else {
			painter.setShape(new ImageShape("menu_buttons", "music-switch-off.png"));
		}
		
		add(painter);
		painter.stamp();
		remove(painter);
	}
	
	@Override
	protected boolean onCanClick(int x, int y) {
		return true;
	}
	
	@Override
	protected void onMouseClicked(int x, int y, MouseEvent detail) {
		isClicked = true;
		System.out.println("Nieco pekne");
	}
}
