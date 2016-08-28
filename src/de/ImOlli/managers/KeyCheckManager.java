package de.ImOlli.managers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyCheckManager implements KeyListener {

	private static boolean keys[] = new boolean[100];

	public static boolean keysCheck(int keycode) {
		if (keycode >= 0 && (keycode < keys.length))
			return keys[keycode];
		else
			return false;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int KeyCode = e.getKeyCode();
		if ((KeyCode >= 0) && (KeyCode < keys.length))
			keys[KeyCode] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int KeyCode = e.getKeyCode();
		if ((KeyCode >= 0) && (KeyCode < keys.length))
			keys[KeyCode] = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

}