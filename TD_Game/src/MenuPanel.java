import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MenuPanel extends JPanel {
    public MenuPanel(Game game) {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;

        JLabel title = new JLabel("TOWER DEFENSE GAME");
        title.setFont(new Font("Arial", Font.BOLD, 32));
        gbc.gridy = 0;
        add(title, gbc);

        JButton startMap1 = new JButton("Start Map 1");
        gbc.gridy = 1;
        add(startMap1, gbc);
        startMap1.addActionListener(e -> game.startGameWithMap(1));

        JButton startMap2 = new JButton("Start Map 2");
        gbc.gridy = 2;
        add(startMap2, gbc);
        startMap2.addActionListener(e -> game.startGameWithMap(2));

        JButton exit = new JButton("Exit");
        gbc.gridy = 3;
        add(exit, gbc);
        exit.addActionListener(e -> System.exit(0));
    }
} 