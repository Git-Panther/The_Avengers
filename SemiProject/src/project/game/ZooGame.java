package project.game;

import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JLabel;

import project.frame.GameFrame;
import project.vo.AnimalArea;
import project.vo.AnimalImage;

public class ZooGame extends Game {

	private LinkedList<AnimalArea> zooArea = new LinkedList<AnimalArea>(); // 동물원 우리
	private LinkedList<AnimalImage> animalImages; // 랜덤으로 할당된 동물들
	private LinkedList<AnimalLabel> animalLabels; // 랜덤으로 할당된 동물들을 감쌀 레이블
	private AnimalArea startingArea; // 랜덤으로 할당된 동물의 레이블이 들어갈 공간

	private AnimalLabel movingLabel; // 움직이는 레이블(또는 이미지)	
	private AnimalArea movingLabelParent; // 움직이는 레이블의 원래 부모
	
	private boolean isDragged; // 마우스 눌림 여부
	private int mouseX, mouseY; // 마우스의 x, y 좌표
	
	public ZooGame() {		
		bgLocation = "resource/image/bg/zoo.jpg";
		quiz = "동물들을 우리로 돌려보내세요.";
		// 문제의 동물들
		imageObjects.add(new AnimalImage("Dog", "resource/image/character/dog.png"));
		imageObjects.add(new AnimalImage("Giraffe", "resource/image/character/giraffe.png"));
		imageObjects.add(new AnimalImage("Hare", "resource/image/character/hare.png"));
		imageObjects.add(new AnimalImage("Lion", "resource/image/character/lion.png"));
		imageObjects.add(new AnimalImage("Monkey", "resource/image/character/monkey.png"));
		imageObjects.add(new AnimalImage("Owl", "resource/image/character/owl.png"));
		
		// 정답 공간
		zooArea.add(new AnimalArea("Dog", new Point(50, 50)));
		zooArea.add(new AnimalArea("Giraffe", new Point(475, 50)));
		zooArea.add(new AnimalArea("Hare", new Point(900, 50)));
		zooArea.add(new AnimalArea("Lion", new Point(50, 325)));
		zooArea.add(new AnimalArea("Monkey", new Point(900, 325)));
		zooArea.add(new AnimalArea("Owl", new Point(50, 600)));
		
		movingLabel = null; // 초기화
		
		// 문제의 동물들 배치공간
		startingArea = new AnimalArea();
		startingArea.setBounds(475, 325, AnimalArea.SIZE_HORIZONAL, AnimalArea.SIZE_VERTICAL);
	}
	
	public LinkedList<AnimalArea> getZooArea() {
		return zooArea;
	}

	public void setZooArea(LinkedList<AnimalArea> zooArea) {
		this.zooArea = zooArea;
	}

	public LinkedList<AnimalImage> getAnimalObjects() {
		return animalImages;
	}

	public void setAnimalObjects(LinkedList<AnimalImage> animalObjects) {
		this.animalImages = animalObjects;
	}
	
	class AnimalLabel extends JLabel{ // 동물들을 담는 레이블
		/**
		 * 
		 */
		private static final long serialVersionUID = 6390517800549703903L;

		private String name; // 동물 이름
		
		public AnimalLabel(AnimalImage image) {
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
		
		animalImages = getImages();
		animalLabels = new LinkedList<AnimalLabel>(); // 동물들 레이블. 실제로 이걸 옮긴다.
		Iterator<AnimalImage> itrObjects = animalImages.iterator(); 
		while (itrObjects.hasNext()) {
			animalLabels.add(new AnimalLabel(itrObjects.next()));
			startingArea.add(animalLabels.getLast());
		}
		// 동물들을 모아놓는다.
		
		class AreaListener implements MouseListener, MouseMotionListener { // Area에 부여할 리스너

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				// 마우스를 뗐을 때 이미지가 패널에 접근(e)했다면 넣고, 아니면 원위치로.
				if(movingLabel != null) {
					Iterator<AnimalArea> itrArea = zooArea.iterator();
					AnimalArea targetArea = null;
//					if(e.getSource() instanceof AnimalImage) {
//						System.out.println("동물임");
//					}
//					else if(e.getSource() instanceof AnimalArea) {
//						System.out.println("동물공간");
//					}  else if(e.getSource() instanceof AnimalLabel) {
//						System.out.println("동물 레이블");
//					}
//					else {
//						System.out.println("형상변환 불가");
//					}
					
					while(itrArea.hasNext()) {
						targetArea= itrArea.next();
						if( e.getSource().equals(targetArea) ) {
							movingLabel.getParent().remove(movingLabel);
							targetArea.add(movingLabel);
							movingLabel.repaint();
							movingLabelParent.repaint();
							isDragged = false;
							movingLabel = null;
							movingLabelParent = null; 
							System.out.println("옮기기 성공");
							return;
							// 옮기는 데 성공		
						} else {
							System.out.println("다음!");
						}
					}
					movingLabel.getParent().remove(movingLabel);
					movingLabelParent.add(movingLabel);
					movingLabel.repaint();
					movingLabelParent.repaint(); // 이동하면 보여줘야 하니
					isDragged = false;
					movingLabel = null;
					movingLabelParent = null; 
					// 원위치로 그리고 초기화
				} else
					System.out.println("옮기기 실패");
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				// 마우스를 눌렀을 때의 좌표 저장
				Iterator<AnimalLabel> itrLabel = animalLabels.iterator();
				AnimalLabel targetLabel = null;
				while(itrLabel.hasNext()) {
					targetLabel = itrLabel.next();
					if(e.getSource() instanceof AnimalImage) {
					System.out.println("동물임");
				}
				else if(e.getSource() instanceof AnimalArea) {
					System.out.println("동물공간");
				}  else if(e.getSource() instanceof AnimalLabel) {
					System.out.println("동물 레이블");
				}
				else {
					System.out.println("형상변환 불가");
				}
					
					if(e.getSource().equals(targetLabel)) {
						movingLabel = targetLabel;
						movingLabelParent = (AnimalArea) targetLabel.getParent();
						mouseX = e.getX() - targetLabel.getX();
						mouseY = e.getY() - targetLabel.getY();
						//눌렀을 때 상대 좌표를 구한다. 현재 마우스 스크린 좌표에서 사각형 위치 좌표의 차이를 구함
						isDragged = true; // 드래그 시작
						movingLabel.getParent().remove(movingLabel);
						frame.add(movingLabel);	
						frame.repaint();
						movingLabel.repaint();
						movingLabelParent.repaint(); // 없어졌으니 반영
						System.out.println("인식 성공");
						return; // 이미 찾았으므로 빠져나온다
					}
				}
				
				System.out.println("인식 실패");
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
			
			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				// 마우스가 움직일 때
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				// 마우스를 드래그하고 있을 때 그린다. 정확히는, 
				if(isDragged){
					movingLabel.setLocation(e.getX() - mouseX, e.getY() - mouseY);
//					System.out.println("움직이는중");
//					movingLabel.setBounds(e.getX() - mouseX, e.getY() - mouseY, movingLabel.getWidth(), movingLabel.getHeight());
				}
				frame.repaint();
				movingLabel.repaint();
			}
			
		}
		
