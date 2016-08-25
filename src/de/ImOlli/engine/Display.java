package de.ImOlli.engine;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JComponent;

public class Display extends JComponent{

	private static final long serialVersionUID = 1L;
	private ArrayList<RenderObject> renderlist;
	
	public Display(){
		renderlist = new ArrayList<>();
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		for(RenderObject renderObject : renderlist){
			renderObject.draw(g);
		}
	}
	
	public ArrayList<RenderObject> getRenderlist(){
		return renderlist;
	}
	
}
