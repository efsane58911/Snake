package de.ImOlli.entitys;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
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
	private ArrayList<Side> moveHistory;
	
	public Player(Integer x, Integer y){
		this.x  = x;
		this.y = y;
		this.moveHistory = new ArrayList<>();
		
		dots = new HashMap<>();
		dots.put(0, new Dot(x, y, 0));
		dots.put(1, new Dot(x-40, y, 1));
		dots.put(2, new Dot(x-80, y, 2));
		dots.put(3, new Dot(x-120, y, 3));
		dots.put(4, new Dot(x-160, y, 4));
		dots.put(5, new Dot(x-200, y, 5));
		dots.put(6, new Dot(x-240, y, 6));
		dots.put(7, new Dot(x-280, y, 7));
		dots.put(8, new Dot(x-320, y, 8));
		dots.put(9, new Dot(x-360, y, 9));
		dots.put(10, new Dot(x-400, y, 10));
		
		
		moveHistory.add(moveDir);
		
	}
	
	@Override
	public void draw(Graphics g) {
		for(Dot dot : dots.values()){
			dot.draw(g);
		}
	}

	public void checkKeys(){
		if(KeyCheckManager.keysCheck(KeyEvent.VK_W)){
			moveDir = Side.TOP;
		}else if(KeyCheckManager.keysCheck(KeyEvent.VK_S)){
			moveDir = Side.BOTTOM;
		}else if(KeyCheckManager.keysCheck(KeyEvent.VK_A)){
			moveDir = Side.LEFT;
		}else if(KeyCheckManager.keysCheck(KeyEvent.VK_D)){
			moveDir = Side.RIGHT;
		}else{
			return;
		}
	}
	
	@Override
	public void update() {
		Integer moveX = 0;
		Integer moveY = 0;
		
		checkKeys();
		
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
			
			moveHistory.add(moveDir);
			
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					try {
						Thread.sleep(300);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					delay = false;
				}
			}).start();
			
			x = x + moveX;
			y = y + moveY;
			
			dots.get(0).updateMovement(moveX, moveY);
			
			for(Dot dot : dots.values()){
				if(dot.getKey() != 0){
					
					Integer gettingKey = dot.getKey();
					Integer finalKey = moveHistory.size()-1-gettingKey;
					
					while((finalKey = moveHistory.size()-1-gettingKey) < 0){
						gettingKey--;
					}
					
					Side side;
					try{
						side = moveHistory.get(finalKey);
					}catch(Exception e){
						side = null;
					}
					
					dot.updateMovement(getMovementBySide(side)[0], getMovementBySide(side)[1]);
				}
			}
			
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
