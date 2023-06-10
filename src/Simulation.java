import java.util.ArrayList;
import java.util.List;

public class Simulation {
	
	Room room;
	Robot[] robots;
	int size;
	String[][] roomGrid;
	List<Thread> robotThreads;
	public boolean running;

	public Simulation() throws Exception {
		// read room dimension
		InputFactory.readRoomDimensions();
		// if information valid
		if (InputFactory.validateRobotInfo()) {
			InputFactory.readRobotInfo();// give robot info
		} else {
			throw new Exception("INPUT ERROR");
		}
		// take the robots created
		robots = InputFactory.getRobots();

		size = InputFactory.getSize();// get room size

		this.room = new Room(size);
		roomGrid = room.getRoomString();// get room strings layout

		robotThreads = new ArrayList<>();

		// This is simulation starts
		startSimulation();
	}

	public void startSimulation() {
		System.out.println("# of Robots: " + robots.length);
		for (Robot robot : robots) {
			this.room.setRoom(robot.getX(), robot.getY(), true);// set the robot position as true

			robot.setRoom(room);// passing the room to the robot

			Thread thread = new Thread(robot);// create thread for the robot to dance

			robotThreads.add(thread);
			thread.start(); // starting the thread
		}

		while (!room.isClean() && collisionDetected(robots)) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("New Map");
			printRoom();
		}

		stopSimulation();
	}

	public void printRoom() {
		String[][] roomCopy = this.room.getRoomString();

		// Prints the robots location in the room
		for (Robot robot : robots) {
			int x = robot.getX();
			int y = robot.getY();
			roomCopy[y][x] = robot.getName();
		}

		for (int i = 0; i < roomCopy.length; i++) {
			for (int j = 0; j < roomCopy[0].length; j++) {
				System.out.print(roomCopy[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("\n");

	}

	public void stopSimulation() {
		for (Robot robot : robots) {
			robot.setCleaning(false); // stopping the thread
		}
	}

	public boolean collisionDetected(Robot[] robot) {
		for (int i = 0; i < robot.length; i++) {
			for (int j = i + 1; j < robot.length; j++) {
				if (robot[i].getX() == robot[j].getX() && robot[i].getY() == robot[j].getY()) {
					System.out.println("COLLISION AT CELL (" + (robot[i].getX()) + ","
							+ (room.getDimension() - robot[j].getY() - 1) + ")");
					return false;
				}
			}
		}
		return true;
	}

}
