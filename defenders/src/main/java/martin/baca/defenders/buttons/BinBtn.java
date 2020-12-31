package martin.baca.defenders.buttons;

import java.awt.Color;
import java.awt.event.MouseEvent;

import sk.upjs.jpaz2.ImageShape;
import sk.upjs.jpaz2.Pane;
import sk.upjs.jpaz2.Turtle;

public class BinBtn extends Pane {
	
	public boolean isClicked = false;

	public BinBtn(boolean isActive) {
		super(50,50);
		
		setBorderWidth(0);
		setTransparentBackground(true);
		
		Turtle painter = new Turtle();
		add(painter);
		if (isActive) {
			painter.setShape(new ImageShape("bin", "bin-active45x45.png"));
		} else {
			painter.setShape(new ImageShape("bin", "bin-inactive45x45.png"));
		}
		painter.setPosition(25, 25);
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
	}
}
