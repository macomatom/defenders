package martin.baca.defenders;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import martin.baca.defenders.buttons.BinBtn;
import martin.baca.defenders.buttons.BuyTowerBtn;
import martin.baca.defenders.buttons.MusicSwitchBtn;
import martin.baca.defenders.buttons.QuitBtn;
import martin.baca.defenders.enemies.*;
import martin.baca.defenders.towers.*;
import sk.upjs.jpaz2.*;
import sk.upjs.jpaz2.theater.*;

/**
 * The scene with main content.
 */
public class MainScene extends Scene {

	/**
	 * Identifier (name) of this scene.
	 */
	public static final String NAME = "Main";

	/**
	 * sets up tick period; 1000 / TICK_PERIOD = FPS
	 */
	private final int TICK_PERIOD = 50;

	/**
	 * List of actors on the stage during this scene.
	 */
	private List<EnemyStage1> enemyList;

	/**
	 * List of coordinates (positions) defining enemy's path across the map
	 */
	private List<String> coordinatesList;

	/**
	 * List of towers
	 */
	private Map<Tower, Integer> towerMap;

	/**
	 * List of tower menu buttons
	 */
//	private List<PurchaseTowerButton> towerButtons;
	private List<BuyTowerBtn> buyTowerBtns;

	/**
	 * List of spots where towers could be placed
	 */
	private Map<TowerSpot, Boolean> towerSpots;

	/**
	 * starts at the beginning of the game and increases by 1 on every tick
	 */
	private int tickCounter = 0;

	/**
	 * skips steps
	 */
//	private int ENEMY_SPEED = 3;

	/**
	 * creates new instance when new tower is purchased
	 */
	private Tower currentTower;

	/**
	 * bin to discard tower
	 */
	private BinBtn binButton;

	/**
	 * menu buttons
	 */
	private QuitBtn quitBtn;
	
	private MusicSwitchBtn musicSwitchBtn;

	/**
	 * Constructs the scene.
	 * 
	 * @param stage the stage where the scene will be shown.
	 */
	public MainScene(Stage stage) {
		super(stage);

		// initialization of lists
		enemyList = new ArrayList<>();
		coordinatesList = new ArrayList<>();
		towerMap = new HashMap<>();
		buyTowerBtns = new ArrayList<>();
		towerSpots = new HashMap<>();
		currentTower = new Tower();

		// initializes tower spots (positions)
		initializeTowerSpots();

		// loads enemie's movement directions (coordinates) from file
		initializeCoordList();

		// creating a background
		createBackground();

		// creates menu for buying towers
		createRightHandSideMenu();

		// creates top right menu
		createTopRightMenu();
	}

	public void initializeTowerSpots() {
		towerSpots.put(new TowerSpot(82, 110), false);
		towerSpots.put(new TowerSpot(400, 150), false);
		towerSpots.put(new TowerSpot(605, 134), false);
		towerSpots.put(new TowerSpot(519, 321), false);
		towerSpots.put(new TowerSpot(791, 150), false);
		towerSpots.put(new TowerSpot(767, 322), false);
		towerSpots.put(new TowerSpot(661, 488), false);
		towerSpots.put(new TowerSpot(356, 532), false);
		towerSpots.put(new TowerSpot(172, 379), false);
	}

	public void initializeCoordList() {
		try (Scanner sc = new Scanner(new File("enemies_path_coordinates.txt"))) {
			while (sc.hasNextLine()) {
				coordinatesList.add(sc.next());
			}
//			System.out.println(coordinatesList.toString());
		} catch (FileNotFoundException e1) {
			System.err.println("File \"enemies_path_coordinates.txt\" is missing.");
		}
	}

	// creates background (with turtle image shape)
	public void createBackground() {
		Turtle painter = new Turtle();
		painter.setShape(new ImageShape("backgrounds", "background-1000x581.png"));
		add(painter);
		painter.center();
		painter.stamp();
		remove(painter);
	}

	public void createRightHandSideMenu() {
		// create and place menu box
		createBuyTowerMenuBox();

		// create buyTowerBtns
		createBuyTowerBtns();

		// create bin button in lower right corner
		createBinButtonBox();
		createBinButton(false);
	}

	public void createTopRightMenu() {
		// create and place menu box
		createTopMenuBox();
		
		// create and place quit button
		createQuitButton();
		
		// create and place music switch button
		createMusicSwitchButton(true);
	}

	public void createBuyTowerMenuBox() {
		Pane menuBox = new Pane(74, 218);
		menuBox.setBorderWidth(0);
		menuBox.setTransparentBackground(false);
		setBackgroundColor(Color.lightGray);

		menuBox.setPosition(926, 285);
		add(menuBox);
	}

	public void createTopMenuBox() {
		Pane menuBox = new Pane(250, 50);
		menuBox.setBorderWidth(0);
		menuBox.setTransparentBackground(false);
		menuBox.setBackgroundColor(new Color(100, 100, 100, 200));

		menuBox.setPosition(750, 0);
		add(menuBox);
	}

	public void createBuyTowerBtns() {
		for (int i = 0; i < 3; i++) {
			BuyTowerBtn btn = new BuyTowerBtn(new ImageShape("towers", "tower" + (i + 1) + "-70x70.png"),
					(i + 1) * 100);

			btn.setPosition(928, 287 + 72 * i);
			add(btn);
			buyTowerBtns.add(btn);
		}
	}

