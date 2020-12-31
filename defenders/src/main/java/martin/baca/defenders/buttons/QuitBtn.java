package martin.baca.defenders.buttons;

import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import sk.upjs.jpaz2.ImageShape;
import sk.upjs.jpaz2.Pane;
import sk.upjs.jpaz2.Turtle;

public class QuitBtn extends Pane {

	public boolean isClicked;
	
	public QuitBtn() {
		super(50,50);
		
		isClicked = false;
		
		setTransparentBackground(true);
		setBorderWidth(0);
		
		Turtle painter = new Turtle();
		painter.setShape(new ImageShape("menu_buttons", "quit35x36.png"));
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
		JOptionPane.showMessageDialog(null, "Do you want to quit?", "Quit", JOptionPane.YES_NO_OPTION);
	}
}
