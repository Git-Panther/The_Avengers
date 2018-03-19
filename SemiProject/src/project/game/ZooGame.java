package project.game;

import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import project.frame.GameFrame;
import project.vo.Area;
import project.vo.SelectableImage;

public class ZooGame extends Game {

	private LinkedList<Area> zooArea = new LinkedList<Area>(); // 동물원 우리
	private LinkedList<SelectableImage> animalObjects; // 랜덤으로 할당된 동물들
	private boolean isDragged; // 마우스 눌림 여부
	
	public ZooGame() {		
		bgLocation = "resource/image/bg/zoo.jpg";
		quiz = "동물들을 우리로 돌려보내세요.";
		// 문제의 동물들
		imageObjects.add(new SelectableImage("Dog", "resource/image/character/dog.png"));
		imageObjects.add(new SelectableImage("Giraffe", "resource/image/character/giraffe.png"));
		imageObjects.add(new SelectableImage("Hare", "resource/image/character/hare.png"));
		imageObjects.add(new SelectableImage("Lion", "resource/image/character/lion.png"));
		imageObjects.add(new SelectableImage("Monkey", "resource/image/character/monkey.png"));
		imageObjects.add(new SelectableImage("Owl", "resource/image/character/owl.png"));
		
		// 정답 공간
		zooArea.add(new Area("Dog", new Point(50, 50)));
		zooArea.add(new Area("Giraffe", new Point(475, 50)));
		zooArea.add(new Area("Hare", new Point(900, 50)));
		zooArea.add(new Area("Lion", new Point(50, 325)));
		zooArea.add(new Area("Monkey", new Point(900, 325)));
		zooArea.add(new Area("Owl", new Point(50, 600)));
	}
	
	public LinkedList<Area> getZooArea() {
		return zooArea;
	}

	public void setZooArea(LinkedList<Area> zooArea) {
		this.zooArea = zooArea;
	}

	public LinkedList<SelectableImage> getAnimalObjects() {
		return animalObjects;
	}

	public void setAnimalObjects(LinkedList<SelectableImage> animalObjects) {
		this.animalObjects = animalObjects;
	}
	
	class AnimalLabel extends JLabel {
		/**
		 * 
		 */
		private static final long serialVersionUID = 6390517800549703903L;

		private String name; // 동물 이름
		
		public AnimalLabel(SelectableImage image) {
			super(image);
			name = image.getName();
		}
		
		public String getName() {
			return name;
		}
	}
	
	@Override
	public void start(GameFrame frame) { // 새로 시작, 게임 엎을 때
		// TODO Auto-generated method stub	
		frame.getQuestionMessage().setText(getQuiz());
		// 문제 텍스트
		
		// 문제 시작시 배치되는 항목 모아놓는 곳
		gamePanel = new JPanel();
		gamePanel.setBounds(475, 325, 250, 250);
		gamePanel.setOpaque(false);
		
		animalObjects = getImages();
		LinkedList<AnimalLabel> animalLabels = new LinkedList<AnimalLabel>(); // 동물들 레이블. 실제로 이걸 옮긴다.
		Iterator<SelectableImage> itrObjects = animalObjects.iterator(); 
		while (itrObjects.hasNext()) {
			animalLabels.add(new AnimalLabel(itrObjects.next()));
			gamePanel.add(animalLabels.getLast());
		}
		// 동물들을 모아놓는다.

		LinkedList<Area> questionArea = getArea();
		Iterator<Area> itrArea = questionArea.iterator();
		while (itrArea.hasNext()) {
			frame.getContentPane().add(itrArea.next());
		}
		// System.out.println("문제 공간 확보 완료");
		frame.getContentPane().add(gamePanel);
		// System.out.println("게임 패널 넣기 완료");
		
		frame.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				// 마우스를 뗐을 때 이미지가 패널에 접근(e)했다면 넣고, 아니면 원위치로.
				isDragged = false;
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				// 마우스를 눌렀을 때의 좌표 저장
//				if(box.contains(new Point(me.getX(),me.getY()))){
//					//#1 마우스 버튼 누름
//					//사각형내 마우스 클릭 상대 좌표를 구함
//					//현재 마우스 스크린 좌표에서 사각형 위치 좌표의 차이를 구함
//					offX = me.getX() - box.x;
//					offY = me.getY() - box.y;
//					
//					//드래그 시작을 표시
//					isDragged = true;
//
//				}
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				// 마우스가 떠났을 때
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				// 마우스가 들어왔을 때
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				// 마우스를 클릭했을 때
			}
		});
		
		frame.addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				// 마우스가 움직일 때
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				// 마우스를 드래그하고 있을 때 그린다.
				//드래그 모드인 경우에만 사각형 이동시킴
//				if(isDragged){
//					box.x = me.getX() - offX;
//					box.y = me.getY() - offY;
//				}
//				repaint();
			}
		});
	}

	@Override
	public void retry(GameFrame frame) { // 실패했을 때 재시도
		// TODO Auto-generated method stub

	}

	@Override
	public boolean check(GameFrame frame) { // 동물원 게임은 이름이 일치하지 않는 것이 발견되면 false다.
		// TODO Auto-generated method stub
		// frame에서 들어간 Area들에 들어간 것들이 Area 정답명과 일치하는지 검사
		
		if(gamePanel.getComponents().length != 0) // 다 안 넣었으므로 틀렸음
			return false;
		
		Iterator<Area> itrArea = getArea().iterator();
		Component[] animals = null;
		Area checkArea = null;
		while(itrArea.hasNext()) {
			checkArea = itrArea.next();
			
			if(checkArea.getComponents().length == 0) // 한 개도 안 들어가 있다면 다음 공간으로
				continue;
			
			animals = checkArea.getComponents();
			for(Component animal : animals) {
				
				if( !((AnimalLabel)animal).getName().equals( ((Area)animal.getParent()).getName() ) ) { // 들어갈 영역이 아닌 경우
					return false;
				}
			}
		}
		
		return true; // 검사를 해서 false가 안 뜨면 true다.
	}

	@Override
	public LinkedList<SelectableImage> getImages() {
		// TODO Auto-generated method stub
		LinkedList<SelectableImage> animalObjects = new LinkedList<SelectableImage>();
		
//		int baseX = 100, baseY = 100;
		
		for(int x = 1; x <= 3; x++) {
			for(int y = 1; y <= 3; y++) {
				animalObjects.add(imageObjects.get( (int)(Math.random() * 6) ));
//				animalObjects.getLast().setOriginalLocation(new Point(baseX, baseY));
//				baseX += 50;
			}	
//			baseX = 100;
//			baseY += 100;
		}
		
		return animalObjects;
	}

	@Override
	public LinkedList<Area> getArea() {
		// TODO Auto-generated method stub		
		return zooArea;
	}

}
