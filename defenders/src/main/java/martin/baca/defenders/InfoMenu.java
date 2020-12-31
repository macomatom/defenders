package martin.baca.defenders;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import sk.upjs.jpaz2.ImageShape;
import sk.upjs.jpaz2.Pane;
import sk.upjs.jpaz2.Turtle;

public class InfoMenu extends Pane {

	private Turtle writerTurtle;
	
	private boolean isDisplayed;

	public InfoMenu() {
		super(1000, 581);
		
		isDisplayed = false;

		setBorderWidth(0);
		setBackgroundColor(new Color(255,255,255,200));

		writerTurtle = new Turtle();
		writerTurtle.turn(90);
		add(writerTurtle);

//		icon.printCenter("Game Instructions");

		Font plainFont = new Font("Serif", Font.PLAIN, 40);
		writerTurtle.setFont(plainFont);
		writerTurtle.setPosition(500, 50);
		writerTurtle.printCenter("Game Instructions");
//		Font italicFont = new Font("Serif", Font.ITALIC, 24);
//		g2.setFont(italicFont);
//		g2.drawString("Welcome to TutorialsPoint", 50, 120);
//		Font boldFont = new Font("Serif", Font.BOLD, 24);
//		g2.setFont(boldFont);
//		g2.drawString("Welcome to TutorialsPoint", 50, 170);
//		Font boldItalicFont = new Font("Serif", Font.BOLD + Font.ITALIC, 24);
//		g2.setFont(boldItalicFont);
//		g2.drawString("Welcome to TutorialsPoint", 50, 220);
		
		remove(writerTurtle);

	}
	
	@Override
	protected void onMouseClicked(int x, int y, MouseEvent detail) {
//		isDisplayed = false;
	}
	
	@Override
	protected boolean onCanClick(int x, int y) {
		return writerTurtle.containsInShape(x, y);
	}

	public boolean isDisplayed() {
		return isDisplayed;
	}

	public void setDisplayed(boolean isClicked) {
		this.isDisplayed = isClicked;
	}
}
