package de.ImOlli.managers;

import de.ImOlli.entitys.Dot;
import de.ImOlli.entitys.Food;
import de.ImOlli.game.Game;

import java.util.ArrayList;

public class FoodManager implements Runnable {

    private ArrayList<Food> spawnedFood;
    private Boolean started = false;
    private Thread thread;
    private Integer delay;
    private Integer maxFoodSpawned;
    private final Game game;

    public FoodManager(Game game, Integer delay, Integer maxFoodSpawned) {
        this.game = game;
        this.spawnedFood = new ArrayList<>();
        this.delay = delay;
        this.maxFoodSpawned = maxFoodSpawned;
    }

    public void start() {
        this.started = true;
        this.thread = new Thread(this);
        thread.start();
    }

    public boolean stop() {
        if (this.started) {
            this.started = false;
            return true;
        } else
            return false;
    }

    public boolean spawnFood() {
        if (getSpawnedFood() < maxFoodSpawned) {

            final int height = (Game.getGameHeight() / 40) - 1;
            final int width = (Game.getGameWidth() / 40);

            final Integer randomX = (int) (Math.random() * width);
            final Integer randomY = (int) (Math.random() * height);

            Food food = new Food(game, randomX * 40, randomY * 40);

            for (Dot dot : game.getPlayer().getDots().values()) {

                //System.out.println("DEBUG: " + dot.getX() / 40 + " " + dot.getY() / 40 + " | " + randomX + " " + randomY);

                if (randomX.equals(dot.getX() / 40) && randomY.equals(dot.getY() / 40)) {
                    spawnFood();
                    return false;
                }
            }

            game.getDisplay().addToRenderList(food);
            spawnedFood.add(food);

            return true;
        } else {
            return false;
        }
    }

    public boolean checkCollision(Integer x, Integer y) {

        if (spawnedFood.isEmpty()) {
            return false;
        }

        for (Food food : spawnedFood) {

            if (food.getX().equals(x) && food.getY().equals(y)) {
                food.delete();
                return true;
            }
        }
        return false;
    }

    @Override
    public void run() {
        while (started) {
            try {
                Thread.sleep(delay);
                spawnFood();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public Integer getSpawnedFood() {
        return spawnedFood.size();
    }

    public ArrayList<Food> getSpawnedFoodList() {
        return spawnedFood;
    }

    public Boolean isStarted() {
        return started;
    }

    public ArrayList<Food> getFoodList() {
        return spawnedFood;
    }

}
