package main;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;
import inputs.KeyboardInput;
import inputs.MouseInput;

import static main.Game.GAME_WIDTH;
import static main.Game.GAME_HEIGHT;

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
        Dimension size = new Dimension(GAME_WIDTH, GAME_HEIGHT);
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
