import java.util.Random;

public class Maze {

	private int width;
    private int height;

    private boolean[][] horizWalls;
    private boolean[][] vertWalls;

    private Random rand;

	/**
	 * Constructor
	 * @param aWidth the number of chambers in each row
	 * @param aHeight the number of chamber in each column
	 */
	public Maze(int aWidth, int aHeight) {
		width = aWidth;
        height = aHeight;

        horizWalls = new boolean[height - 1][width];
        vertWalls = new boolean[width - 1][height];

        rand = new Random();

        generateMaze(0, width, height, 0);
    }

    private void generateMaze(int north, int east, int south, int west) {
        if (south - north <= 1 || east - west <= 1) {
            return;
        }

        int randRow = rand.nextInt(south - north - 1) + north;
        int randCol = rand.nextInt(east - west - 1) + west;

        for (int i = west; i < east; i++)
            horizWalls[randRow][i] = true;
        for (int i = north; i < south; i++)
            vertWalls[randCol][i] = true;

        int randWall = (int)(Math.random() * 4);
        int randHole;
        if (randWall != 0) {
            randHole = rand.nextInt(randRow - north + 1) + north;
            vertWalls[randCol][randHole] = false;
        }
        if (randWall != 1) {
            randHole = rand.nextInt(east - randCol - 1) + randCol + 1;
            horizWalls[randRow][randHole] = false;
        }
        if (randWall != 2) {
            randHole = rand.nextInt(south - randRow - 1) + randRow + 1;
            vertWalls[randCol][randHole] = false;
        }
        if (randWall != 3) {
            randHole = rand.nextInt(randCol - west + 1) + west;
            horizWalls[randRow][randHole] = false;
        }

        generateMaze(north,         randCol + 1,    randRow + 1,    west);
        generateMaze(north,         east,           randRow + 1,    randCol + 1);
        generateMaze(randRow + 1,   randCol + 1,    south,          west);
        generateMaze(randRow + 1,   east,           south,          randCol + 1);
    }

	/**
	 * getWidth
	 * @return the width of this maze
	 */
	public int getWidth() {
		return width;
	}
	
	/**
	 * getHeight
	 * @return the height of this maze
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * isNorthWall
	 * @param row the row identifier of a chamber
	 * @param column the column identifier of a chamber
	 * @return true if the chamber at row row and column column
	 * contain a north wall. Otherwise, return false
	 */
	public boolean isNorthWall(int row, int column) {
        return row == 0 || horizWalls[row - 1][column];
	}
	
	/**
	 * isEastWall
	 * @param row the row identifier of a chamber
	 * @param column the column identifier of a chamber
	 * @return true if the chamber at row row and column column
	 * contain an east wall. Otherwise, return false
	 */
	public boolean isEastWall(int row, int column) {
        return column == width - 1 || vertWalls[column][row];
    }
	
	/**
	 * isSouthWall
	 * @param row the row identifier of a chamber
	 * @param column the column identifier of a chamber
	 * @return true if the chamber at row row and column column
	 * contain a south wall. Otherwise, return false
	 */
	public boolean isSouthWall(int row, int column) {
        return row == height - 1 || horizWalls[row][column];
    }
	
	/**
	 * isWestWall
	 * @param row the row identifier of a chamber
	 * @param column the column identifier of a chamber
	 * @return true if the chamber at row row and column column
	 * contain a west wall. Otherwise, return false
	 */
	public boolean isWestWall(int row, int column) {
        return column == 0 || vertWalls[column - 1][row];
    }
}