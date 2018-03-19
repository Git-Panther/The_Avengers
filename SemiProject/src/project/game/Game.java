package project.game;

import java.util.LinkedList;

import javax.swing.JPanel;

import project.frame.GameFrame;
import project.vo.Area;
import project.vo.SelectableImage;

public abstract class Game {
//	protected String genre; // 장르
//	protected Map<String, Image> map = new TreeMap<String, Image>();
	protected String bgLocation; // 경로
	protected LinkedList<SelectableImage> imageObjects = new LinkedList<SelectableImage>();
	protected JPanel gamePanel;
	protected String quiz;
	
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

	public JPanel getGamePanel() {
		return gamePanel;
	}

	public void setGamePanel(JPanel gamePanel) {
		this.gamePanel = gamePanel;
	}

	public abstract void start(GameFrame frame);
	public abstract void retry(GameFrame frame);
	public abstract boolean check(GameFrame frame);

	public abstract LinkedList<SelectableImage> getImages();
	public abstract LinkedList<Area> getArea();
}
