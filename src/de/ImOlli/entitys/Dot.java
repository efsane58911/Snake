package de.ImOlli.entitys;

import java.awt.Color;
import java.awt.Graphics;

import de.ImOlli.engine.RenderObject;

public class Dot extends RenderObject {

	private Integer x;
	private Integer y;
	private final Integer width = 40;
	private final Integer height = 40;
	
	public Dot(Integer x, Integer y){
		this.x = x;
		this.y = y;
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
		g.setColor(Color.BLUE);
		g.fillRect(x, y, width, height);
	}

	@Override
	public void update() {
		
	}
	
	public void updateMovement(Integer moveX, Integer moveY){
		this.x = x + moveX;
		this.y = y + moveY;
	}

}
