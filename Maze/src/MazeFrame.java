import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JFrame;

public class MazeFrame
{
	public static void main(String[] args) throws InterruptedException
	{
        int width = 15;
		int height = 10;
		JFrame frame = new JFrame();
		Maze maze = new Maze(width, height);
		ArrayList<Pair<Integer,Integer>> solution = new ArrayList<Pair<Integer,Integer>>();
		MazeComponent mc = new MazeComponent(maze, solution);
		frame.setSize(800,800);
		frame.setTitle("Maze");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(mc);
		frame.setVisible(true);
		
		solution.add(new Pair<Integer,Integer>(0,0));
		Thread.sleep(1000);
		boolean result = solveMaze(solution, mc, maze,
                new Pair<Integer, Integer>(maze.getHeight() - 1, maze.getWidth() - 1), 100);
		mc.repaint();
        System.out.println(result ? "Success" : "Failure");
	}

	/** Solve Maze: recursively solve the maze
	 * 
	 * @param solution   : The array list solution is needed so that every recursive call,
	 *                     a new (or more) next position can be added or removed.
	 * @param mc         : This is the MazeComponent. We need that only for the purpose of
	 *                     animation. We need to call mc.repaint() every time a new position
	 *                     is added or removed. For example,
	 *                       :
	 *                     solution.add(...);
	 *                     mc.repaint();
	 *                     Thread.sleep(sleepTime);
	 *                       :
	 *                     solution.remove(...);
	 *                     mc.repaint();
	 *                     Thread.sleep(sleepTime);
	 *                       :
	 * @param maze       : The maze data structure to be solved.
     * @param goal       : The destination to be solved to.
	 * @return a boolean value to previous call to tell the previous call whether a solution is
	 *         found.
	 * @throws InterruptedException: We need this because of our Thread.sleep(50);
	 */
    public static boolean solveMaze(ArrayList<Pair<Integer, Integer>> solution, MazeComponent mc, Maze maze,
                                    Pair<Integer, Integer> goal, long sleepTime) throws InterruptedException {

        Pair<Integer, Integer> current = solution.get(solution.size() - 1);

        int lastDirection = solution.size() > 1 ?
                getDirection(solution.get(solution.size() - 1), solution.get(solution.size() - 2)) : -1;


        Pair<Integer, Integer> next;
        if (lastDirection != -1) {
            for (int i = (1 + lastDirection) % 4; i != lastDirection; i = (i + 1) % 4) {
                if (!getSides(maze, current)[i]) {
                    next = getPoint(current, i);
                    solution.add(next);
                    mc.repaint();
                    Thread.sleep(sleepTime);
                    if (pointEquals(next, goal) || solveMaze(solution, mc, maze, goal, sleepTime))
                        return true;
                }
            }
        } else {
            for (int i = 0; i < 4; i++) {
                if (!getSides(maze, current)[i]) {
                    next = getPoint(current, i);
                    solution.add(next);
                    mc.repaint();
                    Thread.sleep(sleepTime);
                    if (pointEquals(next, goal) || solveMaze(solution, mc, maze, goal, sleepTime))
                        return true;
                }
            }
        }

        solution.remove(solution.size() - 1);
        mc.repaint();
        Thread.sleep(sleepTime);

        return false;
    }


    private static boolean[] getSides(Maze maze, Pair<Integer, Integer> position) {
        return new boolean[] {
                maze.isNorthWall(position.fst(), position.snd()),
                maze.isEastWall(position.fst(), position.snd()),
                maze.isSouthWall(position.fst(), position.snd()),
                maze.isWestWall(position.fst(), position.snd())
        };
    }

    private static int getDirection(Pair<Integer, Integer> first, Pair<Integer, Integer> second) {
        int rise = second.fst() - first.fst();
        int run = second.snd() - first.snd();

        if (rise < 0)
            return 0;
        if (run > 0)
            return 1;
        if (rise > 0)
            return 2;
        if (run < 0)
            return 3;
        return -1;
    }

    private static Pair<Integer, Integer> getPoint(Pair<Integer, Integer> start, int direction) {
        switch(direction) {
            case (0): return new Pair<Integer, Integer>(start.fst() - 1, start.snd());
            case (1): return new Pair<Integer, Integer>(start.fst(), start.snd() + 1);
            case (2): return new Pair<Integer, Integer>(start.fst() + 1, start.snd());
            case (3): return new Pair<Integer, Integer>(start.fst(), start.snd() - 1);
            default : return new Pair<Integer, Integer>(-1, -1);
        }
    }

    private static boolean pointEquals(Pair<Integer, Integer> first, Pair<Integer, Integer> second) {
        return first.fst() == second.fst() && first.snd() == second.snd();
    }
}