import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TowerDefenseGame extends JFrame {
    private static final int Window_Width = 800 ;
    private static final int Window_Height = 600 ;
    private static final int Title_Size = 32 ;
    private static final int Board_Width = 20 ;
    private static final int Board_Height = 15 ;

    private GameBoard gameBoard;
    private GamePanel gamePanel;
    public TowerDefenseGame(){
        super("Tower Defense Game");
        setSize(Window_Width,Window_Height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        
        initialize();
        
        gamePanel = new GamePanel();
        add (gamePanel,BorderLayout.CENTER);

        JPanel controPanel = createControlPanel();
        add (controPanel, BorderLayout.SOUTH);
        setVisible(true);
        new Thread(this::gameLoop).start();
    }

    private void initialize(){
        gameBoard = new GameBoard(Board_Width, Board_Height);
    }





}