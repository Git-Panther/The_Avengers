package project.game;

import java.util.LinkedList;

import javax.swing.ImageIcon;

import project.frame.GameFrame;

public abstract class Game {
//	protected String genre; // 장르
//	protected Map<String, Image> map = new TreeMap<String, Image>();
	protected String bgLocation; // 경로
	protected LinkedList<ImageIcon> imageObjects = new LinkedList<ImageIcon>();
	protected String quiz;
	protected GameFrame frame;
	
//	public Map<String, Image> getList() {
//		return map;
//	}
	
	public String getQuiz() {
		return quiz;
	}

	public void setQuiz(String quiz) {
		this.quiz = quiz;
	}

	public String getBackgroundLocation() {
		return bgLocation;
	}	

	public abstract void start();
	public abstract void restart();
	public abstract void retry();
	public abstract boolean check();

	public void setGameFrame(GameFrame gameFrame) {
		// TODO Auto-generated method stub
		this.frame = gameFrame;
	}
	
	public GameFrame getGameFrame() {
		return frame;
	}
}
