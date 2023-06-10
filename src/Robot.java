public class Robot implements Runnable {
	private int x;
	private int y;
	private char direction;
	private int counter = 1;
	private int distance = 1;
	private Room room;
	private int roomSize;
	private String name;
	public boolean isSpiral = true;
	private boolean isCleaning;

	public Robot(String name, int x, int y, char direction) {
		this.x = x;
		this.y = y;
		this.direction = direction;
		this.name = name;
		this.isCleaning = true;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public char getDirection() {
		return direction;
	}

	public void setDirection(char direction) {
		this.direction = direction;
	}

	public int getCounter() {
		return counter;
	}

	public int getDistance() {
		return distance;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public void moveSpiral() {
		// Move the robot based on the current direction
		switch (direction) {
		case 'U':
			for (int i = 0; i < distance; i++) {
				roboSleep();
				y--;
				if (bound(this, this.room)) {
					break;
				}
				this.room.setRoom(x, y, true);
			}
			setDirection('L');
			break;

		case 'D':
			for (int i = 0; i < distance; i++) {
				roboSleep();
				y++;
				if (bound(this, this.room)) {
					break;
				}
				this.room.setRoom(x, y, true);
			}
			setDirection('R');
			break;

		case 'R':
			for (int i = 0; i < distance; i++) {
				roboSleep();
				x++;
				if (bound(this, this.room)) {
					break;
				}
				this.room.setRoom(x, y, true);
			}
			setDirection('U');
			break;

		case 'L':
			for (int i = 0; i < distance; i++) {
				roboSleep();
				x--;
				if (bound(this, this.room)) {
					break;
				}
				this.room.setRoom(x, y, true);
			}
			setDirection('D');
			break;
		}
		if (counter == 2) {
			distance++;
			counter = 0;
		}
		counter++;
	}

	public void moveSquare() {
		// Move the robot based on the current direction
		switch (direction) {
		case 'U':
			for (int i = 0; i < roomSize; i++) {
				roboSleep();
				y--;
				if (bound(this, this.room)) {
					break;
				}
				this.room.setRoom(x, y, true);
			}
			setDirection('L');
			break;

		case 'D':
			for (int i = 0; i < roomSize; i++) {
				roboSleep();
				y++;
				if (bound(this, this.room)) {
					break;
				}
				this.room.setRoom(x, y, true);
			}
			setDirection('R');
			break;

		case 'R':
			for (int i = 0; i < roomSize; i++) {
				roboSleep();
				x++;
				if (bound(this, this.room)) {
					break;
				}
				this.room.setRoom(x, y, true);
			}
			setDirection('U');
			break;

		case 'L':
			for (int i = 0; i < roomSize; i++) {
				roboSleep();
				x--;
				if (bound(this, this.room)) {
					break;
				}
				this.room.setRoom(x, y, true);
			}
			setDirection('D');
			break;
		}
	}

	public boolean bound(Robot robot, Room room) {
		// Out of bounds
		if (x < 0) {
			robot.setX(0);
			isSpiral = false;
			return true;

		}
		if (y < 0) {
			robot.setY(0);
			isSpiral = false;
			return true;
		}
		if (x >= room.getDimension()) {
			robot.setX(room.getDimension() - 1);
			isSpiral = false;
			return true;
		}
		if (y >= room.getDimension()) {
			robot.setY(room.getDimension() - 1);
			isSpiral = false;
			return true;
		}
		return false;
	}

	public void setRoom(Room room) {
		this.room = room;
		this.roomSize = room.getDimension();
	}

	private void roboSleep() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getName() {
		return name;
	}


	public void setCleaning(boolean clean) {
		this.isCleaning = clean;
	}

	@Override
	public void run() {

		while (this.isCleaning) {
			if (isSpiral) {
				moveSpiral();
			} else {
				moveSquare();
			}
			roboSleep();
		}
	}

}
