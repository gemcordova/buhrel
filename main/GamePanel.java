package main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import inputs.KeyboardInput;
import inputs.MouseInput;
import static utilz.Constants.PlayerConstants.*;
import static utilz.Constants.Direction.*;

public class GamePanel extends JPanel{
    private MouseInput mouseInput;
    private float xDelta = 100, yDelta = 100;
    private BufferedImage img;
    private BufferedImage[][] animations;
    private int aniTick, aniIndex, aniSpeed = 15;
    private int playerAction = DOUBLE_JUMP;
    private int playerDir = -1;
    private boolean moving = false;
    private int previousAction = -1;

    public GamePanel() {
        importImg();
        loadAnimations();
        mouseInput = new MouseInput(this);
        addKeyListener(new KeyboardInput(this));
        addMouseListener(mouseInput);
        addMouseMotionListener(mouseInput);
        setPanelSize();
    }

    private void loadAnimations() {
        animations = new BufferedImage[11][8];

        for(int j = 0; j < animations.length; j++) {
            for(int i = 0; i < animations[j].length; i++)
                animations[j][i] = img.getSubimage(i * 48, j * 48, 48, 48);
        }
    }

    private void importImg() {
        InputStream is = getClass().getResourceAsStream("/res/Characters/Character_1.png");
        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void setPanelSize() {
        Dimension size = new Dimension(1280, 800);
        setMinimumSize(size);
        setMaximumSize(size);
        setPreferredSize(size);
    }

    public void setDirection(int direction) {
        this.playerDir = direction;
        moving = true;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    private void updateAnimationTick() {
        aniTick++;

        if(aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;

            if(aniIndex >= GetSpriteAmount(playerAction))
                aniIndex = 0;
        }
    }

    public void setAnimation() {
        if (moving)
            playerAction = RUN;
        else
            playerAction = IDLE;

        if (playerAction != previousAction) {
            aniIndex = 0;
            aniTick = 0;
            previousAction = playerAction;
        }
    }

    private void updatePos() {
        if(moving) {
            switch (playerDir) {
                case UP:
                    yDelta -= 5;
                    break;
                case LEFT:
                    xDelta -= 5;
                    break;
                case DOWN:
                    yDelta += 5;
                    break;
                case RIGHT:
                    xDelta += 5;
                    break;
                default:
                    break;
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        setAnimation();
        updateAnimationTick();
        updatePos();

        g.drawImage(animations[playerAction][aniIndex],(int)xDelta, (int)yDelta, 48*2, 48*2, null);
    }
}
