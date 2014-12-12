import java.util.Stack;

public class TowerOfHanoi {
	private Stack<Integer>[] towers;
    private int numDiscs;

	/* Construct the Towers of Hanoi (3 towers) with aNumDisc
	 * on the first tower. Each tower can be identified by an
	 * integer number (0 for the first tower, 1 for the second
	 * tower, and 2 for the third tower). Each disc can be identified
	 * by an integer number starting from 0 (for the smallest disc)
	 * and (aNumDisc - 1) for the largest disc.
	 */
	public TowerOfHanoi(int aNumDiscs) {
        numDiscs = aNumDiscs;

        // @SuppressWarnings("unchecked") ???
        towers = new Stack[3];
        for (int i = 0; i < 3; i++)
            towers[i] = new Stack<Integer>();

        for (int i = numDiscs - 1; i >= 0; i--)
            towers[0].push(i);

    }
	
	/* Returns an array of integer representing the order of
	 * discs on the tower (from bottom up). The bottom disc should
	 * be the first element in the array and the top disc should be
	 * the last element of the array. The size of the array MUST
	 * be the number of discs on the tower. For example, suppose
	 * the tower 0 contains the following discs 0,1,4,6,7,8 (from top
	 * to bottom). This method should return the array [8,7,6,4,1,0]
	 * (from first to last). 
	 * @param tower the integer identify the tower number.
	 * @return an array of integer representing the order of discs.
	 */
	public int[] getArrayOfDiscs(int tower) {
        int[] result = new int[getNumberOfDiscs(tower)];
        Object[] input = towers[tower].toArray();
        for (int i = 0; i < input.length; i++ )
            result[i] = (Integer)input[i];

        return result;
	}
	
	/* Gets the total number of discs in this Towers of Hanoi
	 * @return the total number of discs in this Towers of Hanoi
	 */
	public int getNumberOfDiscs() { return numDiscs; }
	
	/* Gets the number of discs on a tower.
	 * @param tower the tower identifier (0, 1, or 2)
	 * @return the number of discs on the tower.
	 */
	public int getNumberOfDiscs(int tower) { return towers[tower].size(); }
	
	/* Moves the top disc from fromTower to toTower. Note that
	 * this operation has to follow the rule of the Tower of Hanoi
	 * puzzle. First fromTower must have at least one disc and second
	 * the top disc of toTower must not be smaller than the top disc
	 * of the fromTower.
	 * @param fromTower the source tower
	 * @param toTower the destination tower
	 * @return ture if successfully move the top disc from
	 *         fromTower to toTower.
	 */
	public boolean moveTopDisc(int fromTower, int toTower) {
		if (towers[fromTower].isEmpty())
            return false;

        if (!towers[toTower].isEmpty() && towers[fromTower].peek() > towers[toTower].peek())
            return false;

        towers[toTower].push(towers[fromTower].pop());
        return true;
	}
}
