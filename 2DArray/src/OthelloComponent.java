import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import javax.swing.JComponent;

@SuppressWarnings("serial")
public class OthelloComponent extends JComponent implements MouseListener
{
    private Simple2DInterface grid;
    private int numRows;
    private int numColumns;
    private int leftMargin = 10;
    private int rightMargin = 10;
    private int topMargin = 10;
    private int bottomMargin = 10;
    private int circleMargin = 5;
    private double circleSize;
    private int width;
    private int height;
    private int topLeftX;
    private int topLeftY;
    private int bottomRightX;
    private int bottomRightY;
    private double cellWidth;
    private double cellHeight;
    private double boardWidth;
    private double boardHeight;
    private boolean isWhite;

    public OthelloComponent(Simple2DInterface a2DArray)
    {
        grid = a2DArray;
        numRows = grid.getNumberOfRows();
        numColumns = grid.getNumberOfColumns();
        isWhite = false;

        this.addMouseListener(this);
    }

    public void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;

        width = this.getWidth();
        height = this.getHeight();

        cellWidth = (double) (width - (leftMargin + rightMargin)) / numColumns;
        cellHeight = (double) (height - (topMargin + bottomMargin)) / numRows;

        if(cellWidth > cellHeight)
        {
            cellWidth = cellHeight;
        }
        else
        {
            cellHeight = cellWidth;
        }
        circleSize = cellWidth - (2 * circleMargin);

        boardWidth = cellWidth * numColumns;
        boardHeight = cellHeight * numRows;

        topLeftX = (width - (int) boardWidth) / 2;
        topLeftY = (height - (int) boardHeight) / 2;
        bottomRightX = topLeftX + (int) boardWidth;
        bottomRightY = topLeftY + (int) boardHeight;

        Line2D.Double line = new Line2D.Double(0,0,0,0);

        // Draw the Board

        g2.setColor(Color.BLACK);

        for(int i = 0; i <= numRows; i++)
        {
            line.setLine(topLeftX, topLeftY + (i * cellHeight), bottomRightX, topLeftY + (i * cellHeight));
            g2.draw(line);
        }

        for(int i = 0; i <= numColumns; i++)
        {
            line.setLine(topLeftX + (i * cellWidth), topLeftY, topLeftX + (i * cellWidth), bottomRightY);
            g2.draw(line);
        }

        // Draw circles

        Ellipse2D.Double circle = new Ellipse2D.Double();

        for(int row = 1; row <= numRows; row++)
        {
            for(int column = 1; column <= numColumns; column++)
            {
                if(grid.get(row, column) == 0)
                {
                    g2.setColor(Color.BLACK);
                    int x = topLeftX + circleMargin + (int) ((column - 1) * cellWidth);
                    int y = topLeftY + circleMargin + (int) ((row - 1) * cellHeight);
                    circle.setFrame(x,y,circleSize,circleSize);
                    g2.fill(circle);
                }
                else if(grid.get(row, column) == 1)
                {
                    int x = topLeftX + circleMargin + (int) ((column - 1) * cellWidth);
                    int y = topLeftY + circleMargin + (int) ((row - 1) * cellHeight);
                    circle.setFrame(x,y,circleSize,circleSize);
                    g2.setColor(Color.WHITE);
                    g2.fill(circle);
                    g2.setColor(Color.BLACK);
                    g2.draw(circle);
                }
            }
        }
    }

    public void mouseClicked(MouseEvent arg0)
    {
        int column = getColumn(arg0.getX());
        int row = getRow(arg0.getY());

        if(row != 0 && column != 0)
        {
            if(grid.get(row, column) == -1)
            {
                if(isWhite)
                {
                    grid.setToOne(row, column);
                }
                else
                {
                    grid.setToZero(row, column);
                }
            }
            else
            {
                if(isWhite && grid.get(row, column) == 0)
                {
                    grid.flip(row, column);
                }
                else if(!isWhite && grid.get(row, column) == 1)
                {
                    grid.flip(row, column);
                }
            }
        }

        repaint();
    }

    public void switchColor()
    {
        isWhite = !isWhite;
    }

    public int getColumn(int x)
    {
        int result = 0;

        for(int column = 1; column <= numColumns; column++)
        {
            if(x > topLeftX + ((column - 1) * cellWidth) && x < topLeftX + (column * cellWidth))
            {
                result = column;
                break;
            }
        }

        return result;
    }

    public int getRow(int y)
    {
        int result = 0;

        for(int row = 1; row <= numRows; row++)
        {
            if(y > topLeftY + ((row - 1) * cellHeight) && y < topLeftY + (row * cellHeight))
            {
                result = row;
                break;
            }
        }

        return result;
    }

    public void mouseEntered(MouseEvent arg0)
    {
    }

    public void mouseExited(MouseEvent arg0)
    {
    }

    public void mousePressed(MouseEvent arg0)
    {
    }

    public void mouseReleased(MouseEvent arg0)
    {
    }
}