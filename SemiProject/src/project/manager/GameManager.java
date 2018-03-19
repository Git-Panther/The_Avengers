package project.manager;

import javax.swing.JFrame;

import project.game.Game;

public class GameManager {
	private Game game;
	
	public GameManager(Game game) {
		this.game = game;
	}
	
	public void start() {
		game.start();
	}
	
	public void reset() {
		game.reset();
	}
	
	public void check() {
		game.check(new JFrame(""));
	}
}
