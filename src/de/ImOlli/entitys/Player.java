package de.ImOlli.entitys;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.HashMap;

import de.ImOlli.engine.RenderObject;
import de.ImOlli.managers.KeyCheckManager;

public class Player extends RenderObject{

	private Integer x;
	private Integer y;
	private HashMap<Integer, Dot> dots;
	
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
			moveY = -40;
		}else if(KeyCheckManager.keysCheck(KeyEvent.VK_S)){
			moveY = 40;
		}else if(KeyCheckManager.keysCheck(KeyEvent.VK_A)){
			moveX = -40;
		}else if(KeyCheckManager.keysCheck(KeyEvent.VK_D)){
			moveX = 40;
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
