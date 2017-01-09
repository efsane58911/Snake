package de.ImOlli.entitys;

import de.ImOlli.engine.RenderObject;
import de.ImOlli.game.Game;

import java.awt.*;

public class Dot extends RenderObject {

    private Integer x;
    private Integer y;
    private final Integer width = 40;
    private final Integer height = 40;
    private final Integer key;

    public Dot(Integer x, Integer y, Integer key) {
        this.x = x;
        this.y = y;
        this.key = key;
    }

    @Override
    public Integer getX() {
        return x;
    }

    @Override
    public Integer getY() {
        return y;
    }

    @Override
    public Integer getWidth() {
        return width;
    }

    @Override
    public Integer getHeight() {
        return height;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.DARK_GRAY);
        g.fillRect(x, y, width, height);
        //g.fillRoundRect(x, y, width, height, 25, 25);
    }

    @Override
    public void update() {

    }

    public void updateMovement(Integer moveX, Integer moveY) {

        this.x = x + moveX;
        this.y = y + moveY;

        if (x + width > Game.getGameWidth()) {
            x = 0;
        }
        if (x < 0) {
            x = Game.getGameWidth() - width;
        }

        if (y + height > Game.getGameHeight()) {
            y = 0;
        }
        if (y < 0) {
            y = Game.getGameHeight() - height;
        }

    }

    public Integer getKey() {
        return this.key;
    }

}
