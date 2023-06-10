public class Room {
	private int size;
	private boolean [][] room; 
	public String [][] roomString = new String [size][size];
	
	public Room (int dimension) {
		this.size = dimension;
		this.room = new boolean[dimension][dimension];
	}
	
	public int getDimension() {
		return this.size;
	}
	
	public boolean[][] getRoom() {
		return this.room;
	}
	
	public void setRoom(int x, int y, boolean val) {
		this.room[y][x] = val;
	}
	
	public String[][] getRoomString() {
		String[][] roomString = new String[this.size][this.size];
		
        for (int i = 0; i < this.room.length; i++) {
            for (int j = 0; j < this.room[0].length; j++) {
            	roomString[i][j] = this.room[i][j] ? "T" : "F";
            }
        }
		return this.roomString = roomString;
    }
	
	public boolean isClean() {
		for (boolean[] row : this.room) {
	        for (boolean value : row) {
	            if (!value) {
	                return false;
	            }
	        }
	    }
		System.out.println("ROOM CLEAN");
	    return true;
	}
}
