import com.sun.javaws.exceptions.InvalidArgumentException;

/* Authored by Corey Noel */

public class Simple2DArray implements Simple2DInterface
{

    private int[][] array2D;
    private int rows, cols;

    /**
     * Constructor: Once a two dimensional array is constructed
     * set all elements in the array to -1.
     * @param aRow the number of rows of this Simple2DArray.
     * @param aColumn the number of columns of this Simple2DArray.
     */
    public Simple2DArray(int aRow, int aColumn)
    {
        array2D = new int[aRow][aColumn];
        rows = aRow;
        cols = aColumn;
        clear();
    }

    /**
     * Gets the number of rows of this Simple 2D Array.
     * @return the number of rows of this Simple 2D array.
     */
    public int getNumberOfRows()
    {
        return rows;
    }

    /**
     * Gets the number of columns of this Simple 2D Array.
     * @return the number of columns of this Simple 2D array.
     */
    public int getNumberOfColumns()
    {
        return cols;
    }

    /**
     * Reset every element to -1
     */
    public void clear()
    {
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                array2D[i][j] = -1;
    }

    /**
     * Sets the value at location row and column to 1.
     * @param row the row number (start at 1).
     * @param column the column number (start at 1).
     */
    public void setToOne(int row, int column)
    {
        if(row > rows || row < 1 || column > cols || column < 1)
            throw new IllegalArgumentException();
        array2D[row - 1][column - 1] = 1;
    }

    /**
     * Sets the value at location row and column to 0.
     * @param row the row number (start at 1).
     * @param column the column number (start at 1).
     */
    public void setToZero(int row, int column)
    {
        if(row > rows || row < 1 || column > cols || column < 1)
            throw new IllegalArgumentException();
        array2D[row - 1][column - 1] = 0;
    }

    /**
     * Reverse the value at row and column. If the value
     * at row and column is 1, reverse it to 0. If the value
     * at row and column is 0, reverse it to 1. If the value
     * at row and column is -1, do nothing.
     * @param row the row number (start at 1).
     * @param column the column number (start at 1).
     */
    public void flip(int row, int column)
    {
        if(row > rows || row < 1 || column > cols || column < 1)
            throw new IllegalArgumentException();
        if(array2D[row - 1][column - 1] == 0)
            array2D[row - 1][column - 1] = 1;
        else if(array2D[row - 1][column - 1] == 1)
            array2D[row - 1][column - 1] = 0;
    }

    /**
     * Gets the value at row and column.
     * @param row the row number (start at 1).
     * @param column the column number (start at 1).
     * @return the value at row and column.
     */
    public int get(int row, int column)
    {
        if(row > rows || row < 1 || column > cols || column < 1)
            throw new IllegalArgumentException();
        return array2D[row - 1][column - 1];
    }
}