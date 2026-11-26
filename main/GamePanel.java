package main;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;
import inputs.KeyboardInput;
import inputs.MouseInput;

public class GamePanel extends JPanel{
    private MouseInput mouseInput;
    private Game game;

    public GamePanel(Game game) {
        this.game = game;

        mouseInput = new MouseInput(this);
        addKeyListener(new KeyboardInput(this));
        addMouseListener(mouseInput);
        addMouseMotionListener(mouseInput);
        
        setPanelSize();
    }

    private void setPanelSize() {
        Dimension size = new Dimension(1280, 800);
        setMinimumSize(size);
        setMaximumSize(size);
        setPreferredSize(size);
    }

    public void updateGame() {

    }

     public void paintComponent(Graphics g) {
        super.paintComponent(g);
        game.render(g);
    }

    public Game getGame() {
        return game;
    }
}
