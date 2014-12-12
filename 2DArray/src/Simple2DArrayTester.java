
public class Simple2DArrayTester
{
    public static void main(String[] args)
    {
        int point = 0;
        int numRows = 10;
        int numColumns = 15;
        boolean notEqualMinusOne = false;

        Simple2DInterface s2d1 = new Simple2DArray(numRows,numColumns);

        // Check that all locations are -1.

        System.out.print("Testing that all locations must be -1: ");

        for(int i = 1; i <= numRows; i++)
        {
            for(int j = 1; j <= numColumns; j++)
            {
                if(s2d1.get(i, j) != -1)
                {
                    notEqualMinusOne = true;
                }
            }
        }

        if(notEqualMinusOne)
        {
            System.out.println("FAIL");
            System.out.println("Not all locations contain -1.\n");
        }
        else
        {
            System.out.println("PASS");
            point++;
        }
        System.out.println("Your current point is " + point + ".\n");

        // Testing the method getNumberOfRows()

        System.out.print("Testing the method getNumberOfRows: ");
        if(s2d1.getNumberOfRows() != numRows)
        {
            System.out.println("FAIL");
            System.out.println("The number of from your method getNumberOfRows should be " + numRows + ".");
            System.out.println("But your method getNumberOfRows returns " + s2d1.getNumberOfRows() + ".\n");
        }
        else
        {
            System.out.println("PASS");
            point++;
        }
        System.out.println("Your current point is " + point + ".\n");

        // Testing the method getNumberOfColumns()

        System.out.print("Testing the method getNumberOfColumns: ");
        if(s2d1.getNumberOfColumns() != numColumns)
        {
            System.out.println("FAIL");
            System.out.println("The number of from your method getNumberOfColumns should be " + numColumns + ".");
            System.out.println("But your method getNumberOfColumns returns " + s2d1.getNumberOfColumns() + ".\n");
        }
        else
        {
            System.out.println("PASS");
            point++;
        }
        System.out.println("Your current point is " + point + ".\n");

        // Testing the method setToOne()

        System.out.print("Testing the method setToOne(): ");

        s2d1.setToOne(5, 9);
        if(s2d1.get(5, 9) != 1)
        {
            System.out.println("FAIL");
            System.out.println("After calling the method setToOne(5,9) the value at row 5 column 9 should be 1.");
            System.out.println("But your method get(5,9) returns " + s2d1.get(5,9) + ".\n");
        }
        else
        {
            System.out.println("PASS");
            point++;
        }
        System.out.println("Your current point is " + point + ".\n");

        // setToZero()

        System.out.print("Testing the method setToZero(): ");

        s2d1.setToZero(9, 5);
        if(s2d1.get(9, 5) != 0)
        {
            System.out.println("FAIL");
            System.out.println("After calling the method setToZero(9,5) the value at row 9 column 5 should be 0.");
            System.out.println("But your method get(9,5) returns " + s2d1.get(9,5) + ".\n");
        }
        else
        {
            System.out.println("PASS");
            point++;
        }
        System.out.println("Your current point is " + point + ".\n");

        // flip()

        System.out.print("Testing flip from one to zero: ");
        s2d1.flip(5, 9);
        if(s2d1.get(5, 9) != 0)
        {
            System.out.println("FAIL");
            System.out.println("After flipping by calling flip(5,9) the value at row 5 column 9 should be 0.");
            System.out.println("But your method get(5,9) returns " + s2d1.get(5,9) + "\n.");
        }
        else
        {
            System.out.println("PASS");
            point++;
        }
        System.out.println("Your current point is " + point + ".\n");

        System.out.print("Testing flip from zero to one: ");
        s2d1.flip(9, 5);
        if(s2d1.get(9, 5) != 1)
        {
            System.out.println("FAIL");
            System.out.println("After flipping by calling flip(9,5) the value at row 9 column 5 should be 1.");
            System.out.println("But your method get(9,5) returns " + s2d1.get(9,5) + "\n.");
        }
        else
        {
            System.out.println("PASS");
            point++;
        }
        System.out.println("Your current point is " + point + ".\n");

        System.out.print("Testing flip -1: ");
        s2d1.flip(1, 1);
        if(s2d1.get(1, 1) != -1)
        {
            System.out.println("FAIL");
            System.out.println("After flipping by calling flip(1,1), the value at row 1 column 1 should be -1.");
            System.out.println("But your method get(1,1) returns " + s2d1.get(1,1) + ".\n");
        }
        else
        {
            System.out.println("PASS");
            point++;
        }
        System.out.println("Your current point is " + point + ".\n");

        // clear()

        System.out.print("Testing the method clear(): ");
        s2d1.clear();

        notEqualMinusOne = false;

        for(int i = 1; i <= numRows; i++)
        {
            for(int j = 1; j <= numColumns; j++)
            {
                if(s2d1.get(i, j) != -1)
                {
                    notEqualMinusOne = true;
                }
            }
        }

        if(notEqualMinusOne)
        {
            System.out.println("FAIL");
            System.out.println("After calling the method clear. Not all locations contain -1.\n");
        }
        else
        {
            System.out.println("PASS");
            point++;
        }
        System.out.println("Your current point is " + point + ".\n");

        if(point == 9)
        {
            System.out.println("Everything looks good one extra point :)");
            point++;
        }

        System.out.println("Your final point is " + point + ".");

        if(point == 10)
        {
            System.out.println("Contratulation! Your class Simple2DArray works perfectly (I guess).");
            System.out.println("You can run OthelloFrame to see how Simple2DArray can be used in a program.");
        }
        else
        {
            System.out.println("There is one or more errors in your class.");
            System.out.println("Fix your bugs to get more points.");
        }
    }
}