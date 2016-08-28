package de.ImOlli.game;

import java.awt.Font;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import de.ImOlli.engine.Display;
import de.ImOlli.engine.RenderObject;
import de.ImOlli.entitys.Player;
import de.ImOlli.managers.KeyCheckManager;

public class Game extends JFrame {

	private static final long serialVersionUID = 1L;
	private static final String title = "Snake";
	private static final String version = "ALPHA v1.1";
	private static final Integer width = 1280;
	private static final Integer height = 720;
	private static Boolean running = false;
	private Thread thread;
	private Insets border;
	private Display display;
	private Player player;

	public Game() {
		setSize(width, height);
		setVisible(true);
		this.border = getInsets();
		setVisible(false);
		setSize(width + border.right + border.left - 10, height + border.bottom + border.top - 10);
		setTitle(title + " " + version);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);
		setLocation(100, 100);

		initMenu();
	}

	private void initMenu() {

		Font f = new Font("Tahoma", Font.BOLD, 100);

		JLabel l = new JLabel(title);
		JLabel l2 = new JLabel(version);
		JButton b = new JButton("Start");
		JButton b2 = new JButton("Options");
		JButton b3 = new JButton("Credits");

		l.setFont(f);

		l.setBounds((width / 2) - 155, 100, 400, 100);
		l2.setBounds(width - 100, height - 50, 100, 50);
		b.setBounds((width / 2) - 200, 300, 400, 40);
		b2.setBounds((width / 2) - 200, 400, 400, 40);
		b3.setBounds((width / 2) - 200, 500, 400, 40);

		add(l);
		add(l2);
		add(b);
		add(b2);
		add(b3);

		b.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
				remove(b);
				remove(b2);
				remove(b3);
				remove(l);
				remove(l2);
				initGame();
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});

		setVisible(true);
	}

	private void initGame() {

		setFocusable(true);
		requestFocus();
		addKeyListener(new KeyCheckManager());

		display = new Display();
		display.setBounds(0, 0, width, height);
		display.setFocusable(true);
		display.addKeyListener(new KeyCheckManager());

		add(display);

		player = new Player(400, 400);

		display.addToRenderList(player);

		startMainLoop();
	}

	private void startMainLoop() {
		setVisible(true);
		running = true;

		thread = new Thread(new Runnable() {

			@Override
			public void run() {
				while (running) {
					try {
						Thread.sleep(15);
						for (RenderObject renderObject : display.getRenderlist()) {
							renderObject.update();
						}
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

	public static Integer getGameWidth() {
		return width;
	}

	public static Integer getGameHeight() {
		return height;
	}
}
