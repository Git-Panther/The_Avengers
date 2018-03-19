package project.game;

import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import project.vo.Area;
import project.vo.SelectableImage;

public abstract class Game {
//	protected String genre; // 장르
//	protected Map<String, Image> map = new TreeMap<String, Image>();
	protected String bgLocation; // 경로
	protected LinkedList<SelectableImage> imageObjects = new LinkedList<SelectableImage>();
	protected JPanel gamePanel = new JPanel();
	
//	public Map<String, Image> getList() {
//		return map;
//	}
	
	public String getBackgroundLocation() {
		return bgLocation;
	}	

	public JPanel getGamePanel() {
		return gamePanel;
	}

	public void setGamePanel(JPanel gamePanel) {
		this.gamePanel = gamePanel;
	}

	public abstract void start();
	public abstract void reset();
	public abstract boolean check(JFrame frame);

	public abstract LinkedList<SelectableImage> getImages();
	public abstract LinkedList<Area> getArea();
}
