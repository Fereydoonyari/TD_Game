import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TowerDefenseGame extends JFrame {
    private static final int Window_Width = 800 ;
    private static final int Window_Height = 600 ;
    private static final int Tile_Size = 32 ;
    private static final int Board_Width = 20 ;
    private static final int Board_Height = 15 ;

    private GameBoard gameBoard;
    private GamePanel gamePanel;
    private boolean isRunning;

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
        isRunning = true;

    }
    private JPanel createControlPanel(){
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        return panel;
    }
    private void gameLoop(){
        final int target_FPS = 60 ;
        final long Optimal_Time = 1000000000 / target_FPS;

        long lastUpdateTime = System.nanoTime();
        long lastRenderTime;

        while (isRunning ){
            long currentTime = System.nanoTime();
            long updateDelta = currentTime - lastUpdateTime ;
            if (updateDelta >= Optimal_Time){
                update ();
                lastUpdateTime = currentTime;
            }

            gamePanel.repaint();

            try {
                Thread.sleep(Math.max(0,(Optimal_Time - (System.nanoTime() - currentTime)) / 1000000));
            }catch(InterruptedException e ){
                e.printStackTrace();
            }
        }
    }
    private void update (){
        gameBoard.update();


    }
    private class GamePanel extends JPanel{
        public GamePanel(){
            setPreferredSize(new Dimension(Window_Width,Window_Height));

        }
        @Override
        protected void paintComponent(Graphics g){
            super.paintComponent(g);
            gameBoard.render(g);
            renderUI(g);
        }

        private void renderUI(Graphics g){
            g.setColor(Color.BLACK);
            g.setFont(new Font("ARIAL",Font.BOLD,16));
            g.drawString("Money", 10,Board_Height * Tile_Size + 20 * Tile_Size + 20 );
            g.drawString("Lives: ", 150, Board_Height * Tile_Size + 20);
            g.drawString("Wave: ", 280, Board_Height * Tile_Size + 20);


        }
    }
    public static  void main(String [] args){
        SwingUtilities.invokeLater(TowerDefenseGame::new);
    }


}