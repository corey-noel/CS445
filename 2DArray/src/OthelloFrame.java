import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;


@SuppressWarnings("serial")
public class OthelloFrame extends JFrame
{
    private int frameWidth = 600;
    private int frameHeight = 600;

    private JLabel msg;
    private JButton switchPlayer;
    private JPanel msgPanel;
    private JPanel controlPanel;

    private Simple2DInterface sa;
    private OthelloComponent oc;
    private boolean isWhite = false;

    public static void main(String[] args)
    {
        JFrame frame = new OthelloFrame();
        frame.setVisible(true);
    }

    public OthelloFrame()
    {
        sa = new Simple2DArray(8,8);

        sa.setToOne(4, 4);
        sa.setToZero(4, 5);
        sa.setToZero(5, 4);
        sa.setToOne(5, 5);

        msg = new JLabel("Click on an empty space to put a disk or click on a disk to change color.");

        oc = new OthelloComponent(sa);

        controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(2,1));

        switchPlayer = new JButton("Switch Color to White");

        class SwitchButtonListener implements ActionListener
        {
            public void actionPerformed(ActionEvent arg0)
            {
                oc.switchColor();
                isWhite = !isWhite;
                if(isWhite)
                {
                    switchPlayer.setText("Switch Color to Black");
                }
                else
                {
                    switchPlayer.setText("Switch Color to White");
                }
            }
        }

        ActionListener sp = new SwitchButtonListener();
        switchPlayer.addActionListener(sp);

        controlPanel.add(switchPlayer);

        msgPanel = new JPanel();
        msgPanel.setBorder(new TitledBorder("Message"));
        msgPanel.add(msg);

        controlPanel.add(msgPanel);

        setSize(frameWidth, frameHeight);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Wanna be Othello");
        add(oc);
        add(controlPanel, BorderLayout.SOUTH);
        setVisible(true);
    }
}