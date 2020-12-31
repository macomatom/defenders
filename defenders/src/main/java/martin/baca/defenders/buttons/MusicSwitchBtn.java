package martin.baca.defenders.buttons;

import java.awt.event.MouseEvent;

import sk.upjs.jpaz2.*;
import sk.upjs.jpaz2.theater.*;

public class MusicSwitchBtn extends Pane {

	private boolean isMusicOn;
//	private boolean isClicked;

	private Stage stage;

	private Turtle icon;

//	public MusicSwitchBtn(boolean isActive) {
//		super(50, 50);
//
//		isActive = true;
//		isClicked = false;
//
//		setTransparentBackground(true);
//		setBorderWidth(0);
//
//		Turtle painter = new Turtle();
//		if (isActive) {
//			painter.setShape(new ImageShape("menu_buttons", "music-switch-on35x30.png"));
//		} else {
//			painter.setShape(new ImageShape("menu_buttons", "music-switch-off.png"));
//		}
//		
//		add(painter);
//		painter.stamp();
//		remove(painter);
//	}

	public MusicSwitchBtn(Stage stage) {
		super(35,30);

		isMusicOn = true;

		this.stage = stage;

		setTransparentBackground(true);
		setBorderWidth(0);

		icon = new Turtle();
		icon.setShape(new ImageShape("menu_buttons", "music-switch-on35x30.png"));
		add(icon);
		icon.center();
	}

	public void updateView() {
		if (isMusicOn) {
			icon.setShape(new ImageShape("menu_buttons", "music-switch-on35x30.png"));
		} else {
			icon.setShape(new ImageShape("menu_buttons", "music-switch-off.png"));
		}
	}

	/**
	 * Returns whether the music is on.
	 * 
	 * @return true, if the music is on, false otherwise.
	 */
	public boolean isMusicOn() {
		return stage.getBackgroundMusic().isPlaying();
	}
//	
//	/**
//	 * Activates/deactivates the background music.
//	 * 
//	 * @param musicOn
//	 *            a boolean, set true to play the background music or false to
//	 *            stop the background music.
//	 */
//	public void setMusicOn(boolean musicOn) {
//		stage.setMutedMusic(!musicOn);
//		updateView();
//	}
//
//	public void setMusicOnOff(boolean isActive) {
//		this.isMusicOn = isActive;
//	}

	@Override
	protected void onMouseReleased(int x, int y, MouseEvent detail) {
		// switch music after the left mouse button is released over this pane
//		if (detail.getButton() == MouseEvent.BUTTON1) {
//			setMusicOn(!isMusicOn());
//		}
		isMusicOn = (isMusicOn) ? false : true;
		updateView();
		System.out.println(isMusicOn);
	}

	@Override
	protected boolean onCanClick(int x, int y) {
		return true;
	}

//	public boolean isClicked() {
//		return isClicked;
//	}
//
//	public void setClicked(boolean isClicked) {
//		this.isClicked = isClicked;
//	}

}
