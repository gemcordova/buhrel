package entities;

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
    private boolean moving = false, attacking = false;
    private int previousAction = -1;
    private boolean up, left, down, right;
    private float playerSpeed = 2.0f;

    public Player(float x, float y) {
        super(x, y);
        loadAnimations();
    }
    
    public void update() {
        updatePos();
        updateAnimationTick();
        setAnimation();
    }

    public void render(Graphics g) {
        g.drawImage(animations[playerAction][aniIndex], (int)x, (int)y, 48*2, 48*2, null);
    }

    private void updateAnimationTick() {
        aniTick++;

        if(aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;

            if(aniIndex >= GetSpriteAmount(playerAction)) {
                aniIndex = 0;
                attacking = false;
            }   
        }
    }

    public void setAnimation() {
        int startAni = playerAction;

        if (moving)
            playerAction = RUN;
        else
            playerAction = IDLE;

        if(attacking)
            playerAction = ATTACK_3;

        if (playerAction != previousAction) {
            aniIndex = 0;
            aniTick = 0;
            previousAction = playerAction;
        }

        if(startAni != playerAction) 
            resetAniTick();
    }

    private void resetAniTick() {
        aniTick = 0;
        aniIndex = 0;
    }

    private void updatePos() {
        moving = false;

        if(left && !right) {
            x -= playerSpeed;
            moving = true;
        } else if(right && !left) {
            x += playerSpeed;
            moving = true;
        }

        if(up && !down) {
            y -= playerSpeed;
            moving = true;
        } else if(down && !up) {
            y += playerSpeed;
            moving = true;
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

    public void resetDirBoolean() {
        up = false;
        left = false;
        down = false;
        right = false;
    }

    public void setAttacking(boolean attacking) {
        this.attacking = attacking;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }
}
