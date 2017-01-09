package de.ImOlli.entitys;

import de.ImOlli.engine.RenderObject;
import de.ImOlli.managers.KeyCheckManager;
import javafx.geometry.Side;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;

public class Player extends RenderObject {

    private Integer x;
    private Integer y;
    private HashMap<Integer, Dot> dots;
    private Side moveDir = Side.RIGHT;
    private Boolean delay = false;
    private ArrayList<Side> moveHistory;

    public Player(Integer x, Integer y) {
        this.x = x;
        this.y = y;
        this.moveHistory = new ArrayList<>();

        dots = new HashMap<>();
        moveHistory.add(moveDir);
        dots.put(0, new Dot(x, y, 0));
        growUp();
        growUp();
    }

    @Override
    public void draw(Graphics g) {
        for (Dot dot : dots.values()) {
            dot.draw(g);
        }
    }

    private void checkKeys() {
        if (KeyCheckManager.keysCheck(KeyEvent.VK_W)) {
            if (moveDir != Side.BOTTOM) {
                moveDir = Side.TOP;
            }
        } else if (KeyCheckManager.keysCheck(KeyEvent.VK_S)) {
            if (moveDir != Side.TOP) {
                moveDir = Side.BOTTOM;
            }
        } else if (KeyCheckManager.keysCheck(KeyEvent.VK_A)) {
            if (moveDir != Side.RIGHT) {
                moveDir = Side.LEFT;
            }
        } else if (KeyCheckManager.keysCheck(KeyEvent.VK_D)) {
            if (moveDir != Side.LEFT) {
                moveDir = Side.RIGHT;
            }
        } else if (KeyCheckManager.keysCheck(KeyEvent.VK_E)) {
            System.out.println("hasd");
            growUp();
        } else {
            return;
        }
    }

    @Override
    public void update() {
        Integer moveX = 0;
        Integer moveY = 0;

        checkKeys();

        if (!delay) {
            switch (moveDir) {
                case TOP:
                    moveY = -40;
                    break;
                case BOTTOM:
                    moveY = 40;
                    break;
                case RIGHT:
                    moveX = 40;
                    break;
                case LEFT:
                    moveX = -40;
                    break;
            }

            delay = true;

            moveHistory.add(moveDir);

            new Thread(new Runnable() {

                @Override
                public void run() {
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    delay = false;
                }
            }).start();

            x = x + moveX;
            y = y + moveY;

            dots.get(0).updateMovement(moveX, moveY);

            for (Dot dot : dots.values()) {
                if (dot.getKey() != 0) {

                    Integer gettingKey = dot.getKey();
                    Side side = getLastMove(gettingKey);

                    dot.updateMovement(getMovementBySide(side)[0], getMovementBySide(side)[1]);
                }
            }
        }
    }

    public Side getLastMove(Integer key) {

        Integer finalKey = moveHistory.size() - 1 - key;

        while ((finalKey = moveHistory.size() - 1 - key) < 0) {
            key--;
        }

        try {
            return moveHistory.get(finalKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void growUp() {
        if (dots.get(dots.size()) == null) {

            Dot dot = dots.get(dots.size() - 1);

            Side side = getLastMove(dot.getKey());

            dots.put(dots.size(), new Dot(dot.getX() - getMovementBySide(side)[0], dot.getY() - getMovementBySide(side)[1], dots.size()));
        }
    }

    private Integer[] getMovementBySide(Side side) {
        switch (side) {
            case TOP:
                return new Integer[]{0, -40};
            case BOTTOM:
                return new Integer[]{0, 40};
            case RIGHT:
                return new Integer[]{40, 0};
            case LEFT:
                return new Integer[]{-40, 0};
        }
        return null;
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
        return null;
    }

    @Override
    public Integer getHeight() {
        return null;
    }

}