		startingArea.addMouseListener(new AreaListener());
		startingArea.addMouseMotionListener(new AreaListener());
		
		LinkedList<AnimalArea> questionArea = getArea();
		Iterator<AnimalArea> itrArea = questionArea.iterator();
		AnimalArea targetArea = null;
		while (itrArea.hasNext()) {
			targetArea =itrArea.next();
			targetArea.addMouseListener(new AreaListener());
			targetArea.addMouseMotionListener(new AreaListener());
			frame.getContentPane().add(targetArea);
		}
		// System.out.println("문제 공간 확보 완료");
		frame.getContentPane().add(startingArea);
		// System.out.println("게임 패널 넣기 완료");		
		
		// 가정 1 : 패널도 레이블만큼 만든다(...) -> 패널은 9개까지이므로 9 * 6 = 54 -> 거기서 기본 틀 + 9 => 63
		// (문제는 너무 많아져서 리스트, 이터레이터 써야 하고 좌표는 그대로 0, 0일 것이라는 거다.)
		// 가정 2 : 마우스의 좌표가 아닌, 객체 안에 들어왔는지로 검사하여 옮기게 한다
		// (문제는 코드가 꼬이너가 널 참조가 빈번히 발생할 확률 존재)
		// 가정 3 : 패널이 아닌 사각형 범위를 잡는다. 1 ~ 9번째가 들어갈 수 있는 범위를 Area가 정해준다.
		// (문제는 들어갈 이미지에다가 답을 설정하는 메소드 만들어줘야 한다는 점. 거기다가 범위도 일일히 정해야 하는 노가다. 아무튼 노가다가 제일 많음.)
		// (그래도 이게 가장 쉬울 것이다. 오류도 덜 날 것이고. 비주얼도 볼 만할 것이고.)
		// 2 -> 3 -> 1 순으로 하자. 그래도 안 되면 클릭 방식으로 변경.
		// 결과 2 : e.getSource()는 동물공간만 인식하므로 레이블에 이벤트 넣어주고 그래도 안된다면 3번 ㄱㄱ => 레이블은 조상 관련 이벤트라 ㅈㅈ
		// 3번 : 패널을 제거하고 대신에 패널을 사각형 틀로 교체. 하지만 사각형 클래스에 1~9 좌표 정해줘야 한다. 즉, 거기서도 리스트 만들어야 해(...)
		// 좌표를 정하므로 이미지가 들어갈 이전 좌표와 다음 좌표 설정하는 알고리즘도 세워야 한다.
		// 결과 3 : 
	}

	@Override
	public void retry(GameFrame frame) { // 실패했을 때 재시도
		// TODO Auto-generated method stub

	}

	@Override
	public boolean check(GameFrame frame) { // 동물원 게임은 이름이 일치하지 않는 것이 발견되면 false다.
		// TODO Auto-generated method stub
		// frame에서 들어간 Area들에 들어간 것들이 Area 정답명과 일치하는지 검사
		
		if(startingArea.getComponents().length != 0) // 다 안 넣었으므로 틀렸음
			return false;
		
		Iterator<AnimalArea> itrArea = getArea().iterator();
		Component[] animals = null;
		AnimalArea checkArea = null;
		while(itrArea.hasNext()) {
			checkArea = itrArea.next();
			
			if(checkArea.getComponents().length == 0) // 한 개도 안 들어가 있다면 다음 공간으로
				continue;
			
			animals = checkArea.getComponents();
			for(Component animal : animals) {
				
				if( !((AnimalLabel)animal).getName().equals( ((AnimalArea)animal.getParent()).getName() ) ) { // 들어갈 영역이 아닌 경우
					return false;
				}
			}
		}
		
		return true; // 검사를 해서 false가 안 뜨면 true다.
	}

	@Override
	public LinkedList<AnimalImage> getImages() {
		// TODO Auto-generated method stub
		LinkedList<AnimalImage> animalObjects = new LinkedList<AnimalImage>();
		
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
	public LinkedList<AnimalArea> getArea() {
		// TODO Auto-generated method stub		
		return zooArea;
	}

}
