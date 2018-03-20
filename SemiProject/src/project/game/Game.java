package project.game;

import java.util.LinkedList;

import javax.swing.JPanel;

import project.frame.GameFrame;
import project.vo.AnimalArea;
import project.vo.AnimalImage;

public abstract class Game {
//	protected String genre; // 장르
//	protected Map<String, Image> map = new TreeMap<String, Image>();
	protected String bgLocation; // 경로
	protected LinkedList<AnimalImage> imageObjects = new LinkedList<AnimalImage>();
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

	public abstract void start(GameFrame frame);
	public abstract void retry(GameFrame frame);
	public abstract boolean check(GameFrame frame);

	public abstract LinkedList<AnimalImage> getImages();
	public abstract LinkedList<AnimalArea> getArea();
}
