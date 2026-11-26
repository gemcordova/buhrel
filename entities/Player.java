package entities;

import static utilz.Constants.Direction.UP;
import static utilz.Constants.Direction.LEFT;
import static utilz.Constants.Direction.DOWN;
import static utilz.Constants.Direction.RIGHT;
import static utilz.Constants.PlayerConstants.*;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

public class Player extends Entity{
    private BufferedImage[][] animations;
    private int aniTick, aniIndex, aniSpeed = 15;
    private int playerAction = DOUBLE_JUMP;
    private int playerDir = -1;
    private boolean moving = false;
    private int previousAction = -1;

    public Player(float x, float y) {
        super(x, y);
        loadAnimations();
    }
    
    public void update() {
        updateAnimationTick();
        setAnimation();
        updatePos();
    }

    public void render(Graphics g) {
        g.drawImage(animations[playerAction][aniIndex], (int)x, (int)y, 48*2, 48*2, null);
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
                    y -= 5;
                    break;
                case LEFT:
                    x -= 5;
                    break;
                case DOWN:
                    y += 5;
                    break;
                case RIGHT:
                    x += 5;
                    break;
                default:
                    break;
            }
        }
    }

    private void loadAnimations() {
        InputStream is = getClass().getResourceAsStream("/res/Characters/Character_1.png");
        try {
            BufferedImage img = ImageIO.read(is);
            animations = new BufferedImage[11][8];

            for(int j = 0; j < animations.length; j++)
                for(int i = 0; i < animations[j].length; i++)
                    animations[j][i] = img.getSubimage(i * 48, j * 48, 48, 48);
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
}