	public void createBinButtonBox() {
		Pane binButtonBox = new Pane(74, 80);
		binButtonBox.setBorderWidth(0);
		binButtonBox.setBackgroundColor(new Color(116, 77, 33, 150));
		binButtonBox.setPosition(926, 504);
		add(binButtonBox);
	}

	public void createBinButton(boolean isActive) {
		binButton = new BinBtn(isActive);
		binButton.setPosition(938, 516);
		add(binButton);
	}
	
	public void createQuitButton() {
		quitBtn = new QuitBtn();
		quitBtn.setPosition(950, 0);
		add(quitBtn);
	}
	
	public void createMusicSwitchButton(boolean isActive) {
		if (musicSwitchBtn != null) {
			remove(musicSwitchBtn);
		}
		musicSwitchBtn = new MusicSwitchBtn(isActive);
		musicSwitchBtn.setPosition(900, 0);
		add(musicSwitchBtn);
	}
	

	@Override
	public void start() {
		// start scene updates - periodically update the scene
		setTickPeriod(TICK_PERIOD);
	}

	@Override
	public void stop() {
		// stop scene updates
		setTickPeriod(0);
	}

	@Override
	protected void onTick() {
		// update scene

		// every enemy makes one step
//		for (int i = 0; i < enemyList.size(); i++) {
//			moveEnemy(i);
//		}

		// every 25 ticks new enemy is created
		if (tickCounter % 25 == 0) {
			createEnemy();
		}
		tickCounter++;
	}

	// creates new enemy, adds him to list, sets up his shape and sets a position
	public void createEnemy() {
		EnemyStage1 e = new EnemyStage1();
		add(e);
		enemyList.add(e);
		e.setPosition(-40, 160);
	}

	public void moveEnemy(int i) {
		List<Integer> intList = parseCoordinates((tickCounter - i * 25) * 3);
		enemyList.get(i).setPosition(intList.get(0), intList.get(1));
	}

	// parses coordinates from string "x,y" to int list x, y
	public List<Integer> parseCoordinates(int tick) {
		// coordinates x, y to parse
		String[] pole = new String[2];
		pole = coordinatesList.get(tick).split(",");
//		System.out.println(Arrays.toString(pole));

		// parsing strings x,y to ints
		List<Integer> list = new ArrayList<>();
		list.add(Integer.parseInt(pole[0]));
		list.add(Integer.parseInt(pole[1]));

		return list;
	}

	public void createTower(double x, double y) {
		if (buyTowerBtns.get(0).createTower) {
			currentTower = new Tower1();
		}
		if (buyTowerBtns.get(1).createTower) {
			currentTower = new Tower2();
		}
		if (buyTowerBtns.get(2).createTower) {
			currentTower = new Tower3();
		}
		add(currentTower);
		currentTower.setPosition(x + currentTower.getDx(), y + currentTower.getDy());
	}

	/**
	 * checks if any of towerSpots was clicked if was, then places tower to spot
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean placeTower(double x, double y) {

		for (TowerSpot ts : towerSpots.keySet()) {
			if (ts.checkTowerSpot(x, y) && !towerSpots.get(ts)) {
				currentTower.setPosition(ts.getX() + currentTower.getDx(), ts.getY() + currentTower.getDy());
				towerSpots.put(ts, true);
				return true;
			}
		}
		return false;
	}

	@Override
	protected void onMouseClicked(int x, int y, MouseEvent detail) {
		// places tower to current cursor position
		for (int i = 0; i < buyTowerBtns.size(); i++) {
			if (buyTowerBtns.get(i).isClicked && placeTower(x, y)) {
				buyTowerBtns.get(i).isClicked = false;
				towerMap.put(currentTower, i + 1);
				currentTower = new Tower();
				createBinButton(false);
			}
		}

		// creates tower immediately after createTowerButton is clicked
		for (int i = 0; i < buyTowerBtns.size(); i++) {
			if (buyTowerBtns.get(i).createTower) {
				if (currentTower != null) {
					remove(currentTower);
					currentTower = new Tower();
				}
				createBinButton(true);
				createTower(x, y);
				buyTowerBtns.get(i).createTower = false;
				buyTowerBtns.get(i).isClicked = true;

			}
		}

		if (binButton.isClicked) {
			remove(currentTower);
			currentTower = new Tower();
			binButton.isClicked = false;
			createBinButton(false);
		}

		if (quitBtn.isClicked) {
			System.exit(0);
		}
		
		if (musicSwitchBtn.isClicked) {
			System.out.println("Nieco krajsie");
			System.out.println(musicSwitchBtn.isActive);
			if (musicSwitchBtn.isActive) {
				createMusicSwitchButton(false);
				musicSwitchBtn.isActive = false;
			} else {
				createMusicSwitchButton(true);
				musicSwitchBtn.isActive = true;
			}
			musicSwitchBtn.isClicked = false;
			
		}

		System.out.println(x + ", " + y);
	}

	/**
	 * changes tower coordinates to current cursor position
	 */
	@Override
	protected void onMouseMoved(int x, int y, MouseEvent detail) {

		for (int i = 0; i < buyTowerBtns.size(); i++) {
			if (buyTowerBtns.get(i).isClicked) {
				currentTower.setPosition(x + currentTower.getDx(), y + currentTower.getDy());
			}
		}
	}
}
