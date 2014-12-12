import javax.swing.JFrame;

public class THSolverFrame {

    static int numMoves;
    static int speed = 10;
    static int numberOfDiscs = 10;

    public static void main(String[] args) throws InterruptedException {
        TowerOfHanoi towers = new TowerOfHanoi(numberOfDiscs);
        THComponent thc = new THComponent(towers);


        JFrame frame = new JFrame();
        frame.setTitle("Tower of Hanoi");
        frame.setSize(500,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(thc);

        frame.setVisible(true);

        Thread.sleep(speed * 50);

        solveTower(towers, thc, 0, 2, 1, numberOfDiscs);

        System.out.println("DONE!!! (" + numMoves + ")");
    }

    public static void solveTower(TowerOfHanoi towers, THComponent thc,
                                  int startPole, int endPole, int tempPole, int numDiscs) throws InterruptedException {
        if (numDiscs == 1) {
            towers.moveTopDisc(startPole, endPole);
            numMoves++;
            thc.repaint();
            Thread.sleep(speed);
        } else {
            solveTower(towers, thc, startPole, tempPole, endPole, numDiscs - 1);
            towers.moveTopDisc(startPole, endPole);
            numMoves++;
            thc.repaint();
            Thread.sleep(speed);
            solveTower(towers, thc, tempPole, endPole, startPole, numDiscs - 1);
        }
    }
}