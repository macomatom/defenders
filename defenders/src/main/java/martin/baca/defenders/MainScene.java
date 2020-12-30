package martin.baca.defenders;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import martin.baca.defenders.buyTowerBtns.*;
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
	private List<Tower> towerList;

	/**
	 * List of tower menu buttons
	 */
//	private List<PurchaseTowerButton> towerButtons;
	private BuyTower1Btn purchaseTower1Button;
	private BuyTower2Btn purchaseTower2Button;
	private BuyTower3Btn purchaseTower3Button;

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
//	private int STEP_SKIP = 3;

	/**
	 * creates new instance when new tower is purchased
	 */
	private Tower currentTower;

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
		towerList = new ArrayList<>();
//		towerButtons = new ArrayList<>();
		towerSpots = new HashMap<>();

		// creating a background
		createBackground();

		// create and place menu box
		createMenuBox();

		// create and place create tower buttons
		purchaseTower1Button = new BuyTower1Btn(stage);
		purchaseTower1Button.setPosition(928, 302);
		add(purchaseTower1Button);

		purchaseTower2Button = new BuyTower2Btn(stage);
		purchaseTower2Button.setPosition(928, 374);
		add(purchaseTower2Button);

		purchaseTower3Button = new BuyTower3Btn(stage);
		purchaseTower3Button.setPosition(928, 446);
		add(purchaseTower3Button);

		// loads enemie's movement directions (coordinates) from file
		try (Scanner sc = new Scanner(new File("enemies_path_coordinates.txt"))) {
			while (sc.hasNextLine()) {
				coordinatesList.add(sc.next());
			}
//			System.out.println(coordinatesList.toString());
		} catch (FileNotFoundException e1) {
			System.err.println("File \"enemies_path_coordinates.txt\" is missing.");
		}

		// initializes tower spots (positions)
		initializeTowerSpots();
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
	protected void onMouseClicked(int x, int y, MouseEvent detail) {

		// Button 1
		// places tower to current cursor position
		if (purchaseTower1Button.isClicked && placeTower(x, y)) {
			purchaseTower1Button.isClicked = false;
			towerList.add(currentTower);
			currentTower = null;
		}

		// creates tower immediately after createTowerButton is clicked
		if (purchaseTower1Button.createTower) {
			createTower(x + 5, y - 40);
			purchaseTower1Button.createTower = false;
			purchaseTower1Button.isClicked = true;
		}

		// Button 2
		// places tower to current cursor position
		if (purchaseTower2Button.isClicked && placeTower(x, y)) {
			purchaseTower2Button.isClicked = false;
			towerList.add(currentTower);
			currentTower = null;
		}

		// creates tower immediately after createTowerButton is clicked
		if (purchaseTower2Button.createTower) {
			createTower(x, y - 37);
			purchaseTower2Button.createTower = false;
			purchaseTower2Button.isClicked = true;
		}

		// Button 3
		// places tower to current cursor position
		if (purchaseTower3Button.isClicked && placeTower(x, y)) {
			purchaseTower3Button.isClicked = false;
			towerList.add(currentTower);
			currentTower = null;
		}

		// creates tower immediately after createTowerButton is clicked
		if (purchaseTower3Button.createTower) {
			createTower(x + 5, y - 40);
			purchaseTower3Button.createTower = false;
			purchaseTower3Button.isClicked = true;
		}
		System.out.println(x + ", " + y);
	}

	/**
	 * changes tower coordinates to current cursor position
	 */
	@Override
	protected void onMouseMoved(int x, int y, MouseEvent detail) {
		if (purchaseTower1Button.isClicked) {
			currentTower.setPosition(x + 5, y - 40);
		}

		if (purchaseTower2Button.isClicked) {
			currentTower.setPosition(x, y - 37);
		}
		
		if (purchaseTower3Button.isClicked) {
			currentTower.setPosition(x, y - 37);
		}
	}

	@Override
	protected void onTick() {
		// update scene

		// every enemy makes one step
		for (int i = 0; i < enemyList.size(); i++) {
			moveEnemy(i);
		}

		// every 25 ticks new enemy is created
		if (tickCounter % 25 == 0) {
			createEnemy();
		}
		tickCounter++;
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

	public void createMenuBox() {
		Pane menuBox = new Pane(74, 218);
		menuBox.setBorderWidth(0);
		menuBox.setTransparentBackground(false);
		setBackgroundColor(Color.lightGray);

		menuBox.setPosition(926, 300);
		add(menuBox);
	}

	public void initializeTowerSpots() {
		towerSpots.put(new TowerSpot(82, 110), false);
		towerSpots.put(new TowerSpot(400, 150), false);
		towerSpots.put(new TowerSpot(605, 134), false);
		towerSpots.put(new TowerSpot(791, 150), false);
		towerSpots.put(new TowerSpot(791, 150), false);
		towerSpots.put(new TowerSpot(767, 322), false);
		towerSpots.put(new TowerSpot(661, 488), false);
		towerSpots.put(new TowerSpot(356, 532), false);
		towerSpots.put(new TowerSpot(172, 379), false);
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
		if (purchaseTower1Button.createTower) {
			currentTower = new Tower1();
		} else if (purchaseTower2Button.createTower) {
			currentTower = new Tower2();
		} else if (purchaseTower3Button.createTower) {
			currentTower = new Tower3(); 
		}

		add(currentTower);
		currentTower.setPosition(x, y);
	}

	public boolean placeTower(double x, double y) {
		// checks if any of towerSpots was clicked
		for (TowerSpot ts : towerSpots.keySet()) {
			if (ts.checkTowerSpot(x, y) && !towerSpots.get(ts)) {
				if (currentTower instanceof Tower1) {
					currentTower.setPosition(ts.getX() + 5, ts.getY() - 40);
				} else if (currentTower instanceof Tower2) {
					currentTower.setPosition(ts.getX(), ts.getY() - 37);
				} else if (currentTower instanceof Tower3) {
					currentTower.setPosition(ts.getX(), ts.getY() - 37);
				}

				towerSpots.put(ts, true);
				return true;
			}
		}
		return false;
	}
}
