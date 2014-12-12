
public interface Simple2DInterface
{
    /**
     * Gets the number of rows of this Simple 2D Array.
     * @return the number of rows of this Simple 2D array.
     */
    public int getNumberOfRows();

    /**
     * Gets the number of columns of this Simple 2D Array.
     * @return the number of columns of this Simple 2D array.
     */
    public int getNumberOfColumns();

    /**
     * Reset every element to -1
     */
    public void clear();

    /**
     * Sets the value at location row and column to 1.
     * @param row the row number (start at 1).
     * @param column the column number (start at 1).
     */
    public void setToOne(int row, int column);

    /**
     * Sets the value at location row and column to 0.
     * @param row the row number (start at 1).
     * @param column the column number (start at 1).
     */
    public void setToZero(int row, int column);

    /**
     * Reverse the value at row and column. If the value
     * at row and column is 1, reverse it to 0. If the value
     * at row and column is 0, reverse it to 1. If the value
     * at row and column is -1, do nothing.
     * @param row the row number (start at 1).
     * @param column the column number (start at 1).
     */
    public void flip(int row, int column);

    /**
     * Gets the value at row and column.
     * @param row the row number (start at 1).
     * @param column the column number (start at 1).
     * @return the value at row and column.
     */
    public int get(int row, int column);
}