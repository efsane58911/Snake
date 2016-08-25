package de.ImOlli.game;

import java.awt.Insets;

import javax.swing.JFrame;

import de.ImOlli.engine.Display;

public class Game extends JFrame{

	private static final long serialVersionUID = 1L;
	private static final String title = "Snake";
	private static final String version = "ALPHA v1.0";
	private static final Integer width = 1280;
	private static final Integer height = 720;
	private static Boolean running = false;
	private Thread thread;
	private Insets border;
	private Display display;

	public Game(){
		setSize(width, height);
		setVisible(true);
		this.border = getInsets();
		setVisible(false);
		setSize(width+border.right+border.left-10, height+border.bottom+border.top-10);
		setTitle(title+" "+version);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);
		setLocation(100, 100);
		
		init();
	}

	private void init() {
		
		display = new Display();
		display.setBounds(0, 0, width, height);
		display.setFocusable(true);
		
		add(display);
		
		startMainLoop();
	}

	private void startMainLoop() {
		setVisible(true);
		running = true;
		
		thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(running){
					try {
						Thread.sleep(15);
						repaint();
					} catch (InterruptedException e) {
						e.printStackTrace();
						running = false;
					}
				}
			}
		});
		thread.start();
	}
}
