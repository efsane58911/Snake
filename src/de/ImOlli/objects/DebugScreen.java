package de.ImOlli.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import de.ImOlli.engine.RenderObject;
import de.ImOlli.game.Game;
import de.ImOlli.managers.KeyCheckManager;

public class DebugScreen extends RenderObject {
	
	private final Integer x = 0;
	private final Integer y = 0;
	private final Integer width = Game.getGameWidth();
	private final Integer height = Game.getGameHeight();
	private Game game;
	
	public DebugScreen(Game game) {
		this.game = game;
	}
	
	@Override
	public void draw(Graphics g) {
		if(KeyCheckManager.keysCheck(KeyEvent.VK_F)){
			g.setColor(Color.black);
			g.drawString("Debug Screen", 10, 20);
		}
	}


	@Override
	public void update() {
		
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

}
