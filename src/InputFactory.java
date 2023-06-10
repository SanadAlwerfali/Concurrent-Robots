import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class InputFactory {

	private static int x;
	private static int y;
	private static char direction;
	private static int size;
	private static int numRobots;
	private static Robot[] robots;

	public static void readRobotInfo() {
		try (BufferedReader br = new BufferedReader(new FileReader("robots.txt"))) {
			int numRobots = Integer.parseInt(br.readLine().trim());
			InputFactory.numRobots = numRobots;
			robots = new Robot[numRobots];
			
			
			
			Robot defaultRobot = new Robot("0", size / 2, size / 2, 'U');
			robots[0] = defaultRobot;
			
			for (int i = 1; i < numRobots; i++) {
				String line = br.readLine();
				
				if (line != null) {
					String[] parts = line.split(" ");
					setX(Integer.parseInt(parts[0]));
					setY(InputFactory.y = Integer.parseInt(parts[1]));
					setDirection(InputFactory.direction = parts[2].charAt(0));
				}
				
				Robot robot = new Robot(Integer.toString(i), getX(), size - getY() - 1, getDirection());
				robots[i] = robot;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static int readRoomDimensions() {
		try (BufferedReader br = new BufferedReader(new FileReader("room.txt"))) {
			String line = br.readLine();
			if (line != null) {
				InputFactory.size = Integer.parseInt(line.trim());

			}
			return InputFactory.size;

		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static boolean validateRobotInfo() {
		try (BufferedReader br = new BufferedReader(new FileReader("robots.txt"))) {
			int numRobots = Integer.parseInt(br.readLine().trim());
			if (numRobots < 1) {
				System.err.println("Invalid number of robots!");
				return false;
			}

			int numLines = 0; // Counter for the number of lines read
			String line;
			while ((line = br.readLine()) != null) {
				numLines++;
				if (numLines > numRobots) {
					System.err.println("Excess robot information!");
					return false;
				}
				String[] parts = line.split(" ");
				if (parts.length != 3) {
					System.err.println("Invalid robot information format!");
					return false;
				}
				int x = Integer.parseInt(parts[0]);
				int y = Integer.parseInt(parts[1]);
				char direction = parts[2].charAt(0);

				if (x < 0 || y < 0 || (direction != 'U' && direction != 'D' && direction != 'L' && direction != 'R')) {
					System.err.println("Invalid robot information values!");
					return false;
				}
			}

			if (numLines != numRobots - 1) {
				System.err.println("Insufficient robot information!");
				return false;
			}

			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static int getX() {
		return x;
	}

	public static int getY() {
		return y;
	}

	public static char getDirection() {
		return direction;
	}

	public static int getSize() {
		return size;
	}

	public static Robot[] getRobots() {
		return robots;
	}

	public static int getNumRobots() {
		return numRobots;
	}

	public static void setX(int x) {
		InputFactory.x = x;
	}

	public static void setY(int y) {
		InputFactory.y = y;
	}

	public static void setDirection(char direction) {
		InputFactory.direction = direction;
	}
}
