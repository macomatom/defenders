package martin.baca.defenders;

import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

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
	private List<Enemy> enemyList;

	/**
	 * List of coordinates (positions) defining enemy's path across the map
	 */
	private List<String> coordinatesList;

	private List<Tower> towerList;
	
	private List<CreateTowerButton> towerButtons;
	/**
	 * starts at the beginning of the game and increases by 1 on every tick
	 */
	private int tickCounter = 0;

	/**
	 * skips steps
	 */
//	private int STEP_SKIP = 3;

	/**
	 * Create menu menu with menu items
	 */
	private RightHandSideMenu rightHandSideMenu;

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
		towerButtons = new ArrayList<>();

		// creating a background
		createBackground();

		// create and place menu box
		rightHandSideMenu = new RightHandSideMenu(getStage());
		rightHandSideMenu.setPosition(926, 300);
		add(rightHandSideMenu);

		// create and place create tower button
		for (int i=0; i<3;i++) {
			towerButtons.add(new CreateTowerButton(stage));
			towerButtons.get(i).setPosition(928, 302 + i*72);
			add(towerButtons.get(i));
		}

		// loads enemie's movement directions (coordinates) from file
		try (Scanner sc = new Scanner(new File("enemies_path_coordinates.txt"))) {
			while (sc.hasNextLine()) {
				coordinatesList.add(sc.next());
			}
//			System.out.println(coordinatesList.toString());
		} catch (FileNotFoundException e1) {
			System.err.println("File \"enemies_path_coordinates.txt\" is missing.");
		}
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

		// places tower to current cursor position
		if (towerButtons.get(0).isClicked) {
			currentTower.setPosition(x + 5, y - 40);
			towerButtons.get(0).isClicked = false;
			towerList.add(currentTower);
			currentTower = null;
		}

		// creates tower immediately after createTowerButton is clicked
		if (towerButtons.get(0).createTower) {
			createTower(x + 5, y - 40);
			towerButtons.get(0).createTower = false;
			towerButtons.get(0).isClicked = true;
		}
//		System.out.println(x + ", " + y);
	}

	/**
	 * changes tower coordinates to current cursor position
	 */
	@Override
	protected void onMouseMoved(int x, int y, MouseEvent detail) {
		if (towerButtons.get(0).isClicked) {
			currentTower.setPosition(x + 5, y - 40);
		}
	}

	@Override
	protected void onTick() {
		// update scene

		List<Integer> intList;

		// every enemy receives directions of next move and makes a move
		for (int i = 0; i < enemyList.size(); i++) {
			intList = parseCoordinates((tickCounter - i * 25) * 3);
			enemyList.get(i).setPosition(intList.get(0), intList.get(1));
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

	// creates new enemy, adds him to list, sets up his shape and sets a position
	public void createEnemy() {
		Enemy e = new Enemy();
		add(e);
		enemyList.add(e);
		e.setPosition(-40, 160);
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
		currentTower = new Tower();
		add(currentTower);
		currentTower.setPosition(x, y);
	}
}
