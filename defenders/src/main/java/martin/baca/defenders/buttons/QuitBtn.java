package martin.baca.defenders.buttons;

import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import sk.upjs.jpaz2.ImageShape;
import sk.upjs.jpaz2.Pane;
import sk.upjs.jpaz2.Turtle;

public class QuitBtn extends Pane {

	private Turtle icon;

	public QuitBtn() {
		super(35,36);

		setTransparentBackground(true);
		setBorderWidth(0);

		icon = new Turtle();
		icon.setShape(new ImageShape("menu_buttons", "quit.png"));
		add(icon);
		icon.center();
	}

	@Override
	protected boolean onCanClick(int x, int y) {
		return true;
	}

	@Override
	protected void onMouseClicked(int x, int y, MouseEvent detail) {
		int n = JOptionPane.showConfirmDialog(null, "Do you want to quit?", "Quit",
				JOptionPane.YES_NO_OPTION);
		if (n == 0) {
			System.exit(0);
		}	
	}
}
