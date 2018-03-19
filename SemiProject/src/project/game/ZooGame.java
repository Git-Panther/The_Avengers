package project.game;

import java.awt.Point;
import java.util.LinkedList;

import javax.swing.JFrame;

import project.vo.Area;
import project.vo.SelectableImage;

public class ZooGame extends Game {

	private LinkedList<Area> zooArea = new LinkedList<Area>();
	
	public ZooGame() {		
		bgLocation = "resource/image/bg/zoo.jpg";
		imageObjects.add(new SelectableImage("Dog", "images/character/dog.png"));
		imageObjects.add(new SelectableImage("Giraffe", "images/character/giraffe.png"));
		imageObjects.add(new SelectableImage("Hare", "images/character/hare.png"));
		imageObjects.add(new SelectableImage("Lion", "images/character/lion.png"));
		imageObjects.add(new SelectableImage("Monkey", "images/character/monkey.png"));
		imageObjects.add(new SelectableImage("Owl", "images/character/owl.png"));
		
		zooArea.add(new Area("Dog", new Point(100, 10)));
		zooArea.add(new Area("Giraffe", new Point(450, 10)));
		zooArea.add(new Area("Hare", new Point(800, 10)));
		zooArea.add(new Area("Lion", new Point(100, 500)));
		zooArea.add(new Area("Monkey", new Point(450, 500)));
		zooArea.add(new Area("Owl", new Point(800, 500)));
	
		gamePanel.setBounds(400, 300, 400, 300);
	}
	
	@Override
	public void start() {
		// TODO Auto-generated method stub

	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean check(JFrame frame) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public LinkedList<SelectableImage> getImages() {
		// TODO Auto-generated method stub
		LinkedList<SelectableImage> animalObjects = new LinkedList<SelectableImage>();
		
		int baseX = 100, baseY = 100;
		
		for(int x = 1; x <= 2; x++) {
			for(int y = 1; y <= 4; y++) {
				animalObjects.add(imageObjects.get( (int)(Math.random() * 6) ));
				animalObjects.getLast().setOriginalLocation(new Point(baseX, baseY));
				baseX += 50;
			}	
			baseX = 100;
			baseY += 100;
		}
		
		return animalObjects;
	}

	@Override
	public LinkedList<Area> getArea() {
		// TODO Auto-generated method stub		
		return zooArea;
	}

}
