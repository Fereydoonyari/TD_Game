import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TowerDefenseGame extends JFrame {
    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;
    private static final int TILE_SIZE = 32;
    private static final int BOARD_WIDTH = 20;
    private static final int BOARD_HEIGHT = 15;
    
    private GameBoard gameBoard;
    private Player player;
    private WaveManager waveManager;
    private boolean isRunning;
    private GamePanel gamePanel;
    private int selectedTowerType = -1; // -1: None, 0: BasicTower, 1: AdvancedTower
    
    public TowerDefenseGame() {
        super("Tower Defense Game");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        
        initialize();
        
        gamePanel = new GamePanel();
        add(gamePanel, BorderLayout.CENTER);
        
        JPanel controlPanel = createControlPanel();
        add(controlPanel, BorderLayout.SOUTH);
        
        setVisible(true);
        
        // Start game loop in a separate thread
        new Thread(this::gameLoop).start();
    }
    
    private void initialize() {
        gameBoard = new GameBoard(BOARD_WIDTH, BOARD_HEIGHT);
        player = new Player(200, 20); // Starting with 200 money and 20 lives
        waveManager = new WaveManager(gameBoard);
        isRunning = true;
        
        // Set up the path
        Path path = gameBoard.getPath();
        path.addWaypoint(0, 5);
        path.addWaypoint(5, 5);
        path.addWaypoint(5, 2);
        path.addWaypoint(10, 2);
        path.addWaypoint(10, 8);
        path.addWaypoint(15, 8);
        path.addWaypoint(15, 5);
        path.addWaypoint(19, 5);
        
        // Mark path tiles
        for (java.awt.Point point : path.getWaypoints()) {
            gameBoard.getTile(point.x, point.y).setType(TileType.PATH);
        }
    }
    
    private JPanel createControlPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        
        JButton startWaveButton = new JButton("Start Next Wave");
        startWaveButton.addActionListener(e -> waveManager.startNextWave());
        
        JButton basicTowerButton = new JButton("Basic Tower ($100)");
        basicTowerButton.addActionListener(e -> selectedTowerType = 0);
        
        JButton advancedTowerButton = new JButton("Advanced Tower ($200)");
        advancedTowerButton.addActionListener(e -> selectedTowerType = 1);
        
        panel.add(startWaveButton);
        panel.add(basicTowerButton);
        panel.add(advancedTowerButton);
        
        return panel;
    }
    
    private void gameLoop() {
        final int TARGET_FPS = 60;
        final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;
        
        long lastUpdateTime = System.nanoTime();
        long lastRenderTime;
        
        while (isRunning) {
            long currentTime = System.nanoTime();
            long updateDelta = currentTime - lastUpdateTime;
            
            if (updateDelta >= OPTIMAL_TIME) {
                update();
                lastUpdateTime = currentTime;
            }
            
            gamePanel.repaint();
            
            // Sleep to save CPU
            try {
                Thread.sleep(Math.max(0, (OPTIMAL_TIME - (System.nanoTime() - currentTime)) / 1000000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    private void update() {
        if (player.isGameOver()) {
            isRunning = false;
            JOptionPane.showMessageDialog(this, "Game Over! You survived " + waveManager.getCurrentWave() + " waves.");
            return;
        }
        
        waveManager.update();
        gameBoard.update();
        
        // Award money for defeated enemies
        for (Enemy enemy : gameBoard.getEnemies()) {
            if (!enemy.isActive() && enemy.getHealth() <= 0) {
                player.earnMoney(enemy.getReward());
            } else if (!enemy.isActive() && enemy.getHealth() > 0) {
                // Enemy reached the end
                player.takeDamage(enemy.getDamage());
            }
        }
    }
    
    private class GamePanel extends JPanel {
        public GamePanel() {
            setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
            
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (selectedTowerType < 0) return;
                    
                    int tileX = e.getX() / TILE_SIZE;
                    int tileY = e.getY() / TILE_SIZE;
                    
                    if (gameBoard.canPlaceTower(tileX, tileY)) {
                        Tower tower = null;
                        int cost = 0;
                        
                        switch (selectedTowerType) {
                            case 0:
                                tower = new BasicTower(tileX, tileY);
                                cost = 100;
                                break;
                            case 1:
                                tower = new AdvancedTower(tileX, tileY);
                                cost = 200;
                                break;
                        }
                        
                        if (tower != null && player.spendMoney(cost)) {
                            gameBoard.placeTower(tower);
                        }
                    }
                }
            });
        }
        
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            
            // Render game board
            gameBoard.render(g);
            
            // Render UI
            renderUI(g);
        }
        
        private void renderUI(Graphics g) {
            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial", Font.BOLD, 16));
            g.drawString("Money: $" + player.getMoney(), 10, BOARD_HEIGHT * TILE_SIZE + 20);
            g.drawString("Lives: " + player.getLives(), 150, BOARD_HEIGHT * TILE_SIZE + 20);
            g.drawString("Wave: " + waveManager.getCurrentWave(), 280, BOARD_HEIGHT * TILE_SIZE + 20);
            
            if (selectedTowerType >= 0) {
                g.drawString("Selected: " + (selectedTowerType == 0 ? "Basic Tower" : "Advanced Tower"), 
                             400, BOARD_HEIGHT * TILE_SIZE + 20);
            }
            
            if (player.isGameOver()) {
                g.setColor(Color.RED);
                g.setFont(new Font("Arial", Font.BOLD, 48));
                g.drawString("GAME OVER", WINDOW_WIDTH / 2 - 150, WINDOW_HEIGHT / 2);
            }
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(TowerDefenseGame::new);
    }
}