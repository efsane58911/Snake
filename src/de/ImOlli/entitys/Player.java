package de.ImOlli.entitys;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.HashMap;

import de.ImOlli.engine.RenderObject;
import de.ImOlli.managers.KeyCheckManager;
import javafx.geometry.Side;

public class Player extends RenderObject{

	private Integer x;
	private Integer y;
	private HashMap<Integer, Dot> dots;
	private Side moveDir = Side.RIGHT;
	private Boolean delay = false;
	
	public Player(Integer x, Integer y){
		this.x  = x;
		this.y = y;
		dots = new HashMap<>();
		dots.put(0, new Dot(x, y));
		dots.put(1, new Dot(x-40, y));
		
	}
	
	@Override
	public void draw(Graphics g) {
		for(Dot dot : dots.values()){
			dot.draw(g);
		}
	}

	@Override
	public void update() {
		Integer moveX = 0;
		Integer moveY = 0;
		
		if(KeyCheckManager.keysCheck(KeyEvent.VK_W)){
			moveDir = Side.TOP;
		}else if(KeyCheckManager.keysCheck(KeyEvent.VK_S)){
			moveDir = Side.BOTTOM;
		}else if(KeyCheckManager.keysCheck(KeyEvent.VK_A)){
			moveDir = Side.LEFT;
		}else if(KeyCheckManager.keysCheck(KeyEvent.VK_D)){
			moveDir = Side.RIGHT;
		}
		
		if(!delay){
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
			
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					try {
						Thread.sleep(400);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					delay = false;
				}
			}).start();
		}
		
		
		x = x + moveX;
		y = y + moveY;
		
		dots.get(0).updateMovement(moveX, moveY);
		dots.get(1).updateMovement(moveX, moveY);
		
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
